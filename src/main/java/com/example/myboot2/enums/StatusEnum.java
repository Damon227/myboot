package com.example.myboot2.enums;

/**
 * @author damon
 * @date 2020-08-13
 */
public enum  StatusEnum {
    /**
     * 删除
     */
    DELETED(-1, "删除"),

    /**
     * 冻结
     */
    FREEZED(0, "冻结"),

    /**
     * 正常
     */
    NORMAL(1, "正常"),

    /**
     * 过期
     */
    EXPIRED(2, "过期");

    /**
     * 枚举值
     */
    private final int code;

    /**
     * 枚举描述
     */
    private final String desc;

    StatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
