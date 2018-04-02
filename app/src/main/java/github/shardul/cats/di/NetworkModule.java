package github.shardul.cats.di;

import android.content.Context;

import java.io.File;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

/**
 * Created by Shardul on 29/03/18.
 */

@Module(includes = AppContextModule.class)
public class NetworkModule {

    @Provides
    @CatsApplicationScope
    public OkHttpClient okHttpClient(Cache cache, HttpLoggingInterceptor loggingInterceptor) {

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();
    }

    @Provides
    @CatsApplicationScope
    public HttpLoggingInterceptor loggingInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        });
    }

    @Provides
    @CatsApplicationScope
    public File cacheFile(@Named("application_context") Context context) {
        return new File(context.getCacheDir(), "ok_http_cache");
    }

    @Provides
    @CatsApplicationScope
    public Cache cache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1024 * 1024);
    }
}
