package com.nchu.software.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nchu.software.entity.Account;
import com.nchu.software.mapper.AccountMapper;
import com.nchu.software.service.AccountService;
import org.springframework.stereotype.Service;

/**
 * @author 15696
 * @description 针对表【account(账户表)】的数据库操作Service实现
 * @createDate 2023-06-13 17:03:31
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account>
        implements AccountService {

}




