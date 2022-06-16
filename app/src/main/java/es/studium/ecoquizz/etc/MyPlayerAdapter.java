package es.studium.ecoquizz.etc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

import es.studium.ecoquizz.R;
import es.studium.ecoquizz.activitys.MenuActivity;
import es.studium.ecoquizz.models.Player;

public class MyPlayerAdapter extends ArrayAdapter<Player> {

    Context context;
    int layoutResource;
    ArrayList<Player> playerList;

    public MyPlayerAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Player> objects) {
        super(context, resource, objects);

        this.context = context;
        layoutResource = resource;
        playerList = objects;
    }

    //Executed once for each element in list
    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(layoutResource, parent, false);

        Player currentPlayer = playerList.get(position);
        ImageView imageView = view.findViewById(R.id.imageView_cardView_ranking);
        if(Methods.king(currentPlayer)){
            imageView.setImageResource(R.drawable.avatar_king);
        }else{
            imageView.setImageResource(chooseImage(currentPlayer));
        }
        TextView textView_playername = view.findViewById(R.id.textView_cadView_username_ranking);
        textView_playername.setText(currentPlayer.getName());
        TextView textView_playerScore = view.findViewById(R.id.textView_cardView_maxScore_ranking);
        textView_playerScore.setText(textView_playerScore.getText().toString() + " " + currentPlayer.getScore());
        ConstraintLayout constraintLayout = view.findViewById(R.id.constraintLayout_ranking);
        CardView cardView = view.findViewById(R.id.cardView_ranking);
        if(currentPlayer.getName().equals(MenuActivity.player.getName())){
            constraintLayout.setBackgroundColor(view.getResources().getColor(R.color.cream));
            textView_playername.setTextColor(view.getResources().getColor(R.color.principal_green));
            textView_playerScore.setTextColor(view.getResources().getColor(R.color.principal_green));
        }
        return view;
    }

    private int chooseImage(Player currentPlayer) {
        int image = 0;
        switch (currentPlayer.getIcon()){
            case 1:
                image = R.drawable.avatar1;
                break;
            case 2:
                image = R.drawable.avatar2;
                break;
            case 3:
                image = R.drawable.avatar3;
                break;
            case 4:
                image = R.drawable.avatar4;
                break;
            case 5:
                image = R.drawable.avatar5;
                break;
            case 6:
                image = R.drawable.avatar6;
                break;
            case 7:
                image = R.drawable.avatar7;
                break;
            case 8:
                image = R.drawable.avatar8;
                break;
        }
        return image;
    }
}
