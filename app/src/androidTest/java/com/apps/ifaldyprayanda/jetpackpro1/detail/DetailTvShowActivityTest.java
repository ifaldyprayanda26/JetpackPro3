package com.apps.ifaldyprayanda.jetpackpro1.detail;

import android.content.Context;
import android.content.Intent;

import com.apps.ifaldyprayanda.jetpackpro1.R;
import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.TvShowEntity;
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

public class DetailTvShowActivityTest {

    private TvShowEntity tvShowEntity = FakeDataDummy.generateDataTv().get(0);

    @Rule
    public ActivityTestRule<DetailTvShowActivity> activityRule = new ActivityTestRule<DetailTvShowActivity>(DetailTvShowActivity.class)
    {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent resulstIntent = new Intent(targetContext, DetailTvShowActivity.class);
            resulstIntent.putExtra(DetailTvShowActivity.EXTRA_TV, tvShowEntity.getTvId());
            return  resulstIntent;
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
    public void loadTvShows()
    {
        onView(withId(R.id.tx_movie)).check(matches(withText(tvShowEntity.getTvTitle())));

        onView(withId(R.id.tx_movie_date)).check(matches(withText(tvShowEntity.getTvDate())));

        onView(withId(R.id.tx_director)).check(matches(withText(tvShowEntity.getTvCreator())));

        onView(withId(R.id.overview)).check(matches(withText(tvShowEntity.getTvOverview())));

        onView(withId(R.id.imageView2)).check(matches(withText(tvShowEntity.getTvPhoto())));

        onView(withId(R.id.imgBackground)).check(matches(withText(tvShowEntity.getTvPhoto())));
    }

    @Test
    public void setTvShowFav()
    {
        onView(withId(R.id.action_fav)).perform(click());
    }

}