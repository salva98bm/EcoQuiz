package es.studium.ecoquizz.login_register_fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import es.studium.ecoquizz.activitys.MenuActivity;
import es.studium.ecoquizz.R;
import es.studium.ecoquizz.etc.Methods;
import es.studium.ecoquizz.models.Player;

public class LoginFragment extends Fragment implements View.OnClickListener {

    View view;
    TextView textViewCreateAccount;
    Button button_sign_in;
    EditText editText_username, editText_password;
    Player player = new Player();

    public LoginFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_fragment, container, false);

        textViewCreateAccount = view.findViewById(R.id.textView_login_register);
        textViewCreateAccount.setOnClickListener(this);
        button_sign_in = view.findViewById(R.id.button_login);
        button_sign_in.setOnClickListener(this);
        editText_username = view.findViewById(R.id.editText_login_username);
        editText_password = view.findViewById(R.id.editText_login_password);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textView_login_register:
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_container_login, new RegisterFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.button_login:
                if(access(editText_username.getText().toString(), editText_password.getText().toString())){
                    saveLogin();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("player", player);
                    Intent intent = new Intent(getActivity(), MenuActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    getActivity().finish();
                }else{
                    Snackbar.make(getView(), R.string.snackbar_wrong_login, BaseTransientBottomBar.LENGTH_LONG)
                            .setBackgroundTint(Color.WHITE)
                            .setTextColor(Color.BLACK)
                            .show();
                }
                break;
        }
    }

    private boolean access(String username, String password) {
        Boolean access = false;

        ArrayList<Player> players = Methods.getPlayers();

        for(int i=0;i<players.size();i++){
            if(username.equals(players.get(i).getName()) && password.equals(players.get(i).getPassword())){
                player = players.get(i);
                access = true;
            }
        }
        return access;
    }

    private void saveLogin() {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("username", player.getName());
            editor.commit();
    }
}