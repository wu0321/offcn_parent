package com.offcn.project.vo.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Auther: lhq
 * @Date: 2021/4/16 13:44
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectVo implements Serializable {


    // 会员id
    private Integer memberid;
    //项目id
    private Integer id;
    // 项目名称
    private String name;
    // 项目简介
    private String remark;
    // 项目头部图片
    private String headerImage;

}
