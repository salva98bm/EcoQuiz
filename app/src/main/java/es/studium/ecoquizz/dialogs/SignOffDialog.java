package es.studium.ecoquizz.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;

import es.studium.ecoquizz.R;
import es.studium.ecoquizz.activitys.LoginRegisterActivity;
import es.studium.ecoquizz.etc.Methods;

public class SignOffDialog extends DialogFragment implements View.OnClickListener {

    Button buttonConfirm, buttonCancel;
    View view;

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        view = layoutInflater.inflate(R.layout.sign_off_dialog, null);
        buttonConfirm = view.findViewById(R.id.button_sign_off_dialog_confirm);
        buttonConfirm.setOnClickListener(this);
        buttonCancel = view.findViewById(R.id.button_sign_off_dialog_cancel);
        buttonCancel.setOnClickListener(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);

        return builder.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_sign_off_dialog_confirm:
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("username");
                editor.commit();

                Methods.stopMusic();

                getActivity().finish();
                Intent intent = new Intent(getActivity(), LoginRegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.button_sign_off_dialog_cancel:
                dismiss();
                break;
        }
    }
}
