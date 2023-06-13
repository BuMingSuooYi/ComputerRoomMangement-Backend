package com.nchu.software.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nchu.software.entity.ComputerRecord;
import com.nchu.software.mapper.ComputerRecordMapper;
import com.nchu.software.service.ComputerRecordService;
import org.springframework.stereotype.Service;

/**
* @author 15696
* @description 针对表【computer_record(上机记录表)】的数据库操作Service实现
* @createDate 2023-06-13 17:03:31
*/
@Service
public class ComputerRecordServiceImpl extends ServiceImpl<ComputerRecordMapper, ComputerRecord>
    implements ComputerRecordService{

}




