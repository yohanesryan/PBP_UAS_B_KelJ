package com.uaspbp.rentalmotor;

import java.util.ArrayList;

public class CreditList{
    public ArrayList<com.uaspbp.rentalmotor.Credit> CREDIT;
    public CreditList(){
        CREDIT = new ArrayList();
        CREDIT.add(Data);
    }
    public static final com.uaspbp.rentalmotor.Credit Data = new com.uaspbp.rentalmotor.Credit("180709977, 180709975", "Juan Miguel, Yohanes Ryan Budhi Dharmawan",
            "FTI", "Informatika");
}
