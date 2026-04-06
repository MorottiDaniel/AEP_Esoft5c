import Services.ServicoSolicitacoes;
import models.EnumStatus;
import models.Solicitacao;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int operacao;
        ServicoSolicitacoes servicoSolicitacoes = new ServicoSolicitacoes();

        do{
            System.out.println("=== Digite uma ação ===");
            System.out.println("1- Solicitar um problema");
            System.out.println("2- Listar todos os problemas");
            System.out.println("3- Mostrar individualmente o processo de um problema");
            System.out.println("4- Atualizar Status de um problema");
            System.out.println("5- Sair do sistema");
            System.out.print("Digite uma opcao: ");

            operacao = scanner.nextInt();
            scanner.nextLine();
            limparTela();

            switch(operacao){
                case 1:
                    System.out.println("1-/Solicitação/ Preencha os dados para solicitar um problema:");
                    System.out.print("Categoria do problema (ex: iluminação, buraco, limpeza, saúde, segurança escolar, etc.): ");
                    String categoria = scanner.nextLine();

                    System.out.print("Descrição do problema: ");
                    String descricao = scanner.nextLine();

                    System.out.print("Bairro do problema solicitado: ");
                    String bairro = scanner.nextLine();

                    System.out.print("Por favor informe se quer permanecer Anonimo (1-SIM/0-NãO): ");
                    int opcaoAnonimo = scanner.nextInt();
                    scanner.nextLine();

                    boolean anonimo = (opcaoAnonimo == 1);

                    String nome = "";
                    if(!anonimo){
                        System.out.print("Informe seu nome: ");
                        nome = scanner.nextLine();
                    }

                    System.out.println("Prioridade (1-Alta, 2-Media, 3-Baixa): ");
                    int prioridade = scanner.nextInt();
                    scanner.nextLine();

                    servicoSolicitacoes.criar(categoria, descricao, bairro, anonimo, nome, prioridade);
                    System.out.println("\nPressione ENTER para continuar...");
                    scanner.nextLine();
                    limparTela();
                    break;

                case 2:
                    System.out.println("2-/Listagem de todos os problemas/");
                    servicoSolicitacoes.listarTodos();
                    System.out.println("\nPressione ENTER para continuar...");
                    scanner.nextLine();
                    limparTela();
                    break;

                case 3:
                    System.out.println("3-/Status de um problema/");
                    System.out.print("Informe o protocolo/id que quer buscar: ");
                    int protocolo = scanner.nextInt();
                    scanner.nextLine();

                    Solicitacao solicitacao = servicoSolicitacoes.buscarProtocolo(protocolo);
                    if(solicitacao != null){
                        solicitacao.exibir();
                        solicitacao.mostrarHistorico();
                    }else{
                        System.out.println("Não encontramos tal protocolo!");
                    }
                    System.out.println("\nPressione ENTER para continuar...");
                    scanner.nextLine();

                    limparTela();
                    break;

                case 4:
                    System.out.println("4-/Atualizar Status de um problema/");
                    System.out.print("Informe o protocolo/id para moderar: ");
                    int protocolo2 = scanner.nextInt();
                    scanner.nextLine();

                    Solicitacao s = servicoSolicitacoes.buscarProtocolo(protocolo2);

                    if(s == null){
                        System.out.println("Protocolo nao encontrado!");
                        System.out.println("\nPressione ENTER para continuar...");
                        scanner.nextLine();
                        limparTela();
                        break;
                    }

                    System.out.print("Novo status (1-TRIAGEM, 2-EM_EXECUCAO, 3-RESOLVIDO, 4-ENCERRADO): ");
                    int opcaoStatus = scanner.nextInt();
                    scanner.nextLine();

                    EnumStatus status = EnumStatus.TRIAGEM;

                    switch (opcaoStatus) {
                        case 1:
                            status = EnumStatus.TRIAGEM;
                            break;
                        case 2:
                            status = EnumStatus.EM_EXECUCAO;
                            break;
                        case 3:
                            status = EnumStatus.RESOLVIDO;
                            break;
                        case 4:
                            status = EnumStatus.ENCERRADO;
                            break;
                        default:
                            System.out.println("Selecione uma das opções apenas!");
                            System.out.println("\nPressione ENTER para continuar...");
                            scanner.nextLine();
                            limparTela();
                            continue;
                    }

                    if(!s.confirmarAtualizacao(status)){
                        System.out.println("Status nao validado! Você deve seguir a ordem de atualização de Status!");
                        System.out.println("Status em que o processo se encontra:  " + s.getStatus());

                        System.out.println("\nPressione ENTER para continuar...");
                        scanner.nextLine();
                        limparTela();
                        break;
                    }

                    System.out.print("Comentario:");
                    String comentario = scanner.nextLine();

                    System.out.print("Responsavel: ");
                    String responsavel = scanner.nextLine();

                    servicoSolicitacoes.atualizarStatus(protocolo2, status, comentario, responsavel);
                    System.out.println("Status do problema atualizado!");
                    System.out.println("\nPressione ENTER para continuar...");
                    scanner.nextLine();
                    limparTela();
                    break;
            }
        }while(operacao!=5);
    }
    public static void limparTela() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }
}
