package br.ufg.inf.fabrica.mural.central.persistencia;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Julliano on 12/11/2014.
 */
public interface PublicacaoDAO {

    public Collection consultarPublicacoes(String termo, Date dataInicio, Date dataFim);
    public Collection obterPublicacoes(String estado);
    public void mudaEstadoPublicacao(String estado, Collection listaPublicacao);

}
