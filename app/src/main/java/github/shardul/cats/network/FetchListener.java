package github.shardul.cats.network;

/**
 * Base fetch mListener implementation to use with fetch tasks
 * Created by Shardul on 30/03/18.
 */
public interface FetchListener<T> {

    public void onFetchComplete(T complete);
    public void onFetchFailed(Exception e);
}
