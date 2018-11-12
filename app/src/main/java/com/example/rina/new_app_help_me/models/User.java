package com.example.rina.new_app_help_me.models;
public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private String gender;
    private String type;

    public User(String name, String email, String password, String gender, String type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.type = type;
    }

    public User(int id, String name, String email, String gender, String type){
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.type = type;
    }

    public User(int id, String name, String email, String password, String gender, String type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getType(){ return type;}
}