package gp.pagopa.stargazersviewer.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import gp.pagopa.stargazersviewer.presentation.stargazers.StargazersScreen
import gp.pagopa.stargazersviewer.presentation.stargazers.StargazersViewModel
import gp.pagopa.stargazersviewer.presentation.theme.StargazersViewerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StargazersViewerTheme {
                StargazersApp()
            }
        }
    }
}

@Composable
private fun StargazersApp() {
    val viewModel: StargazersViewModel = hiltViewModel()
    StargazersScreen(
        state = viewModel.state.value,
        onSearchClick = { owner, repo ->
            viewModel.getStargazers(owner, repo)
        }
    )
}