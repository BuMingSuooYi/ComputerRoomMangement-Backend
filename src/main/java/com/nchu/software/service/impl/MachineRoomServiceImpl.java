package com.nchu.software.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nchu.software.entity.MachineRoom;
import com.nchu.software.service.MachineRoomService;
import com.nchu.software.mapper.MachineRoomMapper;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author Lai
* @description 针对表【machine_room(机房表)】的数据库操作Service实现
* @createDate 2023-06-18 14:49:22
*/
@Service
public class MachineRoomServiceImpl extends ServiceImpl<MachineRoomMapper, MachineRoom>
    implements MachineRoomService{
    public  int result(String str) {
        String pattern = "(\\d+)\\*(\\d+)";

        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(str);

        if (matcher.find()) {
            int num1 = Integer.parseInt(matcher.group(1));
            int num2 = Integer.parseInt(matcher.group(2));
            return num1 * num2;
        } else {
            return -1;
        }
    }
}




