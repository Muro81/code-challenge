package com.lukamurisic.lumiform_code_challenge.core.utils

interface StringProvider {
    fun getString(resId: Int): String
    fun getStringWithArgs(resId: Int, vararg args: Any): String
}