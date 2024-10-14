//package com.example.layouts;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.DialogFragment;
//
//public class PaymentFragment extends DialogFragment {
//
//    private TextView subTotalTextView, deliveryChargeTextView, discountTextView, totalPriceTextView;
//
//    // Required empty public constructor
//    public PaymentFragment() {}
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_payment, container, false);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        // Initialize TextViews
//        subTotalTextView = view.findViewById(R.id.subTotalTextView);
//        deliveryChargeTextView = view.findViewById(R.id.deliveryChargeTextView);
//        discountTextView = view.findViewById(R.id.discountTextView);
//        totalPriceTextView = view.findViewById(R.id.totalPriceTextView);
//
//        // Retrieve and display data
//        if (getArguments() != null) {
//            double subTotal = getArguments().getDouble("subTotal", 0);
//            double deliveryCharge = getArguments().getDouble("deliveryCharge", 0);
//            double discount = getArguments().getDouble("discount", 0);
//            double totalPrice = getArguments().getDouble("totalPrice", 0);
//            updateTextViews(subTotal, deliveryCharge, discount, totalPrice);
//        }
//    }
//
//    // Method to update TextViews with data
//    private void updateTextViews(double subTotal, double deliveryCharge, double discount, double totalPrice) {
//        subTotalTextView.setText(String.format("%.2f $", subTotal));
//        deliveryChargeTextView.setText(String.format("%.2f $", deliveryCharge));
//        discountTextView.setText(String.format("%.2f $", discount));
//        totalPriceTextView.setText(String.format("%.2f $", totalPrice));
//    }
//}
package com.example.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class PaymentFragment extends DialogFragment {

    private TextView subTotalTextView, deliveryChargeTextView, discountTextView, totalPriceTextView;
    private Button proceed;
    // Required empty public constructor
    public PaymentFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_payment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize TextViews
        subTotalTextView = view.findViewById(R.id.subTotalTextView);
        deliveryChargeTextView = view.findViewById(R.id.deliveryChargeTextView);
        discountTextView = view.findViewById(R.id.discountTextView);
        totalPriceTextView = view.findViewById(R.id.totalPriceTextView);
        proceed = view.findViewById(R.id.paymentProceed);


        // Retrieve and display data
        if (getArguments() != null) {
            double subTotal = getArguments().getDouble("subTotal", 0);
            double deliveryCharge = getArguments().getDouble("deliveryCharge", 0);
            double discount = getArguments().getDouble("discount", 0);
            double totalPrice = getArguments().getDouble("totalPrice", 0);

            // Log data for debugging
            Log.d("PaymentFragment", "subTotal: " + subTotal);
            Log.d("PaymentFragment", "deliveryCharge: " + deliveryCharge);
            Log.d("PaymentFragment", "discount: " + discount);
            Log.d("PaymentFragment", "totalPrice: " + totalPrice);

            updateTextViews(subTotal, deliveryCharge, discount, totalPrice);
        }
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the OrderConfirmedActivity
                Intent intent = new Intent(getActivity(), OrderConfirmedActivity.class);
                startActivity(intent);
            }
        });
    }

    // Method to update TextViews with data
    private void updateTextViews(double subTotal, double deliveryCharge, double discount, double totalPrice) {
        subTotalTextView.setText(String.format("%.2f RS", subTotal));
        deliveryChargeTextView.setText(String.format("%.2f RS", deliveryCharge));
        discountTextView.setText(String.format("%.2f RS", discount));
        totalPriceTextView.setText(String.format("%.2f RS", totalPrice));


    }

}






