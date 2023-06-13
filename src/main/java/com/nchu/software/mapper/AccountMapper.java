package com.nchu.software.mapper;

import com.nchu.software.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 15696
* @description 针对表【account(账户表)】的数据库操作Mapper
* @createDate 2023-06-13 17:03:31
* @Entity generator.entity.Account
*/
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

}




