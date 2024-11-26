package com.example.dogs;

public class DogImage {

    private String message;
    private String status;


    public DogImage(String name, String status) {
        this.message = name;
        this.status = status;
    }


    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "DogImage{" +
                "name='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
