package com.offcn.order.service.impl;

import com.offcn.dycommons.response.AppResponse;
import com.offcn.order.service.ProjectServiceFeign;
import com.offcn.order.vo.resp.TReturn;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: lhq
 * @Date: 2021/4/16 10:15
 * @Description:
 */
@Component
public class ProjectServiceException implements ProjectServiceFeign {
    @Override
    public AppResponse<List<TReturn>> findReturnList(Integer projectId) {
        AppResponse response = AppResponse.fail(null);
        response.setMessage("查询回报增量列表发生错误，触发熔断");
        return response;
    }
}
