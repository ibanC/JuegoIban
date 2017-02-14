package com.example.dm2.juegoiban;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void jugar(View view)
    {
        setContentView(new VistaJuego(this));
    }
    public void salir(View view){
        ActivityCompat.finishAffinity(this);
        System.exit(0);
        finish();
    }
}
