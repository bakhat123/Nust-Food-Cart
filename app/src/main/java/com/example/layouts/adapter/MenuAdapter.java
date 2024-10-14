//package com.example.layouts.adapter;
//
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.layouts.databinding.CartItemBinding;
//import com.example.layouts.databinding.MenuItemBinding;
//
//import java.util.List;
//
//public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
//    private final List<String> menuItemsName;
//    private final List<String> menuItemPrice;
//    private final List<Integer> menuImage;
//
//    public MenuAdapter(List<String> menuItemsName, List<Integer> menuImage,List<String> menuItemPrice) {
//        this.menuItemsName = menuItemsName;
//        this.menuItemPrice = menuItemPrice;
//        this.menuImage = menuImage;
//    }
//
//
//    @NonNull
//    @Override
//    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        MenuItemBinding binding = MenuItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
//        return new MenuAdapter.MenuViewHolder(binding);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
//        String item = menuItemsName.get(position);
//        int image = menuImage.get(position);
//        String price = menuItemPrice.get(position);
//        holder.bind(item,image,price);
//    }
//
//    @Override
//    public int getItemCount() {
//        return menuItemsName.size();
//    }
//
//    class MenuViewHolder extends RecyclerView.ViewHolder {
//        private final MenuItemBinding binding;
//
//        public MenuViewHolder(MenuItemBinding binding) {
//            super(binding.getRoot());
//            this.binding = binding;
//        }
//
//        public void bind(String item, int image, String price) {
//            binding.FoodNameMenu.setText(item);
//            binding.PriceMenu.setText(price);
//            binding.menuimage.setImageResource(image);
//        }
//    }
//}

package com.example.layouts.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layouts.CartSingleton;
import com.example.layouts.databinding.MenuItemBinding;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private final List<String> menuItemsName;
    private final List<String> menuItemPrice;
    private final List<Integer> menuImage;

    public MenuAdapter(List<String> menuItemsName, List<Integer> menuImage, List<String> menuItemPrice) {
        this.menuItemsName = menuItemsName;
        this.menuItemPrice = menuItemPrice;
        this.menuImage = menuImage;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MenuItemBinding binding = MenuItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MenuViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        String item = menuItemsName.get(position);
        int image = menuImage.get(position);
        String price = menuItemPrice.get(position);
        holder.bind(item, image, price);
    }

    @Override
    public int getItemCount() {
        return menuItemsName.size();
    }

    class MenuViewHolder extends RecyclerView.ViewHolder {
        private final MenuItemBinding binding;

        public MenuViewHolder(MenuItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(String item, int image, String price) {
            binding.FoodNameMenu.setText(item);
            binding.PriceMenu.setText(price);
            binding.menuimage.setImageResource(image);

            binding.AddtoCartMenu.setOnClickListener(v -> {
                // Add the item to the cart
                CartSingleton.getInstance().addItem(item, image, price);

                Toast.makeText(binding.getRoot().getContext(), item + " added to cart", Toast.LENGTH_SHORT).show();
            });
        }
    }
}
