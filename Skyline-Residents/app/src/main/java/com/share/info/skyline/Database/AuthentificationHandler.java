package com.share.info.skyline.Database;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthentificationHandler {

    private final FirebaseAuth firebaseAuth;
    private String username;
    private String password;

    public AuthentificationHandler(String username, String password) {
        this.username = username;
        this.password = password;
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public Boolean authenticate() {

        firebaseAuth.signInWithEmailAndPassword(this.username, this.password).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){


                        }

                        else {

                        }


                    }

                });

        return false;
    }
}
