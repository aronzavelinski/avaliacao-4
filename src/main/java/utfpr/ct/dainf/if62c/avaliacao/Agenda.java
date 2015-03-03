package utfpr.ct.dainf.if62c.avaliacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

/**
 * IF62C Fundamentos de Programação 2
 * Avaliação parcial.
 * @author 
 */
public class Agenda {
    private final String descricao;
    private final List<Compromisso> compromissos = new ArrayList<>();
    private final Timer timer;

    public Agenda(String descricao) {
        this.descricao = descricao;
        timer = new Timer(descricao);
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Compromisso> getCompromissos() {
        return compromissos;
    }
    
    public void novoCompromisso(Compromisso compromisso) {
        compromissos.add(compromisso);
        Aviso aviso = new AvisoFinal(compromisso);
        compromisso.registraAviso(aviso);
        // com a classe Aviso devidamente implementada, o erro de compilação
        // deverá desaparecer
        timer.schedule(aviso, compromisso.getData());
    }
    
    public void novoAviso(Compromisso compromisso, int antecedencia) {
        Date date = compromisso.getData();
        Aviso av = new Aviso(compromisso);
        AvisoFinal af = new AvisoFinal(compromisso);

        compromisso.registraAviso(av);
        Timer timer = new Timer(compromisso.getDescricao());
        timer.schedule(av, new Date(date.getTime() - antecedencia*1000));
        timer.schedule(af, date);
    }
    
    public void novoAviso(Compromisso compromisso, int antecedencia, int intervalo) {
        Date date = compromisso.getData();
        Aviso av = new Aviso(compromisso);
        AvisoFinal af = new AvisoFinal(compromisso);
    
        compromisso.registraAviso(av);
        Timer timer = new Timer(compromisso.getDescricao());
        timer.schedule(av, new Date(date.getTime() - antecedencia*1000), intervalo*1000);    
        timer.schedule(af, date);
    }
    
    public void cancela(Compromisso compromisso) {
        List<Aviso> av = compromisso.getAvisos();
        
        for(Aviso a: av){
            cancela(a);
        }
        compromissos.remove(compromisso);
        
    }
    
    public void cancela(Aviso aviso) {
        aviso.cancel();
        aviso.getCompromisso().removeAviso(aviso);
    }
    
    public void destroi() {
        for(Compromisso c: compromissos){
            cancela(c);
        }
    }
}
