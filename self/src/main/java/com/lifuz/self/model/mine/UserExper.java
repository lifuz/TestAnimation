package com.lifuz.self.model.mine;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户经验等级表
 *
 * 实体类
 *
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/7/28 22:04
 */

@Data
@NoArgsConstructor
public class UserExper {

    //用户id
    private Long userId;

    //用户经验值
    private Long experValue;

    //用户等级
    private String experLevel;

    //当前等级最大经验值
    private Long experMaxValue;

    //等级图片
    private String experPic;

    //修改时间
    private Date modifyTime;

}
