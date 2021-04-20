package com.offcn.user.service;

import com.offcn.user.pojo.TMember;
import com.offcn.user.pojo.TMemberAddress;

import java.util.List;

/**
 * @Auther: lhq
 * @Date: 2021/4/14 09:28
 * @Description:
 */
public interface UserService {

    /**
     * 注册会员
     * @param tMember
     */
    public void register(TMember tMember);


    /**
     * 用户登录
     * @param loginacct
     * @param password
     * @return
     */
    public TMember  login(String loginacct,String password);

    /**
     * 根据Id查询会员信息
     * @param id
     * @return
     */
    public TMember findMemberById(Integer id);


    /**
     * 根据编号查询地址列表
     * @param memberId
     * @return
     */
    public List<TMemberAddress> findAddressList(Integer memberId);
}
