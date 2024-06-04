package views.viewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import respository.HomeRepository

class HomeViewModel : ViewModel() {
    private val _navigationIndex = MutableStateFlow(0)
    val navigationIndex: StateFlow<Int> = _navigationIndex.asStateFlow()

    fun setNavigationIndex(index: Int) {
        _navigationIndex.value = index
    }

    private val _apiResponse = MutableStateFlow("Loading")
    val apiResponse: StateFlow<String> = _apiResponse.asStateFlow()

    fun setApiResponse(response: String) {
        _apiResponse.value = response
    }

    private val homeRepository = HomeRepository()
    suspend fun fetchApiResponse(url: String): String {
        return homeRepository.fetchApiResponse(url)
    }
}
