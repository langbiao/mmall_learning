package com.mmall.service.impl;

import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.dao.ProductMapper;
import com.mmall.pojo.Product;
import com.mmall.service.IProductService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("iProduct")
public class ProductServiceImpl implements IProductService {

    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductMapper productMapper;

    public ServerResponse saveOrUpdateProduct(Product product) {
        if (product == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }

        if (StringUtils.isNotBlank(product.getSubImages())) {
            String[] subImageAray = product.getSubImages().split(",");
            if (subImageAray.length > 0) {
                product.setMainImage(subImageAray[0]);
            }
        }

        try {
            // 新增
            if (product.getId() == null) {
                int rowCount = productMapper.insert(product);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccessMessage("添加成功");
                } else {
                    return ServerResponse.createByErrorMessage("添加失败");
                }
            } else {
                // 更新
                productMapper.updateByPrimaryKey(product);
                return ServerResponse.createBySuccessMessage("更新成功");
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            return ServerResponse.createByErrorMessage("系统错误");
        }
    }

    @Override
    public ServerResponse saveSalesStatus(Integer productId, Integer productStatus) {
        if (productId == null || productStatus == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        Product product = new Product();
        product.setId(productId);
        product.setStatus(productStatus);

        int rowCount = productMapper.updateByPrimaryKeySelective(product);

        if (rowCount > 0) {
            return ServerResponse.createBySuccessMessage("状态更新成功");
        }

        return ServerResponse.createByErrorMessage("状态更新失败");
    }
}
