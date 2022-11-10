
package canva;

import javax.swing.*;
import java.awt.*;

/**
 * @author	Gomon Sergey
 * 
 * Класс рисует содержимое верхнего левого угла канвы. Там написана 
 * размерность сетки (пиксели). Часть JSCrollPane, устанавливается
 * через setCorner().
 */
public class CanvaCorner extends JComponent {
    private final Color BACKGROUND_COLOR = Color.white;
    private final Color LINE_COLOR = Color.black;
    private final String TEXT = "PEL";

    public CanvaCorner() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        int xBegin = 0;
        int xEnd = getWidth();
        int yBegin = 0;
        int yEnd = getHeight();

        g.setColor(BACKGROUND_COLOR);
        g.fillRect(xBegin, yBegin, xEnd, yEnd);

        g.setFont(new Font("SansSerif", Font.PLAIN, 9));
        g.setColor(LINE_COLOR);
        g.drawChars(TEXT.toCharArray(), 0, TEXT.length(), xEnd / 2 - 10, yEnd / 2 + 5);
    }
}
