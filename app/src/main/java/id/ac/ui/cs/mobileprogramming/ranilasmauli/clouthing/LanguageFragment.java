package id.ac.ui.cs.mobileprogramming.ranilasmauli.clouthing;

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
import android.widget.Toast;

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
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                group.check(checkedId);
            }
        });

        textViewSelectedLang = languageView.findViewById(R.id.tv_current_lang);
        buttonSave = languageView.findViewById(R.id.bt_save_lang);
        buttonSave.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButton = languageView.findViewById(radioGroup.getCheckedRadioButtonId());
                textViewSelectedLang.setText(radioButton.getText());

                Toast.makeText(view.getContext(), "RADIO BUTTON " + radioButton.getText(), Toast.LENGTH_SHORT).show();
                // do setup
                if (radioGroup.getCheckedRadioButtonId() == R.id.lang_en) setLocal("en", languageView);
                else if (radioGroup.getCheckedRadioButtonId() == R.id.lang_id) setLocal("ID", languageView);
            }
        });

        if (savedInstanceState == null) {
            Toast.makeText(languageView.getContext(), "DEFAULT", Toast.LENGTH_SHORT).show();

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
        v.getContext().getResources().getConfiguration().setLocale(locale);
        v.getContext().getResources().updateConfiguration(v.getResources().getConfiguration(),
                v.getContext().getResources().getDisplayMetrics());

        Toast.makeText(v.getContext(), "LANG: "+locale.getLanguage(), Toast.LENGTH_SHORT).show();

    }
}
