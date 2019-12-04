package com.apps.ifaldyprayanda.jetpackpro1.fav.tvshow;

import com.apps.ifaldyprayanda.jetpackpro1.R;
import com.apps.ifaldyprayanda.jetpackpro1.testing.SingleFragmentActivity;
import com.apps.ifaldyprayanda.jetpackpro1.utils.EspressoIdlingResource;
import com.apps.ifaldyprayanda.jetpackpro1.utils.RecyclerViewItemCountAssertion;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class FavTvShowFragmentTest {

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityTestRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private FavTvShowFragment favTvShowFragment = new FavTvShowFragment();

    @Before
    public void setUp()
    {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
        activityTestRule.getActivity().setFragment(favTvShowFragment);
    }

    @After
    public void teadDown()
    {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadTvShows()
    {
        onView(withId(R.id.rv_FavTv)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_FavTv)).check(new RecyclerViewItemCountAssertion(10));
        onView(withId(R.id.tv_item_title)).check(matches(isDisplayed()));
    }
}