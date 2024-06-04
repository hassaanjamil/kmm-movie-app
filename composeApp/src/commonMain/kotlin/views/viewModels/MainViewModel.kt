package views.viewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _navigationIndex = MutableStateFlow(0)
    val navigationIndex: StateFlow<Int> = _navigationIndex.asStateFlow()

    fun setNavigationIndex(index: Int) {
        _navigationIndex.value = index
    }
}
