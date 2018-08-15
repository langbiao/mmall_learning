package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.pojo.User;
import com.mmall.service.IProductService;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@RequestMapping("/manage/product")
@Controller
public class ProductManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IProductService iProductService;

    @RequestMapping(value = "productSave.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse productSave(HttpSession session, Product product) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("请登录");
        }

        if(!iUserService.checkAdminRole(user).isSuccess()) {
            return ServerResponse.createByErrorMessage("您不是管理管");
        }

        return  iProductService.saveOrUpdateProduct(product);
    }

    @RequestMapping(value = "setSalesStatus.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse setSalesStatus(HttpSession session, Integer productId, Integer status) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("请登录");
        }


        if(!iUserService.checkAdminRole(user).isSuccess()) {
            return ServerResponse.createByErrorMessage("您不是管理管");
        }

        return iProductService.saveSalesStatus(productId, status);
    }

    public static void main(String[] args) {
        System.out.println(0.01 + 0.05);
    }

}
