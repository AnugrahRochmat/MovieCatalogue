package io.github.anugrahrochmat.moviecatalogue.util.other;

import android.app.Application;
import android.content.Context;

import io.github.anugrahrochmat.moviecatalogue.util.helper.LocaleHelper;

public class Home extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
}
