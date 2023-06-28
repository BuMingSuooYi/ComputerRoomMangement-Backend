package com.nchu.software.service;

import com.nchu.software.entity.RejectRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

/**
 * @author Lai
 * @description 针对表【reject_record(机房不可用表)】的数据库操作Service
 * @createDate 2023-06-18 14:49:22
 */
public interface RejectRecordService extends IService<RejectRecord> {
    /**
     * 给今天机房不可用设定计算器
     */
    public void getToday();
    /**
     * 添加一个封闭计时器
     */
    public void addCloseTimer(RejectRecord rejectRecord, LocalDateTime startTime);


    /**
     * 添加一个开放计算器
     */
    public void addOpenTimer();
}
