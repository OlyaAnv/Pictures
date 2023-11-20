package com.example.pictures.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pictures.data.constants.NUMBER_OF_PICTURES
import com.example.pictures.data.models.Photo
import com.example.pictures.domain.repo.PictureRepo
import com.example.pictures.domain.repo.PictureRepoImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PicturesViewModel : ViewModel() {
    private var pictureRepo: PictureRepo = PictureRepoImpl()

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean> = _isLoading

    private val _status = MutableLiveData("")
    val status: LiveData<String> = _status

    private val _photo = MutableLiveData<List<Photo>>()
    val photo: LiveData<List<Photo>> = _photo

    init {
        getDataFromInternet()
    }

    private fun getDataFromInternet() {
        Log.d("mylog", "запустилась getDataFromInternet()")
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = withContext(Dispatchers.IO) {
                    Log.d("mylog", "ретрофит")
                    pictureRepo.loadPictures(NUMBER_OF_PICTURES)
                }
                if (response.isSuccessful) {
                    _photo.value = response.body()?.photos
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                Log.d("mylog", "Ошибка ретрофита ${e.message}")
                _status.value = "Не удалось получить данные из интернета"
                _isLoading.value = false
            }
        }
    }
}