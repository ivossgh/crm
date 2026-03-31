package com.example.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {
        public void salvarContato(Usuario usuario)throws SQLException{
        String sql = "INSERT INTO clientes(nome, telefone, data, valor, local, email) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection cnn = Conexao.getConnection();
            PreparedStatement stmt = cnn.prepareStatement(sql)) {

                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getTelefone());
                stmt.setObject(3, usuario.getData());
                stmt.setDouble(4, usuario.getValor());
                stmt.setString(5, usuario.getLocal());
                stmt.setString(6, usuario.getEmail());

                stmt.executeUpdate();
                System.out.println("Cliente " + usuario.getNome() + " Salvo com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar cliente" + e.getMessage());
        }
    }
}
