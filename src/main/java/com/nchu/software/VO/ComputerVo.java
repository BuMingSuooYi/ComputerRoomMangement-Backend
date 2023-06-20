package com.nchu.software.VO;

import com.nchu.software.entity.Computer;
import com.nchu.software.entity.MachineRoom;

public class ComputerVo extends Computer {
    private MachineRoom machineRoomObject;



    public ComputerVo(){
    }
    public ComputerVo(Computer computer, MachineRoom machineRoomObject){
        this.setComputer(computer);
        this.setMachineRoomObject(machineRoomObject);
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

    public MachineRoom getMachineRoomObject() {
        return machineRoomObject;
    }

    public void setMachineRoomObject(MachineRoom machineRoomObject) {
        this.machineRoomObject = machineRoomObject;
    }

    @Override
    public String toString() {
        return "ComputerVo{" +
                "machineRoomObject=" + machineRoomObject +
                '}';
    }
}
