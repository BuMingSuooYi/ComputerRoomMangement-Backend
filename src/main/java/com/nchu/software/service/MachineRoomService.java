package com.nchu.software.service;

import com.nchu.software.entity.MachineRoom;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.regex.Pattern;

/**
* @author Lai
* @description 针对表【machine_room(机房表)】的数据库操作Service
* @createDate 2023-06-18 14:49:22
*/
public interface MachineRoomService extends IService<MachineRoom> {


    int result(String str);
}
