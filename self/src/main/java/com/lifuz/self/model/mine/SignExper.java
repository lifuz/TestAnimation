package com.lifuz.self.model.mine;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户签到信息表
 * <p>
 * 实体类
 *
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/7/28 22:16
 */

@Data
@NoArgsConstructor
public class SignExper {

    //用户id
    private Long userId;

    //每次签到可以获取到的经验值
    private Long experValue;

    //当前签到周期的天数
    private Integer cycleDayNumber;

    //签到中断次数
    private Long interruptTimes;

    //首次开始时间
    private Date createTime;

    //修改时间
    private Date modifyTime;

    //本次签到开始时间
    private Date startTime;

    //总签到次数
    private Long signNum;

}
