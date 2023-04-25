# optimized-sequences
Optimized sequence functions flatten + distinct

I researched all sequences functions and found some problems with functions flatten and distinct.
They works slow. 

I optimized it and speed up flatten function on 35-40% and distinct function on 15-18%.
You can look it in microbenchmark tests.

I hope, you should use my optimization of this function in standard implementations.


## distinct [OptimizedDistinct.kt]
This function use non standard implementation with abstract class. Virtual methods is slow.
I removed abstract class.

This function use enum class for store calculation state. 
When you use "when" condition, enum class use useless operation to copy array of enum ordinals to stack. It's useless and slow.
Jake Wharton wrote about it in 2019 https://jakewharton.com/r8-optimization-enum-switch-maps/ 
Look microbenchmark test [com.example.benchmark.EnumTest] which show that Int works on 10-11% faster than enum class
I changed enum class to Int for store state

My optimized function working on 15-18% faster than standard distinct function
Look microbenchmark test [com.example.benchmark.OptimizedDistinctTest]

## flatten [OptimizedFlatten.kt]
This function call internal function [ensureItemIterator] every time, when you call [next], [hasNext] methods.
It's slow. I optimized it and added state for store result of calculation [itemIterator] variable.

This function use nullable type for variable [itemIterator]. It's add two read/write operation on every call of this variable.
I optimized it and added EmptyIterator object which replaced null value.

My optimized function working on 35-40% faster than standard distinct function
If you optimized flatten, you speed up plus function too. Plus function based on flatten.
Look microbenchmark test [com.example.benchmark.OptimizedFlattenTest]

