
package canva;

import graphicseditor.MainWindow;
import graphicseditor.controls.StatusBar;
import shapes.IGraphicsObject;
import shapes.controls.IGraphicsObjectControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс канвы. У данного класса есть список фигур, которые реализуюм интерфейс 
 * IGraphicsObject. Эти фигуры прорисовываются в методе paintComponent(). 
 * 
 * @author Gomon Serge
 */
public class Canva extends javax.swing.JPanel {

    //Константные параметры канвы
    private final int		DEFAULT_CELL_SIZE	= 9;
	private final int		DEFAULT_HEIGHT		= 90;
	private final int		DEFAULT_WIDTH		= 90;
    private final Color		BACKGROUND_COLOR	= Color.white;
    private final Color		LINE_COLOR			= Color.gray;

    private MainWindow mainWindow;
	private StatusBar statusBar;

    private int heightCell;		//Высота поля (клеток)
    private int widthCell;		//Ширина поля (клеток)
    private int cellSize;		//Размер клетки (пикселей)

    private CanvaAxis absciss;
    private CanvaAxis ordinate;
	private CanvaCorner corner;

    private boolean showGrid;
    //private BufferedImage gridImage;

    private List<IGraphicsObject> shapes;
    private IGraphicsObject currentShape;        //Текущая фигура

	private IGraphicsObjectControl currentGraphicsObjectControl;
	
