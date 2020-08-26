package com.winwang.moviehtml.ui.hot

import androidx.lifecycle.viewModelScope
import com.winwang.mvvm.base.viewmodel.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 *Created by WinWang on 2020/7/15
 *Description-> 采用koin注入版本，可以使用构造函数的时候传入Respository，
 *              如果不想用koin注入，可以在viewmodel中lazy初始化Repository的使用，看个人的使用喜好
 */
class HotViewModel(private val homeRepository: HomeRepository) : BaseViewModel() {



    fun getTestData() {
        viewModelScope.async {  }
        launch(block = {
            homeRepository.getTestData()
        })
    }

    fun test() = "10"


}