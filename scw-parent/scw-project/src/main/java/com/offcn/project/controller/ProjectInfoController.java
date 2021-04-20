package com.offcn.project.controller;

import com.offcn.dycommons.response.AppResponse;
import com.offcn.project.enums.ProjectImageTypeEnume;
import com.offcn.project.pojo.*;
import com.offcn.project.service.ProjectInfoService;
import com.offcn.project.vo.resp.ProjectDetailsVo;
import com.offcn.project.vo.resp.ProjectVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: lhq
 * @Date: 2021/4/16 09:48
 * @Description:
 */
@Api(tags = "项目模块（项目信息）")
@RestController
@RequestMapping("/project")
public class ProjectInfoController {


    @Autowired
    private ProjectInfoService projectInfoService;


    @ApiOperation("根据项目ID查询回报增量列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "projectId", value = "项目ID", required = true)
    })
    @GetMapping("/details/returns/{projectId}")
    public AppResponse<List<TReturn>> findReturnList(@PathVariable(name = "projectId") Integer projectId) {
        return AppResponse.ok(projectInfoService.findReturnList(projectId));
    }

    @ApiOperation("查询标签列表")
    @GetMapping("/findAllTags")
    public AppResponse<List<TTag>> findAllTags() {
        return AppResponse.ok(projectInfoService.findAllTags());
    }

    @ApiOperation("查询分类列表")
    @GetMapping("/findAllTypes")
    public AppResponse<List<TType>> findAllTypes() {
        return AppResponse.ok(projectInfoService.findAllTypes());
    }


    @ApiOperation("查询项目列表")
    @GetMapping("/findAllProject")
    public AppResponse<List<ProjectVo>> findAllProject() {
        //1.查询所有的项目列表
        List<TProject> projectList = projectInfoService.findAllProject();
        List<ProjectVo> projectVoList = new ArrayList<>();
        //2.遍历项目列表，根据项目ID查询图片信息，并将头部图片设置到项目对象中
        for (TProject project : projectList) {
            //复制属性   pojo-->vo
            ProjectVo projectVo = new ProjectVo();
            BeanUtils.copyProperties(project, projectVo);

            List<TProjectImages> imagesList = projectInfoService.findImageByProjectId(project.getId());
            for (TProjectImages projectImages : imagesList) {
                //判断是否是头部图片
                if (projectImages.getImgtype() == ProjectImageTypeEnume.HEADER.getCode().byteValue()) {
                    projectVo.setHeaderImage(projectImages.getImgurl());
                }
            }
            projectVoList.add(projectVo);
        }
        return AppResponse.ok(projectVoList);
    }


    @ApiOperation("查询项目详情")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "projectId", value = "项目ID", required = true)
    })
    @GetMapping("/findProjectById")
    public AppResponse<ProjectDetailsVo> findProjectById(Integer projectId){
        //1.查询项目详情
        TProject project = projectInfoService.findProjectById(projectId);
        //复制属性
        ProjectDetailsVo projectDetailsVo = new ProjectDetailsVo();
        BeanUtils.copyProperties(project,projectDetailsVo);
        //2.查询图片信息，并设置
        List<TProjectImages> imagesList = projectInfoService.findImageByProjectId(projectId);
        List<String> imgUrlList = new ArrayList<>();
        for(TProjectImages projectImages:imagesList){
            //头部图片
            if(projectImages.getImgtype() == ProjectImageTypeEnume.HEADER.getCode().byteValue()){
                projectDetailsVo.setHeaderImage(projectImages.getImgurl());
            }else{
                imgUrlList.add(projectImages.getImgurl());
                projectDetailsVo.setDetailsImage(imgUrlList);
            }
        }

        //3.查询回报信息，并设置
        List<TReturn> returnList = projectInfoService.findReturnList(projectId);
        projectDetailsVo.setProjectReturns(returnList);
        return AppResponse.ok(projectDetailsVo);
    }


    @ApiOperation("查询回报增量详情")
    @GetMapping("/returns/info/{returnId}")
    public AppResponse<TReturn> findReturnById(@PathVariable(name = "returnId") Integer returnId){
        return AppResponse.ok(projectInfoService.findReturnById(returnId));
    }


}
