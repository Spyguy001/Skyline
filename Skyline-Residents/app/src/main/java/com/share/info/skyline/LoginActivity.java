package com.share.info.skyline;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.share.info.skyline.Database.RemoteFirebase;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button loginButton;
    private EditText loginEmail;
    private EditText loginPassword;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        firebaseAuth = FirebaseAuth.getInstance();

        // user is already logged in
        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            Intent intent = new Intent(getApplicationContext(), InitilizationActivity.class);
            intent.putExtra("uid", firebaseAuth.getCurrentUser().getUid());
            startActivity(intent);
        }


        loginButton = (Button) findViewById(R.id.login_button);
        loginEmail = (EditText) findViewById(R.id.email);
        loginPassword = (EditText) findViewById(R.id.password);

        loginButton.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);

    }

    @Override
    public void onClick(View v) {

        if (v == loginButton) {
            try {
                userLogin();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void userLogin() throws InterruptedException {

        final String email = loginEmail.getText().toString().trim();
        final String password = loginPassword.getText().toString().trim();

        // pre-verification checks
        if (email.isEmpty() || password.isEmpty()) {

            progressDialog.dismiss();
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setMessage("Please enter an email and a password");
            builder.show();
            return;
        }

        progressDialog.setMessage("Please wait while you are being logged in...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
                LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            progressDialog.dismiss();

                            finish();
                            Intent intent = new Intent(getApplicationContext(), InitilizationActivity.class);
                            intent.putExtra("uid", firebaseAuth.getCurrentUser().getUid());

                            // TODO: this is a temporary hack
                            FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( LoginActivity.this,
                                    new OnSuccessListener<InstanceIdResult>() {
                                @Override
                                public void onSuccess(InstanceIdResult instanceIdResult) {
                                    String newToken = instanceIdResult.getToken();
                                    RemoteFirebase.getInstance().updateToken(newToken);

                                }
                            });

                            startActivity(intent);

                        } else {
                            progressDialog.dismiss();
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage("Please make sure you entered " +
                                    "the correct email and password");
                            builder.show();
                        }
                    }
                });
    }

}
