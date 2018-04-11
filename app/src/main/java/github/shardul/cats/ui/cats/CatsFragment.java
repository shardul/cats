package github.shardul.cats.ui.cats;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import github.shardul.cats.R;
import github.shardul.cats.binding.FragmentBindingComponent;
import github.shardul.cats.databinding.CatsFragmentBinding;
import github.shardul.cats.models.Cat;
import github.shardul.cats.util.AutoClearedValue;

/**
 * Created by Shardul on 31/03/18.
 */

public class CatsFragment extends Fragment {

    private final static int MAX_COLUMNS = 2;

    private AutoClearedValue<CatsFragmentBinding> mBinding;
    private AutoClearedValue<CatsAdapter> mAdapter;
    private CatsViewModel mCatsViewModel;

    private List<Cat> mCats;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCatsViewModel = ViewModelProviders.of(this).get(CatsViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        CatsFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.cats_fragment,
                container,false);
        binding.setFragment(this);

        mBinding = new AutoClearedValue<CatsFragmentBinding>(this, binding);
        mAdapter = new AutoClearedValue<CatsAdapter>(this, new CatsAdapter(
                new FragmentBindingComponent(this)));

        RecyclerView recyclerView = mBinding.getValue().catsFragmentRecyclerView;
        recyclerView.setHasFixedSize(true);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), MAX_COLUMNS);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(mAdapter.getValue());

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        subscribe();
    }

    public void refreshClicked(){
        mCatsViewModel.fetchNewCats();
    }

    private void subscribe() {

        mCatsViewModel.getCats().observe(this, new Observer<List<Cat>>() {
            @Override
            public void onChanged(@Nullable List<Cat> cats) {
                if (cats == null || cats.isEmpty()) {
                    loading(true);
                } else {
                    mAdapter.getValue().submitList(cats);
                    loading(false);
                }

                mCats = cats;
            }
        });
    }

    private void loading(boolean isLoading) {
        if(isLoading) {

            if (mCats != null) {
                Snackbar.make(mBinding.getValue().catsFragmentRefresh,
                        R.string.cats_fragment_loading_message, Snackbar.LENGTH_SHORT).show();
            }

            mBinding.getValue().catsFragmentProgress.setVisibility(View.VISIBLE);
            mBinding.getValue().catsFragmentRefresh.setVisibility(View.GONE);
        } else {
            mBinding.getValue().catsFragmentProgress.setVisibility(View.GONE);
            mBinding.getValue().catsFragmentRefresh.setVisibility(View.VISIBLE);
        }
    }
}
