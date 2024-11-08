package com.example.assignment2

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

private const val LAUNCH_TIMEOUT = 5000L
private const val BASIC_SAMPLE_PACKAGE = "com.example.assignment2"

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class UIAutomatorClass {

    private lateinit var device: UiDevice

    @Before
    fun setUp() {

        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        device.pressHome()
        val launcherPackage = device.launcherPackageName

        device.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT
        )
        //find launcher icon and click on it
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        val appIcon = device.findObject(By.text(appName))
        appIcon.click()

        device.wait(
            Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
            LAUNCH_TIMEOUT
        )
    }


    @Test
    fun testAppLaunch() {

        val appMainScreen = device.findObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0))
        //find button
        val explicitButton = device.findObject(By.text("Start Activity Explicitly"))

        explicitButton.click()


        device.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT)

        //list of mobile swe challenges
        val challenges = listOf(
            "Different Operating Systems",
            "Unstable Environments",
            "Rapid Changes in the Platform",
            "Users Involvement",
            "Maximizing App Users"
        )

        //check for challenges
        var challengeFound = false
        for (challenge in challenges) {
            if (device.hasObject(By.textContains(challenge))) {
                challengeFound = true
                break
            }
        }

        //check boolean flag
        assertThat("None of the challenges were found in the second activity", challengeFound, notNullValue())
    }
}
