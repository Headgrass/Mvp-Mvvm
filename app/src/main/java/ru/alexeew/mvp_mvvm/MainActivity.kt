package ru.alexeew.mvp_mvvm

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import ru.alexeew.mvp_mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var binding: ActivityMainBinding
    private var presenter: LoginContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = restorePresenter()
        presenter?.onAttach(this)

        binding.loginButton.setOnClickListener {
            presenter?.onLogin(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }

        binding.tvForgotPassword.setOnClickListener {
            Toast.makeText(this, "Смотри почту", Toast.LENGTH_SHORT).show()
        }
        binding.tvRegistration.setOnClickListener {
            Toast.makeText(this, "Зарегистрирован", Toast.LENGTH_SHORT).show()
        }
    }

    private fun restorePresenter(): LoginPresenter {
        val presenter = lastCustomNonConfigurationInstance as? LoginPresenter
        return presenter ?: LoginPresenter()
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

    override fun setSuccess() {
        binding.loginButton.isVisible = false
        binding.loginEditText.isVisible = false
        binding.passwordEditText.isVisible = false
        binding.root.setBackgroundColor(Color.GREEN)
    }

    override fun setError(error: String) {
        Toast.makeText(this, "ERROR $error", Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        binding.loginButton.isEnabled = true
        hideKeyboard(this)
    }

    override fun hideProcess() {
        binding.loginButton.isEnabled = false
    }

    private fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = activity.currentFocus
    }
}