package es.studium.ecoquizz.crud;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import es.studium.ecoquizz.models.Player;

public class PostPlayer extends AsyncTask<Void, Void, String> {

    Player player;

    public PostPlayer(Player player){
        this.player = player;
    }

    @Override
    protected String doInBackground(Void... voids) {

        try{
            //Creamos la URL de conexión al API
            URL url = new URL("http://192.168.1.136/ApiRest/jugadores.php");
            //Creamos la conexión HTTP
            HttpURLConnection myConnection = (HttpURLConnection) url.openConnection();
            //Establecer el método de comunicación.
            myConnection.setRequestMethod("POST");

            //Conexión exitosa
            String response = "";
            HashMap<String, String> postDataParams = new HashMap<String, String>();
            postDataParams.put("nombreJugador", this.player.getName());
            postDataParams.put("claveJugador", this.player.getPassword());
            postDataParams.put("puntuacionJugador", String.valueOf(this.player.getScore()));
            postDataParams.put("avatarJugador", String.valueOf(this.player.getIcon()));

            myConnection.setDoInput(true);
            myConnection.setDoOutput(true);

            OutputStream os = myConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();

            myConnection.getResponseCode();
            if(myConnection.getResponseCode() == 200){
                //Sucess
                myConnection.disconnect();
            }else{
                //Error
                Log.println(Log.ASSERT, "Error", "Error");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if(first){
                first = false;
            }else{
                result.append("&");
            }
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }
}
