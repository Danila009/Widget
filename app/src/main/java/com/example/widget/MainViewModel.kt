package com.example.widget

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.widget.api.model.Music
import com.example.widget.api.repository.ApiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val apiRepository: ApiRepository
):ViewModel() {

    private val _responseMusic:MutableStateFlow<Music?> = MutableStateFlow(null)
    val responseMusic = _responseMusic.asStateFlow().filterNotNull()

    fun getMusic(){
        viewModelScope.launch {
            _responseMusic.value = apiRepository.getMusic().body()!!
        }
    }
}