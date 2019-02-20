package chat.rocket.core.compat.internal

import chat.rocket.common.RocketChatException
import chat.rocket.core.compat.Call
import chat.rocket.core.compat.Callback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.startCoroutine

@JvmOverloads
fun <T> callback(
        context: CoroutineContext = Dispatchers.Default,
        callback: Callback<T>,
        block: suspend CoroutineScope.() -> T
): Call {
    val job = Job()
    val newContext = context + job
    block.startCoroutine(GlobalScope, object : Continuation<T> {
        override val context: CoroutineContext = newContext

        override fun resumeWith(result: Result<T>) {
            if (result.isSuccess) {
                callback.onSuccess(result.getOrThrow())
            } else {
                callback.onError(result.exceptionOrNull() as? RocketChatException
                        ?: RocketChatException("Unknown Error"))
            }
        }

    })
    return Call(job)
}