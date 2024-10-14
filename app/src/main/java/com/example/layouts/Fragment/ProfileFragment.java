package com.example.layouts.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.layouts.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    private EditText nameEditText, addressEditText, emailEditText, phoneEditText;
    private Button saveButton;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        nameEditText = view.findViewById(R.id.nameEditText);
        addressEditText = view.findViewById(R.id.addressEditText);
        emailEditText = view.findViewById(R.id.emailEditText);
        phoneEditText = view.findViewById(R.id.phoneEditText);
        saveButton = view.findViewById(R.id.button6);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null) {
            String userId = currentUser.getUid();
            databaseReference = FirebaseDatabase.getInstance().getReference("user").child(userId);

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String email = dataSnapshot.child("email").getValue(String.class);
                        String address = dataSnapshot.child("location").getValue(String.class);
                        String name = dataSnapshot.child("userName").getValue(String.class);
                        String phone = dataSnapshot.child("phoneNumber").getValue(String.class);
                        nameEditText.setText(name);
                        addressEditText.setText(address);
                        emailEditText.setText(email);
                        phoneEditText.setText(phone);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle possible errors
                }
            });

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newName = nameEditText.getText().toString().trim();
                    String newAddress = addressEditText.getText().toString().trim();
                    String newPhone = phoneEditText.getText().toString().trim();



                    databaseReference.child("userName").setValue(newName);
                    databaseReference.child("location").setValue(newAddress);
                    databaseReference.child("phoneNumber").setValue(newPhone);

                    Toast.makeText(getContext(), "New Changes are made", Toast.LENGTH_SHORT).show();
                }
            });
        }

        return view;
    }
}
