package com.example.pictures.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internet_module.NUMBER_OF_PICTURES
import com.example.internet_module.Photo
import com.example.pictures.domain.repo.PictureRepo
import com.example.pictures.domain.repo.PictureRepoImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

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

    fun getDataFromInternet(dontUseCash:Boolean = false) {
        Timber.tag("mylog").d("launch getDataFromInternet()")
        viewModelScope.launch {
            _isLoading.value = true
            _status.value = ""
            try {
                val list = withContext(Dispatchers.IO) {
                    Timber.tag("mylog").d("retrofit load")
                    if(dontUseCash) {pictureRepo.loadPicturesWithoutCash(NUMBER_OF_PICTURES)}
                    else {pictureRepo.loadPictures(NUMBER_OF_PICTURES)}
                }
                _photo.value = list.photos
                _isLoading.value = false
            } catch (e: Exception) {
                _status.value = "Не удалось получить данные из интернета"
                _isLoading.value = false
                Timber.tag("mylog").d(e)
            }
        }
    }

    fun clearListOfPhoto(){
        _photo.value = emptyList()
    }
}