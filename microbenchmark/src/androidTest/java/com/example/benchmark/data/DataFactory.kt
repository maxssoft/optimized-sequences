package com.example.benchmark.data

/**
 * functions for generate test data
 *
 * @author Max Sidorov on 21.04.2023
 */

fun createIntList(count: Int): List<Int> {
    return mutableListOf<Int>().apply {
        (0..count).forEach { add(it) }
        shuffle()
    }
}

fun createListOfList(count: Int, countInternal: Int): List<List<Int>> {
    return mutableListOf<List<Int>>().apply {
        (0..count).forEach {
            add(createIntList(countInternal))
        }
        shuffle()
    }
}

