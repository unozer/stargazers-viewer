package gp.pagopa.stargazersviewer

import gp.pagopa.stargazersviewer.data.StargazersRepository
import gp.pagopa.stargazersviewer.domain.GetStargazersUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class GetStargazersUseCaseTest {
    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Test
    fun isGettingStargazers() = scope.runTest {
        val repository = StargazersRepository(
            FakeApiService()
        )
        val getStargazersUseCase = GetStargazersUseCase(repository)

        val stargazers = getStargazersUseCase("", "") //TODO fix test

        assert(stargazers.toList().isNotEmpty())
    }
}