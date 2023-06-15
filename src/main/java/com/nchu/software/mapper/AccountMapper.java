package com.nchu.software.mapper;

import com.nchu.software.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Lai
* @description 针对表【account(账户表)】的数据库操作Mapper
* @createDate 2023-06-15 14:45:15
* @Entity com.nchu.software.entity.Account
*/
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

}




