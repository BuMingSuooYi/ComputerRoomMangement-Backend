package com.nchu.software.service;

import com.nchu.software.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Lai
* @description 针对表【account(账户表)】的数据库操作Service
* @createDate 2023-06-18 14:49:22
*/
public interface AccountService extends IService<Account> {
    public Account SaveFromParameters(String username,String password,int type);

    public Account SaveStudent(String username) ;
}
