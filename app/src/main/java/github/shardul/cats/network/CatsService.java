package github.shardul.cats.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Shardul on 29/03/18.
 */

public interface CatsService {

    @GET("api/images/get?format=xml&results_per_page=20")
    Call<CatsResponse> getTwentyCatsOfCategory(@Query("category") String category);
}
