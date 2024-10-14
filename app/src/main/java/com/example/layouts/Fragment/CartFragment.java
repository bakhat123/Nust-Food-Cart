//
//package com.example.layouts.Fragment;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//
//import com.example.layouts.PaymentFragment;
//import com.example.layouts.R;
//import com.example.layouts.adapter.CartAdapter;
//import com.example.layouts.databinding.FragmentCartBinding;
//import com.example.layouts.databinding.FragmentPaymentBinding;
//
//public class CartFragment extends Fragment {
//
//    private FragmentCartBinding binding;
//    private CartAdapter adapter;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        binding = FragmentCartBinding.inflate(inflater, container, false);
//
//        // Set onClickListener for button5 to show PaymentFragment
//        binding.button5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Example data for demonstration
//                double subTotal = 120.0;
//                double deliveryCharge = 10.0;
//                double discount = 20.0;
//                double totalPrice = subTotal + deliveryCharge - discount;
//
//                // Create instance of PaymentFragment with data
//                PaymentFragment paymentFragment = new PaymentFragment();
//                paymentFragment.show(getParentFragmentManager(), "PaymentFragment");
//            }
//        });
//
//        return binding.getRoot();
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        adapter = new CartAdapter();
//        binding.cartRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
//        binding.cartRecyclerView.setAdapter(adapter);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        adapter.notifyDataSetChanged(); // Refresh the adapter to show updated cart items
//    }
//}
package com.example.layouts.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.layouts.PaymentFragment;
import com.example.layouts.R;
import com.example.layouts.adapter.CartAdapter;
import com.example.layouts.databinding.FragmentCartBinding;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;
    private CartAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);

        // Set onClickListener for button5 to show PaymentFragment
        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.updateData(); // Update the adapter's data

                // Calculate subtotal dynamically from CartAdapter
                double subTotal = adapter.getTotalPrice();
                double deliveryCharge = 10.0; // Example delivery charge
                double discount = 20.0; // Example discount
                double totalPrice = subTotal + deliveryCharge - discount;

                // Ensure total price is not negative
                if (totalPrice < 0) {
                    totalPrice = 0;
                }

                // Check if there are items in the cart
                if (subTotal == 0) {
                    Toast.makeText(getContext(), "No items in the cart", Toast.LENGTH_SHORT).show();
                } else {
                    // Log data for debugging
                    Log.d("CartFragment", "subTotal: " + subTotal);
                    Log.d("CartFragment", "deliveryCharge: " + deliveryCharge);
                    Log.d("CartFragment", "discount: " + discount);
                    Log.d("CartFragment", "totalPrice: " + totalPrice);

                    // Create instance of PaymentFragment with data
                    PaymentFragment paymentFragment = new PaymentFragment();
                    Bundle bundle = new Bundle();
                    bundle.putDouble("subTotal", subTotal);
                    bundle.putDouble("deliveryCharge", deliveryCharge);
                    bundle.putDouble("discount", discount);
                    bundle.putDouble("totalPrice", totalPrice);
                    paymentFragment.setArguments(bundle);
                    paymentFragment.show(getParentFragmentManager(), "PaymentFragment");
                }
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new CartAdapter();
        binding.cartRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.cartRecyclerView.setAdapter(adapter);
        adapter.updateData();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.updateData(); // Refresh the adapter to show updated cart items
    }
}
