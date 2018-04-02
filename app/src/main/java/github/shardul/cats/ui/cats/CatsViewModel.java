package github.shardul.cats.ui.cats;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import github.shardul.cats.CatsApplication;
import github.shardul.cats.CatsRepository;
import github.shardul.cats.models.Cat;

/**
 * Created by Shardul on 31/03/18.
 */

public class CatsViewModel extends AndroidViewModel {

    private final MediatorLiveData<List<Cat>> mObservableCats;
    private final CatsRepository catsRepository;

    public CatsViewModel(@NonNull Application application) {
        super(application);

        mObservableCats = new MediatorLiveData<>();
        mObservableCats.setValue(null);

        catsRepository = ((CatsApplication) application).getRepository();
        LiveData<List<Cat>> cats = catsRepository.getObservableCats();

        mObservableCats.addSource(cats, mObservableCats::setValue);
    }

    public LiveData<List<Cat>> getCats() {
        return mObservableCats;
    }

    public void fetchNewCats() {
        catsRepository.fetchNewCats();
    }

}
