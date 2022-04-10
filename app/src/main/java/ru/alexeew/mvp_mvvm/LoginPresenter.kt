package ru.alexeew.mvp_mvvm

import android.os.Handler
import android.os.Looper
import java.lang.Thread.sleep

class LoginPresenter: LoginContract.Presenter {
    private var view: LoginContract.View? = null
    private val uiHandler = Handler(Looper.getMainLooper())

    override fun onAttach(view: LoginContract.View) {
        this.view = view
    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()
        Thread {
            sleep(3000)
            uiHandler.post {
                view?.hideProcess()
                if (checkCredentials(login, password)) {
                    view?.setSuccess()
                } else {
                    view?.setError("Неверный пароль")
                }
            }
        }.start()
    }

    private fun checkCredentials(login: String, password: String): Boolean {
        return login == password
    }

    override fun onCredentialsChanged() {
        TODO("Not yet implemented")
    }

    override fun onRegistration() {
        TODO("Not yet implemented")
    }

    override fun onForgot() {
        TODO("Not yet implemented")
    }
}