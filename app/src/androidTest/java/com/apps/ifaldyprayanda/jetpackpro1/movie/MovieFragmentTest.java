package com.apps.ifaldyprayanda.jetpackpro1.movie;

import com.apps.ifaldyprayanda.jetpackpro1.R;
import com.apps.ifaldyprayanda.jetpackpro1.testing.SingleFragmentActivity;
import com.apps.ifaldyprayanda.jetpackpro1.utils.EspressoIdlingResource;
import com.apps.ifaldyprayanda.jetpackpro1.utils.RecyclerViewItemCountAssertion;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class MovieFragmentTest {

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private MovieFragment movieFragment = new MovieFragment();

    @Before
    public  void setUp()
    {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
        activityRule.getActivity().setFragment(movieFragment);
    }

    @After
    public void tearDown()
    {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadMovies()
    {
        onView(withId(R.id.rv_movie)).check(new RecyclerViewItemCountAssertion(10));
    }

}