package com.lordxarus.tron

/**
 * Created by jeremy on 11/20/16.
 */

fun Array<BoardConsts>.set2d(x: Int, y: Int, width: Int, value: BoardConsts) {
    this[y * width + x] = value
}

//fun applyDirection()
