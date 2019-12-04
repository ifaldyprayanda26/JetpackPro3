package com.apps.ifaldyprayanda.jetpackpro1;

import com.apps.ifaldyprayanda.jetpackpro1.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class JetpackPro1Test {

    @Rule
    public ActivityTestRule<HomeActivity> activityRule = new ActivityTestRule<>(HomeActivity.class);

    @Before
    public void setUp()
    {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown()
    {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void toMovieDetailActivityTest()
    {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.tx_movie)).check(matches(withText("Joker")));
    }

    @Test
    public void toDetailTvShowActivityTest()
    {

        onView(withId(R.id.rvTvShow)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.tx_movie)).check(matches(withText("Supernatural")));
    }

    @Test
    public void toFavDetailTest()
    {
        onView(withId(R.id.action_fav)).check(matches(isDisplayed()));
        onView(withId(R.id.action_fav)).perform(click());

        onView(withId(R.id.viewpager)).check(matches(isDisplayed()));
        onView(withId(R.id.viewpager)).perform(swipeRight());

        onView(withId(R.id.rv_FavMovie)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_FavMovie)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.viewpager)).check(matches(isDisplayed()));
        onView(withId(R.id.viewpager)).perform(swipeLeft());

        onView(withId(R.id.rv_FavTv)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_FavTv)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }
}
