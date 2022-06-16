package es.studium.ecoquizz.crud;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import es.studium.ecoquizz.activitys.LoginRegisterActivity;
import es.studium.ecoquizz.etc.Methods;
import es.studium.ecoquizz.login_register_fragments.RegisterFragment;

public class GetPlayer extends AsyncTask<Void, Void, String> {

    protected void onPreExecute() {
    }

    public String doInBackground(Void... argumentos) {
        try {
            //Creamos la URL de conexión al API
            URL url = new URL("http://192.168.1.136/ApiRest/jugadores.php");
            //Creamos la conexión HTTP
            HttpURLConnection myConnection = (HttpURLConnection) url.openConnection();
            //Establecer el método de comunicación.
            myConnection.setRequestMethod("GET");
            if (myConnection.getResponseCode() == 200) {
                //Conexión exitosa
                //Creamos Stream para la lectura de datos desde el servidor
                InputStream responseBody = myConnection.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                //Creamos el buffer de lectura
                BufferedReader bufferedReader = new BufferedReader(responseBodyReader);
                String line = "";
                StringBuilder responseStrBuilder = new StringBuilder();
                //Leemos el flujo de entrada
                while ((line = bufferedReader.readLine()) != null) {
                    responseStrBuilder.append(line);
                }
                //Parseamos respuesta en formato JSON
                Methods.playersJson = new JSONArray(responseStrBuilder.toString());

                responseBody.close();
                responseBodyReader.close();
                myConnection.disconnect();

            } else {
                //Error en la conexión
                Log.println(Log.ERROR, "Error", "¡Conexión fallida! 1");
            }

        } catch (Exception e) {
            Log.println(Log.ERROR, "Error", "¡Conexión fallida! 2");
        }

        return null;
    }
}
