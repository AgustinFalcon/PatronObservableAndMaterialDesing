package com.example.materialdesing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.example.materialdesing.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val singUpViewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        singUpViewModel.viewStates.observe(this) {
            handleViewState(it)
        }


        binding.txtName.addTextChangedListener {
            singUpViewModel.validateName(binding.txtName.text.toString())
        }

        binding.txtEmail.addTextChangedListener {
            singUpViewModel.validateEmail(binding.txtEmail.text.toString())
        }

        binding.txtPassword.addTextChangedListener {
            singUpViewModel.validatePassword(binding.txtPassword.text.toString())
        }

        binding.txtConfirmPassword.addTextChangedListener {
            singUpViewModel.validateConfirmPassword(binding.txtPassword.text.toString(), binding.txtConfirmPassword.text.toString())
        }

        //navegacion desde sing up a home
        /*binding.btnSingUp.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }*/


    }





    private fun handleViewState(viewState: SingUpViewStates) {
        when (viewState) {
            is SingUpViewStates.FieldErrorName -> binding.inputName.error = " "
            is SingUpViewStates.SuccessName -> binding.inputName.error = null
            is SingUpViewStates.FieldErrorEmail -> binding.inputEmail.error = " "
            is SingUpViewStates.SuccessEmail -> binding.inputEmail.error = null
            is SingUpViewStates.FieldErrorPassword -> binding.inputPassword.error = " "
            is SingUpViewStates.SuccessPassword -> binding.inputPassword.error = null
            is SingUpViewStates.FieldErrorConfirmPassword -> binding.inputConfirmPassword.error = " "
            is SingUpViewStates.FieldErrorConfirmPasswordMessage -> binding.inputConfirmPassword.error = "Passwords are not the same"
            is SingUpViewStates.SuccessConfirmPassword -> binding.inputConfirmPassword.error = null
            is SingUpViewStates.btnSuccess -> binding.btnSingUp.isEnabled = true
            is SingUpViewStates.btnError -> binding.btnSingUp.isEnabled = false
        }
    }


}