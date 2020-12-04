package com.uaspbp.rentalmotor.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uaspbp.rentalmotor.Dao.TransaksiDao;

import java.util.List;

public class TransaksiResponse {
    @SerializedName("data")
    @Expose
    private List<TransaksiDao> transaksii = null;

    @SerializedName("message")
    @Expose
    private String message;

    public List<TransaksiDao> getTransaksii() {return transaksii;}

    public void setTransaksii(List<TransaksiDao> transaksii) {this.transaksii = transaksii;}

    public String getMessage() { return message;}

    public void setMessage(String message) {this.message= message;}

}
