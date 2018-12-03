package com.srm.test.entity;

import javax.persistence.AttributeConverter;

public class ClientTypeEnumConverter implements AttributeConverter<ClientTypeEnum, Integer> {

    public Integer convertToDatabaseColumn(ClientTypeEnum value) {
        if (value == null) {
            return null;
        }

        return value.getCode();
    }

    public ClientTypeEnum convertToEntityAttribute(Integer value) {
        if (value == null) {
            return null;
        }

        return ClientTypeEnum.fromCode(value);
    }
}

