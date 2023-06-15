package com.nchu.software.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nchu.software.entity.Section;
import com.nchu.software.service.SectionService;
import com.nchu.software.mapper.SectionMapper;
import org.springframework.stereotype.Service;

/**
* @author Lai
* @description 针对表【section(节次表)】的数据库操作Service实现
* @createDate 2023-06-15 14:45:15
*/
@Service
public class SectionServiceImpl extends ServiceImpl<SectionMapper, Section>
    implements SectionService{

}




