package is.shapes.view;

import is.TestGraphics2;
import is.command.HistoryCommandHandlerInvoker;
import is.shapes.controller.GraphicObjectController;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.CircleObject;
import is.shapes.model.ImageObject;
import is.shapes.model.RectangleObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIController {
    public GUIController(){
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

        final CommandLine cl= new CommandLine(handler,gPanelReceiver);

        GraphicObjectViewFactory.FACTORY.installView(RectangleObject.class, new RectangleObjectView());
        GraphicObjectViewFactory.FACTORY.installView(CircleObject.class, new CircleObjectView());
        GraphicObjectViewFactory.FACTORY.installView(ImageObject.class, new ImageObjectView());


        AbstractGraphicObject go = new RectangleObject(new Point(180, 80), 20, 50);

        JButton deleteButton = new JButton(new DeleteObjectAction(go,gPanelReceiver,handler));
        deleteButton.setText("Delete");
        toolbar.add(deleteButton);
//RECTANGLE CREATE

        JButton rectButton = new JButton(new CreateObjectAction(go,gPanelReceiver,handler));
        rectButton.setText(go.getType());
        toolbar.add(rectButton);
        rectButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractGraphicObject ago = new RectangleObject(new Point(180, 80), 20, 50);
                rectButton.setAction(new CreateObjectAction(ago,gPanelReceiver,handler));
                rectButton.setText(ago.getType());

            }
        });
//CIRCLE CREATE

        go = new CircleObject(new Point(200, 100), 10);
        JButton circButton = new JButton(new CreateObjectAction(go, gPanelReceiver, handler));
        circButton.setText(go.getType());
        toolbar.add(circButton);
        circButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractGraphicObject ago = new CircleObject(new Point(200, 100), 10);
                circButton.setAction(new CreateObjectAction(ago,gPanelReceiver,handler));
                circButton.setText(ago.getType());

            }
        });

//IMG CREATE
        go = new ImageObject(new ImageIcon(TestGraphics2.class.getResource("shapes/model/NyaNya.gif")),
                new Point(240, 187));


        JButton imgButton = new JButton(new CreateObjectAction(go, gPanelReceiver, handler));
        imgButton.setText(go.getType());
        toolbar.add(imgButton);
        imgButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractGraphicObject ago= new ImageObject(new ImageIcon(TestGraphics2.class.getResource("shapes/model/NyaNya.gif")),
                        new Point(240, 187));
                imgButton.setAction(new CreateObjectAction(ago,gPanelReceiver,handler));
                imgButton.setText(ago.getType());

            }
        });


        final GraphicObjectController goc = new GraphicObjectController(handler);



        gPanelReceiver.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                goc.setControlledObject(gPanelReceiver.getGraphicObjectAt(e.getPoint()));
                System.out.println((goc.getSubject().getID()+ " " + goc.getSubject()));
                deleteButton.setAction(new DeleteObjectAction(goc.getSubject(),gPanelReceiver,handler));
                deleteButton.setText("Delete");
                new clickedWindow(goc.getSubject()).setVisible(true);

            }
        });


        deleteButton.addActionListener(evt -> gPanelReceiver.remove(goc.getSubject()));


        f.add(toolbar, BorderLayout.NORTH);
        f.add(new JScrollPane(gPanelReceiver), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new FlowLayout());

        controlPanel.add(goc);
        controlPanel.add(cl);
        f.setTitle("Shapes");
        f.getContentPane().add(controlPanel, BorderLayout.SOUTH);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }
}