	/**
	 * 
	 * @param mainWindow главное окно программы
	 */
	public Canva(MainWindow mainWindow, StatusBar statusBar) {
		
		initComponents();
		setBackground(BACKGROUND_COLOR);
		
        this.mainWindow		= mainWindow;
		this.statusBar		= statusBar;
		this.shapes			= new ArrayList<IGraphicsObject>();
		this.absciss		= new CanvaAxis(CanvaAxis.Type.HORIZONTAL);
		this.ordinate		= new CanvaAxis(CanvaAxis.Type.VERTICAL);
		this.corner			= new CanvaCorner();
        this.heightCell		= DEFAULT_HEIGHT;
        this.widthCell		= DEFAULT_WIDTH;
        this.cellSize		= DEFAULT_CELL_SIZE;

        this.showGrid = false;

        this.addMouseMotionListener(new MouseMotionListener() {
			@Override
            public void mouseDragged(MouseEvent e) {
				if (currentShape != null) {
					currentShape.processMouseMove(getCellX(e.getX()), getCellY(e.getY()));
				}				
				if(currentGraphicsObjectControl != null) {
					currentGraphicsObjectControl.processMouseMove(getX(e.getX()), getY(e.getY()), cellSize);
				}
				setStatusBarMousePosition(e.getX(), e.getY());
				repaintCanva();
            }

			@Override
            public void mouseMoved(MouseEvent e) {
                setStatusBarMousePosition(e.getX(), e.getY());			
            }
        });

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					if (currentShape != null) {
						if(!currentShape.isComplete()) {
							currentShape.processMouseDoubleClick(getCellX(e.getX()), getCellY(e.getY()));
						}		
						if(currentShape.isComplete()) {
							resetShape();
						}						
					}
					if(currentGraphicsObjectControl != null) {
						currentGraphicsObjectControl.processMouseDoubleClick(getX(e.getX()), getY(e.getY()), cellSize);
					}
					repaintCanva();					
				}
            }

			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getClickCount() == 1) {
					if (currentShape != null) {
						if(!currentShape.isComplete()) {
							currentShape.processMousePress(getCellX(e.getX()), getCellY(e.getY()));
						}				
					}
					if(currentGraphicsObjectControl != null) {
						boolean res = currentGraphicsObjectControl.processMousePress(getX(e.getX()), getY(e.getY()), cellSize);
						if(!res) {
							resetGraphicsObjectControl();
						}
					}
					repaintCanva();
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if(currentShape != null) {
					if(!currentShape.isComplete()) {
						currentShape.processMouseRelease(getCellX(e.getX()), getCellY(e.getY()));
					}
					if(currentShape.isComplete()) {
						resetShape();
					}					
				}
				if(currentGraphicsObjectControl != null) {
					currentGraphicsObjectControl.processMouseRelease(getX(e.getX()), getY(e.getY()), cellSize);
				}
				repaintCanva();	
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}
        });
		
		revalidate();
		repaintCanva();
    }

	public void setGraphicsObjectControl(IGraphicsObjectControl control) {
		currentGraphicsObjectControl = control;
		statusBar.setAction(control.getName());
		repaintCanva();
	}
	
	public void resetGraphicsObjectControl() {
		currentGraphicsObjectControl = null;
		resetShape();
	}	
	
	/**
	 * 
	 * @return JScrollPane с установленной в центе канвой.
	 */
	public JComponent getScrollPane() {
        JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setColumnHeaderView(absciss);
        scrollPane.setRowHeaderView(ordinate);
        scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, corner);
        return scrollPane;
	}	
	
	/**
	 * 
	 * @return размер сетки
	 */
	public int getCellSize() {
		return cellSize;
    }	
	
	/**
	 * 
	 * @param shape новая фигура
	 */
	public void setCurrentShape(IGraphicsObject shape) {
		if(currentShape != null) {
			if(!currentShape.isComplete()) {
				shapes.remove(currentShape);
			}
		}
		if(shape != null) {
			shapes.add(shape);
		}
		currentShape = shape;
		currentGraphicsObjectControl = null;
    }

	/**
	 * 
	 * @param showGrid если true, то сетка будет прорисовываться, иначе
	 * сетка прориовываться не будет
	 */
	public void showGrid(boolean showGrid) {
        this.showGrid = showGrid;
		repaintCanva();
    }

	/**
	 * 
	 * @param cellSize новый размер сетки
	 */
	public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
		revalidate();
		repaintCanva();
    }

	/**
	 * 
	 * @param height новая ширина канвы
	 */
	public void setHeightCell(int height) {
        this.heightCell = height;
		revalidate();
		repaintCanva();
    }

	/**
	 * 
	 * @param width новая высота канвы
	 */
	public void setWidthCell(int width) {
        this.widthCell = width;
		revalidate();
		repaintCanva();
    }	
	
	public int getHeightCell() {
		return heightCell;
	}
	
	public int getWidthCell() {
		return widthCell;
	}
	
	/**
	 * 
	 * Сбрасывает сбрасывает текущую фигуру и сбрасывает её у mainWindow.
	 */
	private void resetShape() {
		mainWindow.selectCursorLeaveSelectedShape();
		statusBar.setAction("Выделение");
		repaintCanva();
	}
	
	
	/**
	 * 
	 * Устанавливает значение координат в строке состояния главного окна.
	 * Передаваемые параметры имеют значение в пикселях, они переводятся
	 * в значение в клетках поля.
	 * 
	 * @param x координата x в пикселях
	 * @param y координата y в пикселях
	 */
	private void setStatusBarMousePosition(int x, int y) {
		mainWindow.setStatusBarMousePosition(getCellX(x), getCellY(y));
	}	
	
	private int getCellX(int x) {
		int xCell = x/cellSize;
		return xCell < widthCell ? xCell : widthCell - 1;
		//return xCell;
	}
	
	private int getCellY(int y) {
		int yCell = y/cellSize;
		return yCell < heightCell ? yCell : heightCell - 1;
		//return yCell;
	}

	private int getX(int x) {
		if(x < 0) return 0;
		return x > widthCell*cellSize ? widthCell*cellSize - 1 : x;
		//return x;
	}
	
	private int getY(int y) {
		if(y < 0) return 0;
		return y > heightCell*cellSize ? heightCell*cellSize - 1 : y;
		//return y;
	}	
	
	/**
	 * 
	 * Перерисовывает канву, все фигуры и т. п.
	 */
    public final void repaintCanva() {
		mainWindow.updateGraphicsObjectPanel();
        //repaintGridImage();
        setPreferredSize(new Dimension(cellSize*widthCell, cellSize*heightCell));
        repaintAxes();
		repaint();
    }

	/**
	 * 
	 * Перерисовывает сетку
	 */
	/*protected void repaintGridImage() {
        if (widthCell == 0 || heightCell == 0 || cellSize == 0) return;
        gridImage = new BufferedImage(
                cellSize * widthCell + 1, cellSize * heightCell + 1,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = gridImage.createGraphics();
        g.setColor(LINE_COLOR);
        for (int i = 0; i <= widthCell; i++) {
            g.drawLine(i * cellSize, 0, i * cellSize, cellSize * heightCell);
        }
        for (int i = 0; i <= heightCell; i++) {
            g.drawLine(0, i * cellSize, cellSize * widthCell, i * cellSize);
        }
    }*/

	/**
	 * 
	 * Перерисовывает оси координат
	 */
	protected void repaintAxes() {
        absciss.setCellSize(cellSize);
        ordinate.setCellSize(cellSize);
        absciss.setWidth(widthCell);
        ordinate.setHeight(heightCell);
        absciss.repaint();
        ordinate.repaint();
		corner.repaint();
    }

	/**
	 * 
	 * Удаляет все фигуры с канвы.
	 */
    public void clear() {
        currentShape = null;
		currentGraphicsObjectControl = null;
		mainWindow.setGraphicsObjectPanel(null);
		shapes.clear();
        repaintCanva();
    }	
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (cellSize > 1 && showGrid) {
			//g.drawImage(gridImage, 0, 0, this);
			
			if (widthCell == 0 || heightCell == 0 || cellSize == 0) return;

			Graphics2D g2d = (Graphics2D)g;
			Color oldColor = g2d.getColor();
			g2d.setColor(LINE_COLOR);
			for (int i = 0; i <= widthCell; i++) {
				g2d.drawLine(i * cellSize, 0, i * cellSize, cellSize * heightCell);
			}
			for (int i = 0; i <= heightCell; i++) {
				g2d.drawLine(0, i * cellSize, cellSize * widthCell, i * cellSize);
			}
			g2d.setColor(oldColor);
			
		}

        CanvaGraphics gf2d = new CanvaGraphics(
                getWidth(), getHeight(), 
				widthCell, heightCell, cellSize, 
				(Graphics2D) g);
		
        for (IGraphicsObject shape : shapes) {
            shape.draw(gf2d);
        }
		
		if(currentGraphicsObjectControl != null) {
			currentGraphicsObjectControl.draw((Graphics2D)g, cellSize);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
