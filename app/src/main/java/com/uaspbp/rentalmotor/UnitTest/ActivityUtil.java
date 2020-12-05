package com.uaspbp.rentalmotor.UnitTest;

import android.content.Context;
import android.content.Intent;

import com.uaspbp.rentalmotor.Dao.UserDao;
import com.uaspbp.rentalmotor.MainActivity;
import com.uaspbp.rentalmotor.Profile.ProfileUser;


public class ActivityUtil {

    private Context context;
    public ActivityUtil(Context context) {
        this.context = context;
    }
    public void startMainActivity() {
        context.startActivity(new Intent(context, MainActivity.class));
    }
    public void startUserProfile(UserDao user) {
        Intent i = new Intent(context, ProfileUser.class);
        i.putExtra("id",user.getId());
//        i.putExtra("name",user.getNama());
//        i.putExtra("alamat",user.getAlamat());
//        i.putExtra("noTelp",user.getNoTelp());
//        i.putExtra("image",user.getImage());
        context.startActivity(i);
    }

}
