package com.nchu.software.VO;

import com.nchu.software.entity.MachineRoom;
import com.nchu.software.entity.RejectRecord;
import com.nchu.software.entity.Section;
import lombok.Data;

@Data
public class RejectRecordVo extends RejectRecord {
    private MachineRoom machineRoomObject;
    private Section sectionObject;


}
