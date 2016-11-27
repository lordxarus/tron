package com.lordxarus.tron

import com.lordxarus.tron.BoardConsts.*
import com.lordxarus.tron.Direction.*
/**
 * Created by jeremy on 11/24/16.
 */

/**
 * @color RED, BLUE, GREEN or ORANGE
 * @return The streak version of that color
 * **/
fun getStreak(color: BoardConsts): BoardConsts {
    when (color) {
        RED -> return RED_STREAK
        BLUE -> return BLUE_STREAK
        GREEN -> return GREEN_STREAK
        ORANGE -> return ORANGE_STREAK
    }
    return SPACE
}

fun getDirection(num: Int): Direction {
    when (num) {
        0 -> return UP
        1 -> return DOWN
        2 -> return LEFT
        3 -> return RIGHT
    }
    return UP
}