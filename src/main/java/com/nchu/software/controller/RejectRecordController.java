package com.nchu.software.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nchu.software.VO.ComputerVo;
import com.nchu.software.VO.RejectRecordVo;
import com.nchu.software.common.MyExcel;
import com.nchu.software.common.Result;
import com.nchu.software.entity.*;
import com.nchu.software.service.MachineRoomService;
import com.nchu.software.service.RejectRecordService;
import com.nchu.software.service.SectionService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rejectRecord")
public class RejectRecordController {
    private final RejectRecordService rejectRecordService;
    private final MachineRoomService machineRoomService;
    private final SectionService sectionService;

    public RejectRecordController(RejectRecordService rejectRecordService, MachineRoomService machineRoomService, SectionService sectionService) {
        this.rejectRecordService = rejectRecordService;
        this.machineRoomService = machineRoomService;
        this.sectionService = sectionService;
    }

    /**
     * d多条件分页查询机房不可用表
     *
     * @param page
     * @param pageSize
     * @param machineRoom
     * @param time
     * @return
     */
    @GetMapping("/page")
    public Result<Page<RejectRecordVo>> getPage(@RequestParam Integer page,
                                                @RequestParam Integer pageSize,
                                                @RequestParam Long machineRoom,
                                                @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime time
    ) {
        LambdaQueryWrapper<RejectRecord> lambdaQueryWrapperRejectRecord = new LambdaQueryWrapper();

        //条件
        //机房id
        lambdaQueryWrapperRejectRecord.eq(machineRoom != -1, RejectRecord::getMachineRoom, machineRoom);
        //日期
        lambdaQueryWrapperRejectRecord.eq(time != null, RejectRecord::getTime, time);//         分页
        Page<RejectRecord> page1 = new Page<>(page, pageSize);

        rejectRecordService.page(page1, lambdaQueryWrapperRejectRecord);
        Page<RejectRecordVo> voPage = new Page<>();
        // 对象拷贝
        BeanUtils.copyProperties(page1, voPage, "records");
        List<RejectRecord> rejectRecordList = page1.getRecords();
        //给新字段赋值
        List<RejectRecordVo> rejectRecordVoList = rejectRecordList.stream().map((item) -> {
            RejectRecordVo rejectRecordVo = new RejectRecordVo();
            BeanUtils.copyProperties(item, rejectRecordVo);
            // 获取机房
            MachineRoom machineRoomObject = machineRoomService.getById(item.getMachineRoom());
            rejectRecordVo.setMachineRoomObject(machineRoomObject);
            //获取节次
            Section section = sectionService.getById(item.getSection());
            rejectRecordVo.setSectionObject(section);
            return rejectRecordVo;
        }).collect(Collectors.toList());
        voPage.setRecords(rejectRecordVoList);
        return Result.success(voPage, "查询成功");
    }

    /**
     * 导入机房不可用
     * @param file
     * @return
     */
    @PostMapping("/uploadExcel")
    public Result<String> uploadFile(@RequestBody MultipartFile file) {
        /**
         * 文件上传临时路径
         */
        MultipartConfigFactory factory = new MultipartConfigFactory();
        String location = "D:\\UselessFile";
        File tmpFile = new File(location);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        factory.setLocation(location);

        try {
            // 创建 Workbook 对象，加载 Excel 文件
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            // 获取第一个 Sheet
            Sheet sheet = workbook.getSheetAt(0);
            //计数
            int num = 0;
            // 遍历每一行（从第二行开始，跳过表头）
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                // 读取每一列的数据
                long machine_room = Long.parseLong(row.getCell(0).toString());
                long section = Long.parseLong(row.getCell(1).toString());
                String time = MyExcel.OriginalStringDisplay(row.getCell(2));

                RejectRecord rejectRecord = new RejectRecord();
                rejectRecord.setMachineRoom(machine_room);
                rejectRecord.setSection(section);
                rejectRecord.setTime(time);
                //按参数保存账户
                rejectRecordService.save(rejectRecord);
                num++;
            }

            return Result.success("成功新增" + num + "条机房不可用消息");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.success("数据格式不正确，导入失败!!!");
        }
    }

    /**
     * 新增机房不可用时间段
     *
     * @param rejectRecord
     * @return
     */
    @PostMapping
    public Result<RejectRecord> newRejectRecord(@RequestBody RejectRecord rejectRecord) {
        rejectRecordService.save(rejectRecord);
        return Result.success(rejectRecord, "新增成功");
    }


    /**
     * 删除机房不可用时间段
     *
     * @param id
     * @return
     */
    @DeleteMapping
    public Result<String> deleteRejectRecord(@RequestParam List<Long> id) {
        if (rejectRecordService.removeByIds(id))
            return Result.success("删除成功");
        return Result.success("删除失败");
    }

    /**
     * 更新机房不可用时间段
     *
     * @param rejectRecord
     * @return boolean
     */
    @PutMapping
    public Result<RejectRecord> updateComputer(@RequestBody RejectRecord rejectRecord) {
        if (rejectRecordService.updateById(rejectRecord))
            return Result.success(rejectRecord, "更新成功");
        return Result.success(rejectRecord, "更新失败");
    }
}
