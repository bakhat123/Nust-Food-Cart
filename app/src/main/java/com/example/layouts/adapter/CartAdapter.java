//
//
//package com.example.layouts.adapter;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.layouts.CartSingleton;
//import com.example.layouts.databinding.CartItemBinding;
//
//import java.util.List;
//
//public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
//
//    private List<String> items;
//    private List<Integer> images;
//    private List<String> prices;
//    private int[] itemQuantities;
//
//    public CartAdapter() {
//        updateData();
//    }
//
//    public void updateData() {
//        this.items = CartSingleton.getInstance().getCartItems();
//        this.images = CartSingleton.getInstance().getCartImages();
//        this.prices = CartSingleton.getInstance().getCartPrices();
//        this.itemQuantities = new int[items.size()];
//        for (int i = 0; i < items.size(); i++) {
//            this.itemQuantities[i] = CartSingleton.getInstance().getItemQuantities().get(i);
//        }
//        notifyDataSetChanged(); // Notify the adapter to refresh the data
//    }
//
//    @NonNull
//    @Override
//    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        CartItemBinding binding = CartItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
//        return new CartViewHolder(binding);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
//        String item = items.get(position);
//        int image = images.get(position);
//        String price = prices.get(position);
//        int quantity = itemQuantities[position];
//        holder.bind(item, image, price, quantity);
//    }
//
//    @Override
//    public int getItemCount() {
//        return items.size();
//    }
//
//    // Method to calculate total price of items in the cart
//    public double getTotalPrice() {
//        double totalPrice = 0.0;
//        for (int i = 0; i < items.size(); i++) {
//            double price = Double.parseDouble(prices.get(i).replace("$", ""));
//            totalPrice += price * itemQuantities[i];
//        }
//        return totalPrice;
//    }
//
//    class CartViewHolder extends RecyclerView.ViewHolder {
//        private final CartItemBinding binding;
//
//        public CartViewHolder(CartItemBinding binding) {
//            super(binding.getRoot());
//            this.binding = binding;
//        }
//
//
//
//        public void bind(String item, int image, String price, int quantity) {
//            binding.CartItemName.setText(item);
//            binding.textView20.setText(price);
//            binding.imageView6.setImageResource(image);
//            binding.cartItemQuantity.setText(String.valueOf(quantity));
//
//            // Plus button click listener
//            binding.plusbutton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int pos = getAdapterPosition();
//                    if (itemQuantities[pos] < 10) {
//                        itemQuantities[pos]++;
//                        binding.cartItemQuantity.setText(String.valueOf(itemQuantities[pos]));
//                        CartSingleton.getInstance().getItemQuantities().set(pos, itemQuantities[pos]);
//                        notifyDataSetChanged(); // Notify adapter on data change
//                    }
//                }
//            });
//
//            // Minus button click listener
//            binding.minusbutton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int pos = getAdapterPosition();
//                    if (itemQuantities[pos] > 0) {
//                        itemQuantities[pos]--;
//                        binding.cartItemQuantity.setText(String.valueOf(itemQuantities[pos]));
//                        CartSingleton.getInstance().getItemQuantities().set(pos, itemQuantities[pos]);
//                        notifyDataSetChanged(); // Notify adapter on data change
//                    }
//                }
//            });
//        }
//    }
//}
//
//
//
package com.example.layouts.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layouts.CartSingleton;
import com.example.layouts.databinding.CartItemBinding;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<String> items;
    private List<Integer> images;
    private List<String> prices;
    private int[] itemQuantities;

    public CartAdapter() {
        updateData();
    }

    public void updateData() {
        this.items = CartSingleton.getInstance().getCartItems();
        this.images = CartSingleton.getInstance().getCartImages();
        this.prices = CartSingleton.getInstance().getCartPrices();
        this.itemQuantities = new int[items.size()];
        for (int i = 0; i < items.size(); i++) {
            this.itemQuantities[i] = CartSingleton.getInstance().getItemQuantities().get(i);
        }
        Log.d("CartAdapter", "Data updated: items=" + items.size() + " images=" + images.size() + " prices=" + prices.size());
        notifyDataSetChanged(); // Notify the adapter to refresh the data
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartItemBinding binding = CartItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        String item = items.get(position);
        int image = images.get(position);
        String price = prices.get(position);
        int quantity = itemQuantities[position];
        holder.bind(item, image, price, quantity);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // Method to calculate total price of items in the cart
    public double getTotalPrice() {
        double totalPrice = 0.0;



        for (int i = 0; i < items.size(); i++) {
            try {
                String priceString = prices.get(i).replace("Rs", ""); // Remove the "Rs" suffix
                double price = Double.parseDouble(priceString);
                totalPrice += price * itemQuantities[i];
            } catch (NumberFormatException e) {
                Log.e("CartAdapter", "Error parsing price at position " + i + ": " + prices.get(i), e);
            }
        }

        return totalPrice;
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        private final CartItemBinding binding;

        public CartViewHolder(CartItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(String item, int image, String price, int quantity) {
            binding.CartItemName.setText(item);
            binding.textView20.setText(price);
            binding.imageView6.setImageResource(image);
            binding.cartItemQuantity.setText(String.valueOf(quantity));

            // Plus button click listener
            binding.plusbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (itemQuantities[pos] < 10) {
                        itemQuantities[pos]++;
                        binding.cartItemQuantity.setText(String.valueOf(itemQuantities[pos]));
                        CartSingleton.getInstance().getItemQuantities().set(pos, itemQuantities[pos]);
                        notifyDataSetChanged(); // Notify adapter on data change
                    }
                }
            });

            // Minus button click listener
            binding.minusbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (itemQuantities[pos] > 0) {
                        itemQuantities[pos]--;
                        binding.cartItemQuantity.setText(String.valueOf(itemQuantities[pos]));
                        CartSingleton.getInstance().getItemQuantities().set(pos, itemQuantities[pos]);
                        notifyDataSetChanged(); // Notify adapter on data change
                    }
                }
            });
        }
    }
}
