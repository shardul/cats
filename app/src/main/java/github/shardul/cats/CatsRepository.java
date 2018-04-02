package github.shardul.cats;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import java.util.List;

import github.shardul.cats.core.AppExecutors;
import github.shardul.cats.db.CatsDatabase;
import github.shardul.cats.models.Cat;
import github.shardul.cats.network.CatsService;
import github.shardul.cats.network.FetchCatsTask;
import github.shardul.cats.network.FetchListener;

/**
 * Single access point to access cats
 * Created by Shardul on 31/03/18.
 */
public class CatsRepository {

    final CatsDatabase mCatsDatabase;
    final AppExecutors mAppExecutors;
    final CatsService mCatsService;

    private MediatorLiveData<List<Cat>> mObservableCats;

    private FetchCatsTask mFetchCatsTask;
    private FetchListener<List<Cat>> mListener;

    public CatsRepository(CatsDatabase catsDatabase, AppExecutors appExecutors,
                          CatsService catsService) {
        mCatsDatabase = catsDatabase;
        mAppExecutors = appExecutors;
        mCatsService = catsService;
        mObservableCats = new MediatorLiveData<>();

        mObservableCats.addSource(mCatsDatabase.catsDao().getAll(), new Observer<List<Cat>>() {
            @Override
            public void onChanged(@Nullable List<Cat> cats) {

                if (cats.isEmpty()) {
                    fetchNewCats();
                }

                CatsRepository.this.mObservableCats.postValue(cats);
            }
        });


    }

    public LiveData<List<Cat>> getObservableCats() {
        return mObservableCats;
    }

    public void fetchNewCats() {

        mAppExecutors.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mCatsDatabase.catsDao().deleteAll();
            }
        });

        mListener = new FetchListener<List<Cat>>() {
            @Override
            public void onFetchComplete(List<Cat> complete) {

                CatsRepository.this.mListener = null;

                mAppExecutors.getDiskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        mCatsDatabase.catsDao().insertAll(complete.toArray(new Cat[complete.size()]));
                    }
                });
            }

            @Override
            public void onFetchFailed(Exception e) {
            }
        };
        mFetchCatsTask = new FetchCatsTask(mCatsService, mListener);
        mAppExecutors.getNetworkIO().execute(mFetchCatsTask);
    }


}
