package com.lifuz.self.enums;

/**
 *
 * 我的模块的枚举
 *
 * 枚举类
 *
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/8/6 7:44
 */
public enum MineState {

    REGISTER_SUCCESS(201,"注册成功");

    private int state;
    private String stateInfo;

    MineState(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static MineState stateOf(int index) {
        for (MineState state:values()){
            if (index == state.getState()) {
                return state;
            }
        }

        return null;
    }
}
