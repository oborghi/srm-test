package com.srm.test.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static java.util.Objects.isNull;

@Entity
@Table(name = "CLIENT_TYPE")
public class ClientType {
    @Column(name = "ID")
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "DESCRIPTION", nullable = false)
    @NotBlank(message = "The description field is required")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ClientType{" +
                "id=" + id +
                ", description='" + (isNull(description) ? "" : description) +'\'' +
                '}';
    }
}
