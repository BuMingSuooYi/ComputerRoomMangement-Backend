package com.nchu.software.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nchu.software.entity.LoginLog;
import com.nchu.software.service.LoginLogService;
import com.nchu.software.mapper.LoginLogMapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
* @author Lai
* @description 针对表【login_log】的数据库操作Service实现
* @createDate 2023-06-20 21:22:18
*/
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog>
    implements LoginLogService{
    @Override
    public void addLoginLog(String userName,String loginId, int status, String info) {
        LoginLog loginLog=new LoginLog();
        loginLog.setUsername(userName);
        loginLog.setLoginIp(loginId);
        //登录时间
        loginLog.setLoginTime(LocalDateTime.now());
        loginLog.setStatus(status);
        loginLog.setInfo(info);
        this.save(loginLog);
    }

    public String getIp(HttpServletRequest request) {
        List<String> ipHeadList = Stream.of("X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "X-Real-IP").collect(Collectors.toList());
        for (String ipHead : ipHeadList) {
            if (checkIP(request.getHeader(ipHead))) {
                return request.getHeader(ipHead).split(",")[0];
            }
        }
        return "0:0:0:0:0:0:0:1".equals(request.getRemoteAddr()) ? "127.0.0.1" : request.getRemoteAddr();

    }

    /**
     * 检查ip存在
     */
    private boolean checkIP(String ip) {
        return !(null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip));
    }
}




