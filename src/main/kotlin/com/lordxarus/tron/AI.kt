package com.lordxarus.tron

import com.lordxarus.tron.BoardConsts.*;
import com.lordxarus.tron.Direction.*;

/**
 * Created by jeremy on 11/26/16.
 */
object AI {

    fun getMove(board: Array<BoardConsts>, player: BoardConsts, boardWidth: Int): Direction {
        for (i in 0..3) {
            val direction = getDirection(i)
            board.forEachIndexed { i, data ->
                if (data == player) {
                    val x = i % boardWidth
                    val y = i / boardWidth
                    val index = (y + direction.direction.second) * boardWidth + (x + direction.direction.first)
                    if (index >= 0 && index < board.size) {
                        if (board[index] == SPACE) {
                            return direction
                        }
                    }
                }
            }
        }
        return UP
    }

}