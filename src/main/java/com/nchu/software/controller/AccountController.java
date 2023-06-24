package com.nchu.software.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nchu.software.common.MyExcel;
import com.nchu.software.common.Result;
import com.nchu.software.entity.Account;
import com.nchu.software.entity.Student;
import com.nchu.software.service.AccountService;
import com.nchu.software.service.ComputerRecordService;
import com.nchu.software.service.StudentService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author JayHrn
 * @Date 2023-06-13 17:09
 * @Description
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private final StudentService studentService;
    private final ComputerRecordService computerRecordService;

    public AccountController(AccountService accountService, StudentService studentService, ComputerRecordService computerRecordService) {
        this.accountService = accountService;
        this.studentService = studentService;
        this.computerRecordService = computerRecordService;
    }

    @PostMapping("/login")
    public Result<Account> getPage(@RequestParam String username,
                                   @RequestParam String password,
                                   @RequestParam Integer type
    ) {
        //密码md5加密
        password = DigestUtils.md5Hex(password);
        //用户名密码校验（用户名唯一校验）
        LambdaQueryWrapper<Account> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Account::getUsername, username);
        Account account = accountService.getOne(lambdaQueryWrapper);
        // 账户不存在
        if (account == null) {
            return Result.error("账户不存在");
        }
        // 密码错误
        if (!account.getPassword().equals(password)) {
            return Result.error("密码错误");
        }
        // 人员类型一致
        if (!account.getType().equals(type)) {
            return Result.error("人员类型不匹配");
        }
        // 账户被禁用
        if (account.getIsDisabled() == 1) {
            return Result.error("账户被禁用");
        }
        return Result.success(account, "登录成功");
    }


    /**
     * 条件分页查询账户
     *
     * @param username
     * @param type
     * @param isDisabled
     * @param page
     * @param pageSize
     * @return Result<IPage < Account>>
     */
    @GetMapping("/page")
    public Result<Page<Account>> getPage(@RequestParam Integer page,
                                         @RequestParam Integer pageSize,
                                         @RequestParam String username,
                                         @RequestParam Integer type,
                                         @RequestParam Integer isDisabled
    ) {
        LambdaQueryWrapper<Account> lambdaQueryWrapper = new LambdaQueryWrapper();
        //条件
        lambdaQueryWrapper.like(username != null, Account::getUsername, username)
                .eq(type != -1, Account::getType, type)//用户名模糊查询
                .eq(isDisabled != -1, Account::getIsDisabled, isDisabled);
        // 分页
        Page<Account> page1 = new Page<>(page, pageSize);
        accountService.page(page1, lambdaQueryWrapper);
        return Result.success(page1, "查询成功");
    }

    /**
     * 新增账户
     *
     * @param account
     * @return
     */
    @PostMapping
    public Result<Account> newAccount(@RequestBody Account account) {
        if (account.getType() != 0 && account.getType() != 1) {
            return Result.error("用户类型错误，新增失败");
        }
        //md5加密
        account.setPassword(DigestUtils.md5Hex(account.getPassword()));
        //保存账户
        accountService.save(account);
        return Result.success(account, "新增成功");
    }

    /**
     * Excel导入管理员账户(只允许导入管理账户，学生账户由导入学生自动产生)
     *
     * @param file
     * @return Result<String>
     */
    @PostMapping("/uploadExcel")
    public Result<String> uploadFile(@RequestBody MultipartFile file) {
        /**
         * 文件上传临时路径
         */
        MultipartConfigFactory factory = new MultipartConfigFactory();
        String location = "D:\\UselessFile";
        File tmpFile = new File(location);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        factory.setLocation(location);

        try {
            // 创建 Workbook 对象，加载 Excel 文件
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            // 获取第一个 Sheet
            Sheet sheet = workbook.getSheetAt(0);
            //计数
            int num = 0;
            // 遍历每一行（从第二行开始，跳过表头）
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                System.out.println(sheet.getLastRowNum());
                Row row = sheet.getRow(i);

                // 读取每一列的数据
                String username = MyExcel.OriginalStringDisplay(row.getCell(0));
                String password = MyExcel.OriginalStringDisplay(row.getCell(1));
                int type = (int) row.getCell(2).getNumericCellValue();
                //判断是不是管理员类型，跳过类型不正确的数据
                if (type != 0 && type != 1) {
                    System.out.println("用户类型错误，跳过该数据");
                    System.out.println(type);
                    continue;
                }
                //按参数保存账户
                if (accountService.SaveFromParameters(username, password, type) != null)
                    num++;
            }

            return Result.success("成功新增" + num + "个账户");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.success("数据格式不正确，导入失败!!!");
        }
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response) {
        // 创建账户列表
        List<Account> accountList = accountService.list();

        // 创建工作簿和工作表
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Account");

        // 创建标题行
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("用户名");
        headerRow.createCell(1).setCellValue("密码");
        headerRow.createCell(2).setCellValue("人员类型");

        // 填充账户数据
        int rowNum = 1;
        for (Account account : accountList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(String.valueOf(account.getUsername()));
            row.createCell(1).setCellValue(account.getPassword());
            row.createCell(2).setCellValue(account.getType());
        }

        // 设置响应头信息
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=Account.xlsx");

        // 将工作簿写入响应输出流
        try {
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping
    public Result<String> deleteAccount(@RequestParam Long id) {
        /**
         * 触发器删除学生上机记录
         */

        //查询学生
        LambdaQueryWrapper<Student> lambdaQueryWrapper1 = new LambdaQueryWrapper();
        lambdaQueryWrapper1.eq(Student::getAccount, id);
        Student student = studentService.getOne(lambdaQueryWrapper1);

//            if (student != null) {
//                //通过学生，删除该学生上机记录
//                LambdaQueryWrapper<ComputerRecord> lambdaQueryWrapper2 = new LambdaQueryWrapper();
//                lambdaQueryWrapper2.eq(ComputerRecord::getStudent, student.getId());
//                computerRecordService.remove(lambdaQueryWrapper2);
//
//                //删除学生
        if (student != null)
            studentService.removeById(student);
//            }

        //删除账户
        boolean f = accountService.removeById(id);
        System.out.println(id);
        return Result.success("删除成功");
    }

    /**
     * 修改密码
     *
     * @param account
     * @return boolean
     */
    @PutMapping
    public Result<Account> updateAccount(@RequestBody Account account) {
        account.setPassword(DigestUtils.md5Hex(account.getPassword()));
        accountService.updateById(account);
        return Result.success(account, "更新成功");
    }
}
