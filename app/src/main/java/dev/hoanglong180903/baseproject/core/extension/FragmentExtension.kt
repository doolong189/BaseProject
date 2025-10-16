package dev.hoanglong180903.baseproject.core.extension

import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import dev.hoanglong180903.baseproject.core.common.DataResult
import dev.hoanglong180903.baseproject.core.common.Event

fun <T> Fragment.observeLivedata(
    liveData: LiveData<Event<DataResult<T>>>,
    progress: ProgressBar? = null,
    onSuccess: (T) -> Unit,
    onError: (String?) -> Unit
) {
    liveData.observe(viewLifecycleOwner, Observer {
        it.getContentIfNotHandled()?.let { response ->
            when (response) {
                is DataResult.Loading -> {
                    progress?.visibility = View.VISIBLE
                }

                is DataResult.Success -> {
                    progress?.visibility = View.GONE
                    onSuccess(response.data)
                }

                is DataResult.Error -> {
                    progress?.visibility = View.GONE
                    onError(response.message)
                }
            }
        }
    })
}