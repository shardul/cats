package github.shardul.cats.binding;

import android.databinding.DataBindingComponent;
import android.support.v4.app.Fragment;

/**
 * Series of data binding adapters to be use when data binding
 * Created by Shardul on 31/03/18.
 */
public class FragmentBindingComponent implements DataBindingComponent {

    private final ImageBindingAdapter mImageBindingAdapter;

    public FragmentBindingComponent(Fragment fragment) {
        mImageBindingAdapter = new ImageBindingAdapter(fragment);
    }

    @Override
    public ImageBindingAdapter getImageBindingAdapter() {
        return mImageBindingAdapter;
    }
}
