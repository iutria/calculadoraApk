package com.example.calculadora;

public class Validaciones {

    public String getTexto(String cadena, String nuevo){
        String respuesta = "";
        if (cadena.equals("0")){
            char charNuevo = nuevo.charAt(0);
            switch (charNuevo){
                case '(': respuesta = nuevo; break;
                case '~': respuesta = "-"; break;
                case '.': respuesta = "0"+nuevo; break;
                default: respuesta = esSigno(nuevo) ? "0" : nuevo; break;
            }
        }else{
            if (!esSigno(nuevo)){
                if (cadena.substring(cadena.length()-1).equals(")")){
                    respuesta=cadena;
                }else{
                    respuesta = cadena+nuevo;
                }
            }else{
                if (nuevo.equals("-") || nuevo.equals("~")){
                    if(esSigno(cadena.substring(cadena.length()-1))){
                        if (cadena.substring(cadena.length()-1).equals(")")){
                            respuesta = cadena + nuevo;
                        }else{
                            if (cadena.substring(cadena.length()-1).equals("-")){
                                respuesta = cadena;
                            }else{
                                respuesta = cadena + "-";
                            }
                        }
                    }else{
                        respuesta = cadena+nuevo;
                    }
                }else{
                    if (cadena.substring(cadena.length()-1).equals(")")){
                        if (esSigno(cadena.substring(cadena.length()-1)) && !cadena.substring(cadena.length()-1).equals(")")){
                            respuesta=cadena;
                        }else{
                            respuesta = cadena+nuevo;
                        }

                    }else{
                        if(esSigno(cadena.substring(cadena.length()-1))){
                            if (nuevo.equals("(") || nuevo.equals(")")){
                                respuesta = cadena+nuevo;
                            }else{
                                respuesta = cadena;
                            }
                        }else{
                            respuesta = cadena+nuevo;
                        }
                    }
                }
            }
        }
        return respuesta;
    }
    private boolean esSigno(String valor){
        if (valor.equals("+") || valor.equals("-") || valor.equals("*") || valor.equals("/") || valor.equals("(") || valor.equals(")") || valor.equals("~") || valor.equals(".")){
            return true;
        }
        return false;
    }
}
