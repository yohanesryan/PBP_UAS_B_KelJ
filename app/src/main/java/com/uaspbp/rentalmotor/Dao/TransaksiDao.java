package com.uaspbp.rentalmotor.Dao;

import com.google.gson.annotations.SerializedName;

public class TransaksiDao {
    @SerializedName("id")
    private String id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("id_penyewa")
    private String id_penyewa;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("pilihan_motor")
    private String pilihan_motor;

    @SerializedName("tgl_sewa")
    private String tgl_sewa;

    @SerializedName("lama_sewa")
    private String lama_sewa;

    public TransaksiDao (String id,String nama, String id_penyewa, String alamat, String pilihan_motor,String tgl_sewa, String lama_sewa)
    {
        this.id = id;
        this.nama = nama;
        this.id_penyewa = id_penyewa;
        this.alamat = alamat;
        this.pilihan_motor = pilihan_motor;
        this.tgl_sewa = tgl_sewa;
        this.lama_sewa = lama_sewa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {return nama;}

    public void setNama(String nama) {this.nama = nama;}

    public String getId_penyewa() {return this.id_penyewa;}

    public void setId_penyewa(String id_penyewa) {this.id_penyewa = id_penyewa;}

    public String getAlamat() {return alamat;}

    public void setAlamat(String alamat) {this.alamat = alamat;}

    public String getPilihan_motor() {return pilihan_motor;}

    public void setPilihan_motor(String pilihan_motor) {this.pilihan_motor = pilihan_motor;}

    public String getTgl_sewa() {return tgl_sewa;}

    public void setTgl_sewa(String tgl_sewa) {this.tgl_sewa = tgl_sewa;}

    public String getLama_sewa() {return lama_sewa;}

    public void setLama_sewa(String lama_sewa) {this.lama_sewa= lama_sewa;}

}
