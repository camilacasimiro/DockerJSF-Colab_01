package com.pratica.infra;

import com.pratica.controller.BandaController;
import com.pratica.domain.Banda;
import com.pratica.domain.BandaInterface;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class BandaJDBC implements BandaInterface {

    private static Connection connection;

    private static final Logger logger = Logger.getLogger(BandaController.class.getName());
    public BandaJDBC() {

        logger.log(Level.INFO, "JDBC");
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5444/pratica01",
                    "jheycf","333"
            );
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BandaJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @Override
    public List<Banda> listaBandas() throws ClassNotFoundException, SQLException {
        try{
            List<Banda> bandas= new ArrayList<>();
//            statement = this.connection.createStatement();
            logger.log(Level.INFO, "Banda");
            ResultSet bandaResult = connection.prepareStatement("SELECT * FROM banda").executeQuery();

            logger.log(Level.INFO, "Lista banda",bandaResult);
//            next percore o ResultSet e reforna false quando estar na ultima posição
            while ( bandaResult.next() ){
                bandas.add(bandaGuia(bandaResult));
            }

            return bandas;

        } catch(SQLException ex){
            Logger.getLogger(BandaJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return Collections.EMPTY_LIST;
        }
    }

    public Banda bandaGuia (ResultSet result) throws SQLException{
        int id = result.getInt("id");
        String localDeOrigem = result.getString("LocalDeOrigem");
        String nomeFantasia = result.getString("NomeFantasia");
//        List<Integrante> integrantes = new ArrayList<>();

        return new Banda(id, localDeOrigem, nomeFantasia, null);
    }

    @Override
    public void adicionaBanda(Banda banda) {

    }

    @Override
    public void atualizaBanda(Banda banda) {

    }

    @Override
    public void removeBanda(Banda banda) {

    }

    @Override
    public List<Banda> buscaBanda(String localDeOrigem) {
       return null;
    }
}
