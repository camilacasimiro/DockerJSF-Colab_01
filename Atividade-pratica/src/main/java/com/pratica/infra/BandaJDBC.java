package com.pratica.infra;

import com.pratica.domain.Banda;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class BandaJDBC {

    private static Connection connection;

    public BandaJDBC() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://host-banco:5432/pratica01",
                    "jheycf","333"
            );
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BandaJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

  public List<Banda> listBanda(){
    try{
        List<Banda> bandas= new ArrayList<>();
        ResultSet bandaResult = connection.prepareStatement("SELECT * FROM banda").executeQuery();

        while ( bandaResult.next() ){
            bandas.add((Banda) bandaResult);
        }

        return bandas;

    } catch(SQLException ex){
        Logger.getLogger(BandaJDBC.class.getName()).log(Level.SEVERE, null, ex);
        return Collections.EMPTY_LIST;
    }
  }

}
