package org.example.Menus;

import org.example.dao.DevolucaoDao;
import org.example.dao.EPIDao;
import org.example.dao.EmprestimoDao;
import org.example.model.Devolucao;
import org.example.model.Emprestimo;
import org.example.model.Usuario;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static org.example.Menus.MenuEPI.listarEPI;

public class MenuUsuarioRestrito {


    public static void exibirMenu(Usuario usuario) {
        Scanner sc = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("\n--- MENU DO USUÁRIO ---");
            System.out.println("1 - Ver EPIs disponíveis");
            System.out.println("2 - Solicitar empréstimo");
            System.out.println("3 - Confirmar devolução");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    listarEPI();
                    break;
                case 2:
                    solicitarEmprestimo(usuario.getId());
                    break;
                case 3:
                    confirmarDevolucao(usuario.getId());
                    break;
                case 0:
                    loop = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public static void solicitarEmprestimo(int idUsuario) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n--- Solicitar Empréstimo ---");
        listarEPI(); // Mostra EPIs disponíveis

        System.out.print("Digite o ID do EPI desejado: ");
        int idEpi = sc.nextInt();
        sc.nextLine(); // limpa o buffer

        System.out.print("Digite a data prevista para devolução (formato: yyyy-MM-dd HH:mm): ");
        String dataStr = sc.nextLine();

        try {
            LocalDateTime dataPrevista = LocalDateTime.parse(dataStr.replace(" ", "T"));
            LocalDateTime dataRetirada = LocalDateTime.now();

            Emprestimo emprestimo = new Emprestimo(idUsuario, idEpi, dataRetirada, dataPrevista);
            EPIDao emprestimoDao = new EPIDao(); // Ou crie um EmprestimoDao se já tiver
            EmprestimoDao.inserirEmprestimo(emprestimo); // Você já tem esse método
        } catch (Exception e) {
            System.out.println("Data inválida ou erro ao criar empréstimo.");
        }
    }

    public static void confirmarDevolucao(int idUsuario) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n--- Devolução de EPI ---");
        List<Emprestimo> emprestimos = EmprestimoDao.listarEmprestimosPorUsuario(idUsuario);

        if (emprestimos.isEmpty()) {
            System.out.println("Você não possui empréstimos pendentes.");
            return;
        }

        for (Emprestimo e : emprestimos) {
            System.out.printf("ID: %d | EPI: %d | Retirada: %s | Prevista: %s | Confirmado: %s\n",
                    e.getIdEmprestimo(), e.getIdEpi(), e.getDataRetirada(), e.getDataPrevistaDevolucao(), e.isConfirmacaoRetirada() ? "Sim" : "Não");
        }

        System.out.print("Digite o ID do empréstimo a devolver: ");
        int idEmprestimo = sc.nextInt();

        boolean sucesso = DevolucaoDao.registrarDevolucao(idEmprestimo, LocalDateTime.now()); // chama o método da instância


        if (sucesso) {
            System.out.println("Devolução registrada com sucesso.");
        } else {
            System.out.println("Erro ao registrar devolução.");
        }
    }


}
