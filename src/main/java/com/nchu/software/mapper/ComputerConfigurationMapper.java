package com.nchu.software.mapper;

import com.nchu.software.entity.ComputerConfiguration;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Lai
* @description 针对表【computer_configuration(电脑配置表)】的数据库操作Mapper
* @createDate 2023-06-15 14:45:15
* @Entity com.nchu.software.entity.ComputerConfiguration
*/
@Mapper
public interface ComputerConfigurationMapper extends BaseMapper<ComputerConfiguration> {

}




