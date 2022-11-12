
package shapes;

import canva.CanvaGraphics;

/**

 * 
 * Интерфейс абстрактной фигуры. Каждая фигура должна реализовывать данный 
 * интерфейс. Через него канва общается с фигурой.
 */
public interface IGraphicsObject {
	
    /**
     * Обработчик счелчка мыши по координатам (x,y).
	 * 
	 * @returns true, если щелчек был успешно обработан. false, если
	 * щелчек никак не обработан (isComplete() должен вернуть true)
	 *
     */
	boolean processMousePress(int x, int y);
	
    /**
    * Данный метод вызывается из метода paintComponent() класса Canva.
    * Данный метод реализует алгоритм рисования фигуры. Рисовать на канве
    * можно через класс CanvaGraphics.
    */
    void draw(CanvaGraphics g);
	

}
