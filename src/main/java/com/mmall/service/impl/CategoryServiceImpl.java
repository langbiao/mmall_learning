package com.mmall.service.impl;

import com.mmall.common.ServerResponse;
import com.mmall.dao.CategoryMapper;
import com.mmall.pojo.Category;
import com.mmall.service.ICategortyService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

@Service("iCategoryService")
public class CategoryServiceImpl implements ICategortyService {

    private Logger logger = (Logger) LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryMapper categoryMapper;

    public ServerResponse addCategory(String categoryName, Integer parentId) {
        if (StringUtils.isBlank(categoryName) || parentId == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }

        Category category = new Category();

        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);

        int count = categoryMapper.insertSelective(category);

        if (count > 0) {
            return ServerResponse.createBySuccess();
        }

        return ServerResponse.createByErrorMessage("添加失败");
    }

    public ServerResponse updateCategoryName(Integer categoryId, String categoryName) {
        if (StringUtils.isBlank(categoryName) || categoryId == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }

        Category category = new Category();

        category.setName(categoryName);
        category.setId(categoryId);

        int count = categoryMapper.insertSelective(category);

        if (count > 0) {
            return ServerResponse.createBySuccess();
        }

        return ServerResponse.createByErrorMessage("更新失败");
    }

    public ServerResponse<List<Category>> getChildParalelCategory(Integer categoryId) {
        if (categoryId == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }

        List<Category> categoryList = categoryMapper.selectCategoryChildByParentId(categoryId);

        if (CollectionUtils.isEmpty(categoryList)) {
            logger.info("未找到当前分类的子分类");
        }

        return ServerResponse.createBySuccess(categoryList);
    }

}
