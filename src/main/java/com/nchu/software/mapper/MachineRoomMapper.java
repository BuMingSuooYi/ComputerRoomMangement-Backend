package com.nchu.software.mapper;

import com.nchu.software.entity.MachineRoom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Lai
* @description 针对表【machine_room(机房表)】的数据库操作Mapper
* @createDate 2023-06-18 14:49:22
* @Entity com.nchu.software.entity.MachineRoom
*/
@Mapper
public interface MachineRoomMapper extends BaseMapper<MachineRoom> {

}




