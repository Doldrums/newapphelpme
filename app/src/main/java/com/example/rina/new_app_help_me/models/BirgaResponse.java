package com.example.rina.new_app_help_me.models;

public class BirgaResponse {
    private boolean error;
    private String birga;

    public BirgaResponse() {

    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getBirga() {
        return birga;
    }

    public void setBirga(String birga) {
        this.birga = birga;
    }
}