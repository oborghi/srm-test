package com.srm.test.entity;

import javax.persistence.AttributeConverter;

public class RiskEnumConverter implements AttributeConverter<RiskEnum, Integer> {

    public Integer convertToDatabaseColumn(RiskEnum value) {
        if (value == null) {
            return null;
        }

        return value.getCode();
    }

    public RiskEnum convertToEntityAttribute(Integer value) {
        if (value == null) {
            return null;
        }

        return RiskEnum.fromCode(value);
    }
}

