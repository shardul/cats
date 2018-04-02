package github.shardul.cats.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Registers with fragment lifecycle callbacks to release resources when fragment is destroyed.
 * Created by Shardul on 02/04/18.
 */
public class AutoClearedValue<T> {

    T mValue;

    public AutoClearedValue(Fragment fragment, T value) {
        final FragmentManager fragmentManager = fragment.getFragmentManager();

        fragmentManager.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
            @Override
            public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
                if (f == fragment) {
                    AutoClearedValue.this.mValue = null;
                    fragmentManager.unregisterFragmentLifecycleCallbacks(this);
                } else {
                    super.onFragmentViewDestroyed(fm, f);
                }
            }
        }, false);

        mValue = value;

    }

    public T getValue() {
        return mValue;
    }
}
