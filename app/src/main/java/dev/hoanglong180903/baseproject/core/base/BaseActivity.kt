package dev.hoanglong180903.baseproject.core.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding> : AppCompatActivity() {

    private var _binding : ViewBinding? = null
    abstract val bindingInflater : (LayoutInflater) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val binding : VB get() = _binding as VB

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        if (!isFinishing && !isDestroyed){
            _binding = bindingInflater.invoke(layoutInflater)
            setContentView(requireNotNull(_binding).root)
            initView()
            initData()
            initEvent()
            initObserve()
        }
    }

    abstract fun initView()
    abstract fun initData()
    abstract fun initEvent()
    abstract fun initObserve()
}