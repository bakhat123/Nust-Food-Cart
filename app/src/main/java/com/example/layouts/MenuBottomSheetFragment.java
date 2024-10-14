package com.example.layouts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.layouts.adapter.MenuAdapter;
import com.example.layouts.databinding.FragmentMenuBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Arrays;
import java.util.List;

public class MenuBottomSheetFragment extends BottomSheetDialogFragment {

    private FragmentMenuBottomSheetBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMenuBottomSheetBinding.inflate(inflater, container, false);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        List<String> menufoodName = Arrays.asList("Burger", "Salad", "IceCream","Soup","Italian Pasta","Donuts", "Fruit Chart","Desert","Chicken Soup");
        List<String> menuprice = Arrays.asList("500 Rs", "170 Rs", "100 Rs","70 Rs","850 Rs", "150 Rs", "300 Rs","10 Rs","100 Rs");
        List<Integer> menuFoodItems = Arrays.asList(R.drawable.menu8, R.drawable.menu2, R.drawable.menu3,R.drawable.menu4,R.drawable.menu5,R.drawable.menu1, R.drawable.menu2, R.drawable.menu3,R.drawable.menu4);

        MenuAdapter adapter = new MenuAdapter(menufoodName, menuFoodItems, menuprice);
        binding.menuRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.menuRecyclerView.setAdapter(adapter);

        return binding.getRoot();
    }

}
