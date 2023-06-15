package com.nchu.software.account;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nchu.software.common.Result;
import com.nchu.software.entity.Account;
import com.nchu.software.entity.ComputerRecord;
import com.nchu.software.entity.Student;
import com.nchu.software.service.AccountService;
import com.nchu.software.service.ComputerRecordService;
import com.nchu.software.service.StudentService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;

@RunWith(SpringRunner.class)
@SpringBootTest//测试注解
public class PostTest {
    @Autowired
    AccountService accountService;
    @Autowired
    StudentService studentService;
    @Autowired
    ComputerRecordService computerRecordService;

    @Test
    public void getAccount() {
        LambdaQueryWrapper<Account> lambdaQueryWrapper=new LambdaQueryWrapper();
        String username="-1";
        int type=-1;
        int is_disabled=-1;
        int pageNum=1;
        int pageSize=10;

        //条件
        if (!username.equals("-1"))
            lambdaQueryWrapper.eq(Account::getUsername,username);
        if (type!=-1)
            lambdaQueryWrapper.eq(Account::getType,type);
        if (is_disabled!=-1)
            lambdaQueryWrapper.eq(Account::getIsDisabled,is_disabled);
        // 分页
        Page<Account> page = new Page<>(pageNum, pageSize);
        IPage<Account> accountPage = accountService.page(page, lambdaQueryWrapper);
        System.out.print(accountPage);
    }

    @Test
    public void add(){
        Account account= new Account();
        account.setUsername("lai3");
        account.setPassword("123456");
        account.setType(2);
        account.setIsDisabled(0);

        accountService.save(account);
    }



//    public void deleteAccount() {
//        Long id=1669235956856725505;
//        int type=2;
//
//        if (type==2){
//            //查询学生
//            LambdaQueryWrapper<Student> lambdaQueryWrapper1=new LambdaQueryWrapper();
//            lambdaQueryWrapper1.eq(Student::getAccount,id);
//            Student student=studentService.getOne(lambdaQueryWrapper1);
//
//            //通过学生，删除该学生上机记录
//            LambdaQueryWrapper<ComputerRecord> lambdaQueryWrapper2=new LambdaQueryWrapper();
//            lambdaQueryWrapper2.eq(ComputerRecord::getStudent,student.getId());
//            computerRecordService.remove(lambdaQueryWrapper2);
//
//            //删除学生
//            studentService.remove(lambdaQueryWrapper1);
//        }
//
//        //删除账户
//        accountService.removeById(id);
//        System.out.print("删除成功");
//    }


//    @Test
//    public void updateAccountTest() {
//        Account account=new Account();
//        LambdaQueryWrapper<Account> lambdaQueryWrapper=new LambdaQueryWrapper();
//        lambdaQueryWrapper.eq(Account::getUsername,account.getUsername());
//        accountService.update(account,lambdaQueryWrapper);
//        System.out.print("更新成功");
//    }

}
