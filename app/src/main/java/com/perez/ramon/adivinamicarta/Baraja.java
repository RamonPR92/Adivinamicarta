package com.perez.ramon.adivinamicarta;

/**
 * Created by Ramon on 03/01/2018.
 */

public class Baraja {

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
