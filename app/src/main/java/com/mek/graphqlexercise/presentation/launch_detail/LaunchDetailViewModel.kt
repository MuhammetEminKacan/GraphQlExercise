package com.mek.graphqlexercise.presentation.launch_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mek.graphqlexercise.domain.usecase.GetLaunchDetailUseCase
import com.mek.graphqlexercise.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchDetailViewModel @Inject constructor(
    private val getLaunchDetailUseCase: GetLaunchDetailUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LaunchDetailState())
    val state: StateFlow<LaunchDetailState> = _state

    fun onIntent(intent: LaunchDetailIntent) {
        when (intent) {
            is LaunchDetailIntent.LoadLaunchDetail -> loadLaunchDetail(intent.launchId)
        }
    }

    private fun loadLaunchDetail(id: String) {
        viewModelScope.launch {
            _state.value = LaunchDetailState(isLoading = true)

            when (val result = getLaunchDetailUseCase(id)) {
                is Resource.Success -> _state.value = LaunchDetailState(
                    isLoading = false,
                    launch = result.data
                )
                is Resource.Error -> _state.value = LaunchDetailState(
                    isLoading = false,
                    error = result.message
                )
                is Resource.Loading -> Unit
            }
        }
    }
}