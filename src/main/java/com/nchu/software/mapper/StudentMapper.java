package com.nchu.software.mapper;

import com.nchu.software.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 15696
* @description 针对表【student(学生表)】的数据库操作Mapper
* @createDate 2023-06-13 17:03:31
* @Entity generator.entity.Student
*/
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}




