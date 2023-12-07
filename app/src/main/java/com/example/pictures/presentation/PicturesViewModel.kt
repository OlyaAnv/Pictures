package com.example.pictures.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pictures.data.repository.PhotoListRepositoryImpl
import com.example.pictures.domain.Photo
import com.example.pictures.domain.PhotoListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

const val NUMBER_OF_PICTURES = 26 //max 132

class PicturesViewModel : ViewModel() {

    private var photoListRepository: PhotoListRepository = PhotoListRepositoryImpl

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean> = _isLoading

    private val _status = MutableLiveData("")
    val status: LiveData<String> = _status

    private val _photo = MutableLiveData<List<Photo>>()
    val photo: LiveData<List<Photo>> = _photo


    init {
        getData()
    }

    fun getData() {
        Timber.tag("mylog").d("launch getDataFromInternet()")
        viewModelScope.launch {
            _isLoading.value = true
            _status.value = ""
            try {
                val list = withContext(Dispatchers.IO) {
                    Timber.tag("mylog").d("retrofit load")
                    photoListRepository.getPhotos(NUMBER_OF_PICTURES)
                }
                _photo.value = list
                _isLoading.value = false

            } catch (e: Exception) {
                _status.value = "не удается загрузить данные из интернета"
                _isLoading.value = false
                Timber.tag("mylog").d(e)
                Timber.tag("mylog").d("${_status.value}")
            }
        }
    }

    fun clearListOfPhoto(){
        _photo.value = emptyList()
    }

    fun setStatus(status:String){
        _status.value = status
    }

    fun setLoading(isLoading:Boolean){
        _isLoading.value = isLoading
    }
}