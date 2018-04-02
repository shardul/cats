package github.shardul.cats.ui.cats;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import github.shardul.cats.R;
import github.shardul.cats.binding.FragmentBindingComponent;
import github.shardul.cats.databinding.CatsItemBinding;
import github.shardul.cats.models.Cat;

/**
 * Created by Shardul on 02/04/18.
 */

public class CatsAdapter extends RecyclerView.Adapter<CatViewHolder> {

    private List<Cat> mCats;
    private FragmentBindingComponent mBindingComponent;

    public CatsAdapter(FragmentBindingComponent imageBindingComponent) {

        mBindingComponent = imageBindingComponent;
    }

    @Override
    public CatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        CatsItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.cats_item, parent,false,
                mBindingComponent);

        CatViewHolder holder = new CatViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(CatViewHolder holder, int position) {
        Cat cat = mCats.get(position);
        holder.setCat(cat);
    }

    @Override
    public int getItemCount() {
        return mCats == null ? 0 : mCats.size();
    }

    public void setCats(List<Cat> cats) {
        mCats = cats;
        notifyDataSetChanged();
    }
}
