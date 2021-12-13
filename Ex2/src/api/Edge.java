package api;

public class Edge implements EdgeData{
    int src;
    int dest;
    double w;
    String info="";
    int tag=0;
    public Edge(int src,int dest,double w,String info,int tag){
        this.src=src;
        this.dest=dest;
        this.w=w;
        this.info=info;
        this.tag=tag;
    }
    public Edge(int src,int dest,double w){
        this.src=src;
        this.dest=dest;
        this.w=w;
    }
    public boolean equal(Edge e){
        return (this.src==e.src&&this.dest==e.dest&&this.w==e.w&&this.tag==e.tag);

    }
    @Override
    public int getSrc() {
        return src;
    }

    @Override
    public int getDest() {
        return dest;
    }

    @Override
    public double getWeight() {
        return w;
    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public void setInfo(String s) {
        info=s;
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
