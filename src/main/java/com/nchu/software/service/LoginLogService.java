package com.nchu.software.service;

import com.nchu.software.entity.LoginLog;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author Lai
* @description 针对表【login_log】的数据库操作Service
* @createDate 2023-06-20 21:22:18
*/
public interface LoginLogService extends IService<LoginLog> {
    public void addLoginLog(String userName,String loginId, int state, String info);

    public String getIp(HttpServletRequest request);
}
