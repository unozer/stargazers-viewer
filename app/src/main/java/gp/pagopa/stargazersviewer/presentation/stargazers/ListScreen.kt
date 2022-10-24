package gp.pagopa.stargazersviewer.presentation.stargazers

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import gp.pagopa.stargazersviewer.data.remote.RemoteStargazer

@Composable
fun ListScreen(stargazers: LazyPagingItems<RemoteStargazer>) {
    LazyColumn(
        contentPadding = PaddingValues(
            vertical = 8.dp,
            horizontal = 8.dp
        )
    ) {
        itemsIndexed(stargazers) { _, stargazer ->
            if (stargazer != null) {
                ListItem(stargazer)
            }
        }
    }
}

@Composable
fun ListItem(stargazer: RemoteStargazer) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            GlideImage(
                imageModel = { stargazer.avatarUrl },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center
                ),
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
            )
            Text(
                text = stargazer.username,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(start = 16.dp)
            )
        }
    }
}