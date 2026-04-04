package com.example.cliente;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // 1. Método para Salvar (Insert)
    public void salvarContato(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO clientes(nome, telefone, valor, local, email, data) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection cnn = Conexao.getConnection();
             PreparedStatement stmt = cnn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getTelefone());
            stmt.setDouble(4, usuario.getValor());
            stmt.setString(5, usuario.getLocal());
            stmt.setString(6, usuario.getEmail());
            // Uso do setObject para LocalDate (moderno)
            stmt.setObject(3, usuario.getData());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar cliente: " + e.getMessage());
        }
    }

    // 2. Método para Buscar (Select)
    public List<Usuario> buscarClientes()throws SQLException{
        List<Usuario> usuarios = new ArrayList<>();

        String sql = "SELECT nome, telefone, valor, local, email, data FROM clientes";

        try (Connection cnn = Conexao.getConnection();
            PreparedStatement stmt = cnn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();) {

            while (rs.next()) {
                String nomeT = rs.getString("nome");
                String telefoneT = rs.getString("telefone");
                double valorT = rs.getDouble("valor");
                String localT = rs.getString("local");
                String emailT = rs.getString("email");
                Date datasql = rs.getDate("data");
                LocalDate dataConvertida = (datasql != null)? datasql.toLocalDate():null;

                Usuario usuario = new Usuario(nomeT, telefoneT, valorT, localT, emailT, dataConvertida);
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    public Usuario buscarClienteID(int id) throws SQLException{
        String sql = "SELECT nome, telefone, valor, local, email, data FROM clientes WHERE id = ?";

        try (Connection cnn = Conexao.getConnection();
            PreparedStatement stmt = cnn.prepareStatement(sql)) {
                stmt.setInt(1, id);

                try (
                    ResultSet rs = stmt.executeQuery();
                ){
                   if(rs.next()){
                        String nomeT = rs.getString("nome");
                        String telefoneT = rs.getString("telefone");
                        double valorT = rs.getDouble("valor");
                        String localT = rs.getString("local");
                        String emailT = rs.getString("email");
                        Date datasql = rs.getDate("data");
                        LocalDate dateConvertida = (datasql !=null)? datasql.toLocalDate(): null;

                        return new Usuario(nomeT, telefoneT, valorT, localT, emailT, dateConvertida);
                   } 
                } 
        } 
        return null;
    }

    public void atualizarContato(Usuario usuario, int id) throws SQLException {
        String sql = "UPDATE clientes SET nome = ?, telefone = ?, valor = ?, local = ?, email = ?, data = ?  WHERE id = ?";

        try (Connection cnn = Conexao.getConnection();
             PreparedStatement stmt = cnn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getTelefone());
            stmt.setDouble(3, usuario.getValor());
            stmt.setString(4, usuario.getLocal());
            stmt.setString(5, usuario.getEmail());
            // Uso do setObject para LocalDate (moderno)
            stmt.setObject(6, usuario.getData());
            stmt.setInt(7, id);

            stmt.executeUpdate();
        } 
    }

    public void excluirContato(int id) throws SQLException {
        String sql = "DELETE FROM clientes WHERE id = ?";

        try (Connection cnn = Conexao.getConnection();
             PreparedStatement stmt = cnn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
        } 
    }
}