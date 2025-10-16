package dev.hoanglong180903.baseproject.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


abstract class BaseFragment<VB : ViewBinding> : Fragment(){

    private var _binding : ViewBinding? = null
    abstract val bindingInflater : (LayoutInflater) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val binding : ViewBinding get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(layoutInflater)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        initEvent()
        initObserve()
    }

    abstract fun initView()
    abstract fun initData()
    abstract fun initEvent()
    abstract fun initObserve()


}