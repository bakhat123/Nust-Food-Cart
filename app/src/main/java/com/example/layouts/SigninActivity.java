//package com.example.layouts;
//import com.example.layouts.databinding.ActivitySigninBinding;
//import com.example.layouts.model.UserModel;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//public class SigninActivity extends AppCompatActivity {
//    private ActivitySigninBinding binding;
//    private FirebaseAuth auth;
//    private String email;
//    private String password;
//    private String userName;
//    private DatabaseReference database;
//    private TextView textView;
//    private EditText emailOrPhoneEditText;
//    private EditText signInPass;
//    private EditText username;
//    private Button button;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivitySigninBinding.inflate(getLayoutInflater());
//        EdgeToEdge.enable(this);
//
//
//        setContentView(binding.getRoot());
//        textView = binding.HaveAccount;
//        button = binding.signInCreateButton;
//
//        auth = FirebaseAuth.getInstance();
//        database = FirebaseDatabase.getInstance().getReference();
//
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Find the email, password, and username EditText views by id
//                 emailOrPhoneEditText = findViewById(R.id.signInEmail);
//                 signInPass = findViewById(R.id.signInPassword);
//                 username = findViewById(R.id.signInName);
//
//                // Get the text from the EditText views
//                email = emailOrPhoneEditText.getText().toString().trim();
//                password = signInPass.getText().toString().trim();
//                userName = username.getText().toString().trim();
//
//                // Check if any of the fields is empty or null
//                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(userName)) {
//
//                    Toast.makeText(SigninActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
//                } else {
//                    createAccount(email, password);
//                }
//            }
//        });
//
//
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SigninActivity.this, loginActivity.class);
//                startActivity(intent);
//
//            }
//        });
//    }
//
//    private void createAccount(String email, String password) {
//        auth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Intent intent = new Intent(SigninActivity.this, loginActivity.class);
//                            startActivity(intent);
//                            Toast.makeText(getApplicationContext(), "Account Created Successfully", Toast.LENGTH_SHORT).show();
//                            saveUserDate();
//                            finish();
//                        } else {
//                            Toast.makeText(getApplicationContext(), "Account Creation Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                            Log.d("Account", "createAccount: Failure", task.getException());
//                        }
//                    }
//                });
//    }
//
//    private void saveUserDate() {
//        email = binding.signInEmail.getText().toString().trim();
//        password = binding.signInPassword.getText().toString().trim();
//        userName = binding.signInName.getText().toString().trim();
//
//        UserModel user = new UserModel(userName, email, password);
//        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//        if (currentUser != null) {
//            String userId = currentUser.getUid();
//
//            database.child("user").child(userId).setValue(user);
//
//        }
//
//    }
//
//
//}


// SigninActivity.java
package com.example.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.example.layouts.databinding.ActivitySigninBinding;
import com.example.layouts.model.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SigninActivity extends AppCompatActivity {
    private ActivitySigninBinding binding;
    private FirebaseAuth auth;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText usernameEditText;
    private Button createAccountButton;
    private TextView haveAccountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        emailEditText = binding.signInEmail;
        passwordEditText = binding.signInPassword;
        usernameEditText = binding.signInName;
        createAccountButton = binding.signInCreateButton;
        haveAccountTextView = binding.HaveAccount;

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String username = usernameEditText.getText().toString().trim();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(username)) {
                    Toast.makeText(SigninActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {
                    createAccount(email, password, username);
                }
            }
        });

        haveAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SigninActivity.this, loginActivity.class);
                startActivity(intent);
            }
        });
    }

    // SigninActivity.java

    private void createAccount(String email, String password, String username) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            if (user != null) {
                                // Send verification email
                                sendVerificationEmail(user);

                                // Save user info to database
                                saveUserToFirebase(user.getUid(), username, email,password);

                                // Inform user and redirect to LoginActivity
                                Toast.makeText(SigninActivity.this, "Account created. Please verify your email.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SigninActivity.this, loginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            Toast.makeText(SigninActivity.this, "Account Creation Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void saveUserToFirebase(String userId, String username, String email,String password) {
        UserModel user = new UserModel(username, email, password, "");
        FirebaseDatabase.getInstance().getReference("user")
                .child(userId)
                .setValue(user)
                .addOnSuccessListener(aVoid -> Log.d("SigninActivity", "User saved successfully."))
                .addOnFailureListener(e -> Log.e("SigninActivity", "Failed to save user.", e));
    }


    private void sendVerificationEmail(FirebaseUser user) {
        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SigninActivity.this, "Verification email sent to " + user.getEmail(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SigninActivity.this, "Failed to send verification email.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
