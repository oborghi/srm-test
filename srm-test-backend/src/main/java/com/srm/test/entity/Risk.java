package com.srm.test.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

import static java.util.Objects.isNull;

@Entity
@Table(name = "RISK")
public class Risk {
    @Column(name = "ID")
    @Id
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
        return "Risk{" +
                "id=" + id +
                ", description='" + (isNull(description) ? "" : description) +'\'' +
                '}';
    }
}
