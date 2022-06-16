package es.studium.ecoquizz.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import es.studium.ecoquizz.R;

public class ScoreDialog extends DialogFragment implements View.OnClickListener {

    TextView textView;
    int score, mode;
    Button button;

    public ScoreDialog(int score, int mode){
        this.score = score;
        this.mode = mode;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.score_dialog, null);

        textView = view.findViewById(R.id.textView_score_dialog);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);

        switch (mode){
            case 1:
                textView.setText("Tu puntuación ha sido " + score + ".\nNo superaste tu récord, ¡vuelve a intentarlo!");
                break;
            case 2:
                textView.setText("Tu puntuación ha sido " + score + "!!\nHas superado tu récord!!");
                break;
            case 3:
                textView.setText("Tu puntuación ha sido " + score + "!!\nHas obtenido la máxima puntuación!!");
                break;
        }

        return builder.create();
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
