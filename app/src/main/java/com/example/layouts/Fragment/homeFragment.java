package com.example.layouts.Fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.layouts.MenuBottomSheetFragment;
import com.example.layouts.R;
import com.example.layouts.adapter.PopularAdapter;
import com.example.layouts.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class homeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.textView16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuBottomSheetFragment bottomSheetDialog = new MenuBottomSheetFragment();
                bottomSheetDialog.show(getParentFragmentManager(),"test");

            }
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.banner1, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.banner2, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.banner3, ScaleTypes.FIT));

        ImageSlider imageSlider = binding.imageSlider;
        imageSlider.setImageList(imageList);
        imageSlider.setImageList(imageList, ScaleTypes.FIT);

        List<String> foodName = Arrays.asList("Burger", "Salad", "IceCream","Soup","Italian Pasta");
        List<String> price = Arrays.asList("500 Rs", "170 Rs", "100 Rs","70 Rs","850 Rs");
        List<Integer> popularFoodItems = Arrays.asList(R.drawable.menu8, R.drawable.menu2, R.drawable.menu3,R.drawable.menu4,R.drawable.menu5);

        PopularAdapter adapter = new PopularAdapter(foodName, price, popularFoodItems);
        binding.PopularRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.PopularRecyclerView.setAdapter(adapter);
    }
}
