package com.nchu.software.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nchu.software.common.Result;
import com.nchu.software.entity.Account;
import com.nchu.software.entity.Section;
import com.nchu.software.service.SectionService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/section")
public class SectionController {
    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    /**
     * 分页查询节次
     * @param page
     * @param pageSize
     * @return Result<IPage < Account>>
     */
    @GetMapping("/page")
    public Result<Page<Section>> getPage(@RequestParam Integer page,
                                         @RequestParam Integer pageSize
    ) {
        // 分页
        Page<Section> page1 = new Page<>(page, pageSize);
        sectionService.page(page1);
        return Result.success(page1, "查询成功");
    }

    /**
     * 获取全部节次
     * @return
     */
    @GetMapping
    public Result<List<Section>> getAll() {
        return Result.success(sectionService.list(), "查询成功");
    }


    /**
     * 新增节次
     * @param section
     * @return
     */
    @PostMapping
    public Result<Section> newSection(@RequestBody Section section) {
        sectionService.save(section);
        return Result.success(section, "新增成功");
    }

    /**
     * 删除节次
     * @param id
     * @return
     */
    @DeleteMapping
    public Result<String> deleteSection(@RequestParam List<Long> id) {
        if (sectionService.removeByIds(id))
            return Result.success("删除成功");
        return Result.success("删除失败");
    }

    /**
     * 更新账户
     *
     * @param section
     * @return boolean
     */
    @PutMapping
    public Result<Section> updateAccount(@RequestBody Section section) {
        if (sectionService.updateById(section))
            return Result.success(section,"删除成功");
        return Result.success(section,"删除失败");
    }
}
