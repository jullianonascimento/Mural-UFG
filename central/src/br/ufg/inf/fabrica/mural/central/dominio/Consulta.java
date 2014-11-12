package br.ufg.inf.fabrica.mural.central.dominio;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Julliano on 12/11/2014.
 */
public class Consulta {

    private Collection publicacoesVigentes;
    private String termo;
    private Date dataInicio;
    private Date dataFim;

    public Consulta(String termo, Date dataInicio, Date dataFim) {
        this.termo = termo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    private boolean validarSolicitacao(String termo, Date periodo){

        return false;
    }

    public Collection executaConsulta(){

        return null;
    }
}
