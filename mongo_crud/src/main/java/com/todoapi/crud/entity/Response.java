package com.todoapi.crud.entity;

public class Response {
    private String message;
    private String status;

    public Response(String message, String status) {
        this.message = message;
        this.status = status;
    }
}
