package com.itis.template

object Test {

    operator fun invoke(
        action: StringBuilder.() -> Unit
    ) {
        StringBuilder().apply {
            append("sadsad")
            action()
        }
    }
}