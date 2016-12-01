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
import java.awt.Component

/**
 * Created by jeremy on 11/20/squareSize.
 */
class Board: JPanel(), KeyListener {

    var board = Array(16384, {i -> BoardConsts.SPACE})
    val boardWidth = 128
    val squareSize = 4
    lateinit var players : HashMap<BoardConsts, Direction>
    var running = true

    init {
        reset()
    }

    /**
     * Manipulates the board array with direction vectors stored in players
     * as well as getting the next move for the AI players
     * **/
    fun update() {
        val deadPlayers = arrayListOf<BoardConsts>()

        // Massively inefficient (store player location instead of iterating over the board)
        players.forEach { player, direction ->
            if (player != RED) players.set(player, AI.getMove(board, player, boardWidth))
        }

        players.forEach { color, dir ->
            var index : Int = 0
            board.forEachIndexed { i, data ->
                if (data == color) {
                    var x = i % boardWidth
                    var y = i / boardWidth

                    // By nature of the 2d array the horizontal axis wraps naturally but not the vertical
                    if (y == 0 && dir == UP) {
                        y = boardWidth
                        x -= 1
                    }
                    if (y == boardWidth - 1 && dir == DOWN) {
                        y = 0
                        x += 1
                    }

                    // Set the previous position to streak
                    board[i] = getStreak(color)
                    // Get the index of the new position of the player
                    index = (y + dir.direction.second) * boardWidth + (x + dir.direction.first)
                }
            }
            if (index < board.size && index > 0) {
                if (board[index] == SPACE) {
                    board[index] = color
                } else {
                    deadPlayers.add(color)
                }
            }
            repaint()
        }
        deadPlayers.forEach { player -> players.remove(player) }
    }

    override fun paintComponent(g: Graphics) {
        // Render players
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
            val x = i % boardWidth
            val y = i / boardWidth
            g.fillRect(((x) * squareSize) - squareSize, ((y) * squareSize) - squareSize, squareSize, squareSize)
        }
        // Grid

/*        g.color = Color.BLACK
        for (i in 0..63) {
            g.drawLine(i * squareSize, 0, i * squareSize, this.height)
        }

        for (i in 0..63) {
            g.drawLine(0, i * squareSize, this.width, i * squareSize)
        }*/

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

    override fun keyTyped(keyEvent: KeyEvent?) {}
    override fun keyReleased(p0: KeyEvent?) {}

    fun reset() {
        board = Array(board.size, {i -> BoardConsts.SPACE})
        board.set2d(rand.nextInt(boardWidth), rand.nextInt(boardWidth), boardWidth, RED)
        board.set2d(rand.nextInt(boardWidth), rand.nextInt(boardWidth), boardWidth, BLUE)
        board.set2d(rand.nextInt(boardWidth), rand.nextInt(boardWidth), boardWidth, GREEN)
        board.set2d(rand.nextInt(boardWidth), rand.nextInt(boardWidth), boardWidth, ORANGE)
        players = hashMapOf(Pair(RED, getDirection(rand.nextInt(3))), Pair(BLUE, getDirection(rand.nextInt(3))), Pair(GREEN, getDirection(rand.nextInt(3))), Pair(ORANGE, getDirection(rand.nextInt(3))))
        running = true
        repaint()
    }

}
