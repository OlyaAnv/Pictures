package com.example.pictures.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pictures.R
import com.example.pictures.data.Photo
import com.example.pictures.data.TestPicture

class PicturesViewModel: ViewModel() {

    private val _status = MutableLiveData("")
    val status: LiveData<String> = _status

    private val _photo = MutableLiveData<List<Photo>>()
    val photo:LiveData<List<Photo>> = _photo

    private val _testPhoto = MutableLiveData<MutableList<TestPicture>>()
    val testPhoto:LiveData<MutableList<TestPicture>> = _testPhoto

    init {
        Log.d("mylog", "инициализация вью модели")
        createData()
    }

    private fun createData(){
        val list: MutableList<TestPicture> = mutableListOf()
        for (i in 1..20) {
            list.add(TestPicture(R.drawable.img))
        }
        _testPhoto.value = list
    }

    private fun getCurrency() {
//        Log.d("mylog", "запустилась гет карренси")
//        viewModelScope.launch {
//            try {
//                _photo.value = NetworkObject.retrofitService.getDataFromInternet()
//                Log.d("mylog", "ретрофит")
//                //val listResult = NetworkObject.retrofitService.getDataFromInternet()
//                //_status.value = listResult
//                //_status.value = "Success: ${_fruts.value!!.name}"
//            } catch (e: Exception) {
//                Log.d("mylog", "Ошибка ретрофита ${e.message}")
//                _status.value = "Не удалось получить данные из интернета"
//            }
//        }

    }
}