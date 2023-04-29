package com.bipin.shopy.validation

interface Validator {
    fun isValid(): Boolean
    fun message(): String?
}