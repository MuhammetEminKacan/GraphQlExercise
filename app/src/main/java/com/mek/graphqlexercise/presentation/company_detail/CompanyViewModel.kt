package com.mek.graphqlexercise.presentation.company_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mek.graphqlexercise.domain.usecase.GetCompanyInfoUseCase
import com.mek.graphqlexercise.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyViewModel @Inject constructor(
    private val getCompanyInfoUseCase: GetCompanyInfoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CompanyState())
    val state: StateFlow<CompanyState> = _state

    fun onIntent(intent: CompanyIntent) {
        when (intent) {
            CompanyIntent.LoadCompanyInfo -> loadCompany()
        }
    }

    private fun loadCompany() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            when (val result = getCompanyInfoUseCase()) {
                is Resource.Success -> _state.value = CompanyState(company = result.data)
                is Resource.Error -> _state.value = CompanyState(error = result.message)
                is Resource.Loading -> Unit
            }
        }
    }
}