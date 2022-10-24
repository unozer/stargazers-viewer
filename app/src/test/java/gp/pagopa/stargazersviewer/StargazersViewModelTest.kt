package gp.pagopa.stargazersviewer

import androidx.paging.DifferCallback
import androidx.paging.NullPaddedList
import androidx.paging.PagingData
import androidx.paging.PagingDataDiffer
import gp.pagopa.stargazersviewer.data.StargazersRepository
import gp.pagopa.stargazersviewer.domain.GetStargazersUseCase
import gp.pagopa.stargazersviewer.presentation.stargazers.StargazersViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class StargazersViewModelTest {
    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Test
    fun initialState_isProduced() = scope.runTest {
        val viewModel = getViewModel()
        val initialState = viewModel.state.value

        assert(initialState.error == null)
        assert(initialState.stargazers.toList().isEmpty())
    }

    @Test
    fun stateWithContent_isProduced() = scope.runTest {
        val viewModel = getViewModel()
        viewModel.getStargazers("asd", "lol")
        //advanceUntilIdle()
        delay(2000)

        val currentState = viewModel.state.value
        //assert(currentState.stargazers.toList().isNotEmpty())
        assert(currentState.error == null)

        val tmp = currentState.stargazers.take(1).toList().first()
        val data = tmp.collectDataForTest()
        assert(data.isNotEmpty())
        println(data[0].username)
        assert(data[0].username == "QuantDev-chaos")
    }

    private fun getViewModel(): StargazersViewModel {
        val restaurantsRepository = StargazersRepository(FakeApiService())
        val getStargazersUseCase = GetStargazersUseCase(restaurantsRepository)
        return StargazersViewModel(getStargazersUseCase, dispatcher)
    }

    suspend fun <T : Any> PagingData<T>.collectDataForTest(): List<T> {
        val dcb = object : DifferCallback {
            override fun onChanged(position: Int, count: Int) {}
            override fun onInserted(position: Int, count: Int) {}
            override fun onRemoved(position: Int, count: Int) {}
        }
        val items = mutableListOf<T>()
        val dif = object : PagingDataDiffer<T>(dcb, dispatcher) {
            override suspend fun presentNewList(
                previousList: NullPaddedList<T>,
                newList: NullPaddedList<T>,
                lastAccessedIndex: Int,
                onListPresentable: () -> Unit
            ): Int? {
                for (idx in 0 until newList.size)
                    items.add(newList.getFromStorage(idx))
                onListPresentable()
                return null
            }
        }
        dif.collectFrom(this)
        return items
    }
}