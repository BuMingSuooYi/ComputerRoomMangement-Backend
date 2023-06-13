package com.nchu.software.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nchu.software.entity.MaintenanceRecord;
import com.nchu.software.mapper.MaintenanceRecordMapper;
import com.nchu.software.service.MaintenanceRecordService;
import org.springframework.stereotype.Service;

/**
* @author 15696
* @description 针对表【maintenance_record(电脑维修记录表)】的数据库操作Service实现
* @createDate 2023-06-13 17:03:31
*/
@Service
public class MaintenanceRecordServiceImpl extends ServiceImpl<MaintenanceRecordMapper, MaintenanceRecord>
    implements MaintenanceRecordService{

}




