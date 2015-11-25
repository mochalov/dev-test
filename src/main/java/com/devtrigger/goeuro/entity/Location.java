package com.devtrigger.goeuro.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * A location obtained from the Location JSON API
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

    private int id;
    private String name;
    private String fullName;
    private String type;
    private String country;
    private GeoPosition geoPosition;

    public Location() {
    }

    public Location(Builder builder) {
        id = builder.id;
        name = builder.name;
        fullName = builder.fullName;
        type = builder.type;
        country = builder.country;
        geoPosition = builder.geoPosition;
    }

    public int getId() {
        return id;
    }

    @JsonProperty("_id")
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    @JsonProperty("geo_position")
    public void setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(id, location.id) &&
                Objects.equals(name, location.name) &&
                Objects.equals(fullName, location.fullName) &&
                Objects.equals(type, location.type) &&
                Objects.equals(country, location.country) &&
                Objects.equals(geoPosition, location.geoPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fullName, type, country, geoPosition);
    }

    public static class Builder {
        private int id;
        private String name;
        private String fullName;
        private String type;
        private String country;
        private GeoPosition geoPosition;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder geoPosition(double latitude, double longitude) {
            this.geoPosition = new GeoPosition(latitude, longitude);
            return this;
        }

        public Location build() {
            return new Location(this);
        }
    }
}
