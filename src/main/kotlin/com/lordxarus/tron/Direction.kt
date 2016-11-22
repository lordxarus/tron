package com.lordxarus.tron

/**
 * Created by jeremy on 11/21/16.
 */
enum class Direction(val direction: Pair<Int, Int>) {

    UP(Pair(0, -1)), DOWN(Pair(0, 1)), LEFT(Pair(-1, 0)), RIGHT(Pair(1, 0));

}