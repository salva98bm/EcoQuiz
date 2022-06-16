package es.studium.ecoquizz.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import es.studium.ecoquizz.R;
import es.studium.ecoquizz.activitys.MenuActivity;
import es.studium.ecoquizz.crud.UpdatePlayer;

public class ChangePasswordDialog extends DialogFragment implements View.OnClickListener {

    Button button_cancel, button_confirm;
    EditText editText_current_password, editText_new_password, editText_repeat_password;
    View view;

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        view = layoutInflater.inflate(R.layout.change_password_dialog, null);

        button_cancel = view.findViewById(R.id.button_change_password_cancel);
        button_cancel.setOnClickListener(this);
        button_confirm = view.findViewById(R.id.button_change_password_confirm);
        button_confirm.setOnClickListener(this);
        editText_current_password = view.findViewById(R.id.editText_current_password);
        editText_new_password = view.findViewById(R.id.editText_new_password);
        editText_repeat_password = view.findViewById(R.id.editText_repeat_new_password);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);

        return builder.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_change_password_cancel:
                dismiss();
                break;
            case R.id.button_change_password_confirm:
                Snackbar snackbar = Snackbar.make(view, "", BaseTransientBottomBar.LENGTH_LONG)
                        .setBackgroundTint(Color.WHITE)
                        .setTextColor(Color.RED);
                if(!editText_current_password.getText().toString().equals(MenuActivity.player.getPassword())){
                    editText_current_password.setText("");
                    snackbar.setText("La contraseña actual no es correcta.").show();
                }else if(editText_new_password.getText().length() == 0 || editText_repeat_password.getText().length() == 0){
                    snackbar.setText("Debes rellenar todos los campos.").show();
                }else if(!editText_new_password.getText().toString().equals(editText_repeat_password.getText().toString())){
                    editText_repeat_password.setText("");
                    snackbar.setText("Las contraseñas no coinciden.").show();
                }else{
                    MenuActivity.player.setPassword(editText_new_password.getText().toString());
                    UpdatePlayer updatePlayer = new UpdatePlayer(MenuActivity.player);
                    updatePlayer.execute();
                    dismiss();
                }
                break;
        }
    }
}
