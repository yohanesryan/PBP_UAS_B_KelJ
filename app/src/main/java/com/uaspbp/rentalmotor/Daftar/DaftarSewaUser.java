package com.uaspbp.rentalmotor.Daftar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.uaspbp.rentalmotor.Adapter.SewaUserRecyclerAdapter;
import com.uaspbp.rentalmotor.Api.ApiClient;
import com.uaspbp.rentalmotor.Api.ApiInterface;
import com.uaspbp.rentalmotor.Create.CreateSewaUser;
import com.uaspbp.rentalmotor.Dao.TransaksiDao;
import com.uaspbp.rentalmotor.R;
import com.uaspbp.rentalmotor.Response.TransaksiResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarSewaUser extends AppCompatActivity {

    private ImageButton ibBack, ibAdd;
    private RecyclerView recyclerView;
    private SewaUserRecyclerAdapter recyclerAdapter;
    private List<TransaksiDao> transaksi = new ArrayList<>();
    private SearchView searchView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_sewa_user);

        ibBack = findViewById(R.id.ibBack);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ibAdd = findViewById(R.id.ibAdd);
        ibAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateSewaUser.class);
                startActivity(intent);
            }
        });

        searchView = findViewById(R.id.searchUser);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setRefreshing(true);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadTransaksi();
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
            if(requestCode == RESULT_OK) {
                finish();
                startActivity(getIntent());
            }
        }
    }

    private void loadTransaksi() {
        ApiInterface apiGetTransaksi = ApiClient.getClient().create(ApiInterface.class);
        Call<TransaksiResponse> callGetTransaksi = apiGetTransaksi.getAllTransaksi("data");

        callGetTransaksi.enqueue(new Callback<TransaksiResponse>() {
            @Override
            public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
                generateDataList((List<TransaksiDao>) response.body().getTransaksi());
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<TransaksiResponse> call, Throwable t) {
                Toast.makeText(DaftarSewaUser.this, "Kesalahan Jaringan", Toast.LENGTH_LONG).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void generateDataList(List<TransaksiDao> transaksiList) {
        recyclerView = findViewById(R.id.TransaksiRecyclerView);
        recyclerAdapter = new SewaUserRecyclerAdapter(transaksiList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);

        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                recyclerAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                recyclerAdapter.getFilter().filter(s);
                return false;
            }
        });
    }
}
