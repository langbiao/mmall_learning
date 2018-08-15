package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CartMapper;
import com.mmall.pojo.User;
import com.mmall.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author laolang
 * @create 2018-08-15 11:12
 * @desc CartController
 **/
@RequestMapping("/cart")
@Controller
public class CartController {

    @Autowired
    private ICartService iCartService;

    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse add(HttpSession session, Integer productId, Integer count) {

        User user = (User) session.getAttribute(Const.CURRENT_USER);

        if (user == null) {
            return ServerResponse.createByErrorMessage("请登录");
        }

        return  iCartService.add(user.getId(), productId, count);

    }

}
