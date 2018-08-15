package com.mmall.service.impl;

import com.mmall.common.ServerResponse;
import com.mmall.dao.CartMapper;
import com.mmall.pojo.Cart;
import com.mmall.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author laolang
 * @create 2018-08-15 11:16
 * @desc CartServiceImpl
 **/
@Service("iCartService")
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;

    public ServerResponse add(Integer userId, Integer productId, Integer count) {

        Cart cart = cartMapper.selectCartInfoByUserIdAndProductId(userId, productId);

        if (cart == null) {
            Cart carItem = new Cart();
            carItem.setQuantity(count);
            carItem.setUserId(userId);
            carItem.setProductId(productId);

            int rowCount = cartMapper.insert(carItem);

            if (rowCount > 0) {
                return ServerResponse.createBySuccessMessage("添加成功");
            } else {
                return ServerResponse.createByErrorMessage("添加失败");
            }
        }

        count = cart.getQuantity() + count;
        cart.setQuantity(count);

        cartMapper.updateByPrimaryKeySelective(cart);

        return ServerResponse.createBySuccessMessage("更新成功");
    }

}
