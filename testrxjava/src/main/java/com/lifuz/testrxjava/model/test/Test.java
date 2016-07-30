package com.lifuz.testrxjava.model.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 测试实体类
 *
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/7/30 11:58
 */

@Data
@NoArgsConstructor
public class Test {

    //是否开启秒杀
    private boolean exposed;

    //一种加密措施
    private String md5;

    //id
    private Long seckillId;

    //系统当前时间
    private Long now;

    //开始时间
    private Long start;

    //结束时间
    private Long end;

}
