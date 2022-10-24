package com.example.onboardingscreen.ui.onBoarding

import com.example.onboardingscreen.R

// TODO should it be a data class?
class OnBoardingPagerItemData(
    val title: Int,
    val body: Int,
    val image: Int,
) {

    companion object {
        fun get() = listOf(
            OnBoardingPagerItemData(
                title = R.string.onBoardingTitle1,
                body = R.string.onBoardingBody1,
                image = R.drawable.onboarding1,
            ),
            OnBoardingPagerItemData(
                title = R.string.onBoardingTitle2,
                body = R.string.onBoardingBody2,
                image = R.drawable.onboarding2,
            ),
            OnBoardingPagerItemData(
                title = R.string.onBoardingTitle3,
                body = R.string.onBoardingBody3,
                image = R.drawable.onboarding3,
            )
        )
    }
}