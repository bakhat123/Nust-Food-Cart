//package com.example.layouts.Fragment;
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.SearchView;
//
//import com.example.layouts.R;
//
//import com.example.layouts.adapter.MenuAdapter;
//import com.example.layouts.databinding.FragmentSearchBinding;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//
//public class SearchFragment extends Fragment {
//
//    private FragmentSearchBinding binding;
//    private MenuAdapter adapter;
//
//    List<String> originalfoodName = Arrays.asList("Burger", "sandwich", "momos", "food", "milkshake", "paneer", "daal", "food", "banana");
//    List<String> originalprice = Arrays.asList("64 Rs", "62 Rs", "78 Rs", "10 Rs", "100 Rs", "62 Rs", "78 Rs", "10 Rs", "100 Rs");
//    List<Integer> originalpopularFoodItems = Arrays.asList(R.drawable.menu1, R.drawable.menu2, R.drawable.menu3, R.drawable.menu4, R.drawable.menu5, R.drawable.menu1, R.drawable.menu2, R.drawable.menu3, R.drawable.menu4);
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//
//    private List<String> filteredMenuFoodName;
//    private List<String> filteredMenuItemPrice;
//    private List<Integer> filteredMenuImage;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        binding = FragmentSearchBinding.inflate(inflater, container, false);
//
//        // Initialize filtered lists
//        filteredMenuFoodName = new ArrayList<>();
//        filteredMenuItemPrice = new ArrayList<>();
//        filteredMenuImage = new ArrayList<>();
//
//        // Initially, show all menu items
//        showAllMenu();
//
//        // Setup search view
//        setupSearchView();
//
//        // Set up RecyclerView with adapter
//        adapter = new MenuAdapter(filteredMenuFoodName, filteredMenuImage,filteredMenuItemPrice);
//        binding.menuRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
//        binding.menuRecyclerView.setAdapter(adapter);
//
//        return binding.getRoot();
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    private void showAllMenu() {
//        filteredMenuFoodName.clear();
//        filteredMenuItemPrice.clear();
//        filteredMenuImage.clear();
//
//        filteredMenuFoodName.addAll(originalfoodName);
//        filteredMenuItemPrice.addAll(originalprice);
//        filteredMenuImage.addAll(originalpopularFoodItems);
//
//        adapter.notifyDataSetChanged();
//    }
//
//    private void setupSearchView() {
//        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                filterMenuItems(query);
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                filterMenuItems(newText);
//                return true;
//            }
//        });
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    private void filterMenuItems(String query) {
//        filteredMenuFoodName.clear();
//        filteredMenuItemPrice.clear();
//        filteredMenuImage.clear();
//
//        for (int index = 0; index < originalfoodName.size(); index++) {
//            String foodName = originalfoodName.get(index);
//            if (foodName.toLowerCase().contains(query.toLowerCase())) {
//                filteredMenuFoodName.add(foodName);
//                filteredMenuItemPrice.add(originalprice.get(index));
//                filteredMenuImage.add(originalpopularFoodItems.get(index));
//            }
//
//
//        }
//        adapter.notifyDataSetChanged();
//    }
//}

package com.example.layouts.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layouts.R;
import com.example.layouts.adapter.MenuAdapter;
import com.example.layouts.databinding.FragmentSearchBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private MenuAdapter adapter;

    private List<String> originalfoodName;
    private List<String> originalprice;
    private List<Integer> originalpopularFoodItems;

    private List<String> filteredMenuFoodName;
    private List<String> filteredMenuItemPrice;
    private List<Integer> filteredMenuImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        originalfoodName = Arrays.asList("Burger", "Salad", "IceCream","Soup","Italian Pasta","Donuts", "Fruit Chart","Desert","Chicken Soup");
        originalprice = Arrays.asList("500 Rs", "170 Rs", "100 Rs","70 Rs","850 Rs", "150 Rs", "300 Rs","10 Rs","100 Rs");
        originalpopularFoodItems = Arrays.asList(R.drawable.menu8, R.drawable.menu2, R.drawable.menu3,R.drawable.menu4,R.drawable.menu5,R.drawable.menu1, R.drawable.menu2, R.drawable.menu3,R.drawable.menu4);

        filteredMenuFoodName = new ArrayList<>(originalfoodName);
        filteredMenuItemPrice = new ArrayList<>(originalprice);
        filteredMenuImage = new ArrayList<>(originalpopularFoodItems);

        adapter = new MenuAdapter(filteredMenuFoodName, filteredMenuImage, filteredMenuItemPrice);
        binding.menuRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.menuRecyclerView.setAdapter(adapter);

        setupSearchView();

        return view;
    }

    private void setupSearchView() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterMenuItems(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterMenuItems(newText);
                return true;
            }
        });
    }

    private void filterMenuItems(String query) {
        filteredMenuFoodName.clear();
        filteredMenuItemPrice.clear();
        filteredMenuImage.clear();

        for (int index = 0; index < originalfoodName.size(); index++) {
            String foodName = originalfoodName.get(index);
            if (foodName.toLowerCase().contains(query.toLowerCase())) {
                filteredMenuFoodName.add(foodName);
                filteredMenuItemPrice.add(originalprice.get(index));
                filteredMenuImage.add(originalpopularFoodItems.get(index));
            }
        }
        adapter.notifyDataSetChanged();
    }
}
