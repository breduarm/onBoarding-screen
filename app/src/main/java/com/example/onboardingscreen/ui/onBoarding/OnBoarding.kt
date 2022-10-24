package com.example.onboardingscreen.ui.onBoarding

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
@Preview
fun OnBoarding() {
    val scope = rememberCoroutineScope()

    Column(Modifier.fillMaxSize()) {
        val pagerItems = OnBoardingPagerItemData.get()
        val pagerState = rememberPagerState(pageCount = pagerItems.size)

        TopSection()

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .weight(0.8f)
        ) { page ->
            OnBoardingPagerItem(pagerItems[page])
        }

        BottomSection(
            itemsNumber = pagerItems.size,
            selectedIndex = pagerState.currentPage,
        ) {
            val nextPageIndex = pagerState.currentPage + 1
            if (nextPageIndex < pagerItems.size) {
                scope.launch {
                    pagerState.scrollToPage(nextPageIndex)
                }
            }
        }
    }
}

@Composable
fun TopSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        // Back button
        IconButton(
            modifier = Modifier.align(Alignment.CenterStart),
            onClick = {
                /*TODO*/
            }
        ) {
            Icon(Icons.Outlined.KeyboardArrowLeft, null)
        }

        // Skip button
        TextButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = {
                /*TODO*/
            }
        ) {
            Text(text = "Skip", color = MaterialTheme.colorScheme.onBackground)
        }
    }
}

@Composable
fun OnBoardingPagerItem(item: OnBoardingPagerItemData) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = item.image),
            contentDescription = null
        )
        Text(
            text = stringResource(id = item.title),
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = stringResource(id = item.body),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BottomSection(
    itemsNumber: Int,
    selectedIndex: Int,
    onNextClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        // Indicators
        Indicators(
            itemsNumber = itemsNumber,
            selectedIndex = selectedIndex,
        )

        // Next button
        FloatingActionButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            onClick = onNextClicked,
        ) {
            Icon(Icons.Outlined.KeyboardArrowRight, null)
        }
    }
}

@Composable
fun BoxScope.Indicators(
    itemsNumber: Int,
    selectedIndex: Int,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.align(Alignment.CenterStart)
    ) {
        repeat(itemsNumber) { index ->
            Indicator(isSelected = index == selectedIndex)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val animationTargetValue = if (isSelected) 25.dp else 10.dp
    val width = animateDpAsState(
        targetValue = animationTargetValue,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    val backgroundColor = if (isSelected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
    }

    Box(
        modifier = Modifier
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(backgroundColor)
    )
}