package com.lifuz.self.util;

import com.auth0.jwt.JWTExpiredException;
import com.auth0.jwt.JWTVerifier;
import com.lifuz.self.enums.TokenState;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/8/28 11:45
 */
public class TokenUtils {

    public static final String secret = "ldfeejke3#0*899:#3ldljjf@@##$%^%fff";

    /**
     * 检验 token 是否合法，返回 map 集合，集合中包含 state 状态码 data鉴权成功后从 token中提取的数据
     * 该方法在过滤器中调用，每次请求API都调用，除了 index.html 与登录注册接口
     *
     * @param token token 字符串
     * @return
     */
    public static Map<String, Object> validToken(String token) {

        Map<String, Object> claims = null;

        try {
            JWTVerifier verifier = new JWTVerifier(secret);

            claims = verifier.verify(token);

            claims.put("state", TokenState.stateOf(103));


        }catch (JWTExpiredException e){
            claims = new HashMap<>();
            claims.put("state", TokenState.stateOf(101));
        }
        catch (Exception e) {
            e.printStackTrace();

//            logger.error(e.getMessage(), e);

            claims = new HashMap<>();
            claims.put("state", TokenState.stateOf(102));
        }


        return claims;

    }
}
