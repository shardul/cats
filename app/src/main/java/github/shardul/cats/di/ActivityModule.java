package github.shardul.cats.di;

import android.app.Activity;
import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Shardul on 29/03/18.
 */
@Module
public class ActivityModule {

    final Context mContext;

    public ActivityModule(Activity activity) {
        mContext = activity;
    }

    @Provides
    @Named("activity_context")
    public Context getmContext() {
        return mContext;
    }
}
