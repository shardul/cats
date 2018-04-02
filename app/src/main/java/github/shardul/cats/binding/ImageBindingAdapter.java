package github.shardul.cats.binding;

import android.databinding.BindingAdapter;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * When bound, imageUrl = @{url} can be used in layout to download images directly.
 * Created by Shardul on 31/03/18.
 */
public class ImageBindingAdapter {

    final Fragment mFragment;

    public ImageBindingAdapter(Fragment fragment) {
        mFragment = fragment;
    }

    @BindingAdapter("imageUrl")
    public void setImage(ImageView image, String url) {
        Glide.with(mFragment).load(url).into(image);
    }
}
