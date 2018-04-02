package github.shardul.cats.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import github.shardul.cats.core.AppExecutors;
import github.shardul.cats.network.CatsService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.SimpleXmlConverterFactory;

/**
 * Created by Shardul on 29/03/18.
 * We only want these to be exposed to outside world
 */
@Module(includes = {NetworkModule.class})
public class CatsServicesModule {

    @Provides
    @CatsApplicationScope
    public Gson gson(){

        GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }

    @Provides
    @CatsApplicationScope
    public CatsService catsService(Retrofit retrofit){
        return retrofit.create(CatsService.class);
    }

    @Provides
    @CatsApplicationScope
    public Retrofit retrofit(OkHttpClient httpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl("http://thecatapi.com/")
                .client(httpClient)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
    }

    @Provides
    @CatsApplicationScope
    public AppExecutors executor() {
        return new AppExecutors();
    }
}
