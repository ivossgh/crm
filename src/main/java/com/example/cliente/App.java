package com.example.cliente;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class App 
{
    static Scanner teclado =  new Scanner(System.in);
    public static void main( String[] args ){
        
        
        System.out.println(" - - Bem vindo ao CRM - - ");
        
        int escolha;
        do {
            menu();
            escolha = Integer.parseInt(teclado.nextLine());
            switch (escolha) {
                case 1:
                    salvarCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    buscarClienteID();
                    break;
                case 4:
                    atualizarCliente();
                    break;
                case 5:
                    deletarCliente();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("opcao invalida");
            }
            
        } while (escolha!=0);

    }

    private static void menu(){
        
        System.out.println("Escolha uma opcao");
        System.out.println("1 - Cadastrar Cliente ");
        System.out.println("2 - Lista de Clientes ");
        System.out.println("3 - Buscar Cliente por ID  ");
        System.out.println("4 - Atualizar Cliente ");
        System.out.println("5 - Deletar Cliente ");
        System.out.println("6 - Sair ");
    }

    private static void salvarCliente(){
        System.out.println(" - Adcionar Cliente - ");

        System.out.println("Nome do Cliente: ");
        String nomeT = teclado.nextLine();

        System.out.println("Telefone do Cliente: ");
        String telefoneT = teclado.nextLine();

        System.out.println("Valor de Compra: ");
        double valorT = teclado.nextDouble();
        teclado.nextLine(); 

        System.out.println("Local do Cliente: ");
        String localT = teclado.nextLine();

        System.out.println("Email do Cliente: ");
        String emailT = teclado.nextLine();

        System.out.print("Data de Cadastro (dd/mm/aaaa): ");
        String dataT = teclado.nextLine();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataConvertida = LocalDate.parse(dataT, formatador);

        Usuario usuario = new Usuario(nomeT, telefoneT, valorT, localT, emailT, dataConvertida);
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            usuarioDAO.salvarContato(usuario);
            System.out.println("Cliente: " + usuario.getNome() + " Salvo com Sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("erro ao salvar o clientes " + e.getMessage());
        }

    }

    private static void listarClientes(){
        System.out.println("Lista de Clientes");

        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            List<Usuario> usuarios = usuarioDAO.buscarClientes(); 

            if(usuarios.isEmpty()){
                System.out.println("Nenhum cliente encontrado");
            }else{
                for(Usuario user: usuarios){
                    System.out.println("- - - - ");

                    System.out.println("Nome: " + user.getNome());
                    System.out.println("Contato: " + user.getTelefone());
                    System.out.println("Valor gasto: " + user.getValor());
                    System.out.println("Localidade: " + user.getLocal());
                    System.out.println("Email: " + user.getEmail());
                    System.out.println("Data de Cadastro: " + user.getData());
                    
                    System.out.println("- - - - ");
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes " + e.getMessage());
        }
    }

    private static void buscarClienteID(){
        System.out.println(" - Selecione o cliente por ID - ");

        System.out.println("Digite o ID: ");
        int id = Integer.parseInt(teclado.nextLine());

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            Usuario user = usuarioDAO.buscarClienteID(id);

            if(user !=null){
                System.out.println("- - - - ");

                System.out.println("Nome do Cliente: " + user.getNome());
                System.out.println("Contato do Cliente: " + user.getTelefone());
                System.out.println("Valor gasto: " + user.getValor());
                System.out.println("Localidade: " + user.getLocal());
                System.out.println("Email do Cliente: " + user.getEmail());
                System.out.println("Data de cadastro: " + user.getData());

                System.out.println("- - - - ");
            }else{
                System.out.println("Nenhum registro encontrado ");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco" + e.getMessage());
        }
    }

    private static void atualizarCliente(){
        System.out.println("Digite o ID do Cliente a ser atualizado : ");
        int id = Integer.parseInt(teclado.nextLine());

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            Usuario userAtualizar = usuarioDAO.buscarClienteID(id);

            if(userAtualizar !=null){

                System.out.println("Novo Nome do Cliente: (Nome Atual) " + userAtualizar.getNome());
                String nomeT = teclado.nextLine();

                System.out.println("Novo telefone do Cliente: (Telefone Atual) " + userAtualizar.getTelefone());
                String telefoneT = teclado.nextLine();

                System.out.println("Valor de Compra: (Valor Atual)" + userAtualizar.getValor());
                double valorT = teclado.nextDouble();
                teclado.nextLine(); 

                System.out.println("Local do Cliente: (Local Atual)" + userAtualizar.getLocal());
                String localT = teclado.nextLine();

                System.out.println("Email do Cliente: (Email ATual)" + userAtualizar.getEmail());
                String emailT = teclado.nextLine();

                System.out.print("Data de Cadastro (dd/mm/aaaa): (Data Atual)" + userAtualizar.getData());
                String dataT = teclado.nextLine();
                DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataConvertida = LocalDate.parse(dataT, formatador);

                Usuario usuarioAtualizado = new Usuario(nomeT, telefoneT, valorT, localT, emailT, dataConvertida);

                try {
                    usuarioDAO.atualizarContato(usuarioAtualizado, id);
                    System.out.println("Cliente: " + usuarioAtualizado.getNome() + " Atualizado com Sucesso!");
                } catch (SQLException e) {
                    throw new RuntimeException("erro ao salvar o clientes " + e.getMessage());
                }

            }else{
                System.out.println("Nenhum registro encontrado ");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco" + e.getMessage());
        }


    }

    private static void deletarCliente(){
        System.out.println("Digite o ID do Cliente a ser Excluido : ");
        int id = Integer.parseInt(teclado.nextLine());

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            Usuario userAtualizar = usuarioDAO.buscarClienteID(id);

            if(userAtualizar !=null){

                try {
                    usuarioDAO.excluirContato(id);
                    System.out.println("Cliente: " + userAtualizar.getNome() + " Excluido com Sucesso!");
                } catch (SQLException e) {
                    throw new RuntimeException("erro ao excluir o clientes " + e.getMessage());
                }

            }else{
                System.out.println("Nenhum registro encontrado ");
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco" + e.getMessage());
        }
    }
}