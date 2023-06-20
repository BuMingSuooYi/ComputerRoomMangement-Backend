package com.nchu.software.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nchu.software.entity.LoginLog;
import com.nchu.software.service.LoginLogService;
import com.nchu.software.mapper.LoginLogMapper;
import org.springframework.stereotype.Service;

/**
* @author Lai
* @description 针对表【login_log】的数据库操作Service实现
* @createDate 2023-06-20 21:22:18
*/
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog>
    implements LoginLogService{

}




