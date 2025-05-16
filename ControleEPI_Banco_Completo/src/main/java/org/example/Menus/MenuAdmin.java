package org.example.Menus;

import java.util.Scanner;

public class MenuAdmin {
    public static void exibirMenu() {
        Scanner sc = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println("\n=== MENU Admin ===");
            System.out.println("1 - Gerenciar EPIs");
            System.out.println("2 - Gerenciar Empréstimos");
            System.out.println("3 - Gerenciar Usuários");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    MenuEPI.exibirMenu();
                    break;
                case 2:
                    MenuEmprestimo.exibirMenu();
                    break;
                case 3:
                    MenuUsuario.exibirMenu(); // Se tiver
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
