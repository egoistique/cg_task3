
package shapes.panels;

import javax.swing.*;

/**
 * Базовый класс для панели инструментов фигуры. Каждая фигура имеет свою
 * панель инструментов.
 * 
 * @author  Gomon Sergey
 */
public abstract class AbstractGraphicsObjectPanel extends JPanel {
	public abstract void updatePanel();
}
