package com.sip.restcrud.remote;

public class APIUtils {

    private APIUtils(){
    };

    public static final String API_URL = "http://192.168.43.165:86/api/";

    public static UserService getUserService(){
        return RetrofitClient.getClient(API_URL).create(UserService.class);
    }

}