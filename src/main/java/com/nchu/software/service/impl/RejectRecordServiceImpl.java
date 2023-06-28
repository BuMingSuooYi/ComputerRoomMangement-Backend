package com.nchu.software.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nchu.software.entity.RejectRecord;
import com.nchu.software.entity.Section;
import com.nchu.software.service.RejectRecordService;
import com.nchu.software.mapper.RejectRecordMapper;
import com.nchu.software.service.SectionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Lai
 * @description 针对表【reject_record(机房不可用表)】的数据库操作Service实现
 * @createDate 2023-06-18 14:49:22
 */
@Service
public class RejectRecordServiceImpl extends ServiceImpl<RejectRecordMapper, RejectRecord>
        implements RejectRecordService {

    private final SectionService sectionService;

    public RejectRecordServiceImpl(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @Override
    public void getToday() {
        //查询今天的机房不可用
        LambdaQueryWrapper<RejectRecord> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        LocalDate today = LocalDate.now();
        lambdaQueryWrapper.eq(RejectRecord::getTime, today);
        List<RejectRecord> rejectRecordList = this.list(lambdaQueryWrapper);
        System.out.println("今天机房不可用数："+rejectRecordList.size());
        //
        for (RejectRecord rejectRecord : rejectRecordList) {
            Section section = sectionService.getById(rejectRecord.getSection());

            LocalDate date = rejectRecord.getTime().toLocalDate();  // 获取年月日
            LocalDateTime startTime = LocalDateTime.of(date, section.getStartTime());
            LocalDateTime endTime = LocalDateTime.of(date, section.getEndTime());

        }

    }

    @Override
    public void addCloseTimer(RejectRecord rejectRecord,LocalDateTime startTime) {

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // 在这里执行数据库操作

                System.out.println("执行数据库操作");
            }
        };

        long delay = calculateDelay(startTime);  // 计算时间间隔

        timer.schedule(task, delay);  // 设置计时器任务

        // 可选：等待计时器任务完成后终止程序
        try {
            Thread.sleep(delay + 1000);  // 延迟1秒等待任务执行
            timer.cancel();  // 终止计时器
            System.exit(0);  // 退出程序
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOpenTimer() {

    }

    /**
     * 计算当前时间与目标日期时间之间的时间间隔，返回以毫秒为单位的延迟值
     * @param targetDateTime
     * @return
     */
    private static long calculateDelay(LocalDateTime targetDateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();  // 获取当前日期时间
        return java.time.Duration.between(currentDateTime, targetDateTime).toMillis();
    }
}




