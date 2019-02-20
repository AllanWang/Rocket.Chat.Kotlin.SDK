package chat.rocket.core.internal

import kotlinx.coroutines.*
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
    parent: CoroutineContext?,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job = GlobalScope.launch(context + (parent ?: EmptyCoroutineContext), start, block)