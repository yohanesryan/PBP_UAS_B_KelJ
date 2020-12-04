package com.uaspbp.rentalmotor.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uaspbp.rentalmotor.Dao.MotorDao;

public class MotorResponseObject {
    @SerializedName("data")
    @Expose
    private MotorDao motor = null;

    @SerializedName("message")
    @Expose
    private String message;

    public MotorDao getMotor() {
        return motor;
    }

    public void setMotor(MotorDao motor) {
        this.motor = motor;
    }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }
}
