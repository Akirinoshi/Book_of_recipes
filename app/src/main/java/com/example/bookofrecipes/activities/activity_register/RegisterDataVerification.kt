package com.example.bookofrecipes.activities.activity_register

import android.widget.EditText
import com.example.bookofrecipes.R

class RegisterDataVerification {

    fun validation (login : EditText, password : EditText, confirmed_password : EditText) : Boolean {
        return allChecks(login, password, confirmed_password)
    }

    private fun allChecks (login : EditText, password : EditText, confirmed_password : EditText) : Boolean {
        if (loginValidate(login) &&
            passwordValidate(password) &&
            passwordReconciliation(password, confirmed_password) &&
            charactersCheck(login) &&
            charactersCheck(password) ) return true
        return false
    }

    private fun loginValidate (login : EditText) : Boolean {
        if (login.text.length < 4 || login.text.length > 12) {
            login.error = login.context.getString(R.string.login_error)
            return false
        }

        return true
    }

    private fun passwordValidate (password : EditText) : Boolean {
        if (password.text.length < 4 || password.text.length > 16) {
            password.error = password.context.getString(R.string.password_error)
            return false
        }

        return true
    }

    private fun passwordReconciliation(password: EditText, confirmed_password: EditText) : Boolean {
        if (password.text.toString() != confirmed_password.text.toString()) {
            confirmed_password.error = confirmed_password.context.getString(R.string.password_error_reconciliation)
            return false
        }

        return true
    }

    private fun charactersCheck(editText: EditText) : Boolean {
        if (editText.text.toString().contains("[^a-zA-Z0-9]".toRegex())) {
            editText.error = editText.context.getString(R.string.characters_check_error)
            return false
        }

        return true
    }
}


