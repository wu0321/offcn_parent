package com.offcn.project.service.impl;

import com.offcn.project.mapper.*;
import com.offcn.project.pojo.*;
import com.offcn.project.service.ProjectInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: lhq
 * @Date: 2021/4/16 09:47
 * @Description:
 */
@Service
public class ProjectInfoServiceImpl implements ProjectInfoService {


    @Autowired
    private TReturnMapper returnMapper;


    @Autowired
    private TTagMapper tTagMapper;

    @Autowired
    private TTypeMapper tTypeMapper;


    @Autowired
    private TProjectMapper projectMapper;



    @Autowired
    private TProjectImagesMapper projectImagesMapper;
    /**
     * 根据项目ID查询回报增量列表
     *
     * @param projectId
     * @return
     */
    @Override
    public List<TReturn> findReturnList(Integer projectId) {
        TReturnExample tReturnExample = new TReturnExample();
        TReturnExample.Criteria criteria = tReturnExample.createCriteria();
        criteria.andProjectidEqualTo(projectId);
        return returnMapper.selectByExample(tReturnExample);
    }

    @Override
    public List<TTag> findAllTags() {
        return tTagMapper.selectByExample(null);
    }

    @Override
    public List<TType> findAllTypes() {
        return tTypeMapper.selectByExample(null);
    }

    /**
     * 查询所有项目列表
     *
     * @return
     */
    @Override
    public List<TProject> findAllProject() {
        return projectMapper.selectByExample(null);
    }

    /**
     * 根据项目ID查询图片信息
     *
     * @param projectId
     * @return
     */
    @Override
    public List<TProjectImages> findImageByProjectId(Integer projectId) {
        TProjectImagesExample tProjectImagesExample = new TProjectImagesExample();
        TProjectImagesExample.Criteria criteria = tProjectImagesExample.createCriteria();
        criteria.andProjectidEqualTo(projectId);
        return projectImagesMapper.selectByExample(tProjectImagesExample);
    }

    /**
     * 查询项目详情
     *
     * @param projectId
     * @return
     */
    @Override
    public TProject findProjectById(Integer projectId) {
        return projectMapper.selectByPrimaryKey(projectId);
    }

    /**
     * 查询回报增量详情
     *
     * @param returnId
     * @return
     */
    @Override
    public TReturn findReturnById(Integer returnId) {
        return returnMapper.selectByPrimaryKey(returnId);
    }
}
