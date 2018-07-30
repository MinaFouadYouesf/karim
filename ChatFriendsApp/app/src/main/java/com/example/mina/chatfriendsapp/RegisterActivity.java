package com.example.mina.chatfriendsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private AutoCompleteTextView mEmailView,nameView;
    private EditText mPasswordView;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        mEmailView = (AutoCompleteTextView) findViewById(R.id.signUP);
        nameView=findViewById(R.id.Name);
        mPasswordView = (EditText) findViewById(R.id.signUpPassword);
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_up_button);
        progressDialog=new ProgressDialog(RegisterActivity.this);

        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();
                String name = nameView.getText().toString();
                if(!TextUtils.isDigitsOnly(email)||!TextUtils.isDigitsOnly(password)||!TextUtils.isDigitsOnly(name)){
                  /*  progressDialog.setTitle("Registering User....");
                    progressDialog.setMessage("please wait until your account is created");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();*/
                    registerForm(email,name,password);
                }
            }
        });
    }

   public void registerForm(String email,String name,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            progressDialog.dismiss();
                            Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                            startActivity(intent);
                        } else {

                            progressDialog.hide();
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_LONG).show();

                        }


                    }
                });
    }

 /*public void registerForm(String email,String name,String password){
     mAuth.signInWithEmailAndPassword(email, password)
             .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {

                     if (!task.isSuccessful()) {
                         // Notify user of failure
                     }
                 }
             }).addOnFailureListener(new OnFailureListener(){
                 @Override
                 public void onFailure(@NonNull Exception e) {
                     Log.d("mmmmmmmmmmmmmmmmmmmmmm", e.getLocalizedMessage());
                 }
             });
 }*/
}
