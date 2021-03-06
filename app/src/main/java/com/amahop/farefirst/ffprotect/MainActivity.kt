package com.amahop.farefirst.ffprotect

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.amahop.farefirst.ffprotect.utils.AppBarConfigurer
import com.amahop.farefirst.ffprotect.utils.AuthManger
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupStatusBarHeight()
        setupViews()
        handleAuth()
    }

    private fun setupStatusBarHeight() {
        AppBarConfigurer.fetchStatusBarHeight(this, statusBarBg) {
            statusBarBg?.layoutParams?.height = it.toInt()
        }
    }

    private fun setupViews() {
        btnSignIn.setOnClickListener(this)
    }

    private fun handleAuth() {
        if (AuthManger.isSignedIn()) {
            showHomeScreen()
        }
    }

    private fun onClickSignIn() {
        AuthManger.requestSignIn(this)
    }

    private fun showHomeScreen() {
        HomeActivity.handleShowHomeActivity(this)
    }

    private fun showMessage(rId: Int) {
        Toast.makeText(this, rId, Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        AuthManger.handleAuthActivityResult(
            this,
            requestCode,
            resultCode,
            data
        ) { isSuccess, errorMessageRId ->
            if (isSuccess) {
                showHomeScreen()
            } else if (errorMessageRId != null) {
                showMessage(errorMessageRId)
            }
        }
    }

    override fun onClick(v: View?) {
        if (v == null) return

        when (v.id) {
            R.id.btnSignIn -> onClickSignIn()
        }
    }
}
