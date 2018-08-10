package com.mmall.service;

import com.mmall.common.ServerResponse;

public interface ICategortyService {

    ServerResponse addCategory(String categoryName, Integer parentId);
}
