package com.example.benchmark

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Random

/**
 * Benchmark test showing the different between when(enum) and when(Int)
 *
 * @author Max Sidorov on 15.04.2023
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class EnumTest {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    private val iterationCount = 10_000

    private val random = Random()

    @Test
    fun enum_test() {
        var sum = 0
        val list = createEnumList(iterationCount)
        benchmarkRule.measureRepeated {
            list.forEach { value ->
                sum += when (value) {
                    TestEnum.TEST_1 -> 0
                    TestEnum.TEST_2 -> 1
                    TestEnum.TEST_3 -> 2
                    TestEnum.TEST_4 -> 3
                    TestEnum.TEST_5 -> 4
                }
            }
        }
        Assert.assertTrue(sum > 0)
    }

    @Test
    fun int_test() {
        var sum = 0
        val list = createIntList(iterationCount)
        benchmarkRule.measureRepeated {
            list.forEach {value ->
                sum += when (value) {
                    TEST_1 -> 0
                    TEST_2 -> 1
                    TEST_3 -> 2
                    TEST_4 -> 3
                    TEST_5 -> 4
                    else -> value
                }
            }
        }
        Assert.assertTrue(sum > 0)
    }

    private fun createEnumList(count: Int): List<TestEnum> {
        return mutableListOf<TestEnum>().also { list ->
            (0..count).forEach {
                list.add(TestEnum.values()[getRandomIndex()])
            }
        }
    }

    private fun createIntList(count: Int): List<Int> {
        return mutableListOf<Int>().also { list ->
            (0..count).forEach {
                list.add(getRandomIndex())
            }
        }
    }

    private fun getRandomIndex(): Int = random.nextInt(TestEnum.values().size)

    enum class TestEnum {
        TEST_1,
        TEST_2,
        TEST_3,
        TEST_4,
        TEST_5,
    }

    companion object {
        const val TEST_1 = 0
        const val TEST_2 = 1
        const val TEST_3 = 2
        const val TEST_4 = 3
        const val TEST_5 = 4
    }
}