package com.lifuz.self.model.mine;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * qq 登录获取用户详细信息
 *
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/8/13 10:23
 */
@Data
@NoArgsConstructor
public class QQUserInfo {

    private String nickname;

    private String figureurl_qq_2;

    private String gender;

}
