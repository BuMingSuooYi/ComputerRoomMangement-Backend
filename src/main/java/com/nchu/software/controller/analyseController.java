package com.nchu.software.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nchu.software.common.Result;
import com.nchu.software.entity.*;
import com.nchu.software.service.*;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/analyse")
public class analyseController {
    private final ComputerRecordService computerRecordService;
    private final MachineRoomService machineRoomService;
    private final ComputerService computerService;
    private final StudentService studentService;
    private final MaintenanceRecordService maintenanceRecordService;
    private final ClazzPeriodService clazzPeriodService;

    public analyseController(ComputerRecordService computerRecordService, MachineRoomService machineRoomService, ComputerService computerService, StudentService studentService, MaintenanceRecordService maintenanceRecordService, ClazzPeriodService clazzPeriodService) {
        this.computerRecordService = computerRecordService;
        this.machineRoomService = machineRoomService;
        this.computerService = computerService;
        this.studentService = studentService;
        this.maintenanceRecordService = maintenanceRecordService;
        this.clazzPeriodService = clazzPeriodService;
    }

    /**
     * 获取每个机房上机总学时
     * @return Result<Map<MachineRoom,Double>>
     */
    @GetMapping("/eachMachineRoom")
    public Result<Map<MachineRoom,Double>> periodByMachineRoom() {
        //遍历上机记录，记录每台电脑id上机学时
        LambdaQueryWrapper<ComputerRecord> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.isNotNull(ComputerRecord::getEndTime);
        List<ComputerRecord> computerRecordList=computerRecordService.list(lambdaQueryWrapper);
        Map<Long,Double> ComputerIdMap =new HashMap<>();
        for (ComputerRecord computerRecord:computerRecordList) {
            if (ComputerIdMap.containsKey(computerRecord.getComputer())){
                //存在，累加上机时间
                Double time=this.timeToHours(computerRecord.getStartTime(),computerRecord.getEndTime());
                Double oldTime=ComputerIdMap.get(computerRecord.getComputer());
                ComputerIdMap.replace(computerRecord.getComputer(),oldTime,oldTime+time);
            }else {
                //不存在，记录该电脑上机时间
                Double time=this.timeToHours(computerRecord.getStartTime(),computerRecord.getEndTime());
                ComputerIdMap.put(computerRecord.getComputer(),time);
            }
        }
        //所有电脑上机学时
        Map<MachineRoom,Double> machineRoomMap =new HashMap<>();
        for (Map.Entry<Long, Double> entry : ComputerIdMap.entrySet()) {
            Long computerId = entry.getKey();
            Double computerTime = entry.getValue();
            Computer computer=computerService.getById(computerId);

            MachineRoom machineRoom=machineRoomService.getById(computer.getMachineRoom());
            if (machineRoomMap.containsKey(machineRoom)) {
                //机房存在
                Double oldTime=machineRoomMap.get(machineRoom);
                machineRoomMap.replace(machineRoom,oldTime,oldTime+computerTime);
            }else {
                //机房不存在
                machineRoomMap.put(machineRoom,computerTime);
            }
        }
        return Result.success(machineRoomMap,"统计成功");
    }

    /**
     * 获取每个班级上机总学时
     * @return
     */
    @GetMapping("/eachClazz")
    public Result<Map<String,Double>> periodByClazz() {
        //遍历上机记录，记录每个学生上机学时
        LambdaQueryWrapper<ComputerRecord> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.isNotNull(ComputerRecord::getEndTime);
        List<ComputerRecord> computerRecordList=computerRecordService.list(lambdaQueryWrapper);
        Map<Long,Double> studentIdMap =new HashMap<>();
        for (ComputerRecord computerRecord:computerRecordList) {
            if (studentIdMap.containsKey(computerRecord.getStudent())){
                //存在，累加上机时间
                Double time=this.timeToHours(computerRecord.getStartTime(),computerRecord.getEndTime());
                Double oldTime=studentIdMap.get(computerRecord.getStudent());
                studentIdMap.replace(computerRecord.getStudent(),oldTime,oldTime+time);
            }else {
                //不存在，记录该电脑上机时间
                Double time=this.timeToHours(computerRecord.getStartTime(),computerRecord.getEndTime());
                studentIdMap.put(computerRecord.getStudent(),time);
            }
        }
        //遍历每个学生，统计班级上机学时
        Map<String,Double> clazzRoomMap =new HashMap<>();
        for (Map.Entry<Long, Double> entry : studentIdMap.entrySet()) {
            Long studentId = entry.getKey();
            Double studentTime = entry.getValue();
            Student student=studentService.getById(studentId);

            if (clazzRoomMap.containsKey(student.getClazz())) {
                //班级存在
                Double oldTime=clazzRoomMap.get(student.getClazz());
                clazzRoomMap.replace(student.getClazz(),oldTime,oldTime+studentTime);
            }else {
                //机房不存在
                clazzRoomMap.put(student.getClazz(),studentTime);
            }
        }
        return Result.success(clazzRoomMap,"统计成功");

    }

