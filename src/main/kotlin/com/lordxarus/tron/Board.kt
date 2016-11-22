package com.lordxarus.tron

import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.util.*
import javax.swing.JPanel

import com.lordxarus.tron.BoardConsts.*
import com.lordxarus.tron.Direction.*
import java.awt.Color

/**
 * Created by jeremy on 11/20/16.
 */
class Board: JPanel(), KeyListener {

    var board = Array(4096, {i -> BoardConsts.SPACE})
    val boardWidth = 64
    var dir = LEFT
    var running = true

    init {
        board.set2d(6, 6, boardWidth, RED)
    }

    fun update() {
        var index : Int = 0
        board.forEachIndexed { i, data ->
            if (data == RED) {
                val x = i % boardWidth
                val y = i / boardWidth
                board[i] = RED_STREAK
                index = (y + dir.direction.second) * boardWidth + (x + dir.direction.first)
            }
        }
        if (board[index] != RED_STREAK) {
            board[index] = RED
        } else {
            running = false
        }
        repaint()
    }

    override fun paintComponent(g: Graphics) {
        board.forEachIndexed { i, data ->
            g.color = Color.WHITE
            when (data) {
                RED -> g.color = Color.RED
                RED_STREAK -> g.color = Color.RED
                BLUE -> g.color = Color.BLUE
                GREEN -> g.color = Color.GREEN
                ORANGE -> g.color = Color.ORANGE
            }
            g.fillRect(((i % boardWidth) * 16) - 16, ((i / boardWidth) * 16) - 16, 16, 16)
        }

        g.color = Color.BLACK
        for (i in 0..63) {
            g.drawLine(i * 16, 0, i * 16, this.height)
        }

        for (i in 0..63) {
            g.drawLine(0, i * 16, this.width, i * 16)
        }

    }

    override fun keyTyped(keyEvent: KeyEvent?) {

    }

    override fun keyPressed(keyEvent: KeyEvent?) {

        if (keyEvent?.keyCode == KeyEvent.VK_SPACE) {
            board = Array(4096, {i -> BoardConsts.SPACE})
            board.set2d(5, 5, boardWidth, RED)
            running = true
            repaint()
        }

        when(keyEvent?.keyCode) {
            KeyEvent.VK_UP -> if (dir != DOWN) dir = UP
            KeyEvent.VK_DOWN -> if (dir != UP) dir = DOWN
            KeyEvent.VK_LEFT -> if (dir != RIGHT) dir = LEFT
            KeyEvent.VK_RIGHT -> if (dir != LEFT) dir = RIGHT
        }
    }

    override fun keyReleased(p0: KeyEvent?) {
//        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
