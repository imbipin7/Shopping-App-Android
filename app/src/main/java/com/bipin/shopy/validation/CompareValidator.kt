package com.bipin.shopy.validation

class CompareValidator(
    private val editable: String,
    private val editable2: String,
    val message: String
) :
    Validator {
    override fun isValid(): Boolean {
        return editable.trim() == editable2.trim()
    }

    override fun message(): String {
        return message
    }
}