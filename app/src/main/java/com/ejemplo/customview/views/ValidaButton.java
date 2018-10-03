package com.ejemplo.customview.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ejemplo.customview.R;
import com.ejemplo.customview.control.OnValidaButtonListener;

/**
 * Created by lventura on 15/09/17.
 */

public class ValidaButton extends LinearLayout {

    private OnValidaButtonListener listener;

    private TextView lblMensaje;
    private Button btnValidar;

    public ValidaButton(Context context) {
        super(context);

        inicializacion("");
    }

    public ValidaButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ValidaButton);
        String nombre_boton = a.getString(R.styleable.ValidaButton_button_name);
        a.recycle();
        inicializacion(nombre_boton);
    }

    public ValidaButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ValidaButton);
        String nombre_boton = a.getString(R.styleable.ValidaButton_button_name);
        a.recycle();
        inicializacion(nombre_boton);
    }

    public void inicializacion(String nombre_boton) {
        String inf_service = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(inf_service);
        li.inflate(R.layout.valida_button, this, true);

        lblMensaje = (TextView) findViewById(R.id.lblMensaje);
        btnValidar = (Button) findViewById(R.id.btnValidar);

        if(!nombre_boton.equals("")) {
            btnValidar.setText(nombre_boton);
        }

        btnValidar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onValida();
            }
        });
    }

    public void getValidar(boolean invalido) {
        Log.i("TAG","CLICK --> " + invalido);
        if (!invalido) {
            setLblMensaje("Tamaño Valido");
        } else {
            setLblMensaje("Tamaño Invalido");
        }
    }

    public void setLblMensaje(String texto) {
        lblMensaje.setText(texto);
    }

    public void setOnValidaListener(OnValidaButtonListener l) {
        listener = l;
    }


}
