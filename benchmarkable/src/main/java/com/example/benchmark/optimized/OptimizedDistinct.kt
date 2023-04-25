package com.example.benchmark.optimized

/**
 * Optimized distinct sequence function
 *
 * Excluded AbstractIterator with virtual methods
 *
 * Working with state over Enum class replaced on Int
 * Using "when" condition with Enum class is slower on 10% as Int
 * article of Jake Wharton https://jakewharton.com/r8-optimization-enum-switch-maps/
 * Look microbenchmark test [com.example.benchmark.EnumTest]
 *
 * I developed this function such as implementation of FilteringSequence
 * and this implementation works on 15-18% faster than standard implementation
 * Look microbenchmark test [com.example.benchmark.OptimizedDistinctTest]
 *
 * @author Max Sidorov on 21.04.2023
 */

fun <T, K> Sequence<T>.optimizedDistinctBy(selector: (T) -> K): Sequence<T> {
    return OptimizedDistinctSequence(this, selector)
}

internal class OptimizedDistinctSequence<T, K>(private val source: Sequence<T>, private val keySelector: (T) -> K) : Sequence<T> {
    override fun iterator(): Iterator<T> = OptimizedDistinctIterator(source.iterator(), keySelector)
}

private class OptimizedDistinctIterator<T, K>(
    private val source: Iterator<T>, private val keySelector: (T) -> K
) : Iterator<T>{
    private val observed = HashSet<K>()
    // { UNDEFINED_STATE, HAS_NEXT_ITEM, HAS_FINISHED }
    private var nextState: Int = UNDEFINED_STATE
    private var nextItem: T? = null

    override fun hasNext(): Boolean {
        if (nextState == UNDEFINED_STATE)
            calcNext()
        return nextState == HAS_NEXT_ITEM
    }

    override fun next(): T {
        if (nextState == UNDEFINED_STATE)
            calcNext()
        if (nextState == HAS_FINISHED)
            throw NoSuchElementException()
        nextState = UNDEFINED_STATE
        return nextItem as T
    }

    private fun calcNext() {
        while (source.hasNext()) {
            val next = source.next()
            val key = keySelector(next)

            if (observed.add(key)) {
                nextItem = next
                nextState = HAS_NEXT_ITEM // found next item
                return
            }
        }
        nextState = HAS_FINISHED // end of iterator
    }
}

private const val UNDEFINED_STATE = -1 // next item undefined
private const val HAS_NEXT_ITEM = 0 // has next item
private const val HAS_FINISHED = 1 // has finished iteration
