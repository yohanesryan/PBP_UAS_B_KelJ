package com.uaspbp.rentalmotor.Daftar;//
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageButton;
//import android.widget.SearchView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.DefaultItemAnimator;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
//
//import com.facebook.shimmer.ShimmerFrameLayout;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class DaftarSewaAdmin extends AppCompatActivity {
//
//    private ImageButton ibBack;
//    private RecyclerView recyclerView;
//    private SewaAdminRecyclerAdapter recyclerAdapter;
//    private List<TransaksiDao> transaksi = new ArrayList<>();
//    private SearchView searchView;
//    private SwipeRefreshLayout swipeRefreshLayout;
//    private ShimmerFrameLayout shimmerFrameLayout;
//    private String sIdPenyewa;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.daftar_motor_admin);
//        shimmerFrameLayout = findViewById(R.id.shimmerLayout);
//        shimmerFrameLayout.startShimmer();
//
//        ibBack = findViewById(R.id.ibBack);
//        ibBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
//
//        searchView = findViewById(R.id.searchUser);
//        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
//        swipeRefreshLayout.setRefreshing(true);
//
//        loadTransaksi();
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                loadTransaksi();
//            }
//        });
//
//    }
//
//    protected void onActivityResult(int requestCode, int resultCode,
//                                    Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 0){
//            if(requestCode == RESULT_OK) {
//                finish();
//                startActivity(getIntent());
//            }
//        }
//    }
//
//    private void loadTransaksi() {
//        ApiInterface apiGetTransaksi = ApiClient.getClient().create(ApiInterface.class);
//        Call<TransaksiResponse> callGetTransaksi = apiGetTransaksi.getAllTransaksi("data");
//
//        callGetTransaksi.enqueue(new Callback<TransaksiResponse>() {
//            @Override
//            public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
//                generateDataList(response.body().getTransaksis());
//                swipeRefreshLayout.setRefreshing(false);
//            }
//
//            @Override
//            public void onFailure(Call<TransaksiResponse> call, Throwable t) {
//                Toast.makeText(DaftarReservasiAdmin.this, "Kesalahan Jaringan", Toast.LENGTH_LONG).show();
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });
//    }
//
//    private void generateDataList(TransaksiDao transaksiList) {
//        recyclerView = findViewById(R.id.TransaksiAdminRecyclerView);
//        recyclerAdapter = new SewaAdminRecyclerAdapter(transaksiList, this);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(recyclerAdapter);
//
//        shimmerFrameLayout.stopShimmer();
//        shimmerFrameLayout.setVisibility(View.GONE);
//
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                recyclerAdapter.getFilter().filter(s);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                recyclerAdapter.getFilter().filter(s);
//                return false;
//            }
//        });
//    }
//}
