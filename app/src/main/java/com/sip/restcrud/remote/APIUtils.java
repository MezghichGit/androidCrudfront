package com.sip.restcrud.remote;

public class APIUtils {

    private APIUtils(){
    };

    public static final String API_URL = "http://192.168.43.165:85/users/";

    public static UserService getUserService(){
        return RetrofitClient.getClient(API_URL).create(UserService.class);
    }

}