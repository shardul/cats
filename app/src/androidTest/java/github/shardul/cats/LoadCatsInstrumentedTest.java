package github.shardul.cats;

import android.app.Activity;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
/**
 * Checks when refresh is tapped progress view is shown,
 * and when refresh is complete refresh button is visible again.
 * */
public class LoadCatsInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void loadCats() {

        final int progressId = R.id.cats_fragment_progress;
        final int buttonId = R.id.cats_fragment_refresh;

        onView(withId(buttonId)).perform(click());

        onView(withId(progressId)).check(matches(isDisplayed()));

        final VisibilityIdlingResource progressIdlingResource = new VisibilityIdlingResource(
                mMainActivityRule.getActivity() , progressId);


        IdlingRegistry.getInstance().register(progressIdlingResource);

        onView(withId(buttonId)).check(matches(isDisplayed()));
    }

}

class VisibilityIdlingResource implements IdlingResource {

    final Activity mActivity;
    final int mResource;
    ResourceCallback mResourceCallback;

    public VisibilityIdlingResource(Activity activity, int resource) {
        this.mActivity = activity;
        this.mResource = resource;
    }

    @Override
    public String getName() {
        return "loading progress";
    }

    @Override
    public boolean isIdleNow() {
        final boolean idleNow = mActivity.findViewById(mResource).getVisibility() == View.GONE;
        if (idleNow) {
            if (mResourceCallback != null) {
                mResourceCallback.onTransitionToIdle();
            }
        }
        return idleNow;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mResourceCallback = callback;
    }
}
