
package graphicseditor.controls;

import canva.Canva;
import graphicseditor.MainWindow;
import shapes.IGraphicsObject;
import shapes.panels.GraphicsObjectPanelFuctory;
import shapes.shapes.*;
import utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс панели инструментов фигур для главного окна
 * 
 * @author Gomon Sergey
 */
public class ShapesToolBar extends JToolBar implements ActionListener {
	
	/**
	 * Название файлов с картинками для иконок
	 */
	private static final String CURSOR_PICTURE			= "cursor";
	private static final String BRESENHAM_LINE_PICTURE	= "bresenham_line";
	private static final String WU_LINE_PICTURE			= "wu_line";
	private static final String HERMIT_CURVE_PICTURE	= "hermit_curve";
	private static final String BEZIER_CURVE_PICTURE	= "bezier_curve";
	private static final String SPLINE_CURVE_PICTURE	= "spline_curve";
	private static final String POLYGON_PICTURE			= "polygon";
	private static final String ELLIPSE_PICTURE			= "ellipse";
	private static final String PARABOLA_PICTURE		= "parabola";
	private static final String CUBE_PICTURE		= "cube";
	private static final String TRIAG_PICTURE		= "triang";

	/**
	 * Всплывающе подсказки для кнопок
	 */
	private static final String CURSOR_TOOLTIP			= "Выделение";
	private static final String BRESENHAM_LINE_TOOLTIP	= "Алгоритм брезенхема";
	private static final String WU_LINE_TOOLTIP			= "Алгоритм Ву";
	private static final String HERMIT_CURVE_TOOLTIP	= "Метод Эрмита";
	private static final String BEZIER_CURVE_TOOLTIP	= "Форма Безье";
	private static final String SPLINE_CURVE_TOOLTIP	= "Сплайн";
	private static final String POLYGON_TOOLTIP			= "Полигон";
	private static final String ELLIPSE_TOOLTIP			= "Эллипс";
	private static final String PARABOLA_TOOLTIP		= "Парабола";
	private static final String CUBE_TOOLTIP			= "Куб";
	private static final String TRIAG_TOOLTIP			= "Триангуляция";

	/**
	 * Действия для строки состояния
	 */
	private static final String DRAW_ACTION_STATUS_BAR		= "Рисование";
	private static final String SELECT_ACTION_STATUS_BAR	= "Выделение";		
	
	/**
	 * Фигура для строки состояния
	 */
	private static final String BRESENHAM_LINE_STATUS_BAR	= "Линия (Брезенхем)";
	private static final String WU_LINE_STATUS_BAR			= "Линия (Брезенхем)";
	private static final String HERMIT_CURVE_STATUS_BAR		= "Кривая (Эрмит)";
	private static final String BEZIER_CURVE_STATUS_BAR		= "Кривая (Безье)";
	private static final String SPLINE_CURVE_STATUS_BAR		= "Кривая (Сплайн)";
	private static final String POLYGON_STATUS_BAR			= "Полигон";
	private static final String ELLIPSE_STATUS_BAR			= "Эллипс";
	private static final String PARABOLA_STATUS_BAR			= "Парабола";
	private static final String CUBE_STATUS_BAR				= "Куб";
	private static final String TRIAG_STATUS_BAR			= "Триангуляция";

	/**
	 * Название команды
	 */
	private static final String CURSOR_ACTION_COMMAND			= "cursor";
	private static final String BRESENHAM_LINE_ACTION_COMMAND	= "bresenham";
	private static final String WU_LINE_ACTION_COMMAND			= "wu";
	private static final String HERMIT_CURVE_ACTION_COMMAND		= "hermit";
	private static final String BEZIER_CURVE_ACTION_COMMAND		= "bezier";
	private static final String SPLINE_CURVE_ACTION_COMMAND		= "spline";
	private static final String POLYGON_ACTION_COMMAND			= "polygon";
	private static final String ELLIPSE_ACTION_COMMAND			= "ellipse";
	private static final String PARABOLA_ACTION_COMMAND			= "parabola";
	private static final String CUBE_ACTION_COMMAND				= "cube";
	private static final String TRIAG_ACTION_COMMAND			= "triangulate";

    private Canva canva;
    private StatusBar statusBar;
	private MainWindow mainWindow;
	private JToggleButton cursorButton;
	
