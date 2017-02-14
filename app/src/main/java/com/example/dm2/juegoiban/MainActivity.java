package com.example.dm2.juegoiban;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (this.getIntent().getExtras() != null) {
            Intent intent = getIntent();
            Bundle b = intent.getExtras();
            Toast.makeText(this,b.getString("Terminado"),Toast.LENGTH_LONG).show();
        }

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
