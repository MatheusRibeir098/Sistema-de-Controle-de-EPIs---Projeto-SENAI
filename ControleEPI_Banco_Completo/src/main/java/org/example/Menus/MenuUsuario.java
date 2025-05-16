package org.example.Menus;

import org.example.dao.UsuarioDao;
import org.example.model.Usuario;

import java.util.Scanner;

public class MenuUsuario {

    public static void exibirMenu() {
        Scanner sc = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("\n--- MENU DE USUÁRIOS ---");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Listar usuários");
            System.out.println("3 - Editar usuário");
            System.out.println("4 - Excluir usuário");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    inserirUsuario();
                    break;
                case 2:
                    listarUsuarios();
                    break;
                case 3:
                    editarUsuario();
                    break;
                case 4:
                    excluirUsuario();
                    break;
                case 0:
                    loop = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public static void inserirUsuario() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        System.out.print("Perfil (ADMIN ou USUARIO): ");
        String perfil = sc.nextLine().toUpperCase();

        Usuario usuario = new Usuario(nome, email, senha, perfil);
        UsuarioDao dao = new UsuarioDao();
        if (dao.inserirUsuario(usuario)) {
            System.out.println("Usuário cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar usuário.");
        }
    }

    public static void listarUsuarios() {
        UsuarioDao dao = new UsuarioDao();
        for (Usuario u : dao.listarUsuarios()) {
            System.out.println("ID: " + u.getId() +
                    ", Nome: " + u.getNome() +
                    ", Email: " + u.getEmail() +
                    ", Perfil: " + u.getPerfil());
        }
    }

    public static void editarUsuario() {
        Scanner sc = new Scanner(System.in);
        System.out.print("ID do usuário que deseja editar: ");
        int id = sc.nextInt();
        sc.nextLine(); // limpar o buffer

        System.out.print("Novo nome: ");
        String nome = sc.nextLine();
        System.out.print("Novo email: ");
        String email = sc.nextLine();
        System.out.print("Nova senha: ");
        String senha = sc.nextLine();
        System.out.print("Novo perfil (ADMIN ou USUARIO): ");
        String perfil = sc.nextLine().toUpperCase();

        Usuario usuario = new Usuario(id, nome, email, senha, perfil);
        UsuarioDao dao = new UsuarioDao();
        if (dao.atualizarUsuario(usuario)) {
            System.out.println("Usuário atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar usuário.");
        }
    }

    public static void excluirUsuario() {
        Scanner sc = new Scanner(System.in);
        System.out.print("ID do usuário que deseja excluir ou '0' para desistir: ");
        int id = sc.nextInt();

        UsuarioDao dao = new UsuarioDao();
        if (id == 0) {
            System.out.println("\nSaindo...\n");
        } else if(dao.excluirUsuario(id)){
            System.out.println("Usuário excluído com sucesso!");

        }
    }

}
