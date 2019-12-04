package com.apps.ifaldyprayanda.jetpackpro1.detail;

import android.content.Context;
import android.content.Intent;

import com.apps.ifaldyprayanda.jetpackpro1.R;
import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.MovieEntity;
import com.apps.ifaldyprayanda.jetpackpro1.utils.EspressoIdlingResource;
import com.apps.ifaldyprayanda.jetpackpro1.utils.FakeDataDummy;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class MovieDetailActivityTest {

    private MovieEntity dummyMovie = FakeDataDummy.generateDataMovie().get(0);

    @Rule
    public ActivityTestRule<MovieDetailActivity> activityRule = new ActivityTestRule<MovieDetailActivity>(MovieDetailActivity.class)
    {
        @Override
        protected Intent getActivityIntent() {
            Context contextTarget = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent resultIntent = new Intent(contextTarget, MovieDetailActivity.class);
            resultIntent.putExtra(MovieDetailActivity.EXTRA_MOVIE, dummyMovie.getMovieId());
            return resultIntent;
        }
    };

    @Before
    public void setUp()
    {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown()
    {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadMovies()
    {

        onView(withId(R.id.tx_movie)).check(matches(withText(dummyMovie.getMovieTitle())));

        onView(withId(R.id.tx_movie_date)).check(matches(withText(dummyMovie.getMovieDate())));

        onView(withId(R.id.tx_director)).check(matches(withText(dummyMovie.getMovieDirector())));

        onView(withId(R.id.overview)).check(matches(withText(dummyMovie.getMovieOverview())));

        onView(withId(R.id.imageView2)).check(matches(withText(dummyMovie.getMoviePhoto())));

        onView(withId(R.id.imgBackground)).check(matches(withText(dummyMovie.getMoviePhoto())));
    }

    @Test
    public void setFavourite()
    {
        onView(withId(R.id.action_fav)).perform(click());
    }

}