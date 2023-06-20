package com.nchu.software.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nchu.software.common.Result;
import com.nchu.software.entity.*;
import com.nchu.software.service.ComputerConfigurationService;
import com.nchu.software.service.ComputerService;
import com.nchu.software.service.MachineRoomService;
import org.springframework.web.bind.annotation.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.List;

@RestController
@RequestMapping("/machineRoom")
public class MachineRoomController {
    private final MachineRoomService machineRoomService;
    private final ComputerService computerService;
    private final ComputerConfigurationService computerConfigurationService;

    public MachineRoomController(MachineRoomService machineRoomService, ComputerService computerService, ComputerConfigurationService computerConfigurationService) {
        this.machineRoomService = machineRoomService;
        this.computerService = computerService;
        this.computerConfigurationService = computerConfigurationService;
    }

    /**
     * 分页多条件查询机房
     *
     * @param name
     * @param state
     * @param page
     * @param pageSize
     * @return Result<IPage < MachineRoom>>
     */
    @GetMapping("/page")
    public Result<Page<MachineRoom>> getPage(@RequestParam Integer page,
                                                     @RequestParam Integer pageSize,
                                                     @RequestParam String name,
                                                     @RequestParam String principle,
                                                     @RequestParam Integer state

    ) {
        LambdaQueryWrapper<MachineRoom> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //条件
        lambdaQueryWrapper.like(name != null, MachineRoom::getName, name);
        lambdaQueryWrapper.like(principle != null, MachineRoom::getPrincipal, principle);
        lambdaQueryWrapper.eq(state != -1, MachineRoom::getState, state);
        //分页
        Page<MachineRoom> page1 = new Page<>(page, pageSize);
        machineRoomService.page(page1, lambdaQueryWrapper);
        return Result.success(page1, "查询成功");
    }

    /**
     * 获取全部机房
     * @return
     */
    @GetMapping
    public Result<List<MachineRoom>> getAll() {

        List<MachineRoom> machineRoomList=machineRoomService.list(new LambdaQueryWrapper<>());
        return Result.success(machineRoomList, "查询成功");
    }

    /**
     * 根据机房查询电脑列表
     *
     * @param id
     * @return Result<List < Computer>>
     */
    @GetMapping("/page/computer")
    public Result<List<Computer>> getComputer(@RequestParam String id) {
        LambdaQueryWrapper<Computer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Computer::getMachineRoom, id);
        List<Computer> computerList = computerService.list(lambdaQueryWrapper);
        return Result.success(computerList, "查询成功");
    }

    /**
     * 根据机位上的电脑id查询电脑配置
     *
     * @param configuration
     * @return
     */
    @GetMapping("/page/computer/configuration")
    public Result<ComputerConfiguration> getConfiguration(@RequestParam String configuration) {
        ComputerConfiguration configuration1 = computerConfigurationService.getById(configuration);
        return Result.success(configuration1, "查询成功");
    }

    /**
     * 新增机房
     *
     * @param machineRoom
     * @return
     */
    @PostMapping
    public Result<MachineRoom> newMachineRoom(@RequestBody MachineRoom machineRoom) {
        machineRoomService.save(machineRoom);
        return Result.success(machineRoom, "新增成功");
    }


    /**
     * 删除机房，并闲置机房内电脑
     *
     * @param id
     * @return
     */
    @DeleteMapping
    public Result<String> deleteAccount(@RequestParam Long id) {
        /**
         * 触发器，闲置电脑
         */
//        //根据机房id查询到电脑列表
//        LambdaQueryWrapper<Computer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq(Computer::getMachineRoom, id);
//        List<Computer> computerList = computerService.list(lambdaQueryWrapper);
//        //更改电脑隶属机房、机位为空
//        for (Computer computer : computerList) {
//            computer.setMachineRoom(null);
//            computer.setCameraStand(null);
//            computerService.updateById(computer);
//        }
        //删除机房
        machineRoomService.removeById(id);
        return Result.success("删除成功，并将电脑闲置");
    }

    /**
     * 更新机房名称、排列模式、负责人等信息
     * （如果排列模式缩小，则多余电脑闲置；如果排列模式扩大，则机位电脑不做处理）
     *
     * @param machineRoom
     * @return
     */
    @PutMapping
    public Result<MachineRoom> updateMachineRoom(@RequestBody MachineRoom machineRoom) {
        MachineRoom machineRoomOld = machineRoomService.getById(machineRoom.getId());
        //导入js
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");

        int oldResult;
        int newResult;
        try {
            //计算字符串a*b的结果
            oldResult = Integer.parseInt(engine.eval(machineRoomOld.getPattern()).toString());
            newResult = Integer.parseInt(engine.eval(machineRoom.getPattern()).toString());
            System.out.println();
        } catch (ScriptException e) {
            return Result.success(machineRoom, "更新失败，排列模式有误");
        }

        //判断新旧排列模式大小
        if (oldResult > newResult) {
            //缩小规模，闲置多余电脑
            //根据机房id查询到电脑列表
            LambdaQueryWrapper<Computer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Computer::getMachineRoom, machineRoomOld.getId());
            List<Computer> computerList = computerService.list(lambdaQueryWrapper);
            //闲置多余电脑
            for (int i = computerList.size() - 1; i > newResult; i--) {
                computerList.get(i).setMachineRoom(null);
                computerList.get(i).setCameraStand(null);
                computerService.updateById(computerList.get(i));
            }
        }

        machineRoomService.updateById(machineRoom);
        return Result.success(machineRoom, "更新成功");
    }


}
