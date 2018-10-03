package com.ejemplo.customview.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;

import com.ejemplo.customview.R;

/**
 * Created by lventura on 14/09/17.
 */

public class CountEditText extends AppCompatEditText implements TextWatcher{

    private float escala;
    private Paint p1;
    private Paint p11;
    private Paint p2;
    private Paint pAux;


    private boolean fuera_de_rango = true;

    private int min_length_caracter = 0;
    private int max_length_caracter = 0;

    public CountEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CountEditText);
        inicializacion(a);
        a.recycle();
    }

    public CountEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CountEditText);
        inicializacion(a);
        a.recycle();
    }

    public CountEditText(Context context) {
        super(context);

        inicializacion();
    }

    public void inicializacion() {

        fuera_de_rango = false;

        p1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        p1.setColor(getResources().getColor(R.color.colorAccent));
        p1.setStyle(Paint.Style.FILL);

        p2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        p2.setColor(Color.WHITE);
        p2.setTextSize(20);

        escala = getResources().getDisplayMetrics().density;
    }

    public void inicializacion(TypedArray a) {

        min_length_caracter = a.getInt(R.styleable.CountEditText_min_length_caracter, 0);
        max_length_caracter = a.getInt(R.styleable.CountEditText_max_length_caracter, 0);

        if(min_length_caracter == 0 && max_length_caracter == 0){
            fuera_de_rango = false;
        }

        p1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        p1.setColor(Color.RED);
        p1.setStyle(Paint.Style.FILL);

        p11 = new Paint(Paint.ANTI_ALIAS_FLAG);
        p11.setColor(Color.BLUE);
        p11.setStyle(Paint.Style.FILL);

        if(fuera_de_rango) {
            pAux = p1;
        } else {
            pAux = p11;
        }


        p2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        p2.setColor(Color.WHITE);
        p2.setTextSize(20);

        escala = getResources().getDisplayMetrics().density;

        this.addTextChangedListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Dibujamos el fondo negro del contador
        canvas.drawRect(this.getWidth()-30*escala,
                5*escala,
                this.getWidth()-5*escala,
                20*escala, pAux);

        //Dibujamos el número de caracteres sobre el contador
        canvas.drawText("" + this.getText().toString().length(),
                this.getWidth()-28*escala,
                17*escala, p2);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

        if(!(min_length_caracter == 0 && max_length_caracter == 0)) {
            int lengthactual = editable.length();
            Log.i("TAM", "TAMAÑO: " + lengthactual);

            if(min_length_caracter > 0 && max_length_caracter > 0){

                if (lengthactual >= min_length_caracter && lengthactual <= max_length_caracter) {
                    fuera_de_rango = false;
                    pAux = p11;
                } else {
                    fuera_de_rango = true;
                    pAux = p1;
                }

            } else {
                if (min_length_caracter > 0) {
                    if (min_length_caracter >= lengthactual) {
                        fuera_de_rango = true;
                        pAux = p1;
                    } else {
                        fuera_de_rango = false;
                        pAux = p11;
                    }
                } else {
                    if (max_length_caracter >= lengthactual) {
                        fuera_de_rango = false;
                        pAux = p11;
                    } else {
                        fuera_de_rango = true;
                        pAux = p1;
                    }
                }
            }
        }
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    public boolean getValido(){
        return fuera_de_rango;
    }
}
