package is.shapes.model;

import is.shapes.controller.interpreter.Context;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Gruppo extends AbstractGraphicObject{//sarebbe quindi questa la mia foglia
    private int id;
    private List<GraphicObject> figli=new ArrayList<GraphicObject>();
    Context context;
    public Gruppo(int id,Context context) {
        this.id = id;
        this.context = context;
    }

    public GraphicObject getContenuto(int posizione){
        return figli.get(posizione);
    }
    public int getSize(){
        if(figli.size()<=0) return 0;
        return figli.size();
    }
    public void add(GraphicObject go){
        figli.add(go);
    }

    public void remove(){
        for(int i=0;i<figli.size()-1;i++){
            figli.remove(i);
        }
    }

    @Override
    public void moveTo(Point2D p) {

    }

    @Override
    public Point2D getPosition() {
        return null;
    }

    @Override
    public Dimension2D getDimension() {
        return null;
    }

    @Override
    public void scale(double factor) {
        for(GraphicObject fig : figli)
            fig.scale(factor);
    }

    @Override
    public boolean contains(Point2D p) {
        for(GraphicObject fig : figli) return fig.contains(p);
        return false;
    }

    @Override
    public String getType() {
        return "gruppo";
    }

    @Override
    public int getID() {
        return id;
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("\n gruppo con ID:"+id+" valori all'interno:\n ");
        for(GraphicObject go : figli)
            sb.append(go.toString());
        sb.append("gruppo con ID:"+id+" terminato \n");
        return sb.toString();
    }
}
