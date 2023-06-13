package com.nchu.software.mapper;

import com.nchu.software.entity.RejectRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 15696
* @description 针对表【reject_record(机房不可用表)】的数据库操作Mapper
* @createDate 2023-06-13 17:03:31
* @Entity generator.entity.RejectRecord
*/
@Mapper
public interface RejectRecordMapper extends BaseMapper<RejectRecord> {

}




