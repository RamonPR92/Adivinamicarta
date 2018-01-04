package com.perez.ramon.adivinamicarta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Baraja miBaraja;
    int [] valores, valoresColumnaUno, valoresColumnaDos,valoresColumnaTres;
    private TextView[] cartas, primeraColumna, segundaColumna, terceraColumna;
    private Button btnColumnaUno, btnColumnaDos, btnColumnaTres;
    private int contadorClics;
    private int columnaElegida;
    private TextView estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        miBaraja = new Baraja();
        //Representa los valores en cada posicion
        valores = new int[9];
        //Columnas con los valores
        valoresColumnaUno = new int[3];
        valoresColumnaDos = new int[3];
        valoresColumnaTres = new int[3];
        contadorClics = 0;//es utilizado para controlar el flujo del juego, cuando sea 2, entonces el juego notifica el resultado
        estado = (TextView)findViewById(R.id.estadoJuego);//Barra se estado situada en la parte inferior

        primeraColumna = new TextView[]{
                (TextView) findViewById(R.id.posicionUno),
                (TextView) findViewById(R.id.posicionDos),
                (TextView) findViewById(R.id.posicionTres)
        };
        segundaColumna = new TextView[]{
                (TextView) findViewById(R.id.posicionCuatro),
                (TextView) findViewById(R.id.posicionCinco),
                (TextView) findViewById(R.id.posicionSeis)
        };
        terceraColumna = new TextView[]{
                (TextView) findViewById(R.id.posicionSiete),
                (TextView) findViewById(R.id.posicionOcho),
                (TextView) findViewById(R.id.posicionNueve)
        };

        btnColumnaUno = (Button)findViewById(R.id.btnColumnaUno);
        btnColumnaDos = (Button)findViewById(R.id.btnColumnaDos);
        btnColumnaTres = (Button)findViewById(R.id.btnColumnaTres);


        //INICIA EL JUEGO
        valores = miBaraja.barajear(); //BARAJEAN CARTAS
        tirarCartas();//CAMBIAN LOS VALORES EN PANTALLA


        btnColumnaUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                columnaElegida = 1;
                accionDeColumnaElegida();
            }
        });

        btnColumnaDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                columnaElegida = 2;
                accionDeColumnaElegida();
            }
        });

        btnColumnaTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                columnaElegida = 3;
                accionDeColumnaElegida();
            }
        });

    }

    /**
     * Cada que una columna es elegida, se aumenta el numero de clics en los botones [max 2 clics],
     * cuando se halla elegido una columna, se recogen las cartas tomando tres las tres columnas y el valor de la columna
     * despues se tiran las cartas y se envia un mensaje en la barra de estado.
     *
     * Cuando el valor clics sea 2 se realiza el calculo para adivinar la carta
     */
    private void accionDeColumnaElegida(){
        if (contadorClics == 0 || contadorClics == 1) {
            contadorClics++;
            valores = miBaraja.recogerCartas(valoresColumnaUno, valoresColumnaDos, valoresColumnaTres, columnaElegida);
            tirarCartas();
            estado.setText("Iteracion " + (contadorClics) + " del juego");

            if(contadorClics == 2){
                estado.setText("Tu carta ha sido adivinada");
                Toast.makeText(this,
                        "El numero que elegiste es el " + String.valueOf(valores[miBaraja.adivinarCarta() - 1]),
                        Toast.LENGTH_LONG).show();
            }
        }

    }

    /**
     * El metodo tirar cartas  es utilizado para cambiar el valor de los TextViews de la pantalla
     * y separar el array principal en tres mas pequeños que pasaran a ser las columnas
     */
    public void tirarCartas(){
        int  posicion = 0;//indica la posicion en el array principal
        for (int i = 0; i < primeraColumna.length; i++) {
            primeraColumna[i].setText(String.valueOf(valores[posicion]));//cambia el valor del texto en el textView
            valoresColumnaUno[i] = valores[posicion];//separa los valores del array principal en cada columna
            posicion++;//aumenta la posicion del array principal
            segundaColumna[i].setText(String.valueOf(valores[posicion]));
            valoresColumnaDos[i] = valores[posicion];
            posicion++;
            terceraColumna[i].setText(String.valueOf(valores[posicion]));
            valoresColumnaTres[i] = valores[posicion];
            posicion++;
        }
    }
}
