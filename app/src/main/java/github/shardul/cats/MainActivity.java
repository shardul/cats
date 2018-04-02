package github.shardul.cats;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import javax.inject.Inject;

import github.shardul.cats.db.CatsDatabase;
import github.shardul.cats.models.Cat;
import github.shardul.cats.network.CatsService;
import github.shardul.cats.network.FetchCatsTask;

public class MainActivity extends AppCompatActivity {

    @Inject
    CatsService mCatsService;

    @Inject
    CatsDatabase mCatsDatabase;

    private FetchCatsTask mFetchCatsTask;
    private List<Cat> cats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
