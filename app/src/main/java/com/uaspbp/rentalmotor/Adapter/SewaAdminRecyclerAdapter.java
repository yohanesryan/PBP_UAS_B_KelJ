package com.uaspbp.rentalmotor.Adapter;

//import android.content.Context;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Filter;
//import android.widget.Filterable;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.FragmentManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//
//import com.uaspbp.rentalmotor.Dao.TransaksiDao;
//import com.uaspbp.rentalmotor.Fragment.DetailSewaFragmentAdmin;
//import com.uaspbp.rentalmotor.R;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class SewaAdminRecyclerAdapter extends RecyclerView.Adapter <SewaAdminRecyclerAdapter.MotorViewHolder> implements Filterable {
//
//    private List<TransaksiDao> dataList;
//    private List<TransaksiDao> filteredDataList;
//    private Context context;
//
//    public SewaAdminRecyclerAdapter(List<TransaksiDao> dataList, Context context) {
//        this.dataList = dataList;
//        this.filteredDataList = dataList;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public SewaAdminRecyclerAdapter.MotorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View view = layoutInflater.inflate(R.layout.recycler_adapter_sewa_admin, parent, false);
//        return new SewaAdminRecyclerAdapter.MotorViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull SewaAdminRecyclerAdapter.MotoriewHolder holder, int position) {
//        final TransaksiDao transaksi = filteredDataList.get(position);
//        holder.twNamaPenyewa.setText(transaksi.getNama());
//        holder.twIdPenyewa.setText(transaksi.getId_penyewa());
//        holder.twAlamatPenyewa.setText(transaksi.getAlamat());
//        holder.twPilihanMotor.setText(transaksi.getPilihan_motor());
//        holder.twTanggalSewa.setText(transaksi.getTglSewa());
//        holder.twLamaSewa.setText(transaksi.getLamaSewa());
//
//        holder.mParent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
//                DetailSewaFragmentAdmin dialog = new DetailSewaFragmentAdmin();
//                dialog.show(manager, "dialog");
//
//                Bundle args = new Bundle();
//                args.putString("id", transaksi.getId_penyewa());
//                dialog.setArguments(args);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return filteredDataList.size();
//    }
//
//    static class MotorViewHolder extends RecyclerView.ViewHolder {
//        private TextView twNamaPenyewa, twIdPenyewa, twAlamatPenyewa, twPilihanMotor, twTanggalSewa, twLamaSewa;
//        private LinearLayout mParent;
//
//        public MotorViewHolder(@NonNull View itemView) {
//            super(itemView);
//            twNamaPenyewa = itemView.findViewById(R.id.twNamaMotor);
//            twIdPenyewa = itemView.findViewById(R.id.twHargaMotor);
//            twAlamatPenyewa = itemView.findViewById(R.id.twAlamatPenyewa);
//            twPilihanMotor = itemView.findViewById(R.id.etPilihanMotor);
//            twTanggalSewa = itemView.findViewById(R.id.etTglSewa);
//            twLamaSewa = itemView.findViewById(R.id.etLamaSewa);
//            mParent = itemView.findViewById(R.id.linearLayout);
//        }
//    }
//
//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(final CharSequence charSequence) {
//
//                filteredDataList = charSequence == null ? dataList :
//                        dataList.stream().filter(data -> data.getNama().toLowerCase().contains(charSequence.toString().toLowerCase())).collect(Collectors.toList());
//
//                FilterResults results = new FilterResults();
//                results.values = filteredDataList;
//                return results;
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                filteredDataList = (List<TransaksiDao>) filterResults.values;
//                notifyDataSetChanged();
//            }
//        };
//    }
//}
