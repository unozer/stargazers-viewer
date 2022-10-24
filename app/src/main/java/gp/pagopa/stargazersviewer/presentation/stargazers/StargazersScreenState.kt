package gp.pagopa.stargazersviewer.presentation.stargazers

import androidx.paging.PagingData
import gp.pagopa.stargazersviewer.data.remote.RemoteStargazer
import kotlinx.coroutines.flow.Flow

data class StargazersScreenState(
    val stargazers: Flow<PagingData<RemoteStargazer>>,
    val error: String? = null
)