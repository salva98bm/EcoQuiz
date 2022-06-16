package es.studium.ecoquizz.login_register_fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import es.studium.ecoquizz.R;
import es.studium.ecoquizz.crud.PostPlayer;
import es.studium.ecoquizz.models.Player;

public class ChooseAvatarFragment extends Fragment implements View.OnClickListener {

    View view;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    Player player;

    public ChooseAvatarFragment(Player player) {
        this.player = player;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.choose_avatar_fragment, container, false);

        imageView1 = view.findViewById(R.id.imageView_avatar1);
        imageView1.setOnClickListener(this);
        imageView2 = view.findViewById(R.id.imageView_avatar2);
        imageView2.setOnClickListener(this);
        imageView3 = view.findViewById(R.id.imageView_avatar3);
        imageView3.setOnClickListener(this);
        imageView4 = view.findViewById(R.id.imageView_avatar4);
        imageView4.setOnClickListener(this);
        imageView5 = view.findViewById(R.id.imageView_avatar5);
        imageView5.setOnClickListener(this);
        imageView6 = view.findViewById(R.id.imageView_avatar6);
        imageView6.setOnClickListener(this);
        imageView7 = view.findViewById(R.id.imageView_avatar7);
        imageView7.setOnClickListener(this);
        imageView8 = view.findViewById(R.id.imageView_avatar8);
        imageView8.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView_avatar1:
                player.setIcon(1);
                loadFragment(player);
                break;
            case R.id.imageView_avatar2:
                player.setIcon(2);
                loadFragment(player);
                break;
            case R.id.imageView_avatar3:
                player.setIcon(3);
                loadFragment(player);
                break;
            case R.id.imageView_avatar4:
                player.setIcon(4);
                loadFragment(player);
                break;
            case R.id.imageView_avatar5:
                player.setIcon(5);
                loadFragment(player);
                break;
            case R.id.imageView_avatar6:
                player.setIcon(6);
                loadFragment(player);
                break;
            case R.id.imageView_avatar7:
                player.setIcon(7);
                loadFragment(player);
                break;
            case R.id.imageView_avatar8:
                player.setIcon(8);
                loadFragment(player);
                break;
        }
    }

    public void loadFragment(Player player) {
        PostPlayer postPlayer = new PostPlayer(player);
        postPlayer.execute();

        Snackbar.make(getView(), R.string.snackbar_post_player, BaseTransientBottomBar.LENGTH_LONG)
                .setBackgroundTint(Color.WHITE)
                .setTextColor(Color.BLACK)
                .show();

        //Limpiamos los fragmentos en la pila
        getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container_login, new LoginFragment());
        fragmentTransaction.commit();
    }
}