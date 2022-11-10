/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EllipsePanel.java
 *
 * Created on Nov 24, 2011, 2:36:33 AM
 */
package shapes.panels;

import canva.Canva;
import shapes.shapes.Ellipse;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Sergey
 */
public class EllipsePanel extends AbstractGraphicsObjectPanel {

	Ellipse object;
	Canva canva;
	
	/** Creates new form EllipsePanel */
	public EllipsePanel(final Ellipse object, final Canva canva) {
		initComponents();
		this.object  = object;
		this.canva = canva;
		
		ChangeListener cangeListener = new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(object.isComplete()) {
					canva.repaintCanva();
				}
			}
		};
		
        SpinnerModel spModelCell = new SpinnerNumberModel(
				0, 0, 100, 1);
		aSpinner.setModel(spModelCell);
		spModelCell = new SpinnerNumberModel(
						0, 0, 100, 1);		
		bSpinner.setModel(spModelCell);
		aSpinner.addChangeListener(cangeListener);
		bSpinner.addChangeListener(cangeListener);
		
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
        aSpinner = new javax.swing.JSpinner();
        label1 = new java.awt.Label();
        jLabel1 = new javax.swing.JLabel();
        bSpinner = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        resTable = new javax.swing.JTable();

        setMaximumSize(new java.awt.Dimension(200, 200));
        setMinimumSize(new java.awt.Dimension(200, 200));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(300, 300));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Параметры"));

        label1.setText("a");

        jLabel1.setText("b");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(aSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                    .addComponent(bSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(aSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Координаты"));

        resTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(resTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner aSpinner;
    private javax.swing.JSpinner bSpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    private javax.swing.JTable resTable;
    // End of variables declaration//GEN-END:variables

	@Override
	public final void updatePanel() {
		
		if(object.isComplete()) {
			object.setAB((Integer)aSpinner.getValue(), (Integer)bSpinner.getValue());
		} else {
			aSpinner.setValue(object.getA());
			bSpinner.setValue(object.getB());				
		}
		
		Object[][] data = null;
		String[] columnNames = null;
		
		if(object.Dis.size() > 0) {
			columnNames = new String[] {"#", "X", "Y", "Di", "d", "Px"};
			data = new Object[object.Dis.size()][6];

			for(int i = 0; i < object.Dis.size(); i++) {
				data[i][0] = i;
				data[i][1] = object.xs.get(i);
				data[i][2] = object.ys.get(i);
				data[i][3] = object.Dis.get(i);
				data[i][4] = object.ds.get(i);
				data[i][5] = object.pxs.get(i);
			}
		} else {
			columnNames = new String[] {"#", "X", "Y"};
			data = new Object[object.Dis.size()][3];

			for(int i = 0; i < object.Dis.size(); i++) {
				data[i][0] = i;
				data[i][1] = object.xs.get(i);
				data[i][2] = object.ys.get(i);
			}			
		}
		
		resTable.setModel(new javax.swing.table.DefaultTableModel(
				data, columnNames));
	
        //Обновляем графическое представление таблицы
        ((AbstractTableModel) resTable.getModel()).fireTableDataChanged();		
	}
}
