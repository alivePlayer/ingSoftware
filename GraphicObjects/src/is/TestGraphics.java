package is;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import is.command.HistoryCommandHandlerInvoker;
import is.shapes.controller.GraphicObjectController;
import is.shapes.model.CircleObject;
import is.shapes.model.GraphicObject;
import is.shapes.model.ImageObject;
import is.shapes.model.RectangleObject;
import is.shapes.view.*;

public class TestGraphics {

	public static void main(String[] args) {

		JFrame f = new JFrame();

		JToolBar toolbar = new JToolBar();
		JButton undoButt = new JButton("Undo");
		JButton redoButt = new JButton("Redo");

		final HistoryCommandHandlerInvoker handler = new HistoryCommandHandlerInvoker();

		undoButt.addActionListener(evt -> handler.undo());

		redoButt.addActionListener(evt -> handler.redo());
		toolbar.add(undoButt);
		toolbar.add(redoButt);

		GraphicObjectLogger logger = new GraphicObjectLogger();

		GraphicObjectPanelReceiver gpanel = new GraphicObjectPanelReceiver();

		gpanel.setPreferredSize(new Dimension(400, 400));
		GraphicObjectViewFactory.FACTORY.installView(RectangleObject.class, new RectangleObjectView());
		GraphicObjectViewFactory.FACTORY.installView(CircleObject.class, new CircleObjectView());
		GraphicObjectViewFactory.FACTORY.installView(ImageObject.class, new ImageObjectView());


		GraphicObject go = new RectangleObject(new Point(80, 80), 20, 50);
		gpanel.add(go);
		go.addGraphicObjectListener(logger);
		GraphicObjectController goc1 = new GraphicObjectController(go, handler);

		go = new CircleObject(new Point(100, 100), 10);
		gpanel.add(go);
		go.addGraphicObjectListener(logger);
		GraphicObjectController goc2 = new GraphicObjectController(go, handler);

		go = new ImageObject(new ImageIcon(TestGraphics.class.getResource("shapes/model/NyaNya.gif")),
				new Point(40, 87));
		gpanel.add(go);
		go.addGraphicObjectListener(logger);
		GraphicObjectController goc3 = new GraphicObjectController(go, handler);

		f.add(toolbar, BorderLayout.NORTH);
		f.add(gpanel, BorderLayout.CENTER);

		JPanel controlPanel = new JPanel(new FlowLayout());
		controlPanel.add(goc1);
		controlPanel.add(goc2);
		controlPanel.add(goc3);



		f.getContentPane().add(new JScrollPane(controlPanel), BorderLayout.SOUTH);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);

	}
}