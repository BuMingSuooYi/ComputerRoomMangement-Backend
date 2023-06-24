package com.nchu.software.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nchu.software.common.MyExcel;
import com.nchu.software.common.Result;
import com.nchu.software.entity.Account;
import com.nchu.software.entity.ComputerRecord;
import com.nchu.software.entity.Student;
import com.nchu.software.service.AccountService;
import com.nchu.software.service.ComputerRecordService;
import com.nchu.software.service.StudentService;
import lombok.extern.slf4j.Slf4j;
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
 * @Author Lai
 * @Date 2023-06-16 13:37
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final AccountService accountService;
    private final ComputerRecordService computerRecordService;

    public StudentController(StudentService studentService, AccountService accountService, ComputerRecordService computerRecordService) {
        this.studentService = studentService;
        this.accountService = accountService;
        this.computerRecordService = computerRecordService;
    }

    /**
     * 多条件分页查询学生
     *
     * @param studentNo
     * @param name
     * @param clazz
     * @param page
     * @param pageSize
     * @return Result<IPage < Student>>
     */
    @GetMapping("/page")
    public Result<Page<Student>> getPage(@RequestParam Integer page,
                                         @RequestParam Integer pageSize,
                                         @RequestParam String studentNo,
                                         @RequestParam String name,
                                         @RequestParam String clazz) {
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper();
        //条件
        lambdaQueryWrapper.like(studentNo != null, Student::getStudentNo, studentNo);//学号模糊查询
        lambdaQueryWrapper.like(name != null, Student::getName, name);//姓名模糊查询
        lambdaQueryWrapper.like(clazz != null, Student::getClazz, clazz);//班级模糊查询
        // 分页
        Page<Student> page1 = new Page<>(page, pageSize);
        studentService.page(page1, lambdaQueryWrapper);
        return Result.success(page1, "查询成功");
    }


    /**
     * 通过账户id查询学生
     *
     * @param account
     * @return Result<Student>
     */
    @GetMapping("/account")
    public Result<Student> getPage(@RequestParam Long account) {
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper();
        //条件
        lambdaQueryWrapper.eq(Student::getAccount, account);//账户查询
        // 分页
        Student student = studentService.getOne(lambdaQueryWrapper);
        return Result.success(student, "查询成功");
    }


    /**
     * 通过学生名称查询学生
     *
     * @param name
     * @return Result<Student>
     */
    @GetMapping("/name")
    public Result<Student> getPage(@RequestParam String name) {
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper();
        //条件
        lambdaQueryWrapper.eq(Student::getName, name);//名字查询

        // 分页
        Student student = studentService.getOne(lambdaQueryWrapper);
        return Result.success(student, "查询成功");
    }

    /**
     * 新增学生(并生成账户)
     *
     * @param student
     * @return Result<String>
     */
    @PostMapping
    public Result<Student> newAccount(@RequestBody Student student) {
        //新增学生账户
        Account account = accountService.SaveStudent(student.getStudentNo());
        //创建学生
        student.setAccount(account.getId());
        studentService.save(student);

        return Result.success(student, "新增成功，并生成账户");
    }

    /**
     * Excel导入学生(并生成账户)
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
        String location = "./";
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
                String student_no = MyExcel.OriginalStringDisplay(row.getCell(0));
                String name = MyExcel.OriginalStringDisplay(row.getCell(1));
                ;
                String clazz = MyExcel.OriginalStringDisplay(row.getCell(2));
                ;
                int sex = (int) row.getCell(3).getNumericCellValue();
                String telephone = MyExcel.OriginalStringDisplay(row.getCell(4));
                ;

                //创建学生账户并保存在数据库
                Account account = accountService.SaveStudent(student_no);

                // 创建学生对象，并设置参数
                Student student = new Student();
                student.setStudentNo(student_no);
                student.setName(name);
                student.setClazz(clazz);
                student.setSex(sex);
                student.setTelephone(telephone);
                //将上面创建的账号id设置在学生表中
                student.setAccount(account.getId());
                //保存到数据库
                studentService.save(student);
                num++;
            }

            return Result.success("成功新增" + num + "个学生");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.success("数据格式不正确，导入失败!!!");
        }
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response) {
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 创建学生列表
        List<Student> studentList = studentService.list();
        log.info("{}", studentList);

        // 创建工作簿和工作表
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Students");

        // 创建标题行
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("学号");
        headerRow.createCell(1).setCellValue("姓名");
        headerRow.createCell(2).setCellValue("班级");
        headerRow.createCell(3).setCellValue("性别");
        headerRow.createCell(4).setCellValue("电话");

        // 填充学生数据
        int rowNum = 1;
        for (Student student : studentList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(String.valueOf(student.getId()));
            row.createCell(1).setCellValue(student.getName());
            row.createCell(2).setCellValue(student.getClazz());
            row.createCell(3).setCellValue(student.getSex());
            row.createCell(4).setCellValue(student.getTelephone());
        }

        // 设置响应头信息
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=students.xlsx");

        // 将工作簿写入响应输出流
        try {
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 删除学生
     *
     * @return
     */
    @DeleteMapping
    public Result<String> deleteAccount(@RequestParam Long id) {
        //删除该学生上机记录
        LambdaQueryWrapper<ComputerRecord> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(ComputerRecord::getStudent, id);
        computerRecordService.remove(lambdaQueryWrapper);
        //删除学生账户
        Student student = studentService.getById(id);
        accountService.removeById(student.getAccount());
        //删除学生
        studentService.removeById(id);
        return Result.success("删除成功，同时删除上机记录和账户");
    }

    /**
     * 更新学生
     *
     * @param student
     * @return Result<Student>
     */
    @PutMapping
    public Result<Student> updateAccount(@RequestBody Student student) {
        //可能更新学生账户的用户名
        Account account = accountService.getById(student.getAccount());
        if (account.getUsername() != student.getStudentNo()) {
            account.setUsername(student.getStudentNo());
            accountService.updateById(account);
        }
        //更新学生
        studentService.updateById(student);
        return Result.success(student, "更新成功");
    }
}
