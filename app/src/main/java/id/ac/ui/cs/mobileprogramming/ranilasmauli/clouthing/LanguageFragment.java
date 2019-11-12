package id.ac.ui.cs.mobileprogramming.ranilasmauli.clouthing;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.Locale;

public class LanguageFragment extends Fragment {

    private Button buttonSave;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private TextView textViewSelectedLang;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View languageView = inflater.inflate(R.layout.fragment_language, container, false);

        radioGroup = languageView.findViewById(R.id.radio_group_lang);
        textViewSelectedLang = languageView.findViewById(R.id.tv_lang_selected_msg);
        buttonSave = languageView.findViewById(R.id.bt_save_lang);
        buttonSave.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButton = languageView.findViewById(radioGroup.getCheckedRadioButtonId());
                textViewSelectedLang.setText(radioButton.getText());

                // do setup
                if (radioButton.getText().equals(R.id.lang_en)) setLocal("en", languageView);
                else if (radioButton.getText().equals(R.id.lang_id)) setLocal("ID", languageView);
            }
        });

        if (savedInstanceState == null) {
            // default lang English

            textViewSelectedLang.setText(((TextView) languageView.findViewById(R.id.lang_en)).getText());

            // do default setup
            setLocal("en", languageView);

        }
        return languageView;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void setLocal(String lang, View v) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);

        v.getContext().getResources().updateConfiguration(config, v.getContext().getResources().getDisplayMetrics());
    }
}
