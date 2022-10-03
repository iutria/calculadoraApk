package com.example.calculadora;

import java.util.ArrayList;

public class convertirPostfija {

    ArrayList<String> Postfija = null;
    String infija = "";
    ArrayList<String> Pila = null;
    String postfija = "";
    String pila = "";
    public String convertir(String infija){
        this.Postfija = new ArrayList<>();
        this.Pila = new ArrayList<>();
        this.infija = infija;
        convertirPostfija();
        return postfija;
    }
    private void convertirPostfija(){
        String[] recorrer = infija.split(";");
        for (int i = 0; i < recorrer.length; i++){
            if (esOperando(recorrer[i])){
                toPostfija(recorrer[i]);
            }else {
                if ((recorrer[i]).equals(")")){
                    ParentesisDerecho();
                }else{
                    if (pila.length()==0){
                        Empilar(recorrer[i]);
                    }else{
                        if ((recorrer[i]).equals("-")){
                            Empilar(recorrer[i]);
                        }else{
                            String ultimoPila = pila.substring(pila.length()-1);
                            if (pFuera(recorrer[i])>pDentro(ultimoPila)){
                                Empilar(recorrer[i]);
                            }else{
                                Desempilar();
                                Empilar(recorrer[i]);
                            }
                        }
                    }
                }
            }
        }
        if (pila.length()>0){
            while (pila.length()>0){
                Desempilar();
            }
        }
    }
    private void ParentesisDerecho(){
        Desempilar();
        if (pila.substring(pila.length()-1).equals("(")){
            pila = pila.substring(0,pila.length()-1);
        }else{
            ParentesisDerecho();
        }
    }
    private void Empilar(String toPila){
        pila = pila + toPila;
    }
    private void Desempilar(){
        toPostfija(pila.substring(pila.length()-1));
        pila = pila.substring(0,pila.length()-1);
    }
    private void toPostfija(String toPostfija){
        postfija = postfija + toPostfija + ";";
    }
    private boolean esOperando(String valor){
        if (valor.equals("+") || valor.equals("-") || valor.equals("*") || valor.equals("/") || valor.equals("(") || valor.equals(")") || valor.equals("~")){
            return false;
        }
        return true;
    }
    private int pDentro(String p){
        int prioridad=0;
        switch (p) {
            case "/":prioridad=2;break;
            case "*":prioridad=2;break;
            case "-":prioridad=1;break;
            case "~":prioridad=1;break;
            case "+":prioridad=1;break;
            case "(":prioridad=0;break;
        }
        return prioridad;
    }
    private int pFuera(String p){
        int prioridad=0;
        switch (p) {
            case "/":prioridad=2;break;
            case "*":prioridad=2;break;
            case "-":prioridad=1;break;
            case "~":prioridad=1;break;
            case "+":prioridad=1;break;
            case "(":prioridad=5;break;
        }
        return prioridad;
    }
}
