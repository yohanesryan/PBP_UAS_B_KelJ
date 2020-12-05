package com.uaspbp.rentalmotor.Api;

import com.uaspbp.rentalmotor.Response.MotorResponse;
import com.uaspbp.rentalmotor.Response.MotorResponseObject;
import com.uaspbp.rentalmotor.Response.TransaksiResponse;
import com.uaspbp.rentalmotor.Response.UserResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    //API User
    @GET("user")
    Call<UserResponse> getUser(@Query("data")String data);

    @GET("user/{email}")
    Call<UserResponse> getUserByEmail(@Path("email")String email,
                                      @Query("data")String data);

    @POST("registrasi")
    @FormUrlEncoded
    Call<UserResponse> createUser(@Field("name")String nama,
                                  @Field("email")String email,
                                  @Field("password")String password,
                                  @Field("alamat")String alamat,
                                  @Field("noTelp")String noTelp,
                                  @Field("image")String image);

    @POST("user/{id}")
    @FormUrlEncoded
    Call<UserResponse> updateUser(@Path("id")String id,
                                  @Field("name")String nama,
                                  @Field("email")String email,
                                  @Field("alamat")String alamat,
                                  @Field("noTelp")String noTelp,
                                  @Field("image")String image);

    //API Motor

    @GET("motor")
    Call<MotorResponse> getAllMotor(@Query("data")String data);

    @GET("motor/{id}")
    Call<MotorResponseObject> getMotorById(@Path("id")String id,
                                           @Query("data")String data);

    @POST("motor")
    @FormUrlEncoded
    Call<MotorResponseObject> createMotor(@Field("nama_motor")String nama_motor,
                                          @Field("harga")String harga);

    @POST("motor/{id}")
    @FormUrlEncoded
    Call<MotorResponseObject> updateMotor(@Path("id")String id,
                                          @Field("nama_motor")String nama_motor,
                                          @Field("harga")String harga);

    @DELETE("motor/{id}")
    Call<MotorResponseObject> deleteMotor(@Path("id")String id);

    //API Transaksi

    @GET("transaksi")
    Call<TransaksiResponse> getAllTransaksi(@Query("data")String data);

    @GET("transaksi/{id_penyewa}")
    Call<TransaksiResponse> getTransaksiByIdPenyewa(@Path("id_penyewa")String id_penyewa,
                                                    @Query("data")String data);

    @POST("transaksi")
    @FormUrlEncoded
    Call<TransaksiResponse> createTransaksi(@Field("nama")String nama_penyewa, @Field("id_penyewa")String id,
                                            @Field("alamat")String alamat, @Field("pilihan_motor")String pilihan,
                                            @Field("tgl_sewa")String tglSewa, @Field("lama_sewa")String lamaSewa);

    @POST("transaksi/{id}")
    @FormUrlEncoded
    Call<TransaksiResponse> updateTransaksi(@Path("id")String id,
                                            @Field("nama_penyewa")String nama_penyewa, @Field("id_penyewa")String id_penyewa,
                                            @Field("alamat")String alamat, @Field("pilihan_motor")String pilihan,
                                            @Field("tgl_sewa")String tglSewa, @Field("lama_sewa")String lamaSewa);

    @DELETE("transaksi/{id}")
    Call<TransaksiResponse> deleteTransaksi(@Path("id")String id);

    @POST("login")
    @FormUrlEncoded
    Call<UserResponse> loginUser(@Field("email") String email,
                                 @Field("password") String password);
}
