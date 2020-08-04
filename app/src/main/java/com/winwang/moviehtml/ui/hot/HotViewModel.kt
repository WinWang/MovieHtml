package com.winwang.moviehtml.ui.hot

import com.winwang.mvvm.base.viewmodel.BaseViewModel

/**
 *Created by WinWang on 2020/7/15
 *Description->
 */
class HotViewModel(private val homeRepository: HomeRepository) : BaseViewModel() {


    fun getTestData() {
        launch(block = {
            homeRepository.getTestData()
        })
    }

}