
package graphicseditor.controls;

import canva.Canva;
import utils.Utils;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс панели инструментов главного окна
 * 
 * @author Gomon Sergey
 */
public class ControlsToolBar extends JToolBar {
	
	private static final String GRID_PICTURE = "grid";
	
	public ControlsToolBar(Canva canva) {
		
		super("Controls", JToolBar.HORIZONTAL);
		setFloatable(false);
		
		//Отступ от края
		add(Box.createRigidArea(new Dimension(8, 0)));
		
		addCanvaSizeChanger(canva);
		addCanvaCellSizeChanger(canva);
		addGridToggle(canva);
	}
	
	private void addGridToggle(final Canva canva) {
        JToggleButton gridButton = new JToggleButton(
				Utils.getIcon(GRID_PICTURE, 25, 25));
        gridButton.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                canva.showGrid(((AbstractButton)e.getSource()).getModel().isSelected());
            }
        });
        add(gridButton);
        addSeparator();			
	}
	
	private void addCanvaCellSizeChanger(final Canva canva) {
        CellSizeSpinner spCell = new CellSizeSpinner(canva, 1, 20, 1);
        add(new JLabel("Сетка: "));
        add(spCell.getSpinner());
        addSeparator();		
	}
	
	private void addCanvaSizeChanger(final Canva canva) {
		
        SpinnerModel spModelX = new SpinnerNumberModel(
				canva.getWidthCell(), 1, 1000, 1);
        JSpinner spX = new JSpinner(spModelX);
        SpinnerModel spModelY = new SpinnerNumberModel(
				canva.getHeightCell(), 1, 1000, 1);
        JSpinner spY = new JSpinner(spModelY);		
		
		add(new JLabel("Размер "));
        add(new JLabel(" X: "));
        add(spX);
        add(new JLabel(" Y: "));
        add(spY);
        addSeparator();			
		
        spX.addChangeListener(new ChangeListener() {
			@Override
            public void stateChanged(ChangeEvent e) {
                canva.setWidthCell((Integer)(((JSpinner)(e.getSource())).getValue()));
            }
        });
        
        spY.addChangeListener(new ChangeListener() {
			@Override
            public void stateChanged(ChangeEvent e) {
                canva.setHeightCell((Integer)(((JSpinner)(e.getSource())).getValue()));
            }
        });
	}
	
}
