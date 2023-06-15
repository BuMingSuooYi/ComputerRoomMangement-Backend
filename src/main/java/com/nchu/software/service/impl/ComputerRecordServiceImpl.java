package com.nchu.software.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nchu.software.entity.ComputerRecord;
import com.nchu.software.service.ComputerRecordService;
import com.nchu.software.mapper.ComputerRecordMapper;
import org.springframework.stereotype.Service;

/**
* @author Lai
* @description 针对表【computer_record(上机记录表)】的数据库操作Service实现
* @createDate 2023-06-15 14:45:15
*/
@Service
public class ComputerRecordServiceImpl extends ServiceImpl<ComputerRecordMapper, ComputerRecord>
    implements ComputerRecordService{

}




