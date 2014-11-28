package br.ufg.inf.fabrica.mural.central.dominio;

import br.ufg.inf.fabrica.mural.central.dominio.Bloqueio;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-28T19:18:36")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, Long> id;
    public static volatile SingularAttribute<Usuario, String> login;
    public static volatile SingularAttribute<Usuario, Boolean> apenasProdutor;
    public static volatile SingularAttribute<Usuario, String> senha;
    public static volatile SingularAttribute<Usuario, Boolean> administrador;
    public static volatile SingularAttribute<Usuario, Bloqueio> bloqueio;

}