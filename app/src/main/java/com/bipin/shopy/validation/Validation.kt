package com.bipin.shopy.validation

import com.bipin.shopy.utils.Alerts

class Validation {
    private var validators: ArrayList<Validator>? = null

    init {
        validators = ArrayList()
    }

    companion object {
        fun create(): Validation {
            return Validation()
        }
    }

    fun isEmpty(editable: String?, message: String): Validation {
        validators?.add(EmptyValidator(editable ?: "", message))
        return this
    }

    fun isEmailValid(editable: String?, message: String): Validation {
        validators?.add(EmailValidator(editable ?: "", message))
        return this
    }

    fun isPhoneValid(editable: String?, message: String): Validation {
        validators?.add(PhoneValidator(editable ?: "", message))
        return this
    }

    fun isValidPassword(editable: String?, message: String): Validation {
        validators?.add(PasswordValidator(editable ?: "", message))
        return this
    }

    fun areEqual(editable: String?, editable2: String?, message: String): Validation {
        validators?.add(CompareValidator(editable ?: "", editable2 ?: "", message))
        return this
    }

    fun areOldAndNewNotEqual(editable: String?, editable2: String?, message: String): Validation {
        validators?.add(CompareNotValidator(editable ?: "", editable2 ?: "", message))
        return this
    }


    fun isValid(): Boolean {
        validators.let {
            it?.forEach { validator ->
                if (!validator.isValid()) {
                    Alerts.showMessage(validator.message() ?: "")
//                    Toast.makeText(
//                        MainActivity.context.get(),
//                        validator.message(),
//                        Toast.LENGTH_SHORT
//                    ).show()
                    return false
                }
            }
        }
        return true
    }

    fun isEqual(editable: String?, editable2: String?, message: String): Validation {
        if (editable == editable2) {
            validators?.add(EmptyValidator(editable ?: "", message))
            return this
        }
        return this
    }
}