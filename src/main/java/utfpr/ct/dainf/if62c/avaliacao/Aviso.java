package utfpr.ct.dainf.if62c.avaliacao;

import java.util.Date;
import java.util.TimerTask;

/**
 * IF62C Fundamentos de Programação 2
 * Avaliação parcial.
 * @author 
 */
public class Aviso extends TimerTask{
    
    protected final Compromisso compromisso;

    public Aviso(Compromisso compromisso) {
       this.compromisso = compromisso;
    }
    
    @Override
    public void run(){
        Date data2 = compromisso.getData();
        Date currentdata = new Date();
        long d2 = data2.getTime();
        long d1 = currentdata.getTime();
        System.out.println(compromisso.getDescricao()+" começa em "+(d2-d1)/1000+"s.");
    }
    
    public Compromisso getCompromisso(){
        return compromisso;
    }
        
}
