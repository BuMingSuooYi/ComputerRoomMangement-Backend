package com.nchu.software.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nchu.software.entity.MaintenanceRecord;
import com.nchu.software.service.MaintenanceRecordService;
import com.nchu.software.mapper.MaintenanceRecordMapper;
import org.springframework.stereotype.Service;

/**
* @author Lai
* @description 针对表【maintenance_record(电脑维修记录表)】的数据库操作Service实现
* @createDate 2023-06-15 14:45:15
*/
@Service
public class MaintenanceRecordServiceImpl extends ServiceImpl<MaintenanceRecordMapper, MaintenanceRecord>
    implements MaintenanceRecordService{

}




