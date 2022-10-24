package gp.pagopa.stargazersviewer.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import gp.pagopa.stargazersviewer.data.remote.RemoteStargazer
import gp.pagopa.stargazersviewer.data.remote.StargazersApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StargazersRepository @Inject constructor(
    private val restInterface: StargazersApiService
) {

    fun getStargazers(
        owner: String,
        repo: String,
        pageSize: Int
    ): Flow<PagingData<RemoteStargazer>> {
        return Pager(
            config = PagingConfig(pageSize = pageSize),
            pagingSourceFactory = {
                StargazersPagingSource(
                    restInterface,
                    owner,
                    repo,
                    pageSize
                )
            }).flow
    }
}