package is.shapes.view;

import is.shapes.model.GraphicEvent;
import is.shapes.model.GraphicObject;
import is.shapes.model.GraphicObjectListener;
import is.shapes.model.Gruppo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.*;

import javax.swing.JComponent;

public class GraphicObjectPanelReceiver extends JComponent implements GraphicObjectListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8993548105090978185L;

	/**
	 * @directed true
	 */

	private final List<GraphicObject> objects = new LinkedList<>();
	private Map<Integer,GraphicObject> objGlobal = new HashMap<Integer,GraphicObject>();
	private int grpNum = 0;
	List <Gruppo> gruppi=new ArrayList<>();




	public GraphicObjectPanelReceiver() {
		setBackground(Color.WHITE);
	}


	public int getGrpNum() {
		return grpNum;
	}
	public void setGrpNum(int grpNum) {
		this.grpNum = grpNum;
		if(grpNum<0) this.grpNum=0;
	}
	public int GetGruppiOccupati(){
		return gruppi.size()+1;
	}
	public Map<Integer,GraphicObject> getObjects() {
		return objGlobal;
	}
	@Override
	public void graphicChanged(GraphicEvent e) {
		repaint();
		revalidate();

	}
	public Gruppo getGruppo(int id){
		try {
			return gruppi.get(id - 1);
		}catch(IndexOutOfBoundsException  | NullPointerException e){
			return null;
		}
	}
	public ArrayList<GraphicObject> getObjectsGlobal(String val) {
		ArrayList<GraphicObject> ret = new ArrayList<GraphicObject>();
		if(val.toLowerCase().equals("all")) {
			for (Integer i: objGlobal.keySet()) {
				ret.add(objGlobal.get(i));
			}
			for(Gruppo g: gruppi) {
				ret.add(g);
			}
			return ret;
		}

		else if(val.toLowerCase().equals("gruppi")) {
			for(Gruppo g: gruppi) {
				ret.add(g);
			}
			return ret;
		}

		for (int i=0;i<objects.size();i++) {
			if(objects.get(i).getType().toLowerCase().equals(val))
				ret.add(objects.get(i));
		}
		return ret;
	}


	public GraphicObject getObjGlobal(int id){
		return objGlobal.get(id);
	}

	public GraphicObject getGraphicObjectAt(Point2D p) {
		for (GraphicObject g : objects) {
			if (g.contains(p))
				return g;
		}
		return null;
	}

	@Override
	public Dimension getPreferredSize() {
		Dimension ps = super.getPreferredSize();
		double x = ps.getWidth();
		double y = ps.getHeight();
		for (GraphicObject go : objects) {
			double nx = go.getPosition().getX() + go.getDimension().getWidth() / 2;
			double ny = go.getPosition().getY() + go.getDimension().getHeight() / 2;
			if (nx > x)
				x = nx;
			if (ny > y)
				y = ny;
		}
		return new Dimension((int) x, (int) y);
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		for (GraphicObject go : objects) {
			GraphicObjectView view = GraphicObjectViewFactory.FACTORY.createView(go);
			view.drawGraphicObject(go, g2);
		}

	}
	public void add(Gruppo g){
		gruppi.add(g);
	}
	public void add(GraphicObject go) {
		objects.add(go);
		objGlobal.put(go.getID(),go);
		go.addGraphicObjectListener(this);
		repaint();
	}

	public void remove(Gruppo g){
		gruppi.remove(g);
	}
	public void remove(GraphicObject go) {
		if (objects.remove(go)) {
			objGlobal.remove(go.getID());
			go.removeGraphicObjectListener(this);
			repaint();
		}

	}

	
}
