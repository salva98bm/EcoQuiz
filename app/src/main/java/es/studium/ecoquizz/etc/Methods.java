package es.studium.ecoquizz.etc;

import android.content.Context;
import android.media.MediaPlayer;

import org.json.JSONArray;

import java.util.ArrayList;

import es.studium.ecoquizz.R;
import es.studium.ecoquizz.activitys.LoginRegisterActivity;
import es.studium.ecoquizz.crud.GetPlayer;
import es.studium.ecoquizz.crud.GetQuestions;
import es.studium.ecoquizz.models.Player;
import es.studium.ecoquizz.models.Question;

public class Methods {

    public static JSONArray playersJson;
    public static JSONArray questionsJson;
    static MediaPlayer mediaPlayer = new MediaPlayer();
    static MediaPlayer mediaPlayer2 = new MediaPlayer();

    public static boolean king(Player player) {
        Boolean rey = false;
        try {
            GetPlayer getPlayer = new GetPlayer();
            getPlayer.execute();

            ArrayList<Player> players = getPlayers();
            if (player.getName().equals(players.get(0).getName())) {
                rey = true;
            }
        } catch (Exception e) {
        }
        return rey;
    }

    public static ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<Player>();
        try {
            GetPlayer getPlayer = new GetPlayer();
            getPlayer.execute();
            Thread.sleep(100);
            int i = 0;
            while (playersJson.getJSONObject(i) != null) {
                int id = Integer.parseInt(playersJson.getJSONObject(i).getString("idJugador"));
                String name = playersJson.getJSONObject(i).getString("nombreJugador");
                String password = playersJson.getJSONObject(i).getString("claveJugador");
                int score = Integer.parseInt(playersJson.getJSONObject(i).getString("puntuacionJugador"));
                int avatar = Integer.parseInt(playersJson.getJSONObject(i).getString("avatarJugador"));

                players.add(new Player(id, name, password, score, avatar));
                i++;
            }
        } catch (Exception e) {}

        return players;
    }

    public static ArrayList<Question> getQuestions() {
        ArrayList<Question> questions = new ArrayList<Question>();
        try{
            GetQuestions getQuestions = new GetQuestions();
            getQuestions.execute();
            Thread.sleep(100);
            int i = 0;
            while(questionsJson.getJSONObject(i) != null){
                int id = Integer.parseInt(questionsJson.getJSONObject(i).getString("idPregunta"));
                String enunciado = questionsJson.getJSONObject(i).getString("enunciadoPregunta");
                String pregunta1 = questionsJson.getJSONObject(i).getString("respuesta1Pregunta");
                String pregunta2 = questionsJson.getJSONObject(i).getString("respuesta2Pregunta");
                String pregunta3 = questionsJson.getJSONObject(i).getString("respuesta3Pregunta");
                String pregunta4 = questionsJson.getJSONObject(i).getString("respuesta4Pregunta");

                questions.add(new Question(id, enunciado, pregunta1, pregunta2, pregunta3, pregunta4));
                i++;
            }
        }catch (Exception e){}

        return questions;
    }

    public static int setAvatar(int icon) {
        int resource;
        switch (icon){
            case 1:
                resource = R.drawable.avatar1;
                break;
            case 2:
                resource = R.drawable.avatar2;
                break;
            case 3:
                resource = R.drawable.avatar3;
                break;
            case 4:
                resource = R.drawable.avatar4;
                break;
            case 5:
                resource = R.drawable.avatar5;
                break;
            case 6:
                resource = R.drawable.avatar6;
                break;
            case 7:
                resource = R.drawable.avatar7;
                break;
            case 8:
                resource = R.drawable.avatar8;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + icon);
        }
        return resource;
    }

    public static boolean nicknameRepeated(String username) {
        Boolean repeated = false;

        ArrayList<Player> players = Methods.getPlayers();

        for (int i = 0; i < players.size(); i++) {
            if (username.equals(players.get(i).getName())) {
                repeated = true;
            }
        }
        return repeated;
    }

    public static void startMusic(Context context){
        if(!mediaPlayer.isPlaying()){
            mediaPlayer = MediaPlayer.create(context, R.raw.fondo);
            mediaPlayer.setLooping(true);
            mediaPlayer.setVolume((float)0.5, (float)0.5);
            mediaPlayer.start();
        }
    }

    public static void stopMusic(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }

    public static void successSound(Context context){
        mediaPlayer2 = MediaPlayer.create(context, R.raw.success);
        mediaPlayer2.start();
    }

    public static void errorSound(Context context){
        mediaPlayer2 = MediaPlayer.create(context, R.raw.error);
        mediaPlayer2.start();
    }
}
