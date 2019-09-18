package com.afkl.travel.exercise.persistence;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationDto {

    @JsonProperty("code")
    private String code;

    @JsonProperty("type")
    private String type;

    @JsonProperty("latitude")
    private Double latitude;

    @JsonProperty("longitude")
    private Double longitude;

    public LocationDto() {}


    public static LocationDto fromEntity(Location location){
        LocationDto dto = new LocationDto();

        dto.setCode(location.getCode());
        dto.setType(location.getType().name());
        dto.setLatitude(location.getLatitude());
        dto.setLongitude(location.getLongitude());

        return dto;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
