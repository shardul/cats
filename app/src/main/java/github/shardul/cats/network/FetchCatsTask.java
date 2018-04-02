package github.shardul.cats.network;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import github.shardul.cats.BuildConfig;
import github.shardul.cats.models.Cat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Makes API call to cat api using CatService, and parses them to send it back to mCall
 * Created by Shardul on 30/03/18.
 */
public class FetchCatsTask extends FetchTask<List<Cat>> implements Callback<CatsResponse> {

    private CatsService mService;

    public FetchCatsTask(CatsService service, FetchListener<List<Cat>> listener) {
        mService = service;
        mListener = new WeakReference<FetchListener<List<Cat>>>(listener);
    }

    @Override
    public void run() {
        Call<CatsResponse> catsCall = mService.getTwentyCatsOfCategory(BuildConfig.CAT_CATEGORY);
        catsCall.enqueue(this);

        mCall = catsCall;
    }

    @Override
    public void onResponse(Call<CatsResponse> call, Response<CatsResponse> response) {

        if (response.isSuccessful()) {
            if (response.body() != null) {
                if (response.body().data.images.imageItems.isEmpty()) {
                    this.fetchFailed(new Exception("no cats live here, try dogs may be ?"));
                } else {
                    ArrayList<Cat> cats = new ArrayList<Cat>();

                    for(Image imageItem: response.body().data.images.imageItems) {
                        cats.add(new Cat(imageItem.id, imageItem.url, imageItem.sourceUrl));
                    }

                    this.fetchComplete(cats);
                }
            }
        } else {
            this.fetchFailed(new Exception("we are not on earth anymore ?"));
        }
    }

    @Override
    public void onFailure(Call<CatsResponse> call, Throwable t) {
        this.fetchFailed(new Exception(t.getMessage()));
    }
}
