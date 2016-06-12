package com.jfsiot.mju.ssangcarpool.model.unique;

import com.jfsiot.mju.ssangcarpool.model.data.UserData;

/**
 * Created by SSS on 2016-05-24.
 */
public class User {
    private static User instance;
    public static synchronized User getInstance(){
        return User.instance == null ? instance = new User() : instance;
    }

    private UserData userData;

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
