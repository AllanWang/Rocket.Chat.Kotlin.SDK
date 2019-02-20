package chat.rocket.core.internal

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

val CommonPool = Dispatchers.Default

fun <T> async(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> T
): Deferred<T> = GlobalScope.async(context, start, block)

fun launch(
    context: CoroutineContext = EmptyCoroutineContext,
    parent: CoroutineContext? = null,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job = GlobalScope.launch(context + (parent ?: EmptyCoroutineContext), start, block)

suspend fun delay(timeout: Long, timeUnit: TimeUnit) = delay(timeUnit.toMillis(timeout))