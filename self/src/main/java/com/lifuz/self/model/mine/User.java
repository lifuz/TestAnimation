package com.lifuz.self.model.mine;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户类
 * 实体类
 *
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/7/28 21:56
 */

@Data
@NoArgsConstructor
public class User {

    //用户id
    private Long userId;

    //用户名
    private String userName;

    //真实姓名
    private String realName;

    //qq登录id
    private String qqOpenId;

    //用户头像
    private String userHeadPortrait;

    //生日
    private Date birthDay;

    //性别
    private String userSex;

    //用户手机号
    private Long userPhone;

    //密码
    private String userPasswd;

    //注册时间
    private Date createTime;

    //更新时间
    private Date modifyTime;

    //用户等级信息
    private UserExper userExper;

}
