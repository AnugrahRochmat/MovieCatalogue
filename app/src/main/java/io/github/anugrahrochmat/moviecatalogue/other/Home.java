package io.github.anugrahrochmat.moviecatalogue.other;

import android.app.Application;
import android.content.Context;

import io.github.anugrahrochmat.moviecatalogue.helper.LocaleHelper;

public class Home extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
}
