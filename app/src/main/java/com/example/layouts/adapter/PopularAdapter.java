//package com.example.layouts.adapter;
//
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.layouts.databinding.PopularItemBinding;
//
//import java.util.List;
//
//public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> {
//
//    private final List<String> items;
//    private final List<Integer> images;
//    private final List<String> prices;
//
//    public PopularAdapter(List<String> items,List<String> price, List<Integer> images) {
//        this.items = items;
//        this.images = images;
//        this.prices = price;
//    }
//
//
//    @NonNull
//    @Override
//    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        PopularItemBinding binding = PopularItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
//        return new PopularViewHolder(binding);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
//        String item = items.get(position);
//        int image = images.get(position);
//        String price = prices.get(position);
//        holder.bind(item, image, price);
//    }
//
//    @Override
//    public int getItemCount() {
//        return items.size();
//    }
//
//    static class PopularViewHolder extends RecyclerView.ViewHolder {
//        private final PopularItemBinding binding;
//
//        public PopularViewHolder(PopularItemBinding binding) {
//            super(binding.getRoot());
//            this.binding = binding;
//        }
//
//        public void bind(String item, int image, String price) {
//            binding.FoodNamePopular.setText(item);
//            binding.PricePopular.setText(price);
//            binding.imageView4.setImageResource(image);
//        }
//    }
//
//}
//

package com.example.layouts.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layouts.CartSingleton;
import com.example.layouts.databinding.PopularItemBinding;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> {

    private final List<String> items;
    private final List<Integer> images;
    private final List<String> prices;

    public PopularAdapter(List<String> items, List<String> prices, List<Integer> images) {
        this.items = items;
        this.images = images;
        this.prices = prices;
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PopularItemBinding binding = PopularItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PopularViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        String item = items.get(position);
        int image = images.get(position);
        String price = prices.get(position);
        holder.bind(item, image, price);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class PopularViewHolder extends RecyclerView.ViewHolder {
        private final PopularItemBinding binding;

        public PopularViewHolder(PopularItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(String item, int image, String price) {
            binding.FoodNamePopular.setText(item);
            binding.PricePopular.setText(price);
            binding.imageView4.setImageResource(image);

            binding.AddtoCartPopular.setOnClickListener(new View.OnClickListener() { // Add a button in your layout
                @Override
                public void onClick(View v) {
                    CartSingleton.getInstance().addItem(item, image,price);

                    Toast.makeText(binding.getRoot().getContext(), item + " added to cart", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
