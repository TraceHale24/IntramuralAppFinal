package com.example.intramuralsappfinal.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;

public class Location {
    private String street;
    private String city;
    private int zip;

    public Location() { }

    public Location(String street, String city, int zip) {
        this.street = street;
        this.city = city;
        this.zip = zip;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip() {
        return this.zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Location)) {
            return false;
        }
        Location location = (Location) o;
        return street.equals(location.street)
                && city.equals(location.city)
                && zip == location.zip;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(street, city, zip);
    }
}
