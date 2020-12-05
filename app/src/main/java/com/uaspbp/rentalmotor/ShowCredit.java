package com.uaspbp.rentalmotor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.uaspbp.rentalmotor.databinding.ActivityCreditBinding;

import java.util.ArrayList;

public class ShowCredit extends AppCompatActivity {
    ArrayList<Credit> ListCredit;
    ActivityCreditBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_credit);

        ListCredit = new CreditList().CREDIT;
        binding.setCredit(ListCredit.get(0));
    }
}
