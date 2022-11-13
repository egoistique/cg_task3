
package shapes.shapes;

import shapes.abstractshapes.AbstractLine;

import java.awt.*;

public class WuLine extends AbstractLine {

	@Override
	protected void calc() {
		clear();
		
		if(!getBeginPoint().isCorrect()) {
			return;
		} else if(!getEndPoint().isCorrect()) {
			plot(getBeginPoint().get(0), getBeginPoint().get(1));
			return;
		}

		if(plotDirectLine(getBeginPoint().get(0), getBeginPoint().get(1), 
				getEndPoint().get(0), getEndPoint().get(1))) {
			return;
		}

		int dX = getEndPoint().get(0) - getBeginPoint().get(0);
		int dY = getEndPoint().get(1) - getBeginPoint().get(1);
		int dXabs = Math.abs(dX);
		int dYabs = Math.abs(dY);

		int err = 0;
		int errInc = 0;
		int errDec = 0;

		int t = 0;
		int tMax = 0;
		int abstractXInc = 0;
		int abstractYInc = 0;
		int abstractX = 0;
		int abstractY = 0;
	

		boolean xLarger = false;

		if (dXabs > dYabs) {

			errInc = dYabs;
			errDec = dXabs;
			err = -dXabs + errInc;
			tMax = dXabs;
			xLarger = true;
			abstractX = getBeginPoint().get(0);
			abstractY = getBeginPoint().get(1);
			abstractXInc = getSign(dX);
			abstractYInc = getSign(dY);
		} else {
			errInc = dXabs;
			errDec = dYabs;
			err = -dYabs + errInc;
			tMax = dYabs;
			xLarger = false;
			abstractX = getBeginPoint().get(1);
			abstractY = getBeginPoint().get(0);
			abstractXInc = getSign(dY);
			abstractYInc = getSign(dX);
		}

		int i = Math.abs(err) * 100 / Math.abs(errDec);

		plot(abstractX, abstractY, Color.PINK);


		while (t < tMax) {
			if (err >= 0) {
				abstractY += abstractYInc;
				err -= errDec;
			} else {
				abstractX += abstractXInc;
				t += 1;

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
