package is;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import is.command.HistoryCommandHandlerInvoker;
import is.shapes.concreteCommand.DeleteConcreteCommand;
import is.shapes.controller.GraphicObjectController;
import is.shapes.model.*;
import is.shapes.view.*;

public class TestGraphics2 {

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

		final GraphicObjectPanelReceiver gPanelReceiver = new GraphicObjectPanelReceiver();

		gPanelReceiver.setPreferredSize(new Dimension(400, 400));

		GraphicObjectViewFactory.FACTORY.installView(RectangleObject.class, new RectangleObjectView());
		GraphicObjectViewFactory.FACTORY.installView(CircleObject.class, new CircleObjectView());
		GraphicObjectViewFactory.FACTORY.installView(ImageObject.class, new ImageObjectView());


		AbstractGraphicObject go = new RectangleObject(new Point(180, 80), 20, 50);

		JButton deleteButton = new JButton(new DeleteObjectAction(go,gPanelReceiver,handler));
		deleteButton.setText("Delete");
		toolbar.add(deleteButton);

		JButton rectButton = new JButton(new CreateObjectAction(go, gPanelReceiver, handler));
		rectButton.setText(go.getType());
		toolbar.add(rectButton);

		go = new CircleObject(new Point(200, 100), 10);
		JButton circButton = new JButton(new CreateObjectAction(go, gPanelReceiver, handler));
		circButton.setText(go.getType());
		toolbar.add(circButton);

		go = new CircleObject(new Point(200, 100), 100);
		JButton circButton2 = new JButton(new CreateObjectAction(go, gPanelReceiver, handler));
		circButton2.setText("big " + go.getType());
		toolbar.add(circButton2);

		go = new ImageObject(new ImageIcon(TestGraphics2.class.getResource("shapes/model/NyaNya.gif")),
				new Point(240, 187));

		JButton imgButton = new JButton(new CreateObjectAction(go, gPanelReceiver, handler));
		imgButton.setText(go.getType());
		toolbar.add(imgButton);

		final GraphicObjectController goc = new GraphicObjectController(handler);


		gPanelReceiver.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				goc.setControlledObject(gPanelReceiver.getGraphicObjectAt(e.getPoint()));
				System.out.println(("0: " + goc.getSubject()));
				deleteButton.setAction(new DeleteObjectAction(goc.getSubject(),gPanelReceiver,handler));
				deleteButton.setText("Delete");

			}
		});


		deleteButton.addActionListener(evt -> gPanelReceiver.remove(goc.getSubject()));


		f.add(toolbar, BorderLayout.NORTH);
		f.add(new JScrollPane(gPanelReceiver), BorderLayout.CENTER);

		JPanel controlPanel = new JPanel(new FlowLayout());

		controlPanel.add(goc);
		f.setTitle("Shapes");
		f.getContentPane().add(controlPanel, BorderLayout.SOUTH);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);

	}
}