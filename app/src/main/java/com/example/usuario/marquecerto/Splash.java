package com.example.usuario.marquecerto;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*public class Splash extends Activity {

    private static int SPLASH_TIME_OUT = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (currentUser == null){

                    Intent novaTela = new Intent(Splash.this, Login.class );
                    startActivity(novaTela);
                    finish();

                }else{

                    Toast.makeText(getApplicationContext(),"Exemplo Toast", Toast.LENGTH_SHORT).show();

                }

            }
        },SPLASH_TIME_OUT);

    }
}*/



public class Splash extends Activity {

    private static int SPLASH_TIME_OUT = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_splash);

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null){//Se usuario não se cadastrou entra no login

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {

                    Intent novaTela = new Intent(Splash.this, Login.class );
                    startActivity(novaTela);
                    finish();

                }
            }, 2000);

        }else{

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {

                    Intent novaTela = new Intent(Splash.this, MainActivity.class );
                    startActivity(novaTela);
                    finish();

                }
            }, 2000);

        }
    }

 }


