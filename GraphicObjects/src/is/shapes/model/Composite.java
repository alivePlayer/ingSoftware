package is.shapes.model;

import is.shapes.controller.interpreter.Context;
import is.shapes.view.ErrorWindow;

public class Composite implements GroupComponent{
    Gruppo gr;
    @Override
    public Gruppo create(Context context) {
        gr=new Gruppo(context.panel.GetGruppiOccupati(),context);
        while(context.st.hasMoreTokens()){
            String token=context.st.nextToken();
            System.out.println(token);
            if(token.charAt(0)=='0'){
                Gruppo gruppoInterno = context.panel.getGruppo(Integer.parseInt(token));
                try {
                    for (int i = 0; i < gruppoInterno.getSize(); i++) {
                        System.out.println(gruppoInterno.getContenuto(i));
                        gr.add(gruppoInterno.getContenuto(i));
                    }
                }catch(NullPointerException e){
                    new ErrorWindow().setVisible(true);
                    return null;
                }
            }
            else {
                gr.add(context.panel.getObjGlobal(Integer.parseInt(token)));
            }
        }
        return gr;
    }

    @Override
    public void remove(Context context) {
        int id=Integer.parseInt(context.st.nextToken());
        gr=context.panel.getGruppo(id);
        gr.remove();

    }


}

