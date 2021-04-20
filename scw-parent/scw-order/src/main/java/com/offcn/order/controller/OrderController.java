package com.offcn.order.controller;

import com.offcn.dycommons.response.AppResponse;
import com.offcn.order.pojo.TOrder;
import com.offcn.order.service.OrderService;
import com.offcn.order.vo.req.OrderInfoSubmitVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: lhq
 * @Date: 2021/4/16 10:45
 * @Description:
 */
@Api(tags = "订单模块")
@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private OrderService orderService;

    @ApiOperation("保存订单")
    @PostMapping("/saveOrder")
    public AppResponse<TOrder> saveOrder(@RequestBody OrderInfoSubmitVo orderInfoSubmitVo) {
        //1.判断用户是否登录
        String memberId = redisTemplate.opsForValue().get(orderInfoSubmitVo.getAccessToken());
        if (StringUtils.isEmpty(memberId)) {
            AppResponse response = AppResponse.fail(null);
            response.setMessage("未登录，无此操作权限");
            return response;
        }
        //2.完成提交订单
        TOrder order = orderService.saveOrder(orderInfoSubmitVo);
        return AppResponse.ok(order);
    }
}
