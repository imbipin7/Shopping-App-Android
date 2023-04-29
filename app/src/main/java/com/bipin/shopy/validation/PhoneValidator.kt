package com.bipin.shopy.validation


class PhoneValidator(private val editable: String, val message: String) :
    Validator {
    override fun isValid(): Boolean {
        return ValidatorUtils.isMobileValid(editable.trim())
    }

    override fun message(): String {
        return message
    }
}