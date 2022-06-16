package es.studium.ecoquizz.crud;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import es.studium.ecoquizz.models.Player;

public class UpdatePlayer extends AsyncTask<Void, Void, String> {

    Player player;

    public UpdatePlayer(Player player){
        this.player = player;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try
        {
            String response = "";
            Uri uri = new Uri.Builder()
                    .scheme("http")
                    .authority("192.168.1.136")
                    .path("/ApiRest/jugadores.php")
                    .appendQueryParameter("idJugador", String.valueOf(this.player.getId()))
                    .appendQueryParameter("nombreJugador", this.player.getName())
                    .appendQueryParameter("claveJugador", this.player.getPassword())
                    .appendQueryParameter("puntuacionJugador", String.valueOf(this.player.getScore()))
                    .appendQueryParameter("avatarJugador", String.valueOf(this.player.getIcon()))
                    .build();
            // Create connection
            URL url = new URL(uri.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("PUT");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            int responseCode=connection.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK)
            {
                String line;
                BufferedReader br=new BufferedReader(new
                        InputStreamReader(connection.getInputStream()));
                while ((line=br.readLine()) != null)
                {
                    response+=line;
                }
            }
            else
            {
                response="";
            }
            connection.getResponseCode();
            if (connection.getResponseCode() == 200)
            {
                // Success
                Log.println(Log.ASSERT,"Resultado", "Registro modificado:"+response);
                connection.disconnect();
            }
            else
            {
                // Error handling code goes here
                Log.println(Log.ASSERT,"Error", "Error");
            }
        }
        catch(Exception e)
        {
            Log.println(Log.ASSERT,"Excepción", e.getMessage());
        }
        return null;
    }
}

