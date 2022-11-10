
package shapes.shapes;

import shapes.abstractshapes.AbstractLine;

import java.awt.*;

/**
 *
 */
public class WuLine extends AbstractLine {

	@Override
	protected void calc() {
		
		//Очищаем старые точки
		clear();
		
		if(!getBeginPoint().isCorrect()) {
			//Если не установлено ни одной точки, то ничего не добавляем
			return;
		} else if(!getEndPoint().isCorrect()) {
			//Если установлена только начальная точка, то добавляем только её
			plot(getBeginPoint().get(0), getBeginPoint().get(1));
			return;
		}
		
		//Если линия вертикальная, горизонтальная или наклонена под 45 
		//градусов, то просто генерируем все точки прямой, не используя  
		//алгоритм Брезенхема.
		if(plotDirectLine(getBeginPoint().get(0), getBeginPoint().get(1), 
				getEndPoint().get(0), getEndPoint().get(1))) {
			return;
		}

		int dX = getEndPoint().get(0) - getBeginPoint().get(0);		//Проекция по ОХ
		int dY = getEndPoint().get(1) - getBeginPoint().get(1);		//Проекция по OY
		int dXabs = Math.abs(dX);	//Длина проекции по OX
		int dYabs = Math.abs(dY);	//Длина проекции по OY

		int err = 0;	    //Значение ошибки
		int errInc = 0;	    //Значение, на которое ошибка увеличивается
		int errDec = 0;	    //Значение, на которое ошибка уменьшается

		/*
		 * Виртуальные координаты. В зависимости от длины проекции, 
		 * abstractX принимает значение либо X (Если длина проекции по 
		 * X больше), либо Y (если длина проекции по Y больше). abstractY 
		 * принимает значение Y, если длина проекции по X больше, и принимает 
		 * значение X, если длина проекции по Y больше. Соответственно 
		 * вычисляются значения abstractXInc и abstractYInc в зависимости от 
		 * длины проекций.
		 * 
		 * Это сделано, чтобы обобщить алгоритм на все октанты.
		 */
		int t = 0;
		int tMax = 0;
		int abstractXInc = 0;
		int abstractYInc = 0;
		int abstractX = 0;
		int abstractY = 0;
	
		/*
		 * Переменная принимает значение true, если длина проекции по X больше,
		 * и значение false, если длина проекции по Y больше. Используется для
		 * вызова метода plot(), который в зависимости от значения этой 
		 * переменной рисует либо точку (x,y), либо точку (y,x).
		 */
		boolean xLarger = false;

		if (dXabs > dYabs) {
			//Длина проекции по X больше.

			errInc = dYabs;		//начение, на которое увеличивается ошибка
			errDec = dXabs;		//Значение, на которое уменьшается ошибка
			err = -dXabs + errInc;	//Первоначальное значение ошибки
			tMax = dXabs;			//Количество шагов, которое необходимо сделать вдоль более длинной проекции
			xLarger = true;			//Проекция по X длинее
			abstractX = getBeginPoint().get(0);		//Первоначальное значение виртуальной координаты X
			abstractY = getBeginPoint().get(1);		//Первоначальное значение виртуальной координаты Y
			abstractXInc = getSign(dX);	//Значение, на которое увеличивается abstractX
			abstractYInc = getSign(dY);	//Значение, на которое увеличивается значение abstractY
		} else {
			 //Длина проекции по Y больше.
			
			errInc = dXabs; //Значение, на которое увеличивается ошибка
			errDec = dYabs;	//Значение, на которое уменьшается ошибка
			err = -dYabs + errInc; //Первоначальное значение ошибки
			tMax = dYabs; //Количество шагов, которое необходимо сделать вдоль более длинной проекции
			xLarger = false;	//Проекция по Y длинее
			abstractX = getBeginPoint().get(1); // Первоначальное значение виртуальной координаты X
			abstractY = getBeginPoint().get(0); //Первоначальное значение виртуальной координаты Y
			abstractXInc = getSign(dY); //Значение, на которое увеличивается abstractX
			abstractYInc = getSign(dX); //Значение, на которое увеличивается значение abstractY
		}

		//Вычисление интенсивности первого пикселя
		int i = Math.abs(err) * 100 / Math.abs(errDec);
		//Построение точки в первоначальных координатах
		plot(abstractX, abstractY, xLarger);

		/*
		 *   Цикл, в котором происходит построение прямой. 
		 *   Если проекция по X больше, то tMax равно длине
		 *   проекции по X, если проекция по Y больше, то
		 *   tMax равно длине проекции по Y.
		 */
		while (t < tMax) {

			if (err >= 0) {
				/*
				*Ошибка стала больше 0, это значит, что
				*реальное расположение линии ближе к следующему
				*пикселю
				*/		
				abstractY += abstractYInc;
				err -= errDec;
			} else {
				/*
				*Ошибка меньше 0, это значит, что
				*реальное расположение линии ближе к текущему
				*пикселю
				*/			
				abstractX += abstractXInc;
				t += 1;

				//Вычисление интенсивности цвета пикселя
				i = Math.abs(err) * 100 / Math.abs(errDec);
				plot(abstractX, abstractY, xLarger, 
						new Color(255*(100-i)/100, 255*(100-i)/100,
								255*(100-i)/100));
				plot(abstractX, abstractY + abstractYInc,xLarger, 
						new Color(255*i/100, 255*i/100,
								255*i/100));
				err += errInc;
			}
		}		
	}
	
}
