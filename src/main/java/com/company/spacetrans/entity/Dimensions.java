package com.company.spacetrans.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@JmixEntity(name = "st_Dimensions")
@Embeddable
public class Dimensions {
    @Column(name = "LENGTH")
    private Double length;

    @Column(name = "WIDTH")
    private Double width;

    @Column(name = "HEIGTH")
    private Double heigth;

    public Double getHeigth() {
        return heigth;
    }

    public void setHeigth(Double heigth) {
        this.heigth = heigth;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }
}