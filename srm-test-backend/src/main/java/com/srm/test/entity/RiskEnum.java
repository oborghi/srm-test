package com.srm.test.entity;

public enum RiskEnum {
    A(1), B(2), C(3);

    private Integer code;

    RiskEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static RiskEnum fromCode(Integer code) {
        for (RiskEnum item : RiskEnum.values()){
            if (item.getCode().equals(code)){
                return item;
            }
        }
        throw new UnsupportedOperationException(
                "The code " + code + " is not supported!");
    }
}