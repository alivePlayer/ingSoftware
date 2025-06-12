package is.shapes.model;

import is.interpreter.Context;

import java.util.List;
import java.util.StringTokenizer;

public class Composite implements GroupComponent{
    Gruppo gr;

    @Override
    public void create(Context context) {
        gr=new Gruppo(context.panel.GetGruppiOccupati(),context);
        while(context.st.hasMoreTokens()){
            String token=context.st.nextToken();
            System.out.println(token);

            if(token.charAt(0)=='0'){
                String idGr=token.substring(1,token.length()-1);
                Gruppo gruppoInterno= context.panel.getGruppo(Integer.parseInt(token));
                for(int i=0;i<gruppoInterno.getSize();i++) {
                    System.out.println(gruppoInterno.getContenuto(i));
                    gr.add(gruppoInterno.getContenuto(i));
                }
            }
            else {
                gr.add(context.panel.getObjGlobal(Integer.parseInt(token)));
            }
        }
        context.panel.add(gr);
    }

    @Override
    public void remove(Context context) {
        int id=Integer.parseInt(context.st.nextToken())-1;
        gr=context.panel.getGruppo(id);
        gr.remove();
        context.panel.remove(gr);
    }

}

