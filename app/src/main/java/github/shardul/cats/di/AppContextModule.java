package github.shardul.cats.di;

import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Shardul on 29/03/18.
 */

@Module
public class AppContextModule {

    final Context mContext;

    public AppContextModule(Context context) {
        mContext = context;
    }

    @Provides
    @CatsApplicationScope
    @Named("application_context")
    public Context context() {
        return mContext;
    }
}
