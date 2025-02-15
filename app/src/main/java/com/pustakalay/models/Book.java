package com.pustakalay.models;

public class Book {
    private String title;
    private String author;
    private String status;
    private int coverResId;

    public Book(String title, String author, String status, int coverResId) {
        this.title = title;
        this.author = author;
        this.status = status;
        this.coverResId = coverResId;
    }

    // Getters and Setters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getStatus() { return status; }
    public int getCoverResId() { return coverResId; }
    
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setStatus(String status) { this.status = status; }
    public void setCoverResId(int coverResId) { this.coverResId = coverResId; }
} 