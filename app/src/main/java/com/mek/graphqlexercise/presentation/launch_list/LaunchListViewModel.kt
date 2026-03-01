package com.mek.graphqlexercise.presentation.launch_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mek.graphqlexercise.domain.usecase.GetPastLaunchesUseCase
import com.mek.graphqlexercise.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchListViewModel @Inject constructor(
    private val getPastLaunchesUseCase: GetPastLaunchesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LaunchListState())
    val state: StateFlow<LaunchListState> = _state

    fun onIntent(intent: LaunchListIntent) {
        when (intent) {
            LaunchListIntent.LoadLaunches -> loadLaunches()
        }
    }

    private fun loadLaunches() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            when (val result = getPastLaunchesUseCase()) {

                is Resource.Success -> {
                    _state.value = LaunchListState(
                        isLoading = false,
                        launches = result.data ?: emptyList()
                    )
                }

                is Resource.Error -> {
                    _state.value = LaunchListState(
                        error = result.message
                    )
                }

                is Resource.Loading -> Unit
            }
        }
    }
}