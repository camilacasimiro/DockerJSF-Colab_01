package com.pratica.infra;


import com.pratica.domain.CPF;
import com.pratica.domain.Integrante;
import com.pratica.domain.IntegranteInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IntegranteJDBC implements IntegranteInterface {

    private static Connection connection;

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
                System.out.println(integrantes);
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
        Date dataDeNascimento = result.getDate("dataDeNascimento");
        String cpf = result.getString("cpf");

        return new Integrante(id, nome, null, cpf);
    }

    @Override
    public void adicionaIntegrante(Integrante integrante) {
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO integrante (nome, dataDeNascimento, cpf) VALUES (?, ?, ?)");

            statement.setString(1, integrante.getNome());
//            statement.setDate(2, integrante.getDataDeNascimento());
            statement.setString(3, integrante.getCpf());
            statement.executeQuery();

        } catch (SQLException e){
            Logger.getLogger(IntegranteJDBC.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void atualizaIntegrante(Integrante integrante) {
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE integrante SET nome=?, dataDeNascimento=? WHERE id=?");
            statement.setString(1, integrante.getNome());
            statement.setString(2, String.valueOf(integrante.getDataDeNascimento()));
            statement.setInt(3, integrante.getId());
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

}
