/*
 * ====================================================================
 * Licença da Fábrica de Software (UFG)
 *
 * Copyright (c) 2014 Fábrica de Software
 * Instituto de Informática (Universidade Federal de Goiás)
 * Todos os direitos reservados.
 *
 * Redistribuição e uso, seja dos fontes ou do formato binário
 * correspondente, com ou sem modificação, são permitidos desde que
 * as seguintes condições sejam atendidas:
 *
 * 1. Redistribuição do código fonte deve conter esta nota, em sua
 *    totalidade, ou seja, a nota de copyright acima, as condições
 *    e a observação sobre garantia abaixo.
 *
 * 2. Redistribuição no formato binário deve reproduzir o conteúdo
 *    desta nota, em sua totalidade, ou seja, o copyright acima,
 *    esta lista de condições e a observação abaixo na documentação
 *    e/ou materiais fornecidos com a distribuição.
 *
 * 3. A documentação fornecida com a redistribuição,
 *    qualquer que seja esta, deve incluir o seguinte
 *    texto, entre aspas:
 *       "Este produto inclui software desenvolvido pela Fábrica
 *       de Software do Instituto de Informática (UFG)."
 *
 * 4. Os nomes Fábrica de Software, Instituto de Informática e UFG
 *    não podem ser empregados para endoçar ou promover produtos
 *    derivados do presente software sem a explícita permissão
 *    por escrito.
 *
 * 5. Produtos derivados deste software não podem ser chamados
 *    "Fábrica de Software", "Instituto de Informática", "UFG",
 *    "Universidade Federal de Goiás" ou contê-los em seus nomes,
 *    sem permissão por escrito.
 *
 * ESTE SOFTWARE É FORNECIDO "COMO ESTÁ". NENHUMA GARANTIA É FORNECIDA,
 * EXPLÍCITA OU NÃO. NÃO SE AFIRMA QUE O PRESENTE SOFTWARE
 * É ADEQUADO PARA QUALQUER QUE SEJA O USO. DE FATO, CABE AO
 * INTERESSADO E/OU USUÁRIO DO PRESENTE SOFTWARE, IMEDIATO OU NÃO,
 * ESTA AVALIAÇÃO E A CONSEQUÊNCIA QUE O USO DELE OCASIONAR. QUALQUER
 * DANO QUE DESTE SOFTWARE DERIVAR DEVE SER ATRIBUÍDO AO INTERESSADO
 * E/OU USUÁRIO DESTE SOFTWARE.
 * ====================================================================
 *
 * Este software é resultado do trabalho de voluntários, estudantes e
 * professores, ao realizar atividades no âmbito da Fábrica de Software
 * do Instituto de Informática (UFG). Consulte <http://fs.inf.ufg.br>
 * para detalhes.
 */

package br.ufg.inf.fabrica.mural.central.persistencia;

import br.ufg.inf.fabrica.mural.central.dominio.Bloqueio;
import br.ufg.inf.fabrica.mural.central.dominio.GrupoDestinatario;
import br.ufg.inf.fabrica.mural.central.dominio.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.text.html.parser.Entity;
import java.util.Collection;
import java.util.Date;

public class UsuarioDAO {

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = null;
        EntityManager entityManager = null;
        factory = Persistence.createEntityManagerFactory("br.ufg.inf.fabrica.muralufg_central_jar_1.0-SNAPSHOTPU");
        entityManager = factory.createEntityManager();
        return entityManager;
    }


    public boolean bloquearUsuario(Usuario usuario, String operacao) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Bloqueio bloqueio = new Bloqueio();
            bloqueio.setMotivo(operacao);
            bloqueio.setDataBloqueio(new Date());
            usuario.setBloqueio(bloqueio);
            em.merge(usuario);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            return false;
        }
        return true;
    }

    public boolean desbloquearUsuario(Usuario usuario) {
        EntityManager em = getEntityManager();
        Usuario usuarioParaSerDesbloqueado = em.find(Usuario.class, usuario.getId());
        Bloqueio bloqueio = usuarioParaSerDesbloqueado.getBloqueio();

        em.getTransaction().begin();
        try {
            bloqueio.setDataDesbloqueio(new Date());
            usuarioParaSerDesbloqueado.setBloqueio(bloqueio);
            em.merge(usuarioParaSerDesbloqueado);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            return false;
        }
        return true;
    }

    public boolean validarUsuario(Usuario usuario) {
        EntityManager em = getEntityManager();
        Usuario usuarioRetornado = em.find(Usuario.class, usuario.getId());
        if (usuarioRetornado == null) {
            return false;
        }
        return true;
    }
}
