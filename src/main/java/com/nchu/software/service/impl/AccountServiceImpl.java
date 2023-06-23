package com.nchu.software.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nchu.software.common.RandomString;
import com.nchu.software.entity.Account;
import com.nchu.software.service.AccountService;
import com.nchu.software.mapper.AccountMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

/**
* @author Lai
* @description 针对表【account(账户表)】的数据库操作Service实现
* @createDate 2023-06-18 14:49:22
*/
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account>
    implements AccountService{

        /**
         * 根据参数保存账户
         * @param username
         * @param password
         * @param type
         * @return
         */
        public Account SaveFromParameters(String username,String password,int type){
            // 创建用户对象，并设置参数
            Account account= new Account();
            account.setUsername(username);
            //md5加密
            password = DigestUtils.md5Hex(password);
            account.setPassword(password);
            account.setType(type);
            //保存到数据库
            //保存到数据库
            if (this.save(account))
                return account;
            else
                return null;
        }

    /**
     * 根据学生学号创建账户
     * @param username
     * @return
     */
    @Override
        public Account SaveStudent(String username) {
            // 创建用户对象，并设置参数
            Account account= new Account();
            account.setUsername(username);
            //md5加密
            String password = DigestUtils.md5Hex("123456");
            account.setPassword(password);
            account.setType(2);
            //保存到数据库
            if (this.save(account))
                return account;
            else
                return null;
        }

}




