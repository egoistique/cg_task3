
package graphicseditor.controls;

import canva.Canva;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Главное меню
 * 
 * @author Gomon Sergey
 */
public class MainMenu extends JMenuBar {
	
	public MainMenu(Canva canva) {
		addFileMenu(canva);
		addHelpMenu();
	}
	
	private void addFileMenu(final Canva canva) {
        JMenu jmFile = new JMenu("Файл");
		add(jmFile);
		
        JMenuItem jmiClear = new JMenuItem("Очистить");
		JMenuItem jmiExit = new JMenuItem("Выход");
		
        jmFile.add(jmiClear);
        jmFile.addSeparator();		
        jmFile.add(jmiExit);		
		
        jmiClear.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                canva.clear();
            }
        });

        jmiExit.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
	}
	
	private void addHelpMenu() {
        JMenu jmHelp = new JMenu("Помощь");
		add(jmHelp);
        JMenuItem jmiAbout = new JMenuItem("О программе...");
		jmHelp.add(jmiAbout);
		
        jmiAbout.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        new JFrame(), "Альфа версия графического пиксельного редактора.\n"
                                + "Авторы: Гомон Сергей, Зорин Павел. \n"
                                + "БГУИР 2011");
            }
        });
	}
}
