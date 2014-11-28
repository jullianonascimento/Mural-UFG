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

import br.ufg.inf.fabrica.mural.central.persistencia.UsuarioDAO;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import br.ufg.inf.fabrica.mural.central.dominio.Usuario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UsuarioDAOTest {
    private EntityManager em;
    @BeforeClass
    public static void setUpClass(){


    }
    @AfterClass
    public static void tearDownClass(){

    }
    @Before
    public void setUp(){
        em = getEntityManager();
    }
    @After
    public void tearDown(){

    }

    @Test
    public void testPersistenciaUsuarioDAO(){
        em.getTransaction().begin();
        Usuario usuarioPersistido = new Usuario();
        usuarioPersistido.setLogin("joao");
        usuarioPersistido.setAdministrador(true);
        usuarioPersistido.setApenasProdutor(false);
        usuarioPersistido.setSenha("123");
        em.persist(usuarioPersistido);
        em.getTransaction().commit();
        System.out.println("id:"+usuarioPersistido.getId());
        Usuario usuarioRetornado = em.find(Usuario.class,usuarioPersistido.getId());
        assertEquals(usuarioPersistido,usuarioRetornado);
    }

    @Test
    public void testValidarUsuario(){
        em.getTransaction().begin();
        Usuario usuarioPersistido = new Usuario();
        usuarioPersistido.setLogin("joao");
        usuarioPersistido.setAdministrador(true);
        usuarioPersistido.setApenasProdutor(false);
        usuarioPersistido.setSenha("123");
        em.persist(usuarioPersistido);
        em.getTransaction().commit();
        System.out.println("id:" + usuarioPersistido.getId());
        Usuario usuarioRetornado = em.find(Usuario.class, usuarioPersistido.getId());

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean usuarioValido = usuarioDAO.validarUsuario(usuarioPersistido);
        assertTrue(usuarioValido);
    }

    @Test
    public void testBloquearUsuario(){
        em.getTransaction().begin();
        Usuario usuarioPersistido = new Usuario();
        usuarioPersistido.setLogin("joao");
        usuarioPersistido.setAdministrador(true);
        usuarioPersistido.setApenasProdutor(false);
        usuarioPersistido.setSenha("123");
        em.persist(usuarioPersistido);
        em.getTransaction().commit();
        Usuario usuarioRetornado = em.find(Usuario.class, usuarioPersistido.getId());

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean usuarioBloqueado = usuarioDAO.bloquearUsuario(usuarioRetornado,"Bloqueio de teste");
        assertTrue(usuarioBloqueado);

    }
    @Test
    public void testDesbloquearUsuario(){
        em.getTransaction().begin();
        Usuario usuarioPersistido = new Usuario();
        usuarioPersistido.setLogin("joao");
        usuarioPersistido.setAdministrador(true);
        usuarioPersistido.setApenasProdutor(false);
        usuarioPersistido.setSenha("123");
        em.persist(usuarioPersistido);
        em.getTransaction().commit();
        Usuario usuarioRetornado = em.find(Usuario.class, usuarioPersistido.getId());

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean usuarioBloqueado = usuarioDAO.bloquearUsuario(usuarioRetornado,"Bloqueio de teste");
        assertTrue(usuarioBloqueado);

        boolean usuariodesBloqueado = usuarioDAO.desbloquearUsuario(usuarioRetornado);
        assertTrue(usuariodesBloqueado);
    }
    private static EntityManager getEntityManager(){
        EntityManagerFactory factory = null;
        EntityManager entityManager = null;
        try {
            factory = Persistence.createEntityManagerFactory("br.ufg.inf.fabrica.muralufg_central_jar_1.0-SNAPSHOTPU");
            entityManager = factory.createEntityManager();
        }finally {
            //factory.close();
        }
        return entityManager;
    }
}
