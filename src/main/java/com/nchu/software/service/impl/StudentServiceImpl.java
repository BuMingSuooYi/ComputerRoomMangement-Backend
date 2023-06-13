package com.nchu.software.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nchu.software.entity.Student;
import com.nchu.software.mapper.StudentMapper;
import com.nchu.software.service.StudentService;
import org.springframework.stereotype.Service;

/**
* @author 15696
* @description 针对表【student(学生表)】的数据库操作Service实现
* @createDate 2023-06-13 17:03:31
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

}




