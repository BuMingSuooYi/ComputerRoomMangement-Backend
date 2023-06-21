package com.nchu.software.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nchu.software.VO.ComputerVo;
import com.nchu.software.common.Result;
import com.nchu.software.entity.*;
import com.nchu.software.service.ComputerConfigurationService;
import com.nchu.software.service.ComputerService;
import com.nchu.software.service.MachineRoomService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/computer")
public class ComputerController {
    private final ComputerService computerService;
    private final MachineRoomService machineRoomService;
    private final ComputerConfigurationService computerConfigurationService;

    public ComputerController(ComputerService computerService, MachineRoomService machineRoomService, ComputerConfigurationService computerConfigurationService) {
        this.computerService = computerService;
        this.machineRoomService = machineRoomService;
        this.computerConfigurationService = computerConfigurationService;
    }

    /**
     * 分页多条件查询电脑
     *
     * @param number
     * @param machineRoomName（机房名称）
     * @param state
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Result<Page<ComputerVo>> getPage(@RequestParam Integer page,
                                            @RequestParam Integer pageSize,
                                            @RequestParam String number,
                                            @RequestParam String machineRoomName,
                                            @RequestParam Integer state
                                            ) {
        LambdaQueryWrapper<Computer> lambdaQueryWrapperComputer = new LambdaQueryWrapper();
        LambdaQueryWrapper<MachineRoom> lambdaQueryWrapperMachineRoom = new LambdaQueryWrapper();

        //条件
        //电脑名称模糊查询
        lambdaQueryWrapperComputer.like(number != null, Computer::getNumber, number);
        //所属机房名称模糊查询
        lambdaQueryWrapperMachineRoom.like(machineRoomName != null, MachineRoom::getName, machineRoomName);
        List<MachineRoom> machineRoomList = machineRoomService.list(lambdaQueryWrapperMachineRoom);
        for (MachineRoom machineRoom : machineRoomList) {
            lambdaQueryWrapperComputer.in(Computer::getMachineRoom, machineRoom.getId());

        }
        //状态查询
        lambdaQueryWrapperComputer.eq(state != -1, Computer::getState, state);
//         分页
        Page<Computer> page1 = new Page<>(page, pageSize);
        computerService.page(page1, lambdaQueryWrapperComputer);

        Page<ComputerVo> voPage = new Page<>();
        // 对象拷贝
        BeanUtils.copyProperties(page, voPage,"records");
        List<Computer> computerList = page1.getRecords();
        //给新字段赋值
        List<ComputerVo> computerVoList = computerList.stream().map((item) -> {
            ComputerVo computerVo = new ComputerVo();
            BeanUtils.copyProperties(item, computerVo);
            // 获取机房
            MachineRoom machineRoom = machineRoomService.getById(item.getMachineRoom());
            computerVo.setMachineRoomObjectObject(machineRoom);
            //获取配置
            ComputerConfiguration computerConfiguration=computerConfigurationService.getById(item.getConfiguration());
            computerVo.setComputerConfigurationObject(computerConfiguration);
            return computerVo;
        }).collect(Collectors.toList());
        voPage.setRecords(computerVoList);
        return Result.success(voPage, "查询成功");
    }

    /**
     * 获取全部电脑
     * @return
     */
    @GetMapping
    public Result<List<Computer>> getAll() {

        List<Computer> computerList=computerService.list(new LambdaQueryWrapper<>());
        return Result.success(computerList, "查询成功");
    }

    /**
     * Excel导入电脑
     *
     * @param file
     * @return Result<String>
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
                System.out.println(sheet.getLastRowNum());
                Row row = sheet.getRow(i);

                // 读取每一列的数据
                String number = row.getCell(0).toString();
                long configuration = Long.parseLong(row.getCell(1).toString());
                long machine_room = Long.parseLong(row.getCell(2).toString());
                int camera_stand = (int) row.getCell(3).getNumericCellValue();
                int state = (int) row.getCell(4).getNumericCellValue();

                Computer computer = new Computer();
                computer.setNumber(number);
                computer.setConfiguration(configuration);
                computer.setMachineRoom(machine_room);
                computer.setCameraStand(camera_stand);
                computer.setState(state);
                //按参数保存电脑
                computerService.save(computer);
                num++;
            }

            return Result.success("成功新增" + num + "个电脑");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.success("数据格式不正确，导入失败!!!");
        }
    }

    /**
     * 新增电脑
     *
     * @param computer
     * @return
     */
    @PostMapping
    public Result<Computer> newComputer(@RequestBody Computer computer) {
        computerService.save(computer);
        return Result.success(computer, "新增成功");
    }


    /**
     * 删除电脑
     *
     * @param id
     * @return
     */
    @DeleteMapping
    public Result<String> deleteComputer(@RequestParam Long id) {
        /**
         * 触发器，删除电脑对应维修记录
         */
        //删除电脑
        computerService.removeById(id);
        return Result.success("删除成功");
    }

    /**
     * 更新电脑
     *
     * @param computer
     * @return boolean
     */
    @PutMapping
    public Result<Computer> updateComputer(@RequestBody Computer computer) {
        computerService.updateById(computer);
        return Result.success(computer, "更新成功");
    }
}
