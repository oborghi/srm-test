package com.srm.test.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static java.util.Objects.isNull;


@Entity
@Table(name = "CLIENT")
public class Client {
    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "NAME", nullable = false, length = 12)
    @NotBlank(message = "The name field is required")
    private String name;

    @Column(name = "CREDIT_LIMIT")
    @NotNull(message = "The creditLimit field is required")
    private Float creditLimit;

    @Column(name = "CLIENT_TYPE")
    @NotNull(message = "The clientType field is required")
    @Convert(converter = ClientTypeEnumConverter.class)
    private ClientTypeEnum clientType;


    public Client(String name, Float creditLimit, ClientTypeEnum clientType) {
        this.name = name;
        this.creditLimit = creditLimit;
        this.clientType = clientType;
    }

    protected Client() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Float creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + (isNull(name) ? "" : name) +'\'' +
                ", creditLimit=" + (isNull(creditLimit) ? "" : String.valueOf(creditLimit))  +
                ", clientType='" + (isNull(clientType) ? "" : String.valueOf(clientType)) + '\'' +
                '}';
    }

    public ClientTypeEnum getClientType() {
        return clientType;
    }

    public void setClientType(ClientTypeEnum clientType) {
        this.clientType = clientType;
    }
}