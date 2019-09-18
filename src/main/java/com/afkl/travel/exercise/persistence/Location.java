package com.afkl.travel.exercise.persistence;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@Entity
@Table(name = "location")
public class Location {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String code;


    @Enumerated(EnumType.STRING)
    private LocationType type;

    private Double latitude;

    private Double longitude;

    private Long parentId;

    protected Location() {}

    public Location(String code, LocationType type, Double latitude, Double longitude, Long parentId) {
        this.code = code;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", type=" + type +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", parentId=" + parentId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public LocationType getType() {
        return type;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Long getParentId() {
        return parentId;
    }
}
