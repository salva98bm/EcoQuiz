package es.studium.ecoquizz.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;

import es.studium.ecoquizz.R;
import es.studium.ecoquizz.etc.Methods;
import es.studium.ecoquizz.menu_fragments.MenuFragment;
import es.studium.ecoquizz.models.Player;

public class MenuActivity extends AppCompatActivity {

    public static Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Eliminamos la barra de notificaciones
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);

        Bundle bundle = this.getIntent().getExtras();
        player = (Player) bundle.getSerializable("player");

        loadFragment(new MenuFragment());

    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container_menu, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Methods.stopMusic();
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = this.getSharedPreferences("Login", Context.MODE_PRIVATE);
        String music = sharedPreferences.getString("music", "true");
        if(music.equals("true")){
            Methods.startMusic(this);
        }
    }
}