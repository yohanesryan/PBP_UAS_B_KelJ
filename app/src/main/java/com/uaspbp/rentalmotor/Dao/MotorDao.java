package com.uaspbp.rentalmotor.Dao;
import com.google.gson.annotations.SerializedName;

public class MotorDao {
    @SerializedName("id")
    private String id;

    @SerializedName("nama_motor")
    private String nama_motor;

    @SerializedName("harga")
    private String harga;

    public MotorDao (String id,String nama_motor, String harga)
    {
        this.id = id;
        this.nama_motor = nama_motor;
        this.harga = harga;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama_motor() {return nama_motor;}

    public void setNama_motor(String nama_motor) {this.nama_motor = nama_motor;}

    public String getHarga() {return harga;}

    public void setHarga(String harga) {this.harga = harga;}

}
