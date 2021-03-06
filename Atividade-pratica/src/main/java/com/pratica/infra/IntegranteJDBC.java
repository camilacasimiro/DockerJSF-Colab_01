package com.pratica.infra;


import com.pratica.controller.IntegranteController;
import com.pratica.domain.CPF;
import com.pratica.domain.Integrante;
import com.pratica.domain.IntegranteInterface;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IntegranteJDBC implements IntegranteInterface {

    private static Connection connection;

    private static final Logger logger = Logger.getLogger(IntegranteController.class.getName());

    public IntegranteJDBC() {

        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5444/pratica01",
                    "jheycf","333"
            );
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(IntegranteJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @Override
    public List<Integrante> listaIntegrantes() {
        try{
            List<Integrante> integrantes= new ArrayList<>();
            ResultSet resultQuery = connection.prepareStatement( "" +
                            "SELECT * FROM integrante")
                    .executeQuery();
//            next percore o ResultSet e reforna false quando estar na ultima posição
            while ( resultQuery.next() ){
                integrantes.add(converterIntegrante(resultQuery));
            }
            return integrantes;

        } catch(SQLException ex){
            Logger.getLogger(IntegranteJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return Collections.EMPTY_LIST;
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

    @Override
    public void adicionaIntegrante(Integrante integrante) {
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO integrante (nome, dataDeNascimento, cpf) VALUES (?, ?, ?)");

            statement.setString(1, integrante.getNome());
            statement.setDate(2, java.sql.Date.valueOf(integrante.getDataDeNascimento()));
            statement.setString(3, integrante.getCpf().valor());
            statement.executeQuery();

        } catch (SQLException e){
            Logger.getLogger(IntegranteJDBC.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void atualizaIntegrante(Integrante integrante) {
        logger.log(Level.INFO, "Lista integrante"+ integrante);
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE integrante SET nome=?, dataDeNascimento=?, cpf=? WHERE id=?");
            statement.setString(1, integrante.getNome());
            statement.setDate(2, java.sql.Date.valueOf(integrante.getDataDeNascimento()));
            statement.setString(3, integrante.getCpf().valor());
            statement.setInt(4, integrante.getId());
            statement.executeQuery();
        } catch (SQLException e) {
            Logger.getLogger(IntegranteJDBC.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public Boolean removeIntegrante(Integrante integrante) {
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM integrante WHERE id=?");
            statement.setInt(1, integrante.getId());
            statement.executeQuery();
        } catch (SQLException e){
            Logger.getLogger(IntegranteJDBC.class.getName()).log(Level.SEVERE, null, e);
        }
        return true;
    }

    @Override
    public List<Integrante> buscaIntegrante(String cpf) {
        try{
            List<Integrante> integrantes= new ArrayList<>();

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM integrante WHERE cpf = ?");

            statement.setString(1, cpf);
            statement.executeQuery();

            ResultSet integrantesResult = statement.getResultSet();

            while (  integrantesResult.next() ){
                integrantes.add(converterIntegrante(integrantesResult));
            }
            return integrantes;

        } catch(SQLException ex){
            Logger.getLogger(IntegranteJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return Collections.EMPTY_LIST;
        }
    }
    public Integrante buscaIntegranteById(int id) {
        try {

            logger.log(Level.INFO, "Integrante busca Entrando ");
            Integrante integrante = null;

            PreparedStatement statement = connection.prepareStatement(
                    "DISTINCT SELECT * FROM integrante WHERE id= ?");

            statement.setInt(1, id);
            statement.executeQuery();

            ResultSet integranteResult = statement.getResultSet();

            integrante = converterIntegrante(integranteResult);

            logger.log(Level.INFO, "Integrante busca : " + integrante);

            return integrante;

        } catch (SQLException ex) {
            Logger.getLogger(BandaJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
