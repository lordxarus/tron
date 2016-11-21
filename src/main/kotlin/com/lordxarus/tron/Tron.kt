package com.lordxarus.tron

import java.awt.Dimension
import javax.swing.JFrame

/**
 * Created by jeremy on 11/20/16.
 */

fun main(args: Array<String>) {
    var frame = JFrame()
    var board = Board()

    frame.add(board)
    frame.size = Dimension(800, 600)
    frame.isVisible = true

    while (true) {
        board.repaint()
        Thread.sleep(1000)
    }
}