	public ShapesToolBar(Canva canva, StatusBar statusBar, 
			MainWindow mainWindow) {
		
		super("Shapes", JToolBar.VERTICAL);
		setFloatable(false);
		
		this.canva = canva;
		this.statusBar = statusBar;
		this.mainWindow = mainWindow;
		
		cursorButton = createToggleButton(
				CURSOR_PICTURE, 
				CURSOR_TOOLTIP, 
				CURSOR_ACTION_COMMAND,
				true);
		JToggleButton bresenhamButton = createToggleButton(
				BRESENHAM_LINE_PICTURE, 
				BRESENHAM_LINE_TOOLTIP, 
				BRESENHAM_LINE_ACTION_COMMAND);
		JToggleButton wuButton = createToggleButton(
				WU_LINE_PICTURE, 
				WU_LINE_TOOLTIP, 
				WU_LINE_ACTION_COMMAND);		
		JToggleButton ellipseButton = createToggleButton(
				ELLIPSE_PICTURE, 
				ELLIPSE_TOOLTIP, 
				ELLIPSE_ACTION_COMMAND);
		JToggleButton parabolaButton = createToggleButton(
				PARABOLA_PICTURE, 
				PARABOLA_TOOLTIP, 
				PARABOLA_ACTION_COMMAND);		
		JToggleButton hermitCurveButton = createToggleButton(
				HERMIT_CURVE_PICTURE,
				HERMIT_CURVE_TOOLTIP,
				HERMIT_CURVE_ACTION_COMMAND);
		JToggleButton bezierCurveButton = createToggleButton(
				BEZIER_CURVE_PICTURE,
				BEZIER_CURVE_TOOLTIP,
				BEZIER_CURVE_ACTION_COMMAND);
		JToggleButton splineCurveButton = createToggleButton(
				SPLINE_CURVE_PICTURE,
				SPLINE_CURVE_TOOLTIP,
				SPLINE_CURVE_ACTION_COMMAND);
		JToggleButton polygonButton = createToggleButton(
				POLYGON_PICTURE,
				POLYGON_TOOLTIP,
				POLYGON_ACTION_COMMAND);			
		JToggleButton cubeButton = createToggleButton(
				CUBE_PICTURE,
				CUBE_TOOLTIP,
				CUBE_ACTION_COMMAND);		
		JToggleButton triagButton = createToggleButton(
				TRIAG_PICTURE,
				TRIAG_TOOLTIP,
				TRIAG_ACTION_COMMAND);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(cursorButton);
		buttonGroup.add(bresenhamButton);
		buttonGroup.add(wuButton);
		buttonGroup.add(ellipseButton);
		buttonGroup.add(parabolaButton);
		buttonGroup.add(hermitCurveButton);
		buttonGroup.add(bezierCurveButton);
		buttonGroup.add(splineCurveButton);
		buttonGroup.add(polygonButton);
		buttonGroup.add(cubeButton);
		buttonGroup.add(triagButton);

		add(cursorButton);
		addSeparator();
		add(bresenhamButton);
		add(wuButton);
		add(ellipseButton);
		add(parabolaButton);
		add(hermitCurveButton);
		add(bezierCurveButton);
		add(splineCurveButton);
		add(polygonButton);
		add(cubeButton);
		add(triagButton);

		statusBar.setAction(SELECT_ACTION_STATUS_BAR);
	}
	
	public void selectCursor() {
		canva.setCurrentShape(null);
		cursorButton.setSelected(true);
		statusBar.setAction(SELECT_ACTION_STATUS_BAR);
		statusBar.setShape();
	}
	
	public void selectCursorLeaveSelectedShape() {
		canva.setCurrentShape(null);
		cursorButton.setSelected(true);
		statusBar.setAction(SELECT_ACTION_STATUS_BAR);
	}	
	
	private JToggleButton createToggleButton(
			String pic, String tooltip, String actionCommand) {
		return createToggleButton(pic, tooltip, actionCommand, false);
	}
	
