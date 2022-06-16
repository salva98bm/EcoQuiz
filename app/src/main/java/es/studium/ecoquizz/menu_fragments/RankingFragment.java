package es.studium.ecoquizz.menu_fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;

import es.studium.ecoquizz.R;
import es.studium.ecoquizz.activitys.LoginRegisterActivity;
import es.studium.ecoquizz.activitys.MenuActivity;
import es.studium.ecoquizz.crud.GetPlayer;
import es.studium.ecoquizz.etc.Methods;
import es.studium.ecoquizz.etc.MyPlayerAdapter;
import es.studium.ecoquizz.models.Player;

public class RankingFragment extends Fragment {

    View view;
    ListView listView;
    ImageView imageView_back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.ranking_fragment, container, false);
        listView = view.findViewById(R.id.list_ranking);
        MyPlayerAdapter myPlayerAdapter = new MyPlayerAdapter(getContext(), R.layout.ranking_item, rellenarJugadores());
        listView.setAdapter(myPlayerAdapter);
        imageView_back = view.findViewById(R.id.imageView_back);
        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_container_menu, new MenuFragment());
                fragmentTransaction.commit();
                onDestroy();
            }
        });
        return view;
    }

    private ArrayList<Player> rellenarJugadores() {
        ArrayList<Player> playerList = Methods.getPlayers();
        ArrayList<Player> topPlayerList = new ArrayList<Player>();

        if(playerList.size()<10){
            for(int i=0;i<playerList.size();i++){
                topPlayerList.add(playerList.get(i));
            }
        }else{
            for(int i=0;i<10;i++){
                topPlayerList.add(playerList.get(i));
            }
        }
        return topPlayerList;
    }
}