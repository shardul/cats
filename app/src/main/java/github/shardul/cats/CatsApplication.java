package github.shardul.cats;

import android.app.Activity;
import android.app.Application;

import github.shardul.cats.di.AppContextModule;
import github.shardul.cats.di.CatsAppComponent;
import github.shardul.cats.di.DaggerCatsAppComponent;
import timber.log.Timber;

/**
 * Created by Shardul on 29/03/18.
 */
public class CatsApplication extends Application {

    private CatsAppComponent mCatsAppComponent;

    public static CatsAppComponent get(Activity activity) {
        return ((CatsApplication)activity.getApplication()).mCatsAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        mCatsAppComponent = DaggerCatsAppComponent.builder()
                .appContextModule(new AppContextModule(getApplicationContext())).build();

    }

    public CatsAppComponent getCatsAppComponent() {
        return mCatsAppComponent;
    }

    public CatsRepository getRepository() {
        return mCatsAppComponent.repository();
    }
}
