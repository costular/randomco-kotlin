package com.costular.randomco.users;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.costular.randomco.ChildView;
import com.costular.randomco.R;
import com.costular.randomco.user_detail.UserDetailActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by costular on 16/07/17.
 */
@RunWith(AndroidJUnit4.class)
public class UsersViewTest {

    @Rule
    public ActivityTestRule<UsersActivity> usersActivityActivityTestRule =
            new ActivityTestRule<UsersActivity>(UsersActivity.class);

    /**
     * Check if "get more users" button works well
     */
    @Test
    public void clickDownloadUsers_testShowLoading() {
        // Check if loading is not visible
        onView(withId(R.id.loading_view)).check(matches(not(isDisplayed())));

        // Click the button
        onView(withId(R.id.fab)).perform(click());

        // Check if loading has activated
        onView(withId(R.id.loading_view)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnUserAvatar_testOpenActivity() {
        // Click on top row
        onView(withId(R.id.users_recyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, ChildView.clickChildViewWithId(R.id.user_avatar)));

        // Check if activity was launched
        intended(hasComponent(UserDetailActivity.class.getName()));
    }

    @Test
    public void clickOnSort_testSortOpened() {
        // Click sort menu
        onView(withId(R.id.action_sort)).perform(click());

        // Check if is visible

    }
}
