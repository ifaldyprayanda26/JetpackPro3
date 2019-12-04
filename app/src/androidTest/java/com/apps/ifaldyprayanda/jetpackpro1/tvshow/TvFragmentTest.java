package com.apps.ifaldyprayanda.jetpackpro1.tvshow;

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

public class TvFragmentTest {

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private TvFragment tvFragment = new TvFragment();

    @Before
    public void setUp()
    {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
        activityRule.getActivity().setFragment(tvFragment);
    }

    @After
    public void tearDown()
    {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadTvShows()
    {
        onView(withId(R.id.rvTvShow)).check(new RecyclerViewItemCountAssertion(10));
    }

}