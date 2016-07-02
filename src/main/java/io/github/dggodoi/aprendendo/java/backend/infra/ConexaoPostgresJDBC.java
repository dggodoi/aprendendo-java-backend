/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.dggodoi.aprendendo.java.backend.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aluno
 */
public class ConexaoPostgresJDBC implements ConexaoJDBC {

    private Connection connection = null;

    public ConexaoPostgresJDBC() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        Properties properties = new Properties();
        properties.put("user", "postgres");
        properties.put("password", "postgres");

        this.connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/help-me?ApplicationName=HelpMe", properties);
        this.connection.setAutoCommit(false);
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }

    @Override
    public void close() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConexaoPostgresJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void commit() throws SQLException {
        this.connection.commit();
        this.close();
    }

    @Override
    public void rollback() {
        if (this.connection != null) {
            try {
                this.connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(ConexaoPostgresJDBC.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                this.close();
            }
        }
    }

}
