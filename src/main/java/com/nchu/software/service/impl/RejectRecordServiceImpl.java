package com.nchu.software.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nchu.software.entity.RejectRecord;
import com.nchu.software.service.RejectRecordService;
import com.nchu.software.mapper.RejectRecordMapper;
import org.springframework.stereotype.Service;

/**
* @author Lai
* @description 针对表【reject_record(机房不可用表)】的数据库操作Service实现
* @createDate 2023-06-15 14:45:15
*/
@Service
public class RejectRecordServiceImpl extends ServiceImpl<RejectRecordMapper, RejectRecord>
    implements RejectRecordService{

}




