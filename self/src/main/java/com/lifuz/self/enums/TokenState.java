package com.lifuz.self.enums;

/**
 *
 * token 状态说明
 *
 * 枚举类
 *
 * 作者：李富
 * 邮箱：lifuzz@163.com
 * 时间：2016/8/4 16:35
 */
public enum TokenState {

    EXPIRED(101,"token已过期"),
    INVALID(102,"token不合法"),
    VALID(103,"有效的token");

    private int state;
    private String stateInfo;

    TokenState(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static TokenState stateOf(int index) {

        for (TokenState state:values()) {
            if (state.getState() == index) {
                return state;
            }
        }

        return null;
    }
}
