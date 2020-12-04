package com.uaspbp.rentalmotor.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uaspbp.rentalmotor.Dao.UserDao;

public class UserResponse {
    @SerializedName("data")
    @Expose
    private UserDao user = null;

    @SerializedName("message")
    @Expose
    private String message;

    public UserDao getUser() {
        return user;
    }

    public void setUser(UserDao user) {
        this.user = user;
    }

    public String getMessage() { return message;}

    public void setMessage(String message) {this.message= message;}

}
