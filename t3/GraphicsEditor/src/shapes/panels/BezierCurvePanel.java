
package shapes.panels;

import canva.Canva;
import shapes.controls.PointsMoveControl;
import shapes.shapes.BezierCurve;
import utils.Utils;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import java.text.DecimalFormat;

/**
 *
 * @author Gomon Sergey
 */
public class BezierCurvePanel extends AbstractGraphicsObjectPanel {

	private BezierCurve curve;
	private Canva canva;
	
	public BezierCurvePanel(BezierCurve curve, final Canva canva) {
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

        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        pointMoveButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        antialiasingCheckBox = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jCheckBox1.setText("jCheckBox1");

        setMinimumSize(new java.awt.Dimension(250, 0));

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

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Точки"));
        jPanel4.setToolTipText("");

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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

	private void pointMoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pointMoveButtonActionPerformed
		canva.setGraphicsObjectControl(new PointsMoveControl(curve));
	}//GEN-LAST:event_pointMoveButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox antialiasingCheckBox;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton pointMoveButton;
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