package com.example.cliente;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        System.out.print("Data de Cadastro (dd/mm/aaaa): ");
        String dataT = teclado.nextLine();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataConvertida = LocalDate.parse(dataT, formatador);

        System.out.println("Valor de Compra: ");
        double valorT = teclado.nextDouble();
        teclado.nextLine(); 

        System.out.println("Local do Cliente: ");
        String localT = teclado.nextLine();

        System.out.println("Email do Cliente: ");
        String emailT = teclado.nextLine();

        Usuario usuario = new Usuario(nomeT, telefoneT, dataConvertida, valorT, localT, emailT);
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            usuarioDAO.salvarContato(usuario);
            System.out.println("Cliente: " + usuario.getNome() + " Salvo com Sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("erro ao salvar o clientes " + e.getMessage());
        }

    }

    private static void listarClientes(){

    }

    private static void buscarClienteID(){

    }

    private static void atualizarCliente(){

    }

    private static void deletarCliente(){

    }
}