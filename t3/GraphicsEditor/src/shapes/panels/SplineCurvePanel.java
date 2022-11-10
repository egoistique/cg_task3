/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SplineCurvePanel.java
 *
 * Created on Nov 14, 2011, 4:06:27 AM
 */
package shapes.panels;

import canva.Canva;
import shapes.controls.PointsMoveControl;
import shapes.shapes.SplineCurve;
import utils.Utils;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import java.text.DecimalFormat;

/**
 *
 * @author Sergey
 */
public class SplineCurvePanel extends AbstractGraphicsObjectPanel {

	private SplineCurve curve;
	private Canva canva;
	
	/** Creates new form SplineCurvePanel */
	public SplineCurvePanel(SplineCurve curve, final Canva canva) {
		initComponents();
		
		pointMoveButton.setIcon(Utils.getIcon("point_move", 25, 25));
		pointMoveButton.setToolTipText("Перемещение точек");
		pointMoveButton.setFocusable(false);		
		
		this.curve = curve;
		this.canva = canva;		
		
		ChangeListener cangeListener = new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				canva.repaintCanva();
			}
		};		

		antialiasingCheckBox.addChangeListener(cangeListener);
		
		updatePanel();	
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
        jPanel2 = new javax.swing.JPanel();
        antialiasingCheckBox = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        resultTable = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(250, 0));
        setPreferredSize(new java.awt.Dimension(250, 435));

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
                .addContainerGap(212, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pointMoveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Настройки"));

        antialiasingCheckBox.setText("Сглаживание");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(antialiasingCheckBox)
                .addContainerGap(145, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(antialiasingCheckBox)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Точки"));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        resultTable.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(resultTable, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(resultTable, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

	private void pointMoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pointMoveButtonActionPerformed
		canva.setGraphicsObjectControl(new PointsMoveControl(curve));
	}//GEN-LAST:event_pointMoveButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox antialiasingCheckBox;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton pointMoveButton;
    private javax.swing.JScrollPane resultTable;
    // End of variables declaration//GEN-END:variables

	@Override
	public final void updatePanel() {
		if(curve.isComplete()) {
			pointMoveButton.setEnabled(true);
		} else {
			pointMoveButton.setEnabled(false);
		}

		curve.setAntialiasing(antialiasingCheckBox.isSelected());
			
		String[] columnNames = {"t", "X", "Y"};
		Object[][] data = new Object[curve.getInfoListSize()][3];

		DecimalFormat myFormatter = new DecimalFormat("######.##");
		
		for(int i = 0; i < curve.getInfoListSize(); i++) {
			data[i][0] = myFormatter.format(curve.getTList().get(i));
			data[i][1] = myFormatter.format(curve.getXList().get(i));
			data[i][2] = myFormatter.format(curve.getYList().get(i));
		}
		
		jTable1.setModel(new javax.swing.table.DefaultTableModel(
				data, columnNames));
	
        //Обновляем графическое представление таблицы
        ((AbstractTableModel) jTable1.getModel()).fireTableDataChanged();		
	}
}