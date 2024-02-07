package com.example.composedweather.ui.feature.dashboard

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composedweather.R
import com.example.composedweather.ui.common.ContentLoaderUI
import com.example.composedweather.ui.theme.ComposedWeatherTheme
import com.example.composedweather.ui.theme.ElectricBlue
import com.example.composedweather.ui.theme.FigtreeExtraBold
import com.example.composedweather.ui.theme.Silver
import com.example.data.model.response.AnalyticSection
import com.example.data.model.response.LinkData
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch


@Composable
fun DashboardUIContainer(
    openWhatsApp: (String) -> Unit,
    onLinkClicked: (LinkData) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    val linkInfo = viewModel.linkInfo.toPersistentList()

    val dates = viewModel.dates.toPersistentList()

    val values = viewModel.values.toPersistentList()

    DashboardUI(
        state = state,
        linkInfo = linkInfo,
        dates = dates,
        values = values,
        openWhatsApp = openWhatsApp,
        onLinkClicked = onLinkClicked,
        modifier = modifier.systemBarsPadding()
    )
}

@Composable
fun DashboardUI(
    state: DashboardViewState,
    linkInfo: PersistentList<Pair<String, List<LinkData>>>,
    dates: PersistentList<String>,
    values: PersistentList<Int>,
    openWhatsApp: (String) -> Unit,
    onLinkClicked: (LinkData) -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedContent(
        targetState = state.isLoading,
        label = "",
        modifier = modifier.fillMaxSize()
    ) {
        if (it) {
            ContentLoaderUI(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            )
        } else {
            AnotherLayerOfConstraintLayoutWrapper(
                state = state,
                linkInfo = linkInfo,
                dates = dates,
                values = values,
                openWhatsApp = openWhatsApp,
                onLinkClicked = onLinkClicked
            )
        }
    }
}

@Composable
fun AnotherLayerOfConstraintLayoutWrapper(
    state: DashboardViewState,
    linkInfo: PersistentList<Pair<String, List<LinkData>>>,
    dates: PersistentList<String>,
    values: PersistentList<Int>,
    openWhatsApp: (String) -> Unit,
    onLinkClicked: (LinkData) -> Unit,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(ElectricBlue)
    ) {
        val (header, lazyColumn) = createRefs()

        DashboardHeaderUI(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .constrainAs(header) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        DashboardContentUI(
            state = state,
            linkInfo = linkInfo,
            dates = dates,
            values = values,
            openWhatsApp = openWhatsApp,
            onLinkClicked = onLinkClicked,
            modifier = Modifier
                .constrainAs(lazyColumn) {
                    top.linkTo(header.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)

                    height = Dimension.fillToConstraints
                }
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DashboardContentUI(
    state: DashboardViewState,
    linkInfo: PersistentList<Pair<String, List<LinkData>>>,
    dates: PersistentList<String>,
    values: PersistentList<Int>,
    openWhatsApp: (String) -> Unit,
    onLinkClicked: (LinkData) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember {
        mutableIntStateOf(0)
    }

    val page = remember(linkInfo[selectedTab]) {
        linkInfo[selectedTab]
    }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(top = 32.dp, bottom = 128.dp),
        modifier = modifier
            .fillMaxSize()
            .background(Silver)
    ) {
        item(key = "Header") {
            HeaderUI(
                title = "Good morning",
                subTitle = "Ajay Manva \uD83D\uDC4B",
                modifier = Modifier.fillMaxWidth(0.9f)
            )
        }

        item(key = "Graph") {
            GraphUI(
                state = state,
                dates = dates,
                values = values,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .clip(RoundedCornerShape(12.dp))
                    .padding(top = 12.dp)
            )
        }

        item(key = "Analytics section") {
            val analyticSection = remember {
                listOf(
                    AnalyticSection(
                        resourceId = R.drawable.ic_top_clicks,
                        title = "Top Clicks",
                        subTitle = "Today's clicks : ${state.totalClicksForToday}"
                    ),
                    AnalyticSection(
                        resourceId = R.drawable.ic_location,
                        title = "Ahmedabad",
                        subTitle = "Top Location"
                    ),
                    AnalyticSection(
                        resourceId = R.drawable.ic_globe,
                        title = "Instagram",
                        subTitle = "Top source"
                    ),
                )
            }

            AnalyticsSectionContainerUI(
                analytics = analyticSection,
                onViewAnalyticsClicked = {

                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(top = 16.dp)
            )
        }


        item("tab_header") {
            val tabs = linkInfo.map { it.first }

            TabHeaderUI(
                tabs = tabs,
                selectedTab = selectedTab,
                onTabChanged = {
                    selectedTab = it
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(top = 8.dp)
            )
        }

        if (page.second.isEmpty()) {
            item("empty-ui-item") {
                Text(
                    text = "No links for this section",
                    color = Color.Black,
                    fontFamily = FigtreeExtraBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                )
            }
        } else {
            items(
                count = page.second.size,
                key = {
                    val linkData = page.second[it]
                    "${linkData.urlId}-${it}"
                }
            ) {
                val linkData = page.second[it]
                LinkInfoUI(
                    onItemClicked = {
                        onLinkClicked(it)
                    },
                    linkData = linkData,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                )
            }
        }

        item(key = "all-links") {
            HollowButtonUI(
                ctaText = "View All Links",
                imageVector = ImageVector.vectorResource(R.drawable.ic_link_2),
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.9f)
                    .padding(top = 8.dp)
            )
        }


        item("Cta-section") {
            CtaSectionContainerUI(
                onContactUsClicked = {
                    openWhatsApp(state.contactNumber)
                },
                onFaqSectionClicked = {

                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(top = 24.dp)
            )
        }

    }
}


@Preview
@Composable
fun DashboardUIPreview() {
    ComposedWeatherTheme {
        val state by remember {
            mutableStateOf(DashboardViewState.default())
        }
        DashboardUI(
            state = state,
            linkInfo = mutableListOf(
                Pair("One", listOf(LinkData.default()))
            ).toPersistentList(),
            dates = mutableListOf(
                ""
            ).toPersistentList(),
            values = mutableListOf(
                1
            ).toPersistentList(),
            openWhatsApp = {

            },
            onLinkClicked = {

            }
        )
    }
}