package github.shardul.cats.network;

import java.lang.ref.WeakReference;

import retrofit2.Call;

/**
 * Provides base implementation for fetch tasks
 * Created by Shardul on 30/03/18.
 */
public abstract class FetchTask<T> implements Runnable {

    protected WeakReference<FetchListener<T>> mListener;
    protected Call mCall;

    public void fetchComplete(T fetched) {
        if (mListener.get() != null) {
            mListener.get().onFetchComplete(fetched);
        }
    }
    public void fetchFailed(Exception e) {
        if (mListener != null) {
            mListener.get().onFetchFailed(e);
        }
    }

    public void cancel() {
        mCall.cancel();
    }
}
