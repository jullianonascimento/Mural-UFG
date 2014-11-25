package br.ufg.inf.fabrica.mural.central.dominio;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-11-25T16:27:56")
@StaticMetamodel(Bloqueio.class)
public class Bloqueio_ { 

    public static volatile SingularAttribute<Bloqueio, String> motivo;
    public static volatile SingularAttribute<Bloqueio, Date> dataBloqueio;
    public static volatile SingularAttribute<Bloqueio, Long> id;
    public static volatile SingularAttribute<Bloqueio, Date> dataDesbloqueio;

}