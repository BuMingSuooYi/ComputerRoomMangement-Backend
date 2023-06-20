package com.nchu.software.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 电脑配置表
 *
 * @TableName computer_configuration
 */
@TableName(value = "computer_configuration")
@Data
public class ComputerConfiguration implements Serializable {
    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 配置名称
     */
    private String name;

    /**
     * 硬件配置
     */
    private String hardware;

    /**
     * 软件配置
     */
    private String software;

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
        ComputerConfiguration other = (ComputerConfiguration) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getHardware() == null ? other.getHardware() == null : this.getHardware().equals(other.getHardware()))
                && (this.getSoftware() == null ? other.getSoftware() == null : this.getSoftware().equals(other.getSoftware()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getHardware() == null) ? 0 : getHardware().hashCode());
        result = prime * result + ((getSoftware() == null) ? 0 : getSoftware().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", hardware=").append(hardware);
        sb.append(", software=").append(software);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