	private JToggleButton createToggleButton(
			String pic, String tooltip, String actionCommand, 
			boolean selected) {
		JToggleButton button = new JToggleButton();
		button.setIcon(Utils.getIcon(pic, 25, 25));
		button.setToolTipText(tooltip);
		button.setActionCommand(actionCommand);
		button.addActionListener(this);
		button.setSelected(selected);
		button.setFocusable(false);
		return button;
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		String actionCommand = e.getActionCommand();

		if(actionCommand.equals(CURSOR_ACTION_COMMAND)) {
			canva.setCurrentShape(null);
			mainWindow.setGraphicsObjectPanel(null);			
			statusBar.setAction(SELECT_ACTION_STATUS_BAR);
			statusBar.setShape();
		} else if(actionCommand.equals(BRESENHAM_LINE_ACTION_COMMAND)) {
			IGraphicsObject shape = new BresenhamLine();
			canva.setCurrentShape(shape);
			mainWindow.setGraphicsObjectPanel(GraphicsObjectPanelFuctory
					.createGraphicsObjectPanel(shape, canva));			
			statusBar.setAction(DRAW_ACTION_STATUS_BAR);
			statusBar.setShape(BRESENHAM_LINE_STATUS_BAR);
		} else if(actionCommand.equals(WU_LINE_ACTION_COMMAND)) {
			IGraphicsObject shape = new WuLine();
			canva.setCurrentShape(shape);
			mainWindow.setGraphicsObjectPanel(GraphicsObjectPanelFuctory
					.createGraphicsObjectPanel(shape, canva));			
			statusBar.setAction(DRAW_ACTION_STATUS_BAR);
			statusBar.setShape(WU_LINE_STATUS_BAR);		
		}  else if(actionCommand.equals(ELLIPSE_ACTION_COMMAND)) {
			IGraphicsObject shape = new Ellipse();
			canva.setCurrentShape(shape);
			mainWindow.setGraphicsObjectPanel(GraphicsObjectPanelFuctory
					.createGraphicsObjectPanel(shape, canva));
			statusBar.setAction(DRAW_ACTION_STATUS_BAR);
			statusBar.setShape(ELLIPSE_STATUS_BAR);			
		} else if(actionCommand.equals(PARABOLA_ACTION_COMMAND)) {
			IGraphicsObject shape = new Parabola();
			canva.setCurrentShape(shape);
			mainWindow.setGraphicsObjectPanel(GraphicsObjectPanelFuctory
					.createGraphicsObjectPanel(shape, canva));
			statusBar.setAction(DRAW_ACTION_STATUS_BAR);
			statusBar.setShape(PARABOLA_STATUS_BAR);	
		} else if(actionCommand.equals(HERMIT_CURVE_ACTION_COMMAND)) {
			IGraphicsObject shape = new HermitCurve();
			canva.setCurrentShape(shape);
			mainWindow.setGraphicsObjectPanel(GraphicsObjectPanelFuctory
					.createGraphicsObjectPanel(shape, canva));
			statusBar.setAction(DRAW_ACTION_STATUS_BAR);
			statusBar.setShape(HERMIT_CURVE_STATUS_BAR);
		} else if(actionCommand.equals(BEZIER_CURVE_ACTION_COMMAND)) {
			IGraphicsObject shape = new BezierCurve();
			canva.setCurrentShape(shape);
			mainWindow.setGraphicsObjectPanel(GraphicsObjectPanelFuctory
					.createGraphicsObjectPanel(shape, canva));
			statusBar.setAction(DRAW_ACTION_STATUS_BAR);
			statusBar.setShape(BEZIER_CURVE_STATUS_BAR);
		} else if(actionCommand.equals(SPLINE_CURVE_ACTION_COMMAND)) {
			IGraphicsObject shape = new SplineCurve();
			canva.setCurrentShape(shape);
			mainWindow.setGraphicsObjectPanel(GraphicsObjectPanelFuctory
					.createGraphicsObjectPanel(shape, canva));
			statusBar.setAction(DRAW_ACTION_STATUS_BAR);
			statusBar.setShape(SPLINE_CURVE_STATUS_BAR);
		} else if(actionCommand.equals(POLYGON_ACTION_COMMAND)) {
			IGraphicsObject shape = new Polygon();
			canva.setCurrentShape(shape);
			mainWindow.setGraphicsObjectPanel(GraphicsObjectPanelFuctory
					.createGraphicsObjectPanel(shape, canva));
			statusBar.setAction(DRAW_ACTION_STATUS_BAR);
			statusBar.setShape(POLYGON_STATUS_BAR);			
		} else if(actionCommand.equals(CUBE_ACTION_COMMAND)) {
			IGraphicsObject shape = new Cube();
			canva.setCurrentShape(shape);
			mainWindow.setGraphicsObjectPanel(GraphicsObjectPanelFuctory
					.createGraphicsObjectPanel(shape, canva));
			statusBar.setAction(DRAW_ACTION_STATUS_BAR);
			statusBar.setShape(CUBE_STATUS_BAR);			
		} else if(actionCommand.equals(TRIAG_ACTION_COMMAND)) {
			IGraphicsObject shape = new Triangulat();
			canva.setCurrentShape(shape);
			mainWindow.setGraphicsObjectPanel(null);
			statusBar.setAction(DRAW_ACTION_STATUS_BAR);
			statusBar.setShape(TRIAG_STATUS_BAR);
		}
	}
}
