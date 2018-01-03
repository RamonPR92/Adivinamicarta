package com.perez.ramon.adivinamicarta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ramon on 03/01/2018.
 */

public class Baraja {


    /**
     * Recoge o fusiona tres arreglos pequeños en uno mas grande
     * @param uno
     * @param dos
     * @param tres
     * @param columnaElegida representa al array que debera quedar siempre en medio de los otros dos
     * @return un array compuesto con los tres pasados como parametro, pero con la columna elegida, en medio de los otros dos arreglos
     */
    public int[] recogerCartas(int[] uno, int[]dos, int[]tres, int columnaElegida){

        switch(columnaElegida){
            case 1:
                return ordenarColumna(dos,uno,tres);

            case 2:
                return ordenarColumna(uno, dos, tres);

            default://En el caso que sea  la columna 3
                return ordenarColumna(uno, tres, dos);
        }
    }

    /**
     * Ordena los tres arreglos en uno mas grande, pero ya estan pasados en un orden
     * @param primero
     * @param segundo
     * @param tercero
     * @return un array en el orden enviado
     */
    private int[] ordenarColumna(int [] primero, int[] segundo, int[] tercero){
         int[] cartasOrdenadas = new int[9];
         int posicionArrayPrincipal = 0;

        for (int i = 0; i < cartasOrdenadas.length ; i++) {
            for (int j = 0; j < primero.length; j++) {
                cartasOrdenadas[posicionArrayPrincipal] = primero[j];
                posicionArrayPrincipal++;//0 a 2
            }
            for (int j = 0; j < primero.length; j++) {
                cartasOrdenadas[posicionArrayPrincipal] = segundo[j];
                posicionArrayPrincipal++;// 3 a 5
            }
            for (int j = 0; j < primero.length; j++) {
                cartasOrdenadas[posicionArrayPrincipal] = tercero[j];
                posicionArrayPrincipal++;// 6 a 8
            }
        }
        return cartasOrdenadas;
    }

    public int adivinarCarta(){
        return  5;
    }

    /**
     * Genera 9 numeros aleatorios entre 1 y 9 , sin repetirlos,
     * esto simulará cuando se barajean las cartas al inicio del juego
     * @return un array de numeros aleatorios sin repetir
     */
    public int[] barajear(){
        int [] cartasBarajeadas = new int[9];
        for (int i = 0; i < cartasBarajeadas.length ; i++) {
            int numAleatorio = (int ) (Math.random() * 9 + 1);//Numero de 1 a 9
            if(numeroRepetido(numAleatorio,cartasBarajeadas)){ //Si el numero esta repetido
                i--; //conserva la posicion del array
            }else{
                cartasBarajeadas[i] = numAleatorio;//mete el numero
            }
        }
        return cartasBarajeadas;
    }

    /**
     * Analiza si el numero se encuentra ya en el array
     * @param numeroAleatorio numero a comparar
     * @param numerosSinRepetir array a comparar
     * @return verdadero si esta repetido o falso si no esta repetido
     */
    private boolean numeroRepetido(int numeroAleatorio, int[] numerosSinRepetir){
        boolean repetido = false;
        for (int i = 0; i < numerosSinRepetir.length ; i++) {
            if(numeroAleatorio == numerosSinRepetir[i]){
                repetido = true;
            }
        }
        return repetido;
    }

    enum cartas{
        CERO,UNO,DOS, TRES, CUATRO,CINCO,SEIS,SIETE,OCHO
    }
}
