/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * WuLinePanel.java
 *
 * Created on Nov 10, 2011, 2:41:44 AM
 */
package shapes.panels;

import canva.Canva;
import shapes.controls.PointsMoveControl;
import shapes.shapes.WuLine;
import utils.Utils;

/**
 *
 * @author Sergey
 */
public class WuLinePanel extends AbstractGraphicsObjectPanel {

	private WuLine line;
	private Canva canva;	
	
	/** Creates new form WuLinePanel */
	public WuLinePanel(WuLine line, Canva canva) {
		initComponents();
		pointMoveButton.setIcon(Utils.getIcon("point_move", 25, 25));
		pointMoveButton.setToolTipText("Перемещение точек");
		pointMoveButton.setFocusable(false);		
		this.line = line;
		this.canva = canva;
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pointMoveButton = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Действия"));

        pointMoveButton.setMaximumSize(new java.awt.Dimension(26, 26));
        pointMoveButton.setMinimumSize(new java.awt.Dimension(26, 26));
        pointMoveButton.setPreferredSize(new java.awt.Dimension(26, 26));
        pointMoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pointMoveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pointMoveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pointMoveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(252, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

	private void pointMoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pointMoveButtonActionPerformed
		canva.setGraphicsObjectControl(new PointsMoveControl(line));
	}//GEN-LAST:event_pointMoveButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton pointMoveButton;
    // End of variables declaration//GEN-END:variables

	@Override
	public void updatePanel() {
		
	}
}
