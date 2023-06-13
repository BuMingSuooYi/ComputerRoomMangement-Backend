package com.nchu.software.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nchu.software.entity.ComputerConfiguration;
import com.nchu.software.service.ComputerConfigurationService;
import com.nchu.software.mapper.ComputerConfigurationMapper;
import org.springframework.stereotype.Service;

/**
* @author Lai
* @description 针对表【computer_configuration(电脑配置表)】的数据库操作Service实现
* @createDate 2023-06-13 13:33:05
*/
@Service
public class ComputerConfigurationServiceImpl extends ServiceImpl<ComputerConfigurationMapper, ComputerConfiguration>
    implements ComputerConfigurationService{

}




