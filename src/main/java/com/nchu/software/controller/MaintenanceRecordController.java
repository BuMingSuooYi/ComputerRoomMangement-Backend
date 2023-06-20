//package com.nchu.software.controller;
//
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.nchu.software.VO.ComputerVo;
//import com.nchu.software.common.Result;
//import com.nchu.software.entity.ClazzPeriod;
//import com.nchu.software.entity.Computer;
//import com.nchu.software.entity.MachineRoom;
//import com.nchu.software.service.ComputerService;
//import com.nchu.software.service.MaintenanceRecordService;
//import org.springframework.beans.BeanUtils;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/maintenanceRecord")
//public class MaintenanceRecordController {
//    private final MaintenanceRecordService maintenanceRecordService;
//    private final ComputerService computerService;
//
//    public MaintenanceRecordController(MaintenanceRecordService maintenanceRecordService, ComputerService computerService) {
//        this.maintenanceRecordService = maintenanceRecordService;
//        this.computerService = computerService;
//    }
//
//    /**
//     * 分页查询班级学时
//     * @param page
//     * @param pageSize
//     * @param clazz
//     * @return
//     */
//    @GetMapping("/page")
//    public Result<Page<ComputerVo>> getPage(@RequestParam String number,
//                                            @RequestParam String machineRoomName,
//                                            @RequestParam Integer state,
//                                            @RequestParam Integer pageNum,
//                                            @RequestParam Integer pageSize) {
//        LambdaQueryWrapper<Computer> lambdaQueryWrapperComputer = new LambdaQueryWrapper();
//        LambdaQueryWrapper<MachineRoom> lambdaQueryWrapperMachineRoom = new LambdaQueryWrapper();
//
//        //条件
//        //电脑名称模糊查询
//        lambdaQueryWrapperComputer.like(number != null, Computer::getNumber, number);
//        //所属机房名称模糊查询
//        lambdaQueryWrapperMachineRoom.like(machineRoomName != null, MachineRoom::getName, machineRoomName);
//        List<MachineRoom> machineRoomList = machineRoomService.list(lambdaQueryWrapperMachineRoom);
//        for (MachineRoom machineRoom : machineRoomList) {
//            lambdaQueryWrapperComputer.in(Computer::getMachineRoom, machineRoom.getId());
//
//        }
//        //状态查询
//        lambdaQueryWrapperComputer.eq(state != -1,Computer::getState, state);
//        // 分页
//        Page<Computer> page = new Page<>(pageNum, pageSize);
//        computerService.page(page, lambdaQueryWrapperComputer);
//
//        Page<ComputerVo> voPage = new Page<>();
//        // 对象拷贝
//        BeanUtils.copyProperties(page, voPage);
//        List<Computer> computerList = page.getRecords();
//        //给新字段赋值
//        List<ComputerVo> computerVoList = computerList.stream().map((item) -> {
//            ComputerVo computerVo = new ComputerVo();
//            BeanUtils.copyProperties(item, computerVo);
//            // 设置获取列表
//            Long machineRoomId = item.getMachineRoom();
//            MachineRoom machineRoom = machineRoomService.getById(machineRoomId);
//            computerVo.setMachineRoomName(machineRoom.getName());
//            return computerVo;
//        }).collect(Collectors.toList());
//        voPage.setRecords(computerVoList);
//        return Result.success(voPage, "查询成功");
//    }
//
//    /**
//     * 获取全部班级学时
//     * @return
//     */
//    @GetMapping
//    public Result<List<ClazzPeriod>> getToComputer(Long id) {
//        Computer computer=computerService.getById(id);
//        LambdaQueryWrapper
//        List<ClazzPeriod> clazzPeriodList=clazzPeriodService.list(new LambdaQueryWrapper<>());
//        return Result.success(clazzPeriodList, "查询成功");
//    }
//
//    /**
//     * 新增班级学时
//     *
//     * @param clazzPeriod
//     * @return
//     */
//    @PostMapping
//    public Result<ClazzPeriod> newClazzPeriod(@RequestBody ClazzPeriod clazzPeriod) {
//        clazzPeriodService.save(clazzPeriod);
//        return Result.success(clazzPeriod, "新增成功");
//    }
//
//
//    /**
//     * 删除班级学时
//     *
//     * @param id
//     * @return
//     */
//    @DeleteMapping
//    public Result<String> deleteClazzPeriod(@RequestParam Long id) {
//        clazzPeriodService.removeById(id);
//        return Result.success("删除成功");
//    }
//
//    /**
//     * 更新班级学时
//     *
//     * @param clazzPeriod
//     * @return boolean
//     */
//    @PutMapping
//    public Result<ClazzPeriod> updateClazzPeriod(@RequestBody ClazzPeriod clazzPeriod) {
//        clazzPeriodService.updateById(clazzPeriod);
//        return Result.success(clazzPeriod, "更新成功");
//    }
//}
