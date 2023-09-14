package com.example.supabasetestproject

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.example.supabasetestproject.presentation.screen.on_boarding.OnBoardingFragment
import com.example.supabasetestproject.presentation.screen.on_boarding.OnBoardingModel
import com.example.supabasetestproject.presentation.screen.on_boarding.OnBoardingScreen
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.LinkedList

@RunWith(MockitoJUnitRunner::class)
class OnBoardingTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setupOnBoardingNavHost() {
    }

    @Test
    fun test_1() {
        val onBoardingQueue = OnBoardingFragment.onBoardingQueue

        val currentItem = onBoardingQueue.poll()
    }

    @Test
    fun test_2() {
        val onBoardingQueue = OnBoardingFragment.onBoardingQueue

        val expectedSize = onBoardingQueue.size - 1
        onBoardingQueue.poll()

        val actualSize = onBoardingQueue.size
        assertEquals(expectedSize, actualSize)
    }

    @Test
    fun test_5() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        val onBoardingQueue =
            LinkedList(
                mutableListOf(
                    OnBoardingModel(
                        R.drawable.on_boarding_image_3,
                        "Real-time Tracking Third",
                        "Enjoy quick pick-up and delivery to your destination",
                    ),
                ),
            )

        rule.setContent {
            navController.setGraph(R.navigation.nav_graph)
            OnBoardingScreen(navController = navController, onBoardingQueue = onBoardingQueue)
        }
        with(rule) {
            onNodeWithTag("SignUpBtn").performClick()
            assertEquals(R.id.signUpFragment, navController.currentDestination?.id)
        }
    }

    @Test
    fun test_6() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        val onBoardingQueue =
            LinkedList(
                mutableListOf(
                    OnBoardingModel(
                        R.drawable.on_boarding_image_3,
                        "Real-time Tracking Third",
                        "Enjoy quick pick-up and delivery to your destination",
                    ),
                ),
            )

        rule.setContent {
            navController.setGraph(R.navigation.nav_graph)
            OnBoardingScreen(navController = navController, onBoardingQueue = onBoardingQueue)
        }
        with(rule) {
            onNodeWithTag("SignUpBtn").performClick()
            assertEquals(R.id.signUpFragment, navController.currentDestination?.id)
        }
    }
}
