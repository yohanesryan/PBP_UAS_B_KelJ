package com.uaspbp.rentalmotor.Dao;
import com.google.gson.annotations.SerializedName;

public class MotorDao {
    @SerializedName("id")
    private String id;

    @SerializedName("nama_motor")
    private String nama_motor;

    @SerializedName("merk")
    private String merk;

    @SerializedName("harga")
    private String harga;

    @SerializedName("imageURL")
    private String imageURL;

    public MotorDao (String id,String nama_motor, String merk, String harga, String imageURL)
    {
        this.id = id;
        this.nama_motor = nama_motor;
        this.merk = merk;
        this.harga = harga;
        this.imageURL = imageURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama_motor() {return nama_motor;}

    public void setNama_motor(String nama_motor) {this.nama_motor = nama_motor;}

    public String getMerk() {return merk;}

    public void setMerk(String merk) {this.merk = merk;}

    public String getHarga() {return harga;}

    public void setHarga(String harga) {this.harga = harga;}

    public String getImageURL() {return imageURL;}

    public void setImageURL(String imageURL) {this.imageURL = imageURL;}

}
