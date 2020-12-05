package com.uaspbp.rentalmotor.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uaspbp.rentalmotor.Dao.TransaksiDao;

import java.util.List;

public class TransaksiResponse {
    @SerializedName("data")
    @Expose
    private List<TransaksiDao> transaksi = null;

    @SerializedName("message")
    @Expose
    private String message;

    public List<TransaksiDao> getTransaksi() {return transaksi;}

    public void setTransaksi(List<TransaksiDao> transaksi) {this.transaksi = transaksi;}

    public String getMessage() { return message;}

    public void setMessage(String message) {this.message= message;}

}
