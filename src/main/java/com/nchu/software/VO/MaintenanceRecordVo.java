package com.nchu.software.VO;

import com.nchu.software.entity.Computer;
import com.nchu.software.entity.MaintenanceRecord;
import lombok.Data;

@Data
public class MaintenanceRecordVo extends MaintenanceRecord {
    private Computer computerObject;
}
