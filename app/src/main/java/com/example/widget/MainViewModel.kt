package com.example.widget

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.widget.api.model.Music
import com.example.widget.api.repository.ApiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val apiRepository: ApiRepository
):ViewModel() {

    private val _responseMusic = MutableStateFlow(listOf<Music>())
    val responseMusic = _responseMusic.asStateFlow()

    fun getMusic(){
        viewModelScope.launch {
            _responseMusic.value = apiRepository.getMusic().body()!!
        }
    }
}