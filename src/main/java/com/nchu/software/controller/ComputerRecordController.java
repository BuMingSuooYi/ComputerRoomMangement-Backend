package com.nchu.software.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nchu.software.VO.ComputerRecordVo;
import com.nchu.software.VO.MaintenanceRecordVo;
import com.nchu.software.common.Result;
import com.nchu.software.entity.Computer;
import com.nchu.software.entity.ComputerRecord;
import com.nchu.software.entity.MaintenanceRecord;
import com.nchu.software.entity.Student;
import com.nchu.software.service.ComputerRecordService;
import com.nchu.software.service.ComputerService;
import com.nchu.software.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/computerRecord")
public class ComputerRecordController {
    private final ComputerRecordService computerRecordService;
    private final StudentService studentService;
    private final ComputerService computerService;

    public ComputerRecordController(ComputerRecordService computerRecordService, StudentService studentService, ComputerService computerService) {
        this.computerRecordService = computerRecordService;
        this.studentService = studentService;
        this.computerService = computerService;
    }

    /**
     * 分页多条件查询上机记录
     * @param page
     * @param pageSize
     * @param student
     * @param machineRoom
     * @param computer
     * @return
     */
    @GetMapping("/page")
    public Result<Page<ComputerRecordVo>> getPage(@RequestParam Integer page,
                                                  @RequestParam Integer pageSize,
                                                  @RequestParam String student,
                                                  @RequestParam long machineRoom,
                                                  @RequestParam String computer
    ) {
        LambdaQueryWrapper<ComputerRecord> lambdaQueryWrapperComputerRecord = new LambdaQueryWrapper();
        LambdaQueryWrapper<Student> lambdaQueryWrapperStudent=new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Computer> lambdaQueryWrapperComputer=new LambdaQueryWrapper<>();

        //条件
        //学生名字模糊搜索
        lambdaQueryWrapperStudent.like(student != null, Student::getName, student);
        List<Student> studentList=studentService.list(lambdaQueryWrapperStudent);
//        for (Student student1:studentList) {
//            lambdaQueryWrapperComputerRecord.in(ComputerRecord::getStudent,student1.getId());
//        }
        List<Long> studentIds = studentList.stream()
                .map(Student::getId)
                .collect(Collectors.toList());

        lambdaQueryWrapperComputerRecord.in(ComputerRecord::getStudent, studentIds);
        //机房id查询、电脑编号
        lambdaQueryWrapperComputer.eq(machineRoom!=-1,Computer::getMachineRoom, machineRoom);
        lambdaQueryWrapperComputer.like(computer!=null,Computer::getNumber, computer);
        List<Computer> computerList=computerService.list(lambdaQueryWrapperComputer);
//        for (Computer computer1:computerList) {
//            lambdaQueryWrapperComputerRecord.in(ComputerRecord::getComputer,computer1.getId());
//        }
        List<Long> computerIds = computerList.stream()
                .map(Computer::getId)
                .collect(Collectors.toList());

        lambdaQueryWrapperComputerRecord.in(ComputerRecord::getComputer, computerIds);
        // 分页
        Page<ComputerRecord> page1 = new Page<>(page, pageSize);
        computerRecordService.page(page1, lambdaQueryWrapperComputerRecord);

        Page<ComputerRecordVo> voPage = new Page<>();
        // 对象拷贝
        BeanUtils.copyProperties(page1, voPage,"records");
        List<ComputerRecord> computerRecordList = page1.getRecords();
        //给新字段赋值
        List<ComputerRecordVo> computerRecordVoList = computerRecordList.stream().map((item) -> {
            ComputerRecordVo computerRecordVo = new ComputerRecordVo();
            BeanUtils.copyProperties(item, computerRecordVo);
            // 设置学生
            Student student2 = studentService.getById(item.getStudent());
            computerRecordVo.setStudentObject(student2);
            //设置电脑
            Computer computer2 = computerService.getById(item.getComputer());
            computerRecordVo.setComputerObject(computer2);
            return computerRecordVo;
        }).collect(Collectors.toList());
        voPage.setRecords(computerRecordVoList);
        return Result.success(voPage, "查询成功");
    }


    /**
     * 新增上机记录
     * @param computerRecord
     * @return
     */
    @PostMapping
    public Result<ComputerRecord> newComputerRecord(@RequestBody ComputerRecord computerRecord) {
        if (computerRecordService.save(computerRecord))
            return Result.success(computerRecord, "新增成功");
        return Result.success(computerRecord, "新增失败");
    }


    /**
     * 删除上机记录
     *
     * @param id
     * @return
     */
    @DeleteMapping
    public Result<String> deleteComputerRecord(@RequestParam List<Long> id) {
        if (computerRecordService.removeByIds(id))
            return Result.success("删除成功");
        return Result.success("删除失败");
    }

    /**
     * 更新上机记录
     *
     * @param computerRecord
     * @return boolean
     */
    @PutMapping
    public Result<ComputerRecord> updateComputerRecord(@RequestBody ComputerRecord computerRecord) {
        computerRecordService.updateById(computerRecord);
        return Result.success(computerRecord, "更新成功");
    }
}

