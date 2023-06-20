package com.nchu.software.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nchu.software.common.Result;
import com.nchu.software.entity.LoginLog;
import com.nchu.software.entity.MachineRoom;
import com.nchu.software.service.LoginLogService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logount")
public class LoginLogController {
    private  final LoginLogService loginLogService;

    public LoginLogController(LoginLogService loginLogService) {
        this.loginLogService = loginLogService;
    }

    /**
     * 条件查询登录日志
     * @param page
     * @param pageSize
     * @param username
     * @return
     */
    @GetMapping("/page")
    public Result<Page<LoginLog>> getPage(@RequestParam Integer page,
                                          @RequestParam Integer pageSize,
                                          @RequestParam String username

    ) {
        LambdaQueryWrapper<LoginLog> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //条件
        lambdaQueryWrapper.like(username != null, LoginLog::getUsername, username);
        //分页
        Page<LoginLog> page1 = new Page<>(page, pageSize);
        loginLogService.page(page1, lambdaQueryWrapper);
        return Result.success(page1, "查询成功");
    }

    /**
     * 删除登录日志
     *
     * @param id
     * @return
     */
    @DeleteMapping
    public Result<String> deleteLoginLog(@RequestParam Long id) {
        if (loginLogService.removeById(id))
            return Result.success("删除成功");
        return Result.success("删除失败");
    }

}
