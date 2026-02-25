package com.Auth.auth_app.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException (String message){

        //Will take the message and pass it to the parent, i.e RuntimeException
        super(message);
    }

    //if no value or message is passed it will automatically call the default message
    public ResourceNotFoundException(){
        super("Resource not found!!");
    }

}
