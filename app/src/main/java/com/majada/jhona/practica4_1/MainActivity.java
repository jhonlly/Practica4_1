package com.majada.jhona.practica4_1;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private EditText et1;
    private TextView tv1, tv4;
    private int n_random;
    int intentos = 0;
 private Button btn_verificar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText) findViewById(R.id.et1);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv4 = (TextView) findViewById(R.id.tv4);
        btn_verificar =(Button) findViewById(R.id.btn_verificar);
        //Añadiendo la informacion guardada al iniciar la actividad.
        SharedPreferences prefs = getSharedPreferences("datos", Context.MODE_PRIVATE);
        int puntuacion_guardada = prefs.getInt("puntuacion", 0);
        tv1.setText("Puntuacion: " +String.valueOf(puntuacion_guardada) );
    }
//Funcion para generar el numero aleatorio, lanza una notificación y muestra el boton verificar.
    public void generar(View v) {
        Random rn = new Random();
        n_random = rn.nextInt(10) + 1;
        Toast notificacion = Toast.makeText(this, "Numero generado, que comience el juego!", Toast.LENGTH_SHORT);
        notificacion.show();
        btn_verificar.setVisibility(View.VISIBLE);
    }

    public void verificar(View v) {
        //Obtengo el numero ingresado por el usuario.
        Integer numero_ingresado = Integer.parseInt(et1.getText().toString());
      //Si el numero es diferente a numero generado
         if (numero_ingresado != n_random) {
            //limpio el campo
           intentos++;
           et1.setText("");
           //envio una notificacion
           Toast notificacion = Toast.makeText(this, ":( Sigue intentando", Toast.LENGTH_SHORT);
           notificacion.show();
           //Sumo los intentos.
           tv4.setText("Numero de intentos: " + intentos);
        } else {
            SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();
            //Obtengo la puntuacion anterior en caso de que no haya ninguna puntuacion guardada, se asignara la puntuacion actual.
            int  puntuacion_anterior = preferencias.getInt("puntuacion", 1);
            editor.putInt("puntuacion",(puntuacion_anterior + 1));
            editor.commit();
             //Oculto el boton cuando el usuario obtiene un punto
             btn_verificar.setVisibility(View.INVISIBLE);
            Toast notificacion = Toast.makeText(this, "Has acerdato", Toast.LENGTH_SHORT);
            notificacion.show();
            tv1.setText("Puntuacion: "+ String.valueOf(puntuacion_anterior+1));
            intentos=0;
            et1.setText("");
            tv4.setText("Numero de intentos: " + intentos);

         }
    }
}
