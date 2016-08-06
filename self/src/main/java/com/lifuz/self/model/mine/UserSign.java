package com.lifuz.self.model.mine;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户签到表
 *
 * 实体类
 *
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/7/28 22:12
 */

@Data
@NoArgsConstructor
public class UserSign {

    //签到id
    private Long signId;

    //签到备注
    private String signMemo;

    //获取的经验值
    private Long signExper;

    //用户id
    private Long userId;

    //签到时间
    private Date createTime;

}
