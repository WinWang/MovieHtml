package com.winwang.moviehtml.ui.hot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.winwang.moviehtml.bean.MovieBean
import com.winwang.mvvm.base.viewmodel.BaseViewModel

/**
 *Created by WinWang on 2020/7/15
 *Description-> 采用koin注入版本，可以使用构造函数的时候传入Respository，
 *              如果不想用koin注入，可以在viewmodel中lazy初始化Repository的使用，看个人的使用喜好
 */
class HotViewModel(private val homeRepository: HomeRepository) : BaseViewModel() {

    val liveDataTest: MutableLiveData<String> = MutableLiveData()


    var movieLiveData = liveData {
        val homeMovie = homeRepository.getTestData()
        emit(homeMovie)
    }


    fun getTestData() {
        launch {
            val asLiveData = homeRepository.getTestData().resultData()

        }
    }


    fun getMovieData(): LiveData<MutableList<MovieBean>> = emit {
        homeRepository.getTestData().resultData()
    }

    val getMovieDataByFlow: LiveData<MutableList<MovieBean>> =
        homeRepository.getTestLiveData().asLiveData()


    fun test() = "10"

    fun testShareViewModel() {
        liveDataTest.postValue("100")
    }

}