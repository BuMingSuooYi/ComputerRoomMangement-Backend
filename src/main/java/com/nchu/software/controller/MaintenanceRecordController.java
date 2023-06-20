package com.nchu.software.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nchu.software.VO.MaintenanceRecordVo;
import com.nchu.software.common.Result;
import com.nchu.software.entity.Computer;
import com.nchu.software.entity.MaintenanceRecord;
import com.nchu.software.service.ComputerService;
import com.nchu.software.service.MaintenanceRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/maintenanceRecord")
public class MaintenanceRecordController {
    private final MaintenanceRecordService maintenanceRecordService;
    private final ComputerService computerService;

    public MaintenanceRecordController(MaintenanceRecordService maintenanceRecordService, ComputerService computerService) {
        this.maintenanceRecordService = maintenanceRecordService;
        this.computerService = computerService;
    }

    /**
     * 分页多条件查询维修记录
     *
     * @param page
     * @param pageSize
     * @param number
     * @param startTime
     * @return
     */
    @GetMapping("/page")
    public Result<Page<MaintenanceRecordVo>> getPage(@RequestParam Integer page,
                                                     @RequestParam Integer pageSize,
                                                     @RequestParam String number,
                                                     @RequestParam LocalDateTime startTime
    ) {
        LambdaQueryWrapper<MaintenanceRecord> lambdaQueryWrapperMaintenanceRecord = new LambdaQueryWrapper();
        LambdaQueryWrapper<Computer> lambdaQueryWrapperComputer = new LambdaQueryWrapper();

        //条件
        //电脑名称模糊查询
        lambdaQueryWrapperComputer.like(number != null, Computer::getNumber, number);
        List<Computer> computerList = computerService.list(lambdaQueryWrapperComputer);
        for (Computer computer : computerList) {
            lambdaQueryWrapperMaintenanceRecord.in(MaintenanceRecord::getComputer, computer.getId());
        }
        //开始日期查询
        lambdaQueryWrapperMaintenanceRecord.ge(startTime != null, MaintenanceRecord::getStartTime, startTime);
        // 分页
        Page<MaintenanceRecord> page1 = new Page<>(page, pageSize);
        maintenanceRecordService.page(page1, lambdaQueryWrapperMaintenanceRecord);

        Page<MaintenanceRecordVo> voPage = new Page<>();
        // 对象拷贝
        BeanUtils.copyProperties(page, voPage, "records");
        List<MaintenanceRecord> maintenanceRecordList = page1.getRecords();
        //给新字段赋值
        List<MaintenanceRecordVo> maintenanceRecordVoList = maintenanceRecordList.stream().map((item) -> {
            MaintenanceRecordVo maintenanceRecordVo = new MaintenanceRecordVo();
            BeanUtils.copyProperties(item, maintenanceRecordVo);
            // 设置获取列表
            Computer computer = computerService.getById(item.getComputer());
            maintenanceRecordVo.setComputerObject(computer);
            return maintenanceRecordVo;
        }).collect(Collectors.toList());
        voPage.setRecords(maintenanceRecordVoList);
        return Result.success(voPage, "查询成功");
    }


    /**
     * 新增维修记录
     *
     * @param maintenanceRecord
     * @return
     */
    @PostMapping
    public Result<MaintenanceRecord> newMaintenanceRecord(@RequestBody MaintenanceRecord maintenanceRecord) {
        if (maintenanceRecordService.save(maintenanceRecord))
            return Result.success(maintenanceRecord, "新增成功");
        return Result.success(maintenanceRecord, "新增失败");
    }


    /**
     * 删除维修记录
     *
     * @param id
     * @return
     */
    @DeleteMapping
    public Result<String> deleteMaintenanceRecord(@RequestParam List<Long> id) {
        if (maintenanceRecordService.removeByIds(id))
            return Result.success("删除成功");
        return Result.success("删除失败");
    }

    /**
     * 更新维修记录
     *
     * @param maintenanceRecord
     * @return boolean
     */
    @PutMapping
    public Result<MaintenanceRecord> updateMaintenanceRecord(@RequestBody MaintenanceRecord maintenanceRecord) {
        maintenanceRecordService.updateById(maintenanceRecord);
        return Result.success(maintenanceRecord, "更新成功");
    }
}
