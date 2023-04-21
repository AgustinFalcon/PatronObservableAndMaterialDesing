package com.example.materialdesing

import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {


    private val _viewState = MutableLiveData<SingUpViewStates>()
    val viewStates : LiveData<SingUpViewStates>
        get() = _viewState



    fun validateName(name: String) {
        if(name.isEmpty()){
            _viewState.value = SingUpViewStates.FieldErrorName
            _viewState.value = SingUpViewStates.btnError

        } else {
            _viewState.value = SingUpViewStates.SuccessName
        }
    }

    fun validateEmail(email: String) {
        if (email.isEmpty() || !PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            _viewState.value = SingUpViewStates.FieldErrorEmail
            _viewState.value = SingUpViewStates.btnError

        } else {
            _viewState.value = SingUpViewStates.SuccessEmail
        }
    }

    fun validatePassword(pw1: String){
        if (pw1.isEmpty() || !pw1.contains("\\d+(?:\\.\\d+)?".toRegex())) {
            _viewState.value = SingUpViewStates.FieldErrorPassword
            _viewState.value = SingUpViewStates.btnError

        } else {
            _viewState.value = SingUpViewStates.SuccessPassword
        }
    }

    fun validateConfirmPassword (pw1: String, pw2: String) {
        if (pw2.isEmpty()) {
            _viewState.value = SingUpViewStates.FieldErrorConfirmPassword
            _viewState.value = SingUpViewStates.btnError

        } else {
            _viewState.value = SingUpViewStates.SuccessConfirmPassword
        }

        if (pw1 != pw2) {
            _viewState.value = SingUpViewStates.FieldErrorConfirmPasswordMessage
            _viewState.value = SingUpViewStates.btnError
        } else {
            _viewState.value = SingUpViewStates.btnSuccess
        }
    }



}



sealed class SingUpViewStates() {
    object FieldErrorName: SingUpViewStates()
    object SuccessName: SingUpViewStates()
    object FieldErrorEmail: SingUpViewStates()
    object SuccessEmail: SingUpViewStates()
    object FieldErrorPassword: SingUpViewStates()
    object SuccessPassword: SingUpViewStates()
    object FieldErrorConfirmPassword: SingUpViewStates()
    object FieldErrorConfirmPasswordMessage: SingUpViewStates()
    object SuccessConfirmPassword: SingUpViewStates()
    object btnSuccess: SingUpViewStates()
    object btnError: SingUpViewStates()
}