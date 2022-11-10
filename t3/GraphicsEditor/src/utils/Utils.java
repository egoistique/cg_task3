/*
 * Utils.java
 * 
 * date:    18.09.2011
 * auth:    Gomon Sergey
 * 
 * Класс вспомогательных методов.
 */
package utils;

import javax.swing.*;
import java.awt.*;

public class Utils {
    //Метод загружает кардинку из каталога resources проекта по названию
    public static Icon getIcon(String name, int width, int height) {
        ImageIcon icon = new ImageIcon(
                Utils.class.getResource("/resources/" + name + ".png"));
        Image image = icon.getImage().getScaledInstance(
                width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
	
	public static int getSign(int x) {
		return x > 0 ? 1 : -1;
	}
	
	public static int getSign(double x) {
		return x > 0 ? 1 : -1;
	}
}
