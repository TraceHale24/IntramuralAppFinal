package com.example.intramuralsappfinal.models;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

public class Event {
    private String location;
    private LocalDateTime dateTime;

    public Event() {
    }

    public Event(String location, LocalDateTime dateTime) {
        this.location = location;
        this.dateTime = dateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Event{" +
                ", location='" + location + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}