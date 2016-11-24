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
    var players = hashMapOf(Pair(RED, LEFT), Pair(BLUE, LEFT), Pair(GREEN, LEFT), Pair(ORANGE, LEFT))
    var running = true

    init {
        board.set2d(6, 6, boardWidth, RED)
        board.set2d(10, 10, boardWidth, BLUE)
        board.set2d(14, 14, boardWidth, GREEN)
        board.set2d(18, 18, boardWidth, ORANGE)
    }

    fun update() {
        var deadPlayers = arrayListOf<BoardConsts>()
        players.forEach { color, dir ->
            var index : Int = 0
            board.forEachIndexed { i, data ->
                if (data == color) {
                    val x = i % boardWidth
                    val y = i / boardWidth
                    board[i] = getStreak(color)
                    index = (y + dir.direction.second) * boardWidth + (x + dir.direction.first)
                }
            }
            if (board[index] == SPACE) {
                board[index] = color
            } else {
                deadPlayers.add(color)
            }
            repaint()
        }
        deadPlayers.forEach { player -> players.remove(player) }
    }

    override fun paintComponent(g: Graphics) {
        board.forEachIndexed { i, data ->
            g.color = Color.WHITE
            when (data) {
                RED -> g.color = Color.RED
                RED_STREAK -> g.color = Color.RED
                BLUE -> g.color = Color.BLUE
                BLUE_STREAK -> g.color = Color.BLUE
                GREEN -> g.color = Color.GREEN
                GREEN_STREAK -> g.color = Color.GREEN
                ORANGE -> g.color = Color.ORANGE
                ORANGE_STREAK -> g.color = Color.ORANGE
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
            reset()
        }

        when(keyEvent?.keyCode) {
            KeyEvent.VK_UP -> if (players.get(RED) != DOWN) players.set(RED, UP)
            KeyEvent.VK_DOWN -> if (players.get(RED) != UP) players.set(RED, DOWN)
            KeyEvent.VK_LEFT -> if (players.get(RED) != RIGHT) players.set(RED, LEFT)
            KeyEvent.VK_RIGHT -> if (players.get(RED) != LEFT) players.set(RED, RIGHT)
        }
    }

    override fun keyReleased(p0: KeyEvent?) {
//        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun reset() {
        board = Array(4096, {i -> BoardConsts.SPACE})
        board.set2d(6, 6, boardWidth, RED)
        board.set2d(10, 10, boardWidth, BLUE)
        board.set2d(14, 14, boardWidth, GREEN)
        board.set2d(18, 18, boardWidth, ORANGE)
        players = hashMapOf(Pair(RED, LEFT), Pair(BLUE, LEFT), Pair(GREEN, LEFT), Pair(ORANGE, LEFT))
        running = true
        repaint()
    }

}
