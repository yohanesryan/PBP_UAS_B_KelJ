package com.uaspbp.rentalmotor.UnitTest;

import com.uaspbp.rentalmotor.Dao.UserDao;

public interface LoginCallback {
    void onSuccess(boolean value, UserDao user);
    void onError();
}
