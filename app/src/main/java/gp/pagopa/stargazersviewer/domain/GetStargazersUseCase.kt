package gp.pagopa.stargazersviewer.domain

import androidx.paging.PagingData
import gp.pagopa.stargazersviewer.data.StargazersRepository
import gp.pagopa.stargazersviewer.data.remote.RemoteStargazer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStargazersUseCase @Inject constructor(
    private val repository: StargazersRepository
) {

    companion object {
        const val PAGE_SIZE: Int = 30
    }

    operator fun invoke(owner: String, repo: String): Flow<PagingData<RemoteStargazer>> {
        return repository.getStargazers(owner, repo, PAGE_SIZE)
    }
}
