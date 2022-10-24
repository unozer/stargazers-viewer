package gp.pagopa.stargazersviewer.presentation.stargazers

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import gp.pagopa.stargazersviewer.data.remote.RemoteStargazer

@Composable
fun StargazersScreen(
    state: StargazersScreenState,
    onSearchClick: (owner: String, repo: String) -> Unit
) {
    val stargazersFlow = state.stargazers
    val lazyRepoItems: LazyPagingItems<RemoteStargazer> = stargazersFlow.collectAsLazyPagingItems()

    Column {
        FormScreen(onSearchClick)

        ListScreen(lazyRepoItems)

        val refreshLoadState = lazyRepoItems.loadState.refresh
        val appendLoadState = lazyRepoItems.loadState.append
        when {
            refreshLoadState is LoadState.Error -> {
                val error = refreshLoadState.error
                ErrorItem(
                    message = error.localizedMessage ?: "",
                    modifier = Modifier.fillMaxWidth()
                )
            }
            appendLoadState is LoadState.Error -> {
                val error = appendLoadState.error
                ErrorItem(
                    message = error.localizedMessage ?: "",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun ErrorItem(
    message: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            maxLines = 2,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            color = Color.Red
        )
    }
}