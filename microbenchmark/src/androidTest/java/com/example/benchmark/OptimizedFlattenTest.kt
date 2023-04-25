package com.example.benchmark

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.benchmark.data.createListOfList
import com.example.benchmark.optimized.optimizedFlatten
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Benchmark test showing the different between optimized and standard implementation of flatten function
 *
 * @author Max Sidorov on 21.04.2023
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class OptimizedFlattenTest {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    private val internalCount = 10

    private val originCollection_100 = createListOfList(100, internalCount)
    private val originCollection_1_000 = createListOfList(1_000, internalCount)
    private val originCollection_10_000 = createListOfList(10_000, internalCount)
    private val originCollection_50_000 = createListOfList(50_000, internalCount)
    private val originCollection_100_000 = createListOfList(100_000, internalCount)

    private fun flatten_standard(sourceCollection: List<List<Int>>): Int {
        return sourceCollection.asSequence()
            .flatten()
            .last()
    }

    fun flatten_optimized(sourceCollection: List<List<Int>>): Int {
        return sourceCollection.asSequence()
            .optimizedFlatten()
            .last()
    }

    @Test
    fun flatten_100_rec_standard() {
        var result = 0
        benchmarkRule.measureRepeated {
            result = flatten_standard(originCollection_100)
        }
        Assert.assertTrue(result >= 0)
    }

    @Test
    fun flatten_1000_rec_standard() {
        var result = 0
        benchmarkRule.measureRepeated {
            result = flatten_standard(originCollection_1_000)
        }
        Assert.assertTrue(result >= 0)
    }

    @Test
    fun flatten_10000_rec_standard() {
        var result = 0
        benchmarkRule.measureRepeated {
            result = flatten_standard(originCollection_10_000)
        }
        Assert.assertTrue(result >= 0)
    }

    @Test
    fun flatten_50000_rec_standard() {
        var result = 0
        benchmarkRule.measureRepeated {
            result = flatten_standard(originCollection_50_000)
        }
        Assert.assertTrue(result >= 0)
    }

    @Test
    fun flatten_100000_rec_standard() {
        var result = 0
        benchmarkRule.measureRepeated {
            result = flatten_standard(originCollection_100_000)
        }
        Assert.assertTrue(result >= 0)
    }

    @Test
    fun flatten_100_rec_optimized() {
        var result = 0
        benchmarkRule.measureRepeated {
            result = flatten_optimized(originCollection_100)
        }
        Assert.assertTrue(result >= 0)
    }

    @Test
    fun flatten_1000_rec_optimized() {
        var result = 0
        benchmarkRule.measureRepeated {
            result = flatten_optimized(originCollection_1_000)
        }
        Assert.assertTrue(result >= 0)
    }

    @Test
    fun flatten_10000_rec_optimized() {
        var result = 0
        benchmarkRule.measureRepeated {
            result = flatten_optimized(originCollection_10_000)
        }
        Assert.assertTrue(result >= 0)
    }

    @Test
    fun flatten_50000_rec_optimized() {
        var result = 0
        benchmarkRule.measureRepeated {
            result = flatten_optimized(originCollection_50_000)
        }
        Assert.assertTrue(result >= 0)
    }

    @Test
    fun flatten_100000_rec_optimized() {
        var result = 0
        benchmarkRule.measureRepeated {
            result = flatten_optimized(originCollection_100_000)
        }
        Assert.assertTrue(result >= 0)
    }

}
