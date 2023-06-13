package com.nchu.software.mapper;

import com.nchu.software.entity.Computer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 15696
* @description 针对表【computer(电脑表)】的数据库操作Mapper
* @createDate 2023-06-13 17:03:31
* @Entity generator.entity.Computer
*/
@Mapper
public interface ComputerMapper extends BaseMapper<Computer> {

}




