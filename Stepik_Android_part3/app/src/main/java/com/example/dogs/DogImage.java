package com.example.dogs;

public class DogImage {

    private String name;
    private String status;


    public DogImage(String name, String status) {
        this.name = name;
        this.status = status;
    }


    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }
}
