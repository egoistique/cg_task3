
package graphicseditor.controls;

import canva.Canva;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * Графический элемент изменения размера сетки канвы.
 * 
 * @author	Gomon Sergey
 */
public class CellSizeSpinner {

    private int minSpinnVal;
    private int maxSpinnVal;
    private int spinnStep;

    private JSpinner spinner;
    private Canva canva;

    public CellSizeSpinner(Canva canva, int min, int max, int step) {
		
        this.canva = canva;
        this.minSpinnVal = min;
        this.maxSpinnVal = max;
        this.spinnStep = step;

        SpinnerModel spModelCell = new SpinnerNumberModel(
				canva.getCellSize(), min, max, step);
        spinner = new JSpinner(spModelCell);
        Listener listener = new Listener();
        spinner.addChangeListener(listener);
        canva.addMouseWheelListener(listener);
    }

    public JSpinner getSpinner() {
        return spinner;
    }

    private int getInc(int val, int inc) {
        if (val + inc < minSpinnVal) return minSpinnVal;
        if (val + inc > maxSpinnVal) return maxSpinnVal;
        return val + inc;
    }

    private class Listener implements MouseWheelListener, ChangeListener, KeyListener {
		@Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            spinner.setValue(getInc((Integer)(spinner.getValue()), 
					e.getWheelRotation()*spinnStep));
        }

		@Override
        public void stateChanged(ChangeEvent e) {
            canva.setCellSize((Integer) spinner.getValue());
        }

		@Override
		public void keyTyped(KeyEvent e) {
			canva.setCellSize((Integer) spinner.getValue());
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}
    }
}
