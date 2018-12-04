package com.example.rina.new_app_help_me.models;

public class Birga {
    private int id;
    private int from;
    private String title;
    private String birga;
    private String sent;

    public Birga(int id, int from, String title, String birga, String sent) {
        this.id = id;
        this.from = from;
        this.title = title;
        this.birga = birga;
        this.sent = sent;
    }

    public int getId() {
        return id;
    }

    public int getFrom() {
        return from;
    }


    public String getTitle() {
        return title;
    }

    public String getBirga() {
        return birga;
    }

    public String getSent() {
        return sent;
    }
}
