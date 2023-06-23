package com.nchu.software.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 机房不可用表
 *
 * @TableName reject_record
 */
@TableName(value = "reject_record")
@Data
public class RejectRecord implements Serializable {
    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 机房表主键
     */
    private Long machineRoom;

    /**
     * 节次表主键
     */
    private Long section;

    /**
     * 日期
     */
    private LocalDateTime time;

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
        RejectRecord other = (RejectRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getMachineRoom() == null ? other.getMachineRoom() == null : this.getMachineRoom().equals(other.getMachineRoom()))
                && (this.getSection() == null ? other.getSection() == null : this.getSection().equals(other.getSection()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMachineRoom() == null) ? 0 : getMachineRoom().hashCode());
        result = prime * result + ((getSection() == null) ? 0 : getSection().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", machineRoom=").append(machineRoom);
        sb.append(", section=").append(section);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public void setTime(String time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(time, formatter);
        this.setTime(date.atStartOfDay());
    }

    public void setTime(LocalDateTime time){
        this.time=time;
    }
}
