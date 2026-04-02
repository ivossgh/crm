package com.example.cliente;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // 1. Método para Salvar (Insert)
    public void salvarContato(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO clientes(nome, telefone, data, valor, local, email) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection cnn = Conexao.getConnection();
             PreparedStatement stmt = cnn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getTelefone());
            // Uso do setObject para LocalDate (moderno)
            stmt.setObject(3, usuario.getData());
            stmt.setDouble(4, usuario.getValor());
            stmt.setString(5, usuario.getLocal());
            stmt.setString(6, usuario.getEmail());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar cliente: " + e.getMessage());
        }
    }

    // 2. Método para Buscar (Select)
    public List<Usuario> buscarClientes() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        // Removida a vírgula extra antes do FROM e adicionado o campo 'local'
        String sql = "SELECT nome, telefone, data, valor, local, email FROM clientes";

        try (Connection cnn = Conexao.getConnection();
             PreparedStatement stmt = cnn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                String nomeT = rs.getString("nome");
                String telefoneT = rs.getString("telefone");

                // Conversão de java.sql.Date para LocalDate
                Date datasql = rs.getDate("data");
                LocalDate dataConvertida = (datasql != null) ? datasql.toLocalDate() : null;

                double valorT = rs.getDouble("valor");
                String localT = rs.getString("local");
                String emailT = rs.getString("email");

                // Instanciação usando o seu construtor
                Usuario usuarioCriado = new Usuario(nomeT, telefoneT, dataConvertida, valorT, localT, emailT);
                usuarios.add(usuarioCriado);
            }
        }
        return usuarios;
    }
}