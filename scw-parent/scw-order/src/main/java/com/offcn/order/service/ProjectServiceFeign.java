package com.offcn.order.service;

import com.offcn.dycommons.response.AppResponse;
import com.offcn.order.service.impl.ProjectServiceException;
import com.offcn.order.vo.resp.TReturn;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Auther: lhq
 * @Date: 2021/4/16 10:10
 * @Description:
 */
@FeignClient(value = "SCWPROJECT",fallback = ProjectServiceException.class)
public interface ProjectServiceFeign {

    @GetMapping("/project/details/returns/{projectId}")
    public AppResponse<List<TReturn>> findReturnList(@PathVariable(name = "projectId") Integer projectId);
}
