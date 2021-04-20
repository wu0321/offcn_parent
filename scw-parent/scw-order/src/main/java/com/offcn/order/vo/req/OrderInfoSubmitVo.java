package com.offcn.order.vo.req;

import com.offcn.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: lhq
 * @Date: 2021/4/16 09:36
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoSubmitVo extends BaseVo {

    private Integer projectid;//项目ID
    private Integer returnid;//回报ID
    private Integer rtncount;//回报数量
    private String address;//收货地址
    private Byte invoice;//是否开发票 0 - 不开发票， 1 - 开发票
    private String invoictitle;//发票名头
    private String remark;//备注

}
