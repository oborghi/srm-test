package com.srm.test.entity;

public enum ClientTypeEnum {
    A(1), B(2), C(3);

    private Integer code;

    ClientTypeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static ClientTypeEnum fromCode(Integer code) {
        for (ClientTypeEnum item :ClientTypeEnum.values()){
            if (item.getCode().equals(code)){
                return item;
            }
        }
        throw new UnsupportedOperationException(
                "The code " + code + " is not supported!");
    }
}