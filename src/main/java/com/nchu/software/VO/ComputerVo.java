package com.nchu.software.VO;

import com.nchu.software.entity.Computer;
import com.nchu.software.entity.ComputerConfiguration;
import com.nchu.software.entity.MachineRoom;
import lombok.Data;

@Data
public class ComputerVo extends Computer {
    private MachineRoom machineRoomObject;

    private ComputerConfiguration computerConfigurationObject;



    public ComputerVo(){
    }
    public ComputerVo(Computer computer, MachineRoom machineRoomObject,ComputerConfiguration computerConfiguration){
        this.setComputer(computer);
        this.setMachineRoomObject(machineRoomObject);
        this.setComputerConfigurationObject(computerConfiguration);
    }

    /**
     * 用已存在的电脑进行赋值
     * @param computer
     */
    public void setComputer(Computer computer){
        this.setId(computer.getId());
        this.setNumber(computer.getNumber());
        this.setConfiguration(computer.getConfiguration());
        this.setMachineRoom(computer.getMachineRoom());
        this.setCameraStand(computer.getCameraStand());
        this.setState(computer.getState());
    }

}
