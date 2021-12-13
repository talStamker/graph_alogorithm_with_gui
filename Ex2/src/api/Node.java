package api;


public class Node implements NodeData{
    private  double x,y,z;
    int id;
    String pos;
    String str="";
    int tag=0;
    double w=0.0;
    public Node(String posS,int id){
        this.id=id;
        pos=posS;
        String[] s=posS.split(",");
        x=Double.parseDouble(s[0]);
        y=Double.parseDouble(s[1]);
        z=Double.parseDouble(s[2]);

    }
    public Node (Node node){
        this.x=node.x;
        this.y=node.y;
        this.z=node.z;
        this.id=node.id;
        this.str=node.str;
        this.tag= node.tag;
        this.w=node.w;
    }
    public boolean ugual(Node n){
        return (this.x==n.x&&this.y==n.y&&this.z==n.z&&this.id==n.id&&this.tag==n.tag&&this.w==n.w);
    }

    @Override
    public int getKey() {
        return id;
    }

    @Override
    public GeoLocation getLocation() {
        GeoLocation g=new Geo(x,y,z);
        return g;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.x=p.x();
        this.y=p.y();
        this.z=p.z();
    }

    @Override
    public double getWeight() {
        return w;
    }

    @Override
    public void setWeight(double w) {
        this.w=w;
    }

    @Override
    public String getInfo() {
        return str ;
    }

    @Override
    public void setInfo(String s) {
        str=s;
    }

    @Override
    public int getTag() {
        return tag;
    }

    @Override
    public void setTag(int t) {
        tag=t;
    }
}
