package com.lifuz.self.model.common;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 通用数据传输类
 *
 * 数据传输类
 *
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/8/4 20:33
 */

@Data
@NoArgsConstructor
public class SelfResult<T> {

    //返回时否成功
    private boolean success;

    //返回的成功数据
    private T data;

    //失败返回的信息
    private String error;

    public SelfResult(boolean success,T data) {
        this.success = success;
        this.data = data;
    }

    public SelfResult(boolean success,String error) {
        this.success = success;
        this.error = error;
    }

}
