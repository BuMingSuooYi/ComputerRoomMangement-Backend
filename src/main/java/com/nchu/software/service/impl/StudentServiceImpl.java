package com.nchu.software.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nchu.software.entity.Student;
import com.nchu.software.service.StudentService;
import com.nchu.software.mapper.StudentMapper;
import org.springframework.stereotype.Service;

/**
* @author Lai
* @description 针对表【student(学生表)】的数据库操作Service实现
* @createDate 2023-06-15 14:45:15
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

}




