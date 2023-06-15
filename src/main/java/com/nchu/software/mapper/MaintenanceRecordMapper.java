package com.nchu.software.mapper;

import com.nchu.software.entity.MaintenanceRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Lai
* @description 针对表【maintenance_record(电脑维修记录表)】的数据库操作Mapper
* @createDate 2023-06-15 14:45:15
* @Entity com.nchu.software.entity.MaintenanceRecord
*/
@Mapper
public interface MaintenanceRecordMapper extends BaseMapper<MaintenanceRecord> {

}




