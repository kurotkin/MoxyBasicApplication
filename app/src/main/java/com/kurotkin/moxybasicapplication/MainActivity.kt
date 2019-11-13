package com.kurotkin.moxybasicapplication

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpAppCompatActivity(), HelloWorldView {

    @InjectPresenter
    lateinit var presenter: HelloWorldPresenter

    var messageDialog: AlertDialog? = null
    var messageView: View? = null

    override fun showTimer() {
        textView.visibility = View.VISIBLE
    }

    override fun hideTimer() {
        textView.visibility = View.GONE
    }

    override fun setTimer(sec: Int) {
        textView.text = sec.toString()
    }

    override fun showMessage(message: Int) {
//        messageDialog = AlertDialog.Builder(this)
//            .setTitle("Time!")
//            .setMessage(message.toString())
//            .setOnDismissListener { presenter.dismiss() }
//            .show()

        val rootView = findViewById<ViewGroup>(R.id.activity_main)
        messageView = layoutInflater.inflate(R.layout.item_message, rootView, true)
        val textMess = messageView?.findViewById<TextView>(R.id.item_message_text)
        textMess?.text = message.toString()
        val closeButton = messageView?.findViewById<Button>(R.id.close_button)
        closeButton?.setOnClickListener { _ -> presenter.dismiss()  }
    }

    override fun hideMessage() {
        //messageDialog?.dismiss()
        messageView?.invalidate()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    override fun onDestroy() {
        super.onDestroy()
        messageDialog?.setOnDismissListener(null)
        messageDialog?.dismiss()
    }
}
