package com.nchu.software.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nchu.software.common.Result;
import com.nchu.software.entity.ClazzPeriod;
import com.nchu.software.entity.ComputerConfiguration;
import com.nchu.software.entity.MachineRoom;
import com.nchu.software.service.ComputerConfigurationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/computerConfiguration")
public class ComputerConfigurationController {
    private final ComputerConfigurationService computerConfigurationService;

    public ComputerConfigurationController(ComputerConfigurationService computerConfigurationService) {
        this.computerConfigurationService = computerConfigurationService;
    }

    /**
     * 多条件分页查询电脑配置
     * @param page
     * @param pageSize
     * @param hardware
     * @param software
     * @return
     */
    @GetMapping("/page")
    public Result<Page<ComputerConfiguration>> getPage(@RequestParam Integer page,
                                                       @RequestParam Integer pageSize,
                                                       @RequestParam String hardware,
                                                       @RequestParam String software
                                             ) {
        LambdaQueryWrapper<ComputerConfiguration> lambdaQueryWrapper = new LambdaQueryWrapper();
        //条件
        lambdaQueryWrapper.like(hardware != null, ComputerConfiguration::getHardware, hardware)
                        .like(software!=null,ComputerConfiguration::getSoftware,software);
        // 分页
        Page<ComputerConfiguration> page1 = new Page<>(page, pageSize);
        computerConfigurationService.page(page1,lambdaQueryWrapper);
        return Result.success(page1, "查询成功");
    }

    /**
     * 获取全部电脑配置
     * @return
     */
    @GetMapping
    public Result<List<ComputerConfiguration>> getAll() {

        List<ComputerConfiguration> computerConfigurationList=computerConfigurationService.list(new LambdaQueryWrapper<>());
        return Result.success(computerConfigurationList, "查询成功");
    }


    /**
     * 新增电脑配置
     *
     * @param computerConfiguration
     * @return
     */
    @PostMapping
    public Result<ComputerConfiguration> newComputerConfiguration(@RequestBody ComputerConfiguration computerConfiguration) {
        computerConfigurationService.save(computerConfiguration);
        return Result.success(computerConfiguration, "新增成功");
    }



    /**
     * 删除电脑配置
     *
     * @param id
     * @return
     */
    @DeleteMapping
    public Result<String> deleteComputerConfiguration(@RequestParam List<Long> id) {
        computerConfigurationService.removeByIds(id);
        return Result.success("删除成功");
    }

    /**
     * 更新电脑配置
     *
     * @param computerConfiguration
     * @return boolean
     */
    @PutMapping
    public Result<ComputerConfiguration> updateClazzPeriod(@RequestBody ComputerConfiguration computerConfiguration) {
        computerConfigurationService.updateById(computerConfiguration);
        return Result.success(computerConfiguration, "更新成功");
    }
}
