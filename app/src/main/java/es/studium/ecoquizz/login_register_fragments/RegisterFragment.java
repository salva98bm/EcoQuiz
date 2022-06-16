package es.studium.ecoquizz.login_register_fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import es.studium.ecoquizz.R;
import es.studium.ecoquizz.activitys.LoginRegisterActivity;
import es.studium.ecoquizz.crud.GetPlayer;
import es.studium.ecoquizz.etc.Methods;
import es.studium.ecoquizz.models.Player;

public class RegisterFragment extends Fragment implements View.OnClickListener {

    View view;
    TextView textViewLogin;
    Button buttonRegister;
    EditText editText_username, editText_password, editText_repeat_password;

    public RegisterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.register_fragment, container, false);

        buttonRegister = view.findViewById(R.id.button_register);
        buttonRegister.setOnClickListener(this);
        textViewLogin = view.findViewById(R.id.textView_register_login);
        textViewLogin.setOnClickListener(this);
        editText_username = view.findViewById(R.id.editText_register_username);
        editText_password = view.findViewById(R.id.editText_register_password);
        editText_repeat_password = view.findViewById(R.id.editText_register_repeat_password);

        return view;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.textView_register_login:
                fragmentTransaction.replace(R.id.frame_container_login, new LoginFragment());
                fragmentTransaction.commit();
                break;
            case R.id.button_register:
                Snackbar snackbar = Snackbar.make(getView(), "", BaseTransientBottomBar.LENGTH_LONG)
                        .setBackgroundTint(Color.WHITE)
                        .setTextColor(Color.BLACK);
                if (Methods.nicknameRepeated(editText_username.getText().toString())) {
                    editText_username.setTextColor(Color.RED);
                    editText_password.setHintTextColor(R.color.principal_blue);
                    editText_repeat_password.setHintTextColor(R.color.principal_blue);
                    snackbar.setText(getResources().getString(R.string.snackbar_repeat_player) + " " + editText_username.getText().toString())
                            .show();
                } else if (editText_username.getText().length() == 0 || editText_password.getText().length() == 0 || editText_repeat_password.getText().length() == 0) {
                    snackbar.setText(R.string.snackbar_empty_fields)
                            .show();
                    if (editText_username.getText().length() == 0) {
                        editText_username.setHintTextColor(Color.RED);
                    } else {
                        editText_username.setTextColor(getResources().getColor(R.color.principal_blue));
                    }
                    if (editText_password.getText().length() == 0) {
                        editText_password.setHintTextColor(Color.RED);
                    } else {
                        editText_password.setTextColor(getResources().getColor(R.color.principal_blue));
                    }
                    if (editText_repeat_password.getText().length() == 0) {
                        editText_repeat_password.setHintTextColor(Color.RED);
                    } else {
                        editText_repeat_password.setTextColor(getResources().getColor(R.color.principal_blue));
                    }
                } else if (!editText_password.getText().toString().equals(editText_repeat_password.getText().toString())) {
                    editText_username.setTextColor(getResources().getColor(R.color.principal_blue));
                    snackbar.setText(R.string.snackbar_different_passwords)
                            .show();
                    editText_repeat_password.setText("");
                    editText_repeat_password.setHintTextColor(Color.RED);
                } else {
                    Player player = new Player(editText_username.getText().toString(), editText_password.getText().toString(), 0);
                    ChooseAvatarFragment chooseAvatarFragment = new ChooseAvatarFragment(player);
                    fragmentTransaction.replace(R.id.frame_container_login, chooseAvatarFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
        }
    }
}