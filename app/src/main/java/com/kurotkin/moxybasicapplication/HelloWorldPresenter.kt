package com.kurotkin.moxybasicapplication

import android.os.AsyncTask
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class HelloWorldPresenter : MvpPresenter<HelloWorldView>() {

    init {
        doAsync({
            viewState.showTimer()
        },{
            viewState.setTimer(it)
        },{
            getViewState().showMessage(345)
        }).execute()
    }

    fun dismiss(){
        viewState.hideMessage()
    }

    class doAsync(val show: () -> Unit,
                  val update: (i: Int) -> Unit,
                  val doit: () -> Unit) : AsyncTask<Void, Int, Void>() {

        override fun onPreExecute() {
            show()
        }

        override fun doInBackground(vararg params: Void?): Void? {
            for(i in 5 downTo 0){
                publishProgress(i)
                Thread.sleep(1000)
            }
            return null
        }

        override fun onProgressUpdate(vararg values: Int?) {
            update.invoke(values[0]!!)
        }

        override fun onPostExecute(result: Void?) {
            doit()
        }
    }
}