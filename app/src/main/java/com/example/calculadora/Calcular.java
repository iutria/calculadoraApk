package com.example.calculadora;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Calcular {

    private ConvertirInfija convertir;
    private convertirPostfija convertirPostfija;
    private String Infija = "";
    private String Postfija = null;

    private ArrayList<String> Reultados = null;

    public String calcular(String cadena){
        Reultados = new ArrayList<>();
        convertir = new ConvertirInfija();
        convertirPostfija = new convertirPostfija();
        Infija = convertir.convertirinfija(cadena);
        Postfija = convertirPostfija.convertir(Infija);
        String[] recorrer = Postfija.split(";");
        for (int i = 0; i < recorrer.length;i++){
            if (esOperando(recorrer[i])){
                Empilar(recorrer[i]);
            }else{
                Sacar(recorrer[i]);
            }
        }
        //return Postfija;
        if (Reultados.get(Reultados.size()-1).equals("Error")){
            return "Error";
        }
        return redondear(Reultados.get(Reultados.size()-1));
        //return Reultados.get(Reultados.size()-1);
    }
    private String redondear(String s){
        char[] c = s.toCharArray();
        int i = 0,seguir=0,pos=0;
        while (i<c.length && seguir==0){
            if (c[i]=='.'){
                seguir=1;
                pos=i;
            }
            i=i+1;
        }
        String n="";
        for (int j = pos; j < c.length;j++){
            if (j==pos){
                n="0";
            }else{
                n=n+c[j];
            }
        }
        int q = 0;
        try{
            q=Integer.parseInt(n);
        }catch (Exception e){
            return s;
        }
        if (q>0){
            return s;
        }else{
            String w ="";
            seguir=0;
            i=0;
            while (i < c.length && seguir==0){
                w=w+c[i];
                if (c[i+1]=='.'){
                    seguir=1;
                }
                i=i+1;
            }
            return w;
        }
    }
    private void Empilar(String item){
        Reultados.add(item);
    }
    private void Sacar(String operador){
        String num2 = Reultados.get(Reultados.size()-1);
        Reultados.remove(Reultados.size()-1);
        String num1 = Reultados.get(Reultados.size()-1);
        Reultados.remove(Reultados.size()-1);
        Operacion(num1,num2, operador);
    }
    private void Operacion(String num1,String num2,String signo){
        double operacion=0;
        String resultado;
        switch(signo){
            case "+": resultado = sumar(num1,num2); break;
            case "~": resultado = restar(num1,num2); break;
            case "-":
                Empilar(num1);
                resultado = "-"+num2;
            break;
            case "*": resultado = multiplicar(num1,num2); break;
            case "/": resultado = dividir(num1,num2); break;
            default: resultado = "expresion incorrecta"; break;
        }
        Empilar(""+resultado);
    }
    private String sumar(String num1 , String num2){
        return " "+ (Float.parseFloat(num1) + Float.parseFloat(num2));
    }
    private String restar(String num1 , String num2){
        return " "+ (Float.parseFloat(num1) - Float.parseFloat(num2));
    }
    private String multiplicar(String num1 , String num2){
        return " "+ (Float.parseFloat(num1) * Float.parseFloat(num2));
    }
    private String dividir(String num1 , String num2){
        if (num2.equals("0")){
            return "No se puede dividir por 0";
        }else{
            return " "+ (Float.parseFloat(num1) / Float.parseFloat(num2));
        }
    }
    private boolean esOperando(String valor){
        if (valor.equals("+") || valor.equals("-") || valor.equals("*") || valor.equals("/") || valor.equals("~")){
            return false;
        }
        return true;
    }

}
