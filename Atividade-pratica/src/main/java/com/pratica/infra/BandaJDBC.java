package com.pratica.infra;

import com.pratica.controller.BandaController;
import com.pratica.domain.Banda;
import com.pratica.domain.BandaInterface;
import com.pratica.domain.CPF;
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
            ResultSet resultQuery = connection.prepareStatement( "SELECT * FROM BANDA").executeQuery();
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
//
//        int idIntegrante = result.getInt("id_integrante");
//        String nomeIntegrate = result.getString("nome");
//        Date dataNascimento = result.getDate("datadenascimento");
//        String cpfIntegrante = result.getString(("cpf"));
//
//        List<Integrante> integrantes = new ArrayList<>();
//        integrantes.add( new Integrante(idIntegrante,nomeIntegrate, dataNascimento, cpfIntegrante));
        return new Banda(id, localDeOrigem, nomeFantasia, null);
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
        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE banda SET nomeFantasia=?,localDeOrigem=? WHERE id=?");
            statement.setString(1, banda.getNomeFantasia());
            statement.setString(2,banda.getLocalDeOrigem());
            statement.setInt(3, banda.getId());
            statement.executeQuery();
        } catch (SQLException e) {
            Logger.getLogger(BandaJDBC.class.getName()).log(Level.SEVERE, null, e);
        }
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
    public List<Banda> buscaBanda(String local) {
        try{
            List<Banda> bandas= new ArrayList<>();

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM banda WHERE localDeOrigem = ?");

            statement.setString(1, local);
            statement.executeQuery();

            ResultSet bandaResult = statement.getResultSet();

            while ( bandaResult.next() ){
                bandas.add(converterBanda(bandaResult));
            }
            return bandas;

        } catch(SQLException ex){
            Logger.getLogger(BandaJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return Collections.EMPTY_LIST;
        }
    }

    public Banda buscaBandaById(int id) {
        try{

            logger.log(Level.INFO, "Banda busca Entrando ");
            Banda banda = null;

            PreparedStatement statement = connection.prepareStatement(
                    "DISTINCT SELECT * FROM banda WHERE id= ?");

            statement.setInt(1, id);
            statement.executeQuery();

            ResultSet bandaResult = statement.getResultSet();

                banda = converterBanda(bandaResult);

                logger.log(Level.INFO, "Banda busca : " + banda);

            return banda;

        } catch(SQLException ex){
            Logger.getLogger(BandaJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }


    public List<Integrante> localizarIntegranteComId(int idBanda) {
        try{

            logger.log(Level.INFO, "Integrante busca Entrando ");
            List<Integrante> dependentes = new ArrayList<>();

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM integrante_banda ib INNER JOIN integrante i ON ib.id_integrante = i.id INNER JOIN banda b ON ib.id_banda = b.id WHERE b.id = ? ");

            statement.setLong(1, idBanda);
            statement.executeQuery();

            ResultSet dependentesResult = statement.getResultSet();

            while ( dependentesResult.next() ){
                dependentes.add(converterIntegrante(dependentesResult));
                System.out.println(dependentes);
            }
            return dependentes;

        } catch(SQLException ex){
            Logger.getLogger(BandaJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Integrante converterIntegrante (ResultSet result) throws SQLException{
        int id = result.getInt("id");
        String nome = result.getString("nome");
        String date = result.getString("dataDeNascimento");
        CPF cpf = new CPF(result.getString("cpf"));
        LocalDate dataDeNascimento = LocalDate.of(
                Integer.parseInt(date.substring(0, 4)),
                Integer.parseInt(date.substring(5, 7)),
                Integer.parseInt(date.substring(8, 10))
        );

        return new Integrante(id, nome, dataDeNascimento, cpf);
    }
}
