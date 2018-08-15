package com.mmall.service;

import com.mmall.common.ServerResponse;

/**
 * @author laolang
 * @create 2018-08-15 11:16
 * @desc ICartService
 **/
public interface ICartService {

    ServerResponse add(Integer userId, Integer productId, Integer count);

}
