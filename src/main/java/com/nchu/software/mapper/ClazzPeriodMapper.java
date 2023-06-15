package com.nchu.software.mapper;

import com.nchu.software.entity.ClazzPeriod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Lai
* @description 针对表【clazz_period(班级学时表)】的数据库操作Mapper
* @createDate 2023-06-15 14:45:15
* @Entity com.nchu.software.entity.ClazzPeriod
*/
@Mapper
public interface ClazzPeriodMapper extends BaseMapper<ClazzPeriod> {

}




