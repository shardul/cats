package github.shardul.cats.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import github.shardul.cats.CatsRepository;
import github.shardul.cats.core.AppExecutors;
import github.shardul.cats.db.CatsDatabase;
import github.shardul.cats.network.CatsService;

/**
 * Created by Shardul on 31/03/18.
 */

@Module(includes = AppContextModule.class)
public class CatsDataModule {

    @Provides
    @CatsApplicationScope
    public CatsDatabase database(@Named("application_context") Context context) {
        return Room.databaseBuilder(context, CatsDatabase.class, "cats.sqlite3").build();
    }

    @Provides
    @CatsApplicationScope
    public CatsRepository repository(CatsDatabase catsDatabase, AppExecutors appExecutors,
                                     CatsService catsService) {
        return new CatsRepository(catsDatabase, appExecutors, catsService);
    }
}
