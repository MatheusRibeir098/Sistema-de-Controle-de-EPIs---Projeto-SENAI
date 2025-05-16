package org.example.Menus;

import org.example.dao.EmprestimoDao;
import org.example.dao.UsuarioDao;
import org.example.model.Emprestimo;
import org.example.model.Usuario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuEmprestimo {

    private static final EmprestimoDao emprestimoDao = new EmprestimoDao();
    private static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n--- MENU EMPRÉSTIMO ---");
            System.out.println("1. Inserir Empréstimo");
            System.out.println("2. Listar Empréstimos");
            System.out.println("3. Editar Empréstimo");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1 -> inserir();
                case 2 -> listar();
                case 3 -> editar();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void inserir() {
        try {
            System.out.print("ID do Usuário: ");
            int idUsuario = Integer.parseInt(sc.nextLine());

            System.out.print("ID do EPI: ");
            int idEpi = Integer.parseInt(sc.nextLine());

            System.out.print("Data de Retirada (yyyy-MM-dd HH:mm:ss): ");
            LocalDateTime retirada = LocalDateTime.parse(sc.nextLine(), formatter);

            System.out.print("Data Prevista de Devolução (yyyy-MM-dd HH:mm:ss): ");
            LocalDateTime devolucao = LocalDateTime.parse(sc.nextLine(), formatter);

            System.out.print("Confirmar retirada (true/false): ");
            boolean confirmacao = Boolean.parseBoolean(sc.nextLine());

            Emprestimo emp = new Emprestimo(0, idUsuario, idEpi, retirada, devolucao, confirmacao);
            if (emprestimoDao.inserirEmprestimo(emp)) {
                System.out.println("Empréstimo inserido com sucesso.");
            } else {
                System.out.println("Erro ao inserir empréstimo.");
            }
        } catch (Exception e) {
            System.out.println("Erro de entrada: " + e.getMessage());
        }
    }

    private static void listar() {
        ArrayList<Emprestimo> lista = emprestimoDao.listarEmprestimos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum empréstimo encontrado.");
        } else {
            for (Emprestimo emp : lista) {
                UsuarioDao usuarioDao = new UsuarioDao();
                ArrayList<Usuario> usuarios = usuarioDao.listarUsuariosPorId(emp.getIdUsuario());
                System.out.println("\n============"+usuarios.get(0).getNome()+"========");
                System.out.println("ID do Emprestimo: "+emp.getIdEmprestimo());
                System.out.println("ID do EPI: "+emp.getIdEpi());
                System.out.println("ID do USUario: "+emp.getIdUsuario());
                System.out.println("Data de Retirada: "+emp.getDataRetirada());
                System.out.println("Data Prevista de Devolução: "+emp.getDataPrevistaDevolucao());
            }
        }
    }

    private static void editar() {
        try {
            System.out.print("ID do Empréstimo a editar: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Novo ID do Usuário: ");
            int idUsuario = Integer.parseInt(sc.nextLine());

            System.out.print("Novo ID do EPI: ");
            int idEpi = Integer.parseInt(sc.nextLine());

            System.out.print("Nova Data de Retirada (yyyy-MM-dd HH:mm:ss): ");
            LocalDateTime retirada = LocalDateTime.parse(sc.nextLine(), formatter);

            System.out.print("Nova Data Prevista de Devolução (yyyy-MM-dd HH:mm:ss): ");
            LocalDateTime devolucao = LocalDateTime.parse(sc.nextLine(), formatter);

            System.out.print("Confirmar retirada (true/false): ");
            boolean confirmacao = Boolean.parseBoolean(sc.nextLine());

            Emprestimo emp = new Emprestimo(id, idUsuario, idEpi, retirada, devolucao, confirmacao);
            if (emprestimoDao.atualizarEmprestimo(emp)) {
                System.out.println("Empréstimo atualizado com sucesso.");
            } else {
                System.out.println("Erro ao atualizar empréstimo.");
            }
        } catch (Exception e) {
            System.out.println("Erro de entrada: " + e.getMessage());
        }
    }


}
