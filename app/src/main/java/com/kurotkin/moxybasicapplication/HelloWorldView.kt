package com.kurotkin.moxybasicapplication

import com.arellomobile.mvp.MvpView

interface HelloWorldView : MvpView {
    fun showMessage(message: Int)
    fun hideMessage()

    fun showTimer()
    fun hideTimer()
    fun setTimer(sec: Int)
}