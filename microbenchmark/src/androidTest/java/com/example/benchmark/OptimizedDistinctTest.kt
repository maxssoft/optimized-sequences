package com.example.benchmark

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.benchmark.data.createIntList
import com.example.benchmark.optimized.optimizedDistinctBy
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Benchmark test showing the different between optimized and standard implementation of distinct function
 *
 * @author Max Sidorov on 21.04.2023
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class OptimizedDistinctTest {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    private val originCollection_100 = createIntList(100)
    private val originCollection_1_000 = createIntList(1_000)
    private val originCollection_10_000 = createIntList(10_000)
    private val originCollection_50_000 = createIntList(50_000)
    private val originCollection_100_000 = createIntList(100_000)

    private fun distinct_standard(sourceCollection: List<Int>): Int {
        return sourceCollection.asSequence()
            .distinctBy { it }
            .last()
    }

    private fun distinct_optimized(sourceCollection: List<Int>): Int {
        return sourceCollection.asSequence()
            .optimizedDistinctBy { it }
            .last()
    }

    @Test
    fun distinct_100_rec_standard() {
        var result: Int = 0
        benchmarkRule.measureRepeated {
            result = distinct_standard(originCollection_100)
        }
        Assert.assertTrue(result >= 0)
    }

    @Test
    fun distinct_1000_rec_standard() {
        var result: Int = 0
        benchmarkRule.measureRepeated {
            result = distinct_standard(originCollection_1_000)
        }
        Assert.assertTrue(result >= 0)
    }

    @Test
    fun distinct_10000_rec_standard() {
        var result: Int = 0
        benchmarkRule.measureRepeated {
            result = distinct_standard(originCollection_10_000)
        }
        Assert.assertTrue(result >= 0)
    }

    @Test
    fun distinct_50000_rec_standard() {
        var result: Int = 0
        benchmarkRule.measureRepeated {
            result = distinct_standard(originCollection_50_000)
        }
        Assert.assertTrue(result >= 0)
    }

    @Test
    fun distinct_100000_rec_standard() {
        var result: Int = 0
        benchmarkRule.measureRepeated {
            result = distinct_standard(originCollection_100_000)
        }
        Assert.assertTrue(result >= 0)
    }

    @Test
    fun distinct_100_rec_optimized() {
        var result: Int = 0
        benchmarkRule.measureRepeated {
            result = distinct_optimized(originCollection_100)
        }
        Assert.assertTrue(result >= 0)
    }

    @Test
    fun distinct_1000_rec_optimized() {
        var result: Int = 0
        benchmarkRule.measureRepeated {
            result = distinct_optimized(originCollection_1_000)
        }
        Assert.assertTrue(result >= 0)
    }

    @Test
    fun distinct_10000_rec_optimized() {
        var result: Int = 0
        benchmarkRule.measureRepeated {
            result = distinct_optimized(originCollection_10_000)
        }
        Assert.assertTrue(result >= 0)
    }

    @Test
    fun distinct_50000_rec_optimized() {
        var result: Int = 0
        benchmarkRule.measureRepeated {
            result = distinct_optimized(originCollection_50_000)
        }
        Assert.assertTrue(result >= 0)
    }

    @Test
    fun distinct_100000_rec_optimized() {
        var result: Int = 0
        benchmarkRule.measureRepeated {
            result = distinct_optimized(originCollection_100_000)
        }
        Assert.assertTrue(result >= 0)
    }
}
