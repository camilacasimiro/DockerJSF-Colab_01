package com.pratica.infra;

import com.pratica.controller.BandaController;
import com.pratica.domain.Banda;
import com.pratica.domain.BandaInterface;
import com.pratica.domain.Integrante;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class BandaJDBC implements BandaInterface {

    private static Connection connection;

    private static final Logger logger = Logger.getLogger(BandaController.class.getName());
    public BandaJDBC() {

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
            ResultSet resultQuery = connection.prepareStatement( "SELECT * FROM INTEGRANTE_BANDA IB INNER JOIN BANDA B ON IB.ID_BANDA = B.ID INNER JOIN INTEGRANTE I ON IB.ID_INTEGRANTE = I.ID").executeQuery();
//            next percore o ResultSet e reforna false quando estar na ultima posição
            while ( resultQuery.next() ){
                bandas.add(converterBanda(resultQuery));
                System.out.println(bandas);
            }
            return bandas;

        } catch(SQLException ex){
            Logger.getLogger(BandaJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return Collections.EMPTY_LIST;
        }
    }

    public Banda converterBanda (ResultSet result) throws SQLException{
        int id = result.getInt("id");
        String localDeOrigem = result.getString("LocalDeOrigem");
        String nomeFantasia = result.getString("NomeFantasia");

        int idIntegrante = result.getInt("id_integrante");
        String nomeIntegrate = result.getString("nome");
        Date dataNascimento = result.getDate("datadenascimento");
        String cpfIntegrante = result.getString(("cpf"));

        List<Integrante> integrantes = new ArrayList<>();
        integrantes.add( new Integrante(idIntegrante,nomeIntegrate, dataNascimento, cpfIntegrante));
        return new Banda(id, localDeOrigem, nomeFantasia, integrantes);
    }

    @Override
    public void adicionaBanda(Banda banda) {
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Banda (localDeOrigem, nomeFantasia) VALUES (?, ?)");

            statement.setString(1, banda.getLocalDeOrigem());
            statement.setString(2, banda.getNomeFantasia());
            statement.executeQuery();

        } catch (SQLException e){
            Logger.getLogger(BandaJDBC.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void atualizaBanda(Banda banda) {

    }

    @Override
    public Boolean removeBanda(Banda banda) {
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM banda WHERE id=?");
            statement.setInt(1, banda.getId());
            statement.executeQuery();
        } catch (SQLException e){
            Logger.getLogger(BandaJDBC.class.getName()).log(Level.SEVERE, null, e);
        }
        return true;
    }

    @Override
    public List<Banda> buscaBanda(String localDeOrigem) {
       return null;
    }
}
