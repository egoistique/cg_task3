
package graphicseditor;

import canva.Canva;
import graphicseditor.controls.ControlsToolBar;
import graphicseditor.controls.MainMenu;
import graphicseditor.controls.ShapesToolBar;
import graphicseditor.controls.StatusBar;
import shapes.panels.AbstractGraphicsObjectPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Класс главного окна программы.
 * 
 * @author Gomon Sergey
 */
public class MainWindow extends javax.swing.JFrame {

    private Canva canva;
    private StatusBar statusBar;
	private ShapesToolBar shapesToolBar;
	private AbstractGraphicsObjectPanel graphicsObjectPanel;
	private JSplitPane splitPane;

    public MainWindow() {
        initComponents();
        initGraphicsEditor();
    }

    private void initGraphicsEditor() {
		
		setMinimumSize(new Dimension(800, 600));
		
        statusBar = new StatusBar();
		canva = new Canva(this, statusBar);
		shapesToolBar = new ShapesToolBar(canva, statusBar, this);
		
		graphicsObjectPanel = null;
		
		setJMenuBar(new MainMenu(canva));

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		JComponent canvaScrollPane = canva.getScrollPane();
		splitPane.add(canvaScrollPane);
		splitPane.setOneTouchExpandable(true);
		splitPane.setResizeWeight(1);
		
        getContentPane().add(splitPane,
                java.awt.BorderLayout.CENTER);
        getContentPane().add(new ControlsToolBar(canva),
                java.awt.BorderLayout.NORTH);
        getContentPane().add(shapesToolBar,
                java.awt.BorderLayout.WEST);
        getContentPane().add(statusBar,
                java.awt.BorderLayout.SOUTH);
    }

	public void selectCursor() {
		shapesToolBar.selectCursor();
	}

	public void selectCursorLeaveSelectedShape() {
		shapesToolBar.selectCursorLeaveSelectedShape();
	}
	
    public void setStatusBarMousePosition(int x, int y) {
        statusBar.setMouseXY(x, y);
    }

	public void setGraphicsObjectPanel(
			AbstractGraphicsObjectPanel newPanel) {
		if(graphicsObjectPanel != null) {
			splitPane.remove(graphicsObjectPanel);
		}
		if(newPanel != null) {
			splitPane.add(newPanel);
		}
		graphicsObjectPanel = newPanel;
	}
	
	public void updateGraphicsObjectPanel() {
		if(graphicsObjectPanel != null) {
			graphicsObjectPanel.updatePanel();
		}
	}
	
    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
            public void run() {
                MainWindow mw = new MainWindow();
                mw.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
