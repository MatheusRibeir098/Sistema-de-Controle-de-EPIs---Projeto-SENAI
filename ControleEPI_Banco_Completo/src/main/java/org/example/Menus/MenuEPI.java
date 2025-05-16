package org.example.Menus;

import org.example.dao.EPIDao;
import org.example.dao.EmprestimoDao;
import org.example.model.EPI;

import java.util.Scanner;

public class MenuEPI {

    public static void exibirMenu(){
    EPIDao dao = new EPIDao();
    Scanner sc = new Scanner(System.in);
    boolean loop = true;

        while(loop)

    {
        System.out.println("+-------------------+");
        System.out.println("| 1 - Cadastrar EPI |");
        System.out.println("| 2 - Listar EPI    |");
        System.out.println("| 3 - Editar EPI    |");
        System.out.println("| 4 - Excluir EPI   |");
        System.out.println("| 5 - Sair          |");
        System.out.println("+-------------------+");
        int resp = sc.nextInt();

        switch (resp) {
            case 1:
                if (inserirEPI()) {
                    System.out.println("EPI Cadastrado com sucesso!");
                } else {
                    System.out.println("Erro ao cadastrar EPI!");
                }
                break;
            case 2:
                if (listarEPI()) {
                    System.out.println("EPI Listado com sucesso!");
                } else {
                    System.out.println("Erro ao listar EPI!");
                }
                break;
            case 3:
                if (editarEPI()) {
                    System.out.println("EPI Editado com sucesso!");
                } else {
                    System.out.println("Erro ao editar EPI!");
                }
                break;
            case 4:
                if (excluirEPI()) {
                    System.out.println("EPI Excluído com sucesso!");
                } else {
                    System.out.println("Erro ao excluir EPI!");
                }
                break;
            case 5:
                System.out.println("Saindo do programa...");
                loop = false;
                break;
            default:
                System.out.println("Opção inválida");
        }
    }
}

public static boolean inserirEPI() {
    EPIDao dao = new EPIDao();
    Scanner sc = new Scanner(System.in);

    System.out.println("Digite o nome do EPI:");
    String nome = sc.nextLine();
    System.out.println("Digite a quantidade do EPI:");
    int quantidade = sc.nextInt();

    EPI novo = new EPI(nome, quantidade);  // Não inclui o status de empréstimo

    return dao.inserirEPI(novo);
}


public static boolean listarEPI() {
    EPIDao dao = new EPIDao();
    Scanner sc = new Scanner(System.in);

    if (dao.listarEPIs().size() > 0) {
        for (EPI epi : dao.listarEPIs()) {
            System.out.println("ID: " + epi.getIdEpi() + " | Nome: " + epi.getNome() + " | Quantidade: " +
                    epi.getQuantidade());
        }
        return true;
    } else {
        return false;
    }
}

public static boolean editarEPI() {
    EPIDao dao = new EPIDao();
    Scanner sc = new Scanner(System.in);

    System.out.println("Digite o ID do EPI que deseja editar:");
    int id = sc.nextInt();
    sc.nextLine();  // Para limpar o buffer
    System.out.println("Digite o novo nome do EPI:");
    String nome = sc.nextLine();
    System.out.println("Digite a nova quantidade do EPI:");
    int quantidade = sc.nextInt();
    sc.nextLine();  // Para limpar o buffer

    return dao.atualizarEPI(id, nome, quantidade);  // Ajustando para o novo método
}

    public static boolean excluirEPI() {
        EPIDao dao = new EPIDao();
        EmprestimoDao emprestimoDao = new EmprestimoDao(); // certifique-se de importar
        Scanner sc = new Scanner(System.in);

        System.out.println("Informe o ID do EPI que deseja excluir:");
        int id = sc.nextInt();

        if (emprestimoDao.epiEmUso(id)) {
            System.out.println("Este EPI não pode ser excluído porque está vinculado a um ou mais empréstimos.");
           return false;
        } else if(dao.excluirEPI(id)) {
            System.out.println("EPI excluído com sucesso.");
            return true;
        }

        return false;

    }

}