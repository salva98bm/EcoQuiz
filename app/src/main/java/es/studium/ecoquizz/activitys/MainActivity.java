package es.studium.ecoquizz.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import es.studium.ecoquizz.R;
import es.studium.ecoquizz.etc.Methods;
import es.studium.ecoquizz.models.Player;

public class MainActivity extends AppCompatActivity {

    ImageView imageView_logo;
    TextView textView_appName, textView_by, textView_company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Animaciones
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.up);
        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.down);

        imageView_logo = findViewById(R.id.imageView_logo);
        imageView_logo.setAnimation(animation2);
        textView_appName = findViewById(R.id.textView_appName);
        textView_appName.setAnimation(animation2);
        textView_by = findViewById(R.id.textView_by);
        textView_by.setAnimation(animation1);
        textView_company = findViewById(R.id.textView_company);
        textView_company.setAnimation(animation1);

        new Handler().postDelayed(() -> {
            SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
            if (sharedPreferences.getString("username", "") != null && !sharedPreferences.getString("username", "").equals("")) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("player", getPlayer(sharedPreferences.getString("username", "")));
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }else{
                Intent intent = new Intent(MainActivity.this, LoginRegisterActivity.class);
                startActivity(intent);
            }
            finish();
        }, 2500); //2500
    }

    private Player getPlayer(String username) {
        ArrayList<Player> players = Methods.getPlayers();
        Player player = new Player();

        for(int i=0;i<players.size();i++){
            if(username.equals(players.get(i).getName())){
                player = players.get(i);
            }
        }
        return player;
    }
}