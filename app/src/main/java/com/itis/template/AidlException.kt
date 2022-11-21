package com.itis.template

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AidlException(
    val errorMessage: String?,
    val errorCode: Int = RUNTIME_EXCEPTION
) : Parcelable {

    fun toException(): Exception {
        return when (errorCode) {
            RUNTIME_EXCEPTION -> RuntimeException(errorMessage)
            ARITHMETIC_EXCEPTION -> ArithmeticException(errorMessage)
            else -> RuntimeException(errorMessage)
        }
    }

    companion object {
        const val RUNTIME_EXCEPTION = 1000
        const val ARITHMETIC_EXCEPTION = 1001
        // TODO другие коды ошибок...
    }
}
