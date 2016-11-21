package com.lordxarus.tron

import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JPanel

/**
 * Created by jeremy on 11/20/16.
 */
class Board: JPanel(), KeyListener {

    override fun paintComponent(g: Graphics) {
        for (i in 0..this.width / 16) {
            g.drawLine(i * 16, 0, i * 16, this.height)
        }

        for (i in 0..this.height / 16) {
            g.drawLine(0, i * 16, this.width, i * 16)
        }
    }

    override fun keyTyped(p0: KeyEvent?) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun keyPressed(p0: KeyEvent?) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun keyReleased(p0: KeyEvent?) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
