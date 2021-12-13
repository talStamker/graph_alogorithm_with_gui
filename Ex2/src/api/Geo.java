package api;

public class Geo implements GeoLocation{
    double x,y,z;
    public Geo(double x1,double y1,double z1){
        x=x1;
        y=y1;
        z=z1;
    }
    @Override
    public double x() {
        return x;
    }

    @Override
    public double y() {
        return y;
    }

    @Override
    public double z() {
        return z;
    }

    @Override
    public double distance(GeoLocation g) {
        double temp=Math.pow(this.x-g.x(),2)+Math.pow(this.y-g.y(),2)+Math.pow(this.z-g.z(),2);
        return Math.sqrt(temp);
    }
}
