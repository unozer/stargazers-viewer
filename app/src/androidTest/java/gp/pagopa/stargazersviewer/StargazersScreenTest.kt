package gp.pagopa.stargazersviewer

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.paging.PagingData
import gp.pagopa.stargazersviewer.data.remote.RemoteStargazer
import gp.pagopa.stargazersviewer.presentation.stargazers.StargazersScreen
import gp.pagopa.stargazersviewer.presentation.stargazers.StargazersScreenState
import gp.pagopa.stargazersviewer.presentation.theme.StargazersViewerTheme
import kotlinx.coroutines.flow.asFlow
import org.junit.Rule
import org.junit.Test

class StargazersScreenTest {

    @get:Rule
    val testRule: ComposeContentTestRule = createComposeRule()

    @Test
    fun initialState_isRendered() {
        testRule.setContent {
            StargazersViewerTheme {
                StargazersScreen(
                    state = StargazersScreenState(
                        stargazers = listOf<PagingData<RemoteStargazer>>().asFlow()
                    ),
                    onSearchClick = { _, _ ->
                    }
                )
            }
        }

        testRule.onNodeWithText("Owner").assertIsDisplayed()
        testRule.onNodeWithText("Repository").assertIsDisplayed()
        testRule.onNodeWithText("SEARCH").assertIsDisplayed()
    }

    @Test
    fun clickOnItem_isRegistered() {
        testRule.setContent {
            StargazersViewerTheme {
                StargazersScreen(
                    state = StargazersScreenState(
                        stargazers = listOf<PagingData<RemoteStargazer>>().asFlow()
                    ),
                    onSearchClick = { owner, repo ->
                        assert(owner == "")
                        assert(repo == "")
                    }
                )
            }
        }

        testRule.onNodeWithText("SEARCH")
            .performClick()
    }
}