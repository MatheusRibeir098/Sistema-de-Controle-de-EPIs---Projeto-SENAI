package org.example;

import org.example.Menus.MenuAdmin;
import org.example.Menus.MenuUsuarioRestrito;
import org.example.ServiceDeLogin.LoginService;
import org.example.model.Usuario;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("=====Login=====");
        System.out.println("Caso ainda não tenha login, Peça a Sua empresa");
            Scanner sc = new Scanner(System.in);
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Senha: ");
            String senha = sc.nextLine();

            Usuario usuarioLogado = LoginService.autenticar(email, senha);

            if (usuarioLogado != null) {
                System.out.println("Login bem-sucedido! Bem-vindo, " + usuarioLogado.getNome());
                if (usuarioLogado.getPerfil().equalsIgnoreCase("ADMIN")) {
                    MenuAdmin.exibirMenu();
                } else {
                    MenuUsuarioRestrito.exibirMenu(usuarioLogado);
                }
            } else {
                System.out.println("Email ou senha inválidos.");
            }


    }
}
