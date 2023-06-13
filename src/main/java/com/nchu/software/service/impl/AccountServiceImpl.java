package com.nchu.software.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nchu.software.entity.Account;
import com.nchu.software.service.AccountService;
import com.nchu.software.mapper.AccountMapper;
import org.springframework.stereotype.Service;

/**
* @author Lai
* @description 针对表【account(账户表)】的数据库操作Service实现
* @createDate 2023-06-13 13:32:37
*/
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account>
    implements AccountService{

}




