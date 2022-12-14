package com.company.spacetrans.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.UUID;

@JmixEntity
@Table(name = "ST_MOON", indexes = {
        @Index(name = "IDX_ST_MOON_PLANET", columnList = "PLANET_ID"),
        @Index(name = "IDX_ST_MOON_ATMOSPHERE", columnList = "ATMOSPHERE_ID")
})
@Entity(name = "st_Moon")
public class Moon {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "PLANET_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Planet planet;

    @JoinColumn(name = "ATMOSPHERE_ID")
    @Composition
    @OneToOne(fetch = FetchType.LAZY)
    private Atmosphere atmosphere;

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}