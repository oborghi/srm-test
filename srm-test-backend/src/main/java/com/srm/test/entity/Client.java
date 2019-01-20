package com.srm.test.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
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

    @Column(name = "RISK")
    @NotNull(message = "The risk field is required")
    @Convert(converter = RiskEnumConverter.class)
    private RiskEnum risk;

    @Column(name = "TAX")
    private Float tax;


    public Client(String name, Float creditLimit, RiskEnum risk) {
        this.name = name;
        this.creditLimit = creditLimit;
        this.risk = risk;
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
                ", name='" + (isNull(name) ? "" : name) + '\'' +
                ", creditLimit=" + (isNull(creditLimit) ? "" : String.valueOf(creditLimit))  +
                ", risk='" + (isNull(risk) ? "" : String.valueOf(risk)) + '\'' +
                '}';
    }

    public RiskEnum getRisk() {
        return risk;
    }

    public void setRisk(RiskEnum risk) {
        this.risk = risk;
    }

    public Float getTax() {
        return tax;
    }

    public void setTax(Float tax) {
        this.tax = tax;
    }
}