
package shapes.panels;

import canva.Canva;
import shapes.controls.PointsMoveControl;
import shapes.shapes.HermitCurve;
import shapes.utils.Coordinate;
import utils.Utils;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

/**
 *
 * @author Gomon Sergey
 */
public class HermitCurvePanel extends AbstractGraphicsObjectPanel {

	private HermitCurve curve;
	private Canva canva;
	
	public HermitCurvePanel(HermitCurve curve, final Canva canva) {
		initComponents();
		
		pointMoveButton.setIcon(Utils.getIcon("point_move", 25, 25));
		pointMoveButton.setToolTipText("Перемещение точек");
		pointMoveButton.setFocusable(false);
		
		this.curve = curve;
		this.canva = canva;
		
		r1xSpinner.setValue(curve.getBeginVector().get(0));
		r1ySpinner.setValue(curve.getBeginVector().get(1));
		r2xSpinner.setValue(curve.getEndVector().get(0));
		r2ySpinner.setValue(curve.getEndVector().get(1));
		
		ChangeListener cangeListener = new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				canva.repaintCanva();
			}
		};
		
		KeyAdapter keyListener = new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				canva.repaintCanva();
			}
		};
		
		antialiasingCheckBox.addChangeListener(cangeListener);
		r1xSpinner.addChangeListener(cangeListener);
		r1ySpinner.addChangeListener(cangeListener);
		r2xSpinner.addChangeListener(cangeListener);
		r2ySpinner.addChangeListener(cangeListener);
		r1xSpinner.addKeyListener(keyListener);
		r1ySpinner.addKeyListener(keyListener);
		r2xSpinner.addKeyListener(keyListener);
		r2ySpinner.addKeyListener(keyListener);		
		
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        antialiasingCheckBox = new javax.swing.JCheckBox();
        r1xSpinner = new javax.swing.JSpinner();
        r1ySpinner = new javax.swing.JSpinner();
        r2xSpinner = new javax.swing.JSpinner();
        r2ySpinner = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        pointMoveButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(250, 0));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Настройки"));

        jLabel1.setText("R1 X:");

        jLabel2.setText("R2 X:");

        jLabel5.setText("Y:");

        jLabel6.setText("Y:");

        antialiasingCheckBox.setText("Сглаживание");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(r2xSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(r2ySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(antialiasingCheckBox)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(r1xSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(r1ySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(r1xSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(r1ySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(r2xSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(r2ySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(antialiasingCheckBox))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Действия"));

        pointMoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pointMoveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(pointMoveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(212, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pointMoveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Точки"));

        resultTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(resultTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

	private void pointMoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pointMoveButtonActionPerformed
		canva.setGraphicsObjectControl(new PointsMoveControl(curve));
	}//GEN-LAST:event_pointMoveButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox antialiasingCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton pointMoveButton;
    private javax.swing.JSpinner r1xSpinner;
    private javax.swing.JSpinner r1ySpinner;
    private javax.swing.JSpinner r2xSpinner;
    private javax.swing.JSpinner r2ySpinner;
    private javax.swing.JTable resultTable;
    // End of variables declaration//GEN-END:variables

	@Override
	public final void updatePanel() {
		if(curve.isComplete()) {
			pointMoveButton.setEnabled(true);
		} else {
			pointMoveButton.setEnabled(false);
		}

		curve.setAntialiasing(antialiasingCheckBox.isSelected());
		
		curve.setBeginVector(new Coordinate(null, (Integer)r1xSpinner.getValue(), (Integer)r1ySpinner.getValue()));
		curve.setEndVector(new Coordinate(null, (Integer)r2xSpinner.getValue(), (Integer)r2ySpinner.getValue()));
		
			
		String[] columnNames = {"t", "X", "Y"};
		Object[][] data = new Object[curve.getInfoListSize()][3];

		DecimalFormat myFormatter = new DecimalFormat("######.##");
		
		for(int i = 0; i < curve.getInfoListSize(); i++) {
			data[i][0] = myFormatter.format(curve.getTList().get(i));
			data[i][1] = myFormatter.format(curve.getXList().get(i));
			data[i][2] = myFormatter.format(curve.getYList().get(i));
		}
		
		resultTable.setModel(new javax.swing.table.DefaultTableModel(
				data, columnNames));
	
        //Обновляем графическое представление таблицы
        ((AbstractTableModel) resultTable.getModel()).fireTableDataChanged();		
	}
}