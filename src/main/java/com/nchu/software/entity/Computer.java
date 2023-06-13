package com.nchu.software.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 电脑表
 * @TableName computer
 */
@TableName(value ="computer")
@Data
public class Computer implements Serializable {
    /**
     * 主键
     */
    @TableId
    private Integer id;

    /**
     * 编号
     */
    private String number;

    /**
     * 电脑配置表主键
     */
    private Integer configuration;

    /**
     * 隶属机房
     */
    private Integer machineRoom;

    /**
     * 机位
     */
    private Integer cameraStand;

    /**
     * 状态，0:空闲,1:使用中,2:维修中
     */
    private Integer state;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Computer other = (Computer) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getConfiguration() == null ? other.getConfiguration() == null : this.getConfiguration().equals(other.getConfiguration()))
            && (this.getMachineRoom() == null ? other.getMachineRoom() == null : this.getMachineRoom().equals(other.getMachineRoom()))
            && (this.getCameraStand() == null ? other.getCameraStand() == null : this.getCameraStand().equals(other.getCameraStand()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getConfiguration() == null) ? 0 : getConfiguration().hashCode());
        result = prime * result + ((getMachineRoom() == null) ? 0 : getMachineRoom().hashCode());
        result = prime * result + ((getCameraStand() == null) ? 0 : getCameraStand().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", number=").append(number);
        sb.append(", configuration=").append(configuration);
        sb.append(", machineRoom=").append(machineRoom);
        sb.append(", cameraStand=").append(cameraStand);
        sb.append(", state=").append(state);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}