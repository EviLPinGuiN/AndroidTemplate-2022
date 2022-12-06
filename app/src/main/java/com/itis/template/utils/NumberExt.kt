package com.itis.template.utils

import android.app.Notification
import android.content.Context
import androidx.core.app.NotificationCompat
import com.itis.template.MainActivity
import com.itis.template.Test
import com.itis.template.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

infix fun Int.`привет_мой_друг`(value: Int): Int = this + value * 10

fun test(action: ((StringBuilder) -> Unit)): String {
    val sb = StringBuilder()
    action(sb)

    return sb.toString()
}

fun test2(action: StringBuilder.() -> Unit): String {
    val sb = StringBuilder()
    sb.action()

    return sb.toString()
}

fun Context.buildNotification(
    channelId: String,
    action: NotificationCompat.Builder.() -> Unit,
): Notification {
    val builder = NotificationCompat.Builder(this, channelId)
    builder.action()
    return builder.build()
}

fun main() {
    val result = 5.привет_мой_друг(10) // 105
    val result2 = 5 привет_мой_друг 10

    val r = test {
        it.append("sdsada")
    }

    val rr = test2 {
        append("sdds").apply {  }
    }

    val user = User("test")
    val user2 = User("test2")

    user += user2

    Test {

    }
}