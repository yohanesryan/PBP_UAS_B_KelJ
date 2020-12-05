package com.uaspbp.rentalmotor.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uaspbp.rentalmotor.Dao.MotorDao;
import com.uaspbp.rentalmotor.Fragment.DetailMotorFragmentUser;
import com.uaspbp.rentalmotor.R;

import java.util.List;
import java.util.stream.Collectors;

public class MotorRecyclerAdapterUser extends RecyclerView.Adapter <MotorRecyclerAdapterUser.MotorViewHolder> implements Filterable {

    private List<MotorDao> dataList;
    private List<MotorDao> filteredDataList;
    private Context context;

    public MotorRecyclerAdapterUser(List<MotorDao> dataList, Context context) {
        this.dataList = dataList;
        this.filteredDataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public MotorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_adapter_motor_user, parent, false);
        return new MotorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MotorViewHolder holder, int position) {
        final MotorDao mtr = filteredDataList.get(position);
        holder.twNamaMotor.setText(mtr.getNama_motor());
        holder.twHargaMotor.setText(mtr.getHarga());

        holder.mParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
                DetailMotorFragmentUser dialog = new DetailMotorFragmentUser();
                dialog.show(manager, "dialog");

                Bundle args = new Bundle();
                args.putString("id", mtr.getId());
                dialog.setArguments(args);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredDataList.size();
    }

    static class MotorViewHolder extends RecyclerView.ViewHolder {
        private TextView twNamaMotor, twHargaMotor;
        private LinearLayout mParent;

        public MotorViewHolder(@NonNull View itemView) {
            super(itemView);
            twNamaMotor = itemView.findViewById(R.id.twNamaMotor);
            twHargaMotor = itemView.findViewById(R.id.twHargaMotor);
            mParent = itemView.findViewById(R.id.linearLayout);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(final CharSequence charSequence) {

                filteredDataList = charSequence == null ? dataList :
                        dataList.stream().filter(data -> data.getNama_motor().toLowerCase().contains(charSequence.toString().toLowerCase())).collect(Collectors.toList());

                FilterResults results = new FilterResults();
                results.values = filteredDataList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredDataList = (List<MotorDao>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
