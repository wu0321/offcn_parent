package com.offcn.order.service;

import com.offcn.order.pojo.TOrder;
import com.offcn.order.vo.req.OrderInfoSubmitVo;

/**
 * @Auther: lhq
 * @Date: 2021/4/16 10:21
 * @Description:   订单接口
 */
public interface OrderService {

    /**
     * 保存订单
     * @param orderInfoSubmitVo
     * @return
     */
    public TOrder saveOrder(OrderInfoSubmitVo orderInfoSubmitVo);
}
