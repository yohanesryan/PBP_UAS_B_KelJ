package com.uaspbp.rentalmotor.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uaspbp.rentalmotor.Dao.MotorDao;

import java.util.List;

public class MotorResponse {

    @SerializedName("data")
    @Expose
    private List<MotorDao> motor = null;

    @SerializedName("message")
    @Expose
    private String message;

    public List<MotorDao> getMotor() { return motor; }

    public void setMotor(List<MotorDao> motor) { this.motor = motor; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }
}
