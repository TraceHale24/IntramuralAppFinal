package com.example.intramuralsappfinal.models;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

public class Event implements Serializable {
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

    public String formatTime(int time) {
        if(time > 12) {
            return String.valueOf(time - 12);
        }
        return String.valueOf(time);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public String toString() {
        String pOrA = " PM";
        if(dateTime.getHour() < 12) {
           pOrA = " AM";
        }

        String minute = "00";
        if(!String.valueOf(dateTime.getMinute()).equals("0")) {
            minute = String.valueOf(dateTime.getMinute());
        }
        return "Time: " + formatTime(dateTime.getHour()) + ":" +minute + pOrA + "\n" +
                "Date:" + dateTime.getMonthValue() + "/" + dateTime.getDayOfMonth() + "\n" +
                "Location: " + location;

    }
}