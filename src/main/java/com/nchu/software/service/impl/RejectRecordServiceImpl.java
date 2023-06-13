package com.nchu.software.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nchu.software.entity.RejectRecord;
import com.nchu.software.mapper.RejectRecordMapper;
import com.nchu.software.service.RejectRecordService;
import org.springframework.stereotype.Service;

/**
* @author 15696
* @description 针对表【reject_record(机房不可用表)】的数据库操作Service实现
* @createDate 2023-06-13 17:03:31
*/
@Service
public class RejectRecordServiceImpl extends ServiceImpl<RejectRecordMapper, RejectRecord>
    implements RejectRecordService{

}




