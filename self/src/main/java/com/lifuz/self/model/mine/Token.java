package com.lifuz.self.model.mine;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * token
 *
 * 实体类
 *
 * 作者：李富
 * 邮箱：lifuzz@163.com
 * 时间：2016/8/6 13:54
 */

@Data
@NoArgsConstructor
public class Token {

    //token字符串
    private String token;

    //过期时间
    private Long expire;

}
