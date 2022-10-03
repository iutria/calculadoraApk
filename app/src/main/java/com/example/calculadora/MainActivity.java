package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText entrada,salida;
    Validaciones validaciones;
    Calcular calcular = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        validaciones = new Validaciones();
        calcular = new Calcular();

        entrada = (EditText) findViewById(R.id.txtEntrada);
        salida = (EditText) findViewById(R.id.txtSalida);

        entrada.setSingleLine(true);
        entrada.setMovementMethod(new ScrollingMovementMethod());
        entrada.setSelected(true);

        salida.setSingleLine(true);
        salida.setMovementMethod(new ScrollingMovementMethod());
    }
    public void Calcular(View view){
        entrada.setTextColor(Color.parseColor("#A6A6A6"));
        String cadena = entrada.getText().toString();
        String resultado="";
        try {
            resultado = calcular.calcular(cadena);
        }catch (Exception e){
            resultado="Error al procesar operación info";
            entrada.setTextColor(Color.parseColor("#B20909"));
        }
        salida.setText(cadena);
        entrada.setText(resultado);
        entrada.setSelection(0);
    }
    public void agregar(View view) {
        entrada.setTextColor(Color.parseColor("#A6A6A6"));
        Button b = (Button) view;
        String a = b.getText().toString();
        String viejo = entrada.getText().toString();
        Addtext(validaciones.getTexto(viejo,a),"");
    }
    public void Borrar(View view){
        quitarUltimo();
    }
    public void Eliminar(View view){
        entrada.setTextColor(Color.parseColor("#A6A6A6"));
        Addtext("0","");
    }
    private void quitarUltimo(){
        entrada.setTextColor(Color.parseColor("#A6A6A6"));
        String a = entrada.getText().toString();
        a = (a.equals("0") || a.length()==1) ? "0" : a.substring(0,a.length()-1);
        Addtext(a,"");
    }
    private void Addtext(String texto,String res){
        this.salida.setText(res);
        if (texto.equals("Error")){
            entrada.setTextColor(Color.parseColor("#B20909"));
        }
        entrada.setText(texto);
        entrada.setSelection(entrada.length());
    }
    public void ponerOperacion(View view){
        String op = salida.getText().toString();
        Eliminar(view);
        Addtext(op,"");
    }
}