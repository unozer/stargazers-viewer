package gp.pagopa.stargazersviewer.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import gp.pagopa.stargazersviewer.data.remote.RemoteStargazer
import gp.pagopa.stargazersviewer.data.remote.StargazersApiService

class StargazersPagingSource(
    private val restInterface: StargazersApiService,
    private val owner: String,
    private val repo: String,
    private val perPage: Int
) : PagingSource<Int, RemoteStargazer>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RemoteStargazer> {
        return try {
            val nextPage = params.key ?: 1
            val stargazers = restInterface.getStargazers(owner, repo, perPage, nextPage)
            LoadResult.Page(
                data = stargazers,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RemoteStargazer>): Int? {
        return null
    }
}