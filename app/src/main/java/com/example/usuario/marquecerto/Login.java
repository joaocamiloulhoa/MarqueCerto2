package com.example.usuario.marquecerto;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends Activity {

    private EditText email, pass;
    private Button login,  registro, esqueci;
    private FirebaseAuth mAuth;
    private ProgressBar pbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.senha);
        login = (Button) findViewById(R.id.btn_entrar);
        registro = (Button) findViewById(R.id.criar_conta);
        esqueci = (Button) findViewById(R.id.esqueceu_senha);
        mAuth = FirebaseAuth.getInstance();
        pbar = (ProgressBar) findViewById(R.id.login_progress);

        pbar.setVisibility(View.INVISIBLE);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String loginEmail = email.getText().toString();
                String loginPass = pass.getText().toString();

                if (!TextUtils.isEmpty(loginEmail)&&!TextUtils.isEmpty(loginPass)){
                    pbar.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(loginEmail, loginPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                sendToMain();

                            }else{

                                String errorMsn = task.getException().getMessage();
                                Toast.makeText(Login.this, "Error: " + errorMsn, Toast.LENGTH_LONG).show();
                            }

                            pbar.setVisibility(View.INVISIBLE);
                        }
                    });

                }


            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser!= null){
            sendToMain();
        }

    }

    private void sendToMain(){

        Intent novaTela = new Intent(Login.this, MainActivity.class );
        startActivity(novaTela);
        finish();

    }

}
