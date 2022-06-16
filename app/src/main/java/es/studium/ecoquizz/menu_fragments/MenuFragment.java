package es.studium.ecoquizz.menu_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import es.studium.ecoquizz.R;

public class MenuFragment extends Fragment implements View.OnClickListener {

    Button button_ranking, button_settings, button_play;

    public MenuFragment() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_fragment, container, false);
        button_ranking = view.findViewById(R.id.button_menu_ranking);
        button_ranking.setOnClickListener(this);
        button_settings = view.findViewById(R.id.button_menu_settings);
        button_settings.setOnClickListener(this);
        button_play = view.findViewById(R.id.button_menu_play);
        button_play.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_menu_ranking:
                loadFragment(new RankingFragment(), false);
                break;
            case R.id.button_menu_settings:
                loadFragment(new SettingsFragment(), false);
                break;
            case R.id.button_menu_play:
                loadFragment(new PlayFragment(), true);
        }
    }

    public void loadFragment(Fragment fragment, boolean game){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container_menu, fragment);
        if(!game){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
}