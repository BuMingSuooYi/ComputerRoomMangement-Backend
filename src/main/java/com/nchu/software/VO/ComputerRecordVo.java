package com.nchu.software.VO;

import com.nchu.software.entity.Computer;
import com.nchu.software.entity.ComputerRecord;
import com.nchu.software.entity.Student;
import lombok.Data;

@Data
public class ComputerRecordVo extends ComputerRecord {
    private Student studentObject;
    private Computer computerObject;
}
