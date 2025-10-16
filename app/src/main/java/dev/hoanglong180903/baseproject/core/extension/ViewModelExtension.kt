package dev.hoanglong180903.baseproject.core.extension

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.hoanglong180903.baseproject.core.common.DataResult
import dev.hoanglong180903.baseproject.core.common.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


fun <T> ViewModel.liveDataResult(
    liveData: MutableLiveData<Event<DataResult<T>>>,
    request : suspend CoroutineScope.() -> DataResult<T>?,
) : Job = viewModelScope.launch(Dispatchers.IO) {
    liveData.postValue(Event(DataResult.Loading))
    when ( val response = request(this) ){
        is DataResult.Success -> {
            liveData.postValue(Event(response))
        }
        is DataResult.Error -> {
            liveData.postValue(Event(DataResult.Error(response.message)))
        }
        else -> {}
    }
}