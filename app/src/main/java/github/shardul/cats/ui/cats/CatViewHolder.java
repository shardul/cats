package github.shardul.cats.ui.cats;

import android.support.v7.widget.RecyclerView;

import github.shardul.cats.databinding.CatsItemBinding;
import github.shardul.cats.models.Cat;

/**
 * Created by Shardul on 02/04/18.
 */

public class CatViewHolder extends RecyclerView.ViewHolder {

    CatsItemBinding mBinding;

    public CatViewHolder(CatsItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public void setCat(Cat cat) {
        mBinding.setCat(cat);
    }
}
