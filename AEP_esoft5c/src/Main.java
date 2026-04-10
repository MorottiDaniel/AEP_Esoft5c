import Services.ServicoSolicitacoes;
import models.EnumStatus;
import models.Solicitacao;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServicoSolicitacoes servico = new ServicoSolicitacoes();
        int opcao;

        do {
            System.out.println("\n=== SISTEMA OBSERVAÇÃO ===");
            System.out.println("1- Solicitar Problema");
            System.out.println("2- Listar Todos");
            System.out.println("3- Detalhes e Histórico");
            System.out.println("4- Atualizar Status (Gestor)");
            System.out.println("5- Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Categoria: "); String cat = scanner.nextLine();
                    System.out.print("Descrição: "); String desc = scanner.nextLine();
                    System.out.print("Bairro: "); String bairro = scanner.nextLine();
                    System.out.print("Anônimo? (1-Sim / 0-Não): ");
                    boolean anon = scanner.nextInt() == 1;
                    scanner.nextLine();

                    String nome = "";
                    if (!anon) {
                        System.out.print("Seu Nome: ");
                        nome = scanner.nextLine();
                    }

                    System.out.print("Prioridade (1-Alta, 2-Média, 3-Baixa): ");
                    int prio = scanner.nextInt();
                    scanner.nextLine();

                    servico.criar(cat, desc, bairro, anon, nome, prio);
                    pausar(scanner);
                    break;

                case 2:
                    servico.listarTodas();
                    pausar(scanner);
                    break;

                case 3:
                    System.out.print("Protocolo: ");
                    int protBusca = scanner.nextInt();
                    scanner.nextLine();

                    Solicitacao s = servico.buscarPorProtocolo(protBusca);
                    if (s != null) {
                        s.exibir();
                        s.mostrarHistorico();
                    } else {
                        System.out.println("Não encontrado.");
                    }
                    pausar(scanner);
                    break;

                case 4:
                    System.out.print("Protocolo para atualizar: ");
                    int prot = scanner.nextInt();
                    scanner.nextLine();

                    Solicitacao sol = servico.buscarPorProtocolo(prot);
                    if (sol == null) {
                        System.out.println("Protocolo inválido.");
                        pausar(scanner);
                        break;
                    }

                    System.out.println("Status Atual: " + sol.getStatus());
                    System.out.print("Novo Status (1-TRIAGEM, 2-EM_EXECUCAO, 3-RESOLVIDO, 4-ENCERRADO): ");
                    int stOp = scanner.nextInt();
                    scanner.nextLine();

                    EnumStatus[] statusArray = {null, EnumStatus.TRIAGEM, EnumStatus.EM_EXECUCAO, EnumStatus.RESOLVIDO, EnumStatus.ENCERRADO};
                    if (stOp < 1 || stOp > 4) {
                        System.out.println("Opção de status inválida.");
                        pausar(scanner);
                        break;
                    }

                    EnumStatus novoStatus = statusArray[stOp];

                    if (sol.podeMudarPara(novoStatus)) {
                        System.out.print("Comentário: "); String com = scanner.nextLine();
                        System.out.print("Responsável: "); String resp = scanner.nextLine();
                        servico.atualizarStatus(prot, novoStatus, com, resp);
                        System.out.println("Status atualizado com sucesso!");
                    } else {
                        System.out.println("Transição não permitida de " + sol.getStatus() + " para " + novoStatus);
                    }
                    pausar(scanner);
                    break;
            }
        } while (opcao != 5);
    }

    public static void pausar(Scanner scanner) {
        System.out.println("\n----------------------------------");
        System.out.println("Pressione ENTER para continuar...");
        scanner.nextLine();
        limparTela();
    }

    public static void limparTela() {
        for (int i = 0; i < 15; i++) {
            System.out.println();
        }
    }
}