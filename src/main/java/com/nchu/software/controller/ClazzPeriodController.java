package com.nchu.software.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nchu.software.common.Result;
import com.nchu.software.entity.Account;
import com.nchu.software.entity.ClazzPeriod;
import com.nchu.software.entity.MachineRoom;
import com.nchu.software.service.ClazzPeriodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/clazzPeriod")
public class ClazzPeriodController {
    private final ClazzPeriodService clazzPeriodService;

    public ClazzPeriodController(ClazzPeriodService clazzPeriodService) {
        this.clazzPeriodService = clazzPeriodService;
    }

    /**
     * 分页查询班级学时
     * @param page
     * @param pageSize
     * @param clazz
     * @return
     */
    @GetMapping("/page")
    public Result<Page<ClazzPeriod>> getPage(@RequestParam Integer page,
                                                 @RequestParam Integer pageSize,
                                                 @RequestParam String clazz
    ) {
        LambdaQueryWrapper<ClazzPeriod> lambdaQueryWrapper = new LambdaQueryWrapper();
        //条件
        lambdaQueryWrapper.like(clazz != null, ClazzPeriod::getClazz, clazz);
        // 分页
        Page<ClazzPeriod> page1 = new Page<>(page, pageSize);
        clazzPeriodService.page(page1,lambdaQueryWrapper);
        return Result.success(page1, "查询成功");
    }

    /**
     * 获取全部班级学时
     * @return
     */
    @GetMapping
    public Result<List<ClazzPeriod>> getAll() {
        List<ClazzPeriod> clazzPeriodList=clazzPeriodService.list(new LambdaQueryWrapper<>());
        return Result.success(clazzPeriodList, "查询成功");
    }

    /**
     * 新增班级学时
     *
     * @param clazzPeriod
     * @return
     */
    @PostMapping
    public Result<ClazzPeriod> newClazzPeriod(@RequestBody ClazzPeriod clazzPeriod) {
        clazzPeriodService.save(clazzPeriod);
        return Result.success(clazzPeriod, "新增成功");
    }


    /**
     * 删除班级学时
     *
     * @param id
     * @return
     */
    @DeleteMapping
    public Result<String> deleteClazzPeriod(@RequestParam Long id) {
        clazzPeriodService.removeById(id);
        return Result.success("删除成功");
    }

    /**
     * 更新班级学时
     *
     * @param clazzPeriod
     * @return boolean
     */
    @PutMapping
    public Result<ClazzPeriod> updateClazzPeriod(@RequestBody ClazzPeriod clazzPeriod) {
        clazzPeriodService.updateById(clazzPeriod);
        return Result.success(clazzPeriod, "更新成功");
    }
}
