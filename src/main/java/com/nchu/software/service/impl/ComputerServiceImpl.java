package com.nchu.software.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nchu.software.entity.Computer;
import com.nchu.software.mapper.ComputerMapper;
import com.nchu.software.service.ComputerService;
import org.springframework.stereotype.Service;

/**
* @author 15696
* @description 针对表【computer(电脑表)】的数据库操作Service实现
* @createDate 2023-06-13 17:03:31
*/
@Service
public class ComputerServiceImpl extends ServiceImpl<ComputerMapper, Computer>
    implements ComputerService{

}




