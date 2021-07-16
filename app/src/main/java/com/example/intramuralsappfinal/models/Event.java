package com.example.intramuralsappfinal.models;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

public class Event {
    private UUID eventId;
    private ZonedDateTime dateTime;
    private Location address;

    public Event() { }

    public Event(ZonedDateTime dateTime, Location address) {
        this.eventId = UUID.randomUUID();
        this.dateTime = dateTime;
        this.address = address;
    }

    public UUID getEventId() {
        return this.eventId;
    }

    public ZonedDateTime getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Location getAddress() {
        return this.address;
    }

    public void setAddress(Location address) {
        this.address = address;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Event)) {
            return false;
        }
        Event event = (Event) o;
        return Objects.equals(dateTime, event.dateTime) && Objects.equals(address, event.address);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(dateTime, address);
    }
}