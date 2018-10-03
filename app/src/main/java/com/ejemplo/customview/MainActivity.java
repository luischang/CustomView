package com.ejemplo.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ejemplo.customview.control.OnValidaButtonListener;
import com.ejemplo.customview.views.CountEditText;
import com.ejemplo.customview.views.ValidaButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CountEditText txtValor = (CountEditText) findViewById(R.id.countEditText);
        final ValidaButton btnValidar = (ValidaButton) findViewById(R.id.controlValidar);

        btnValidar.setOnValidaListener(new OnValidaButtonListener() {
            @Override
            public void onValida() {
                btnValidar.getValidar(txtValor.getValido());
            }
        });
    }
}
