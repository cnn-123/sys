package com.example.sys.demo.service.impl;

import com.example.sys.demo.entity.Category;
import com.example.sys.demo.mapper.CategoryMapper;
import com.example.sys.demo.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 陈莉
 * @since 2020-02-11
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
