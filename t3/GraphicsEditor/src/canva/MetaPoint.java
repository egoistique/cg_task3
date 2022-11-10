package canva;

public class MetaPoint {
    int x;
    int y;
    String pixel = "";
    Integer delta = null;
    Integer nextDelta = null;
    Integer sigma = null;

    public MetaPoint() {

    }

    public MetaPoint(int xx, int yy, String p, int d, int s) {
        x = xx;
        y = yy;
        pixel = p;
        delta = d;
        sigma = s;
    }

    public MetaPoint(int xx, int yy) {
        x = xx;
        y = yy;
    }

    public MetaPoint clone() {
        MetaPoint old = this;
        MetaPoint p;
        if( old.getSigma() != null){
            p = new MetaPoint(old.getX(), old.getY(), old.getPixel(), old.getDelta(), old.getSigma());
            p.setNextDelta(old.getNextDelta());


        }
        else{
             p = new MetaPoint(old.getX(), old.getY());
        }
        return p;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getPixel() {
        return pixel;
    }

    public void setPixel(String pixel) {
        this.pixel = pixel;
    }

    public Integer getDelta() {
        return delta;
    }

    public void setDelta(Integer delta) {
        this.delta = delta;
    }

    public Integer getSigma() {
        return sigma;
    }

    public void setSigma(Integer sigma) {
        this.sigma = sigma;
    }

    public Integer getNextDelta() {
        return nextDelta;
    }

    public void setNextDelta(Integer nextDelta) {
        this.nextDelta = nextDelta;
    }
}
