package gp.pagopa.stargazersviewer.presentation.stargazers

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import gp.pagopa.stargazersviewer.data.di.MainDispatcher
import gp.pagopa.stargazersviewer.data.remote.RemoteStargazer
import gp.pagopa.stargazersviewer.domain.GetStargazersUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StargazersViewModel @Inject constructor(
    private val getStargazersUseCase: GetStargazersUseCase,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _state = mutableStateOf(
        StargazersScreenState(
            stargazers = listOf<PagingData<RemoteStargazer>>().asFlow()
        )
    )
    val state: State<StargazersScreenState> get() = _state

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        _state.value = _state.value.copy(error = exception.message)
    }

    fun getStargazers(owner: String, repo: String) {
        viewModelScope.launch(errorHandler + dispatcher) {
            _state.value = _state.value.copy(
                stargazers = getStargazersUseCase(owner, repo)
            )
        }
    }
}