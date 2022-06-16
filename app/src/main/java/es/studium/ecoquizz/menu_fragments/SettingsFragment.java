package es.studium.ecoquizz.menu_fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import es.studium.ecoquizz.R;
import es.studium.ecoquizz.activitys.MenuActivity;
import es.studium.ecoquizz.crud.UpdatePlayer;
import es.studium.ecoquizz.dialogs.ChangePasswordDialog;
import es.studium.ecoquizz.dialogs.SignOffDialog;
import es.studium.ecoquizz.etc.Methods;

public class SettingsFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    View view;
    EditText editText_username;
    TextView textView_label_sign_off;
    Button button_change_password, button_sign_off, button_back_to_menu;
    ImageView imageView_edit_username;
    Switch aSwitch_music, aSwitch_sounds;

    public SettingsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.settings_fragment, container, false);

        button_change_password = view.findViewById(R.id.button_settings_change_password);
        button_change_password.setOnClickListener(this);
        button_sign_off = view.findViewById(R.id.button_settings_signOff);
        button_sign_off.setOnClickListener(this);
        imageView_edit_username = view.findViewById(R.id.imageView_edit);
        imageView_edit_username.setOnClickListener(this);
        editText_username = view.findViewById(R.id.editText_settings_username);
        editText_username.setText(MenuActivity.player.getName());
        textView_label_sign_off = view.findViewById(R.id.textView_repeat_user);
        aSwitch_music = view.findViewById(R.id.switch_music);
        aSwitch_music.setOnCheckedChangeListener(this);
        aSwitch_sounds = view.findViewById(R.id.switch_sound);
        aSwitch_sounds.setOnCheckedChangeListener(this);
        button_back_to_menu = view.findViewById(R.id.button_back_to_menu);
        button_back_to_menu.setOnClickListener(this);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        String music = sharedPreferences.getString("music", "true");
        String sound = sharedPreferences.getString("sound", "true");
        if(music.equals("true")){
            aSwitch_music.setChecked(true);
        }else if(music.equals("false")){
            aSwitch_music.setChecked(false);
        }
        if(sound.equals("true")){
            aSwitch_sounds.setChecked(true);
        }else if(sound.equals("false")){
            aSwitch_sounds.setChecked(false);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_settings_signOff:
                SignOffDialog signOffDialog = new SignOffDialog();
                signOffDialog.show(getActivity().getSupportFragmentManager(), "Sign Off Dialog");
                textView_label_sign_off.setVisibility(View.INVISIBLE);
                break;
            case R.id.button_settings_change_password:
                ChangePasswordDialog changePasswordDialog = new ChangePasswordDialog();
                changePasswordDialog.show(getActivity().getSupportFragmentManager(), "Change Password Dialog");
                textView_label_sign_off.setVisibility(View.INVISIBLE);
                break;
            case R.id.imageView_edit:
                if(!Methods.nicknameRepeated(editText_username.getText().toString())){
                    textView_label_sign_off.setText(getResources().getString(R.string.label_update_user));
                    textView_label_sign_off.setTextColor(getResources().getColor(R.color.principal_green));
                    textView_label_sign_off.setVisibility(View.VISIBLE);
                    editText_username.clearFocus();

                    MenuActivity.player.setName(editText_username.getText().toString());

                    UpdatePlayer updatePlayer = new UpdatePlayer(MenuActivity.player);
                    updatePlayer.execute();

                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", MenuActivity.player.getName());
                    editor.commit();

                }else{
                    textView_label_sign_off.setText(getResources().getString(R.string.label_repeat_user));
                    textView_label_sign_off.setTextColor(getResources().getColor(R.color.red_light));
                    textView_label_sign_off.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.button_back_to_menu:
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_container_menu, new MenuFragment());
                fragmentTransaction.commit();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.switch_music:
                if(isChecked){
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("music", "true");
                    editor.commit();
                    Methods.startMusic(this.getContext());
                }else{
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("music", "false");
                    editor.commit();
                    Methods.stopMusic();
                }
                break;
            case R.id.switch_sound:
                if(isChecked){
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("sound", "true");
                    editor.commit();
                }else{
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("sound", "false");
                    editor.commit();
                }
                break;
        }
    }
}