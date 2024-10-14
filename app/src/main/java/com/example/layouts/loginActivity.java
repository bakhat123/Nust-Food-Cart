
package com.example.layouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.example.layouts.databinding.ActivityLoginBinding;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class loginActivity extends AppCompatActivity {
    private TextView textView1;
    private ActivityLoginBinding binding;
    private String email;
    private String password;
    private FirebaseAuth auth;
    private DatabaseReference database;
    private GoogleSignInAccount googleSignInAccount;
    private Button button;
    private Button googlebutton;
    private EditText logInE_mail;
    private EditText logInPass;
    private Button facebookButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        button = binding.logInButton;
        googlebutton = binding.googleButton;
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        textView1 = findViewById(R.id.dontHaveAccount);
        facebookButton = findViewById(R.id.facebookButton);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logInE_mail = findViewById(R.id.logInEmail);
                logInPass = findViewById(R.id.logInPassword);

                email = logInE_mail.getText().toString().trim();
                password = logInPass.getText().toString().trim();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(loginActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {
                    createUserAccount(email, password);
                }
            }
        });

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });


        googlebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                launcher.launch(signInIntent);
            }
        });
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                launcher.launch(signInIntent);
            }
        });

    }

    private void createUserAccount(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            if (user != null && user.isEmailVerified()) {
                                // Email is verified, proceed with login
                                Toast.makeText(loginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                updateUI(user);
                            } else {
                                // Email is not verified, prompt the user to verify
                                Toast.makeText(loginActivity.this, "Please verify your email before logging in", Toast.LENGTH_SHORT).show();
                                // You can also send a verification email again here if needed
                            }
                        } else {
                            Toast.makeText(loginActivity.this, "Invalid Details", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        Intent intent = new Intent(loginActivity.this, ChooseLocationActivity.class);
        intent.putExtra("userId", user.getUid());
        intent.putExtra("username", user.getDisplayName());
        intent.putExtra("email", user.getEmail());
        startActivity(intent);
        finish();
    }

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
            if (task.isSuccessful()) {
                GoogleSignInAccount account = task.getResult();
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                auth.signInWithCredential(credential).addOnCompleteListener(authTask -> {
                    if (authTask.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        if (user != null && user.isEmailVerified()) {
                            Toast.makeText(this, "Successfully signed in with Google", Toast.LENGTH_SHORT).show();
                            updateUI(user);
                        } else {
                            Toast.makeText(this, "Please verify your email before logging in", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Google sign-in failed", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "Google sign-in failed", Toast.LENGTH_SHORT).show();
            }
        }
    });
}






