package com.uaspbp.rentalmotor.UnitTest;

import com.uaspbp.rentalmotor.Dao.UserDao;


public interface LoginView {
    String getEmail();
    void showEmailError(String message);
    String getPassword();
    void showPasswordError(String message);
    void startMainActivity();
    void startUserProfileActivity(UserDao user);
    void showLoginError(String message);
    void showErrorResponse(String message);
}
