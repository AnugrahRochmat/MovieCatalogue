package io.github.anugrahrochmat.moviecatalogue.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.anugrahrochmat.moviecatalogue.R;
import io.github.anugrahrochmat.moviecatalogue.ui.MainActivity;
import io.github.anugrahrochmat.moviecatalogue.util.other.LocaleHelper;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.spinner_language) Spinner spinnerLangSettings;
    ArrayAdapter<String> langAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.setting);

        langAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, getResources().getStringArray(R.array.language_option));
        spinnerLangSettings.setAdapter(langAdapter);

        if (LocaleHelper.getLanguage(this).equalsIgnoreCase("en")) {
            spinnerLangSettings.setSelection(langAdapter.getPosition("English"));
        } else if (LocaleHelper.getLanguage(this).equalsIgnoreCase("in")) {
            spinnerLangSettings.setSelection(langAdapter.getPosition("Indonesian"));
        }

        spinnerLangSettings.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                LocaleHelper.setLocale(this, "en");
                break;
            case 1:
                LocaleHelper.setLocale(this, "in");
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
