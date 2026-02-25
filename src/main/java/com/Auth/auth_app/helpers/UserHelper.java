package com.Auth.auth_app.helpers;

import java.util.UUID;


//While we request the JSON , we get the UUID in string, but it is stored in form of UUID in database
public class UserHelper {
    public static UUID parseUUID(String uuid){
        return UUID.fromString(uuid);
    }
}