    /**
     * 获取维修次数最多的n台电脑
     * @return Result<Map<Computer,Integer>>
     */
    @GetMapping("/maximumMaintenance")
    public Result<Map<Computer,Integer>> maximumMaintenance(
    ) {
        //遍历维修记录，记录每台电脑维修次数
        List<MaintenanceRecord> maintenanceRecordList=maintenanceRecordService.list();
        Map<Long,Integer> computerIdMap =new HashMap<>();
        for (MaintenanceRecord maintenanceRecord:maintenanceRecordList) {
            if (computerIdMap.containsKey(maintenanceRecord.getComputer())){
                //存在，累加维修次数
                Integer num=computerIdMap.get(maintenanceRecord.getComputer());
                computerIdMap.replace(maintenanceRecord.getComputer(),num,num+1);
            }else {
                //不存在，记录该电脑维修次数
                computerIdMap.put(maintenanceRecord.getComputer(),1);
            }
        }
        // 获取维修次数最多的前 n 台电脑id
        int n = 5;
        // 创建一个列表，存储键值对
        List<Map.Entry<Long, Integer>> entryList = new ArrayList<>(computerIdMap.entrySet());

        // 对列表进行排序，按维修次数降序排列
        Collections.sort(entryList, new Comparator<Map.Entry<Long, Integer>>() {
            @Override
            public int compare(Map.Entry<Long, Integer> entry1, Map.Entry<Long, Integer> entry2) {
                return entry2.getValue().compareTo(entry1.getValue());
            }
        });

        // 获取前 n 个键值对，即维修次数最多的前 n 台电脑id
        List<Map.Entry<Long, Integer>> topNEntries = entryList.subList(0, Math.min(n, entryList.size()));

        //获取n 台电脑
        Map<Computer,Integer> computerMap=new HashMap<>();
        for (Map.Entry<Long, Integer> entry:topNEntries) {
            Computer computer=computerService.getById(entry.getKey());
            Integer num=entry.getValue();
            computerMap.put(computer,num);
        }
        return Result.success(computerMap,"统计成功");
    }

    /**
     * 获取自己和目标学时的差
     * @return Result<List<Double>>
     */
    @GetMapping("/period")
    public Result<List<Double>> period(@RequestParam Long student,
                               @RequestParam String clazz) {
        //按学生查询上机记录
        LambdaQueryWrapper<ComputerRecord> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ComputerRecord::getStudent,student);
        List<ComputerRecord> computerRecordList=computerRecordService.list(lambdaQueryWrapper);
        Double time=0.0;
        //统计学时
        for (ComputerRecord computerRecord:computerRecordList ) {
            time=time+this.timeToHours(computerRecord.getStartTime(),computerRecord.getEndTime());
        }
        //查询班级目标学时
        LambdaQueryWrapper<ClazzPeriod> clazzPeriodLambdaQueryWrapper=new LambdaQueryWrapper<>();
        clazzPeriodLambdaQueryWrapper.eq(ClazzPeriod::getClazz,clazz);
        ClazzPeriod clazzPeriod=clazzPeriodService.getOne(clazzPeriodLambdaQueryWrapper);

        List<Double> timeList=new ArrayList<>();
        timeList.add(time);
        timeList.add((double) clazzPeriod.getTime());
        return Result.success(timeList,"成功获得自己学时和目标学时");
    }

    /**
     * 输入两个时间，获得按小时输出的时间差浮点数
     * @param startTime
     * @param endTime
     * @return
     */
    private Double timeToHours(LocalDateTime startTime , LocalDateTime endTime){
        Duration duration = Duration.between(startTime, endTime);

        double hoursDifference = duration.toHours(); // 获取小时差
        double minutesDifference = duration.toMinutes() % 60 / 60.0; // 获取分钟差，并将其转换为小数

        double totalDifference = hoursDifference + minutesDifference; // 小时差 + 分钟差

        return totalDifference;
    }



}
