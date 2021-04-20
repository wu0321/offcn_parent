package com.offcn.order.service.impl;

import com.offcn.dycommons.enums.OrderStatusEnum;
import com.offcn.dycommons.response.AppResponse;
import com.offcn.order.mapper.TOrderMapper;
import com.offcn.order.pojo.TOrder;
import com.offcn.order.service.OrderService;
import com.offcn.order.service.ProjectServiceFeign;
import com.offcn.order.vo.req.OrderInfoSubmitVo;
import com.offcn.order.vo.resp.TReturn;
import com.offcn.utils.AppDateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

/**
 * @Auther: lhq
 * @Date: 2021/4/16 10:23
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectServiceFeign projectServiceFeign;


    @Autowired
    private TOrderMapper orderMapper;

    /**
     * 保存订单
     *
     * @param orderInfoSubmitVo
     * @return
     */
    @Override
    public TOrder saveOrder(OrderInfoSubmitVo orderInfoSubmitVo) {
        String accessToken = orderInfoSubmitVo.getAccessToken();
        String memberId = redisTemplate.opsForValue().get(accessToken);
        if (StringUtils.isEmpty(memberId)) {
            throw new RuntimeException("未登录，无此操作权限！");
        }
        TOrder torder = new TOrder();
        //1.复制属性  orderInfoSubmitVo--->torder
        BeanUtils.copyProperties(orderInfoSubmitVo, torder);
        //2. 生成一个随机订单编号
        String orderNum = UUID.randomUUID().toString().replace("-", "");
        torder.setOrdernum(orderNum);
        //3. 设置投资人、创建时间，支付状态
        torder.setInvoice(orderInfoSubmitVo.getInvoice()+"");
        torder.setMemberid(Integer.parseInt(memberId));
        torder.setCreatedate(AppDateUtils.getFormatTime());
        torder.setStatus(OrderStatusEnum.UNPAY.getCode() + "");
        //4. 远程服务调用项目模块，查询回报增量列表
        AppResponse<List<TReturn>> response = projectServiceFeign.findReturnList(torder.getProjectid());
        List<TReturn> returnList = response.getData();
        if (!CollectionUtils.isEmpty(returnList)) {
            for (TReturn tReturn : returnList) {
                if (tReturn.getType().equals("0")) {    //实物回报
                    //5.计算支持金额    回报数量*回报单价+运费
                    Integer money = torder.getRtncount() * tReturn.getSupportmoney() + tReturn.getFreight();
                    torder.setMoney(money);
                    break;
                }
            }

        }
        //6.执行保存订单
        orderMapper.insertSelective(torder);
        return torder;
    }
}
