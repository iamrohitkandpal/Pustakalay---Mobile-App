package com.pustakalay.models;

public class RoomSlot {
    private String time;
    private String status;

    public RoomSlot(String time, String status) {
        this.time = time;
        this.status = status;
    }

    // Getters and Setters
    public String getTime() { return time; }
    public String getStatus() { return status; }
    
    public void setTime(String time) { this.time = time; }
    public void setStatus(String status) { this.status = status; }
} 