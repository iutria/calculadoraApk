package com.example.calculadora;

public class ConvertirInfija {
    public String convertirinfija(String infija){
        String transformadaPuntos="";
        for (int i = 0; i < infija.length(); i++){
            if (transformadaPuntos.length()==0){
                transformadaPuntos = infija.charAt(i)+";";
            }else {
                if (esSigno(String.valueOf(infija.charAt(i)))){
                    transformadaPuntos = transformadaPuntos + infija.charAt(i) + ";";
                }else{
                    if (esSigno(getUltimo(transformadaPuntos))){
                        transformadaPuntos = transformadaPuntos + infija.charAt(i) + ";";
                    }else{
                        transformadaPuntos = transformadaPuntos.substring(0,transformadaPuntos.length()-1)+infija.charAt(i) + ";";
                    }
                }
            }
        }
        return transformadaPuntos;
        //return getUltimo(transformadaPuntos);
    }
    private boolean esSigno(String valor){
        if (
            valor.equals("+") ||
            valor.equals("~") ||
            valor.equals("-") ||
            valor.equals("*") ||
            valor.equals("/") ||
            valor.equals("(") ||
            valor.equals(")")
        ){
            return true;
        }else{
            return false;
        }

    }
    private String getUltimo(String st){
        int ultimo = st.length();
        return st.substring(ultimo-2,ultimo-1);
    }

}
