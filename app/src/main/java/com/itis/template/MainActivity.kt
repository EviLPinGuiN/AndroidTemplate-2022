package com.itis.template

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.itis.template.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private val scope = MainScope()

    val handler = CoroutineExceptionHandler { _, exception ->
        println("Oh shit, here we go again: $exception")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // from binding
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        binding?.run {

        }

//        buildNotification(channelId = "sdsad") {
//            setSmallIcon()
//            setCategory()
//            title = ""
//        }

        lifecycleScope.launch {
            val result = getUserFromBE()
            binding?.tvHelloMessage?.text = result.name
        }

        try {
            val job = lifecycleScope.launch(handler) {
                val result = lifecycleScope.async {
                    getUserFromBE()
                }
                val result2 = lifecycleScope.async {
                    getUserFromBE(delay = 2000)
                }
                val result3 = lifecycleScope.async {
                    getUserFromBE(delay = 3000)
                }
                val user = result.await()
                val user2 = result2.await()

                Log.e("sds", "${user.name} + ${user2.name}")

                Log.e("sds", "message")
            }

            job.invokeOnCompletion {

            }
        } catch (ex: Exception) {

        }
    }

    override fun onDestroy() {
        super.onDestroy()

        scope.cancel()
    }

    private suspend fun getUserFromBE(
        delay: Long = 1000
    ): User = withContext(Dispatchers.IO) {
        if (delay <= 1000L) throw RuntimeException("ERROR!!!")
        delay(delay)
        User("sdsd")
    }


    private suspend fun insertUserToDb(
        user: User
    ): User = withContext(Dispatchers.IO) {
        database.insert(user)

        fetchDataFromSdk()
        user
    }

    suspend fun fetchDataFromSdk() = suspendCancellableCoroutine<String> {
        SDK.addSuccessListner {
            it.resume("it.name")
        }.addErrorListner { it: Throwalbe->
            it.resumeWithException(Throwable(it.message))
        }
    }
}