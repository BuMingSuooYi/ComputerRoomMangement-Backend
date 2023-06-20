package com.nchu.software.mapper;

import com.nchu.software.entity.LoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Lai
* @description 针对表【login_log】的数据库操作Mapper
* @createDate 2023-06-20 21:22:18
* @Entity com.nchu.software.entity.LoginLog
*/
@Mapper
public interface LoginLogMapper extends BaseMapper<LoginLog> {

}




