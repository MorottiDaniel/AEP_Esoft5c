package models;

import java.util.ArrayList;
import java.util.List;

public class Solicitacao {
    private int protocolo;
    private EnumStatus status;
    private String categoria;
    private String descricao;
    private String bairro;
    private String nome;
    private boolean anonimo;
    private int prioridade;
    private List<HistoricoDoStatus> historico;

    public Solicitacao(int protocolo, String categoria, String descricao, String bairro, String nome, boolean anonimo, int prioridade) {
        this.protocolo = protocolo;
        this.status = EnumStatus.ABERTO;
        this.categoria = categoria;
        this.descricao = descricao;
        this.bairro = bairro;
        this.anonimo = anonimo;
        this.prioridade = prioridade;
        this.nome = anonimo ? "ANÔNIMO" : nome;

        this.historico = new ArrayList<>();
        this.historico.add(new HistoricoDoStatus(EnumStatus.ABERTO, "Solicitação criada no sistema", "SISTEMA"));
    }

    // Clean Code: Método centralizado para validar a regra de negócio de transição de status
    public boolean podeMudarPara(EnumStatus novoStatus) {
        if (this.status == EnumStatus.ABERTO) return novoStatus == EnumStatus.TRIAGEM;
        if (this.status == EnumStatus.TRIAGEM) return novoStatus == EnumStatus.EM_EXECUCAO;
        if (this.status == EnumStatus.EM_EXECUCAO) return novoStatus == EnumStatus.RESOLVIDO;
        if (this.status == EnumStatus.RESOLVIDO) return novoStatus == EnumStatus.ENCERRADO;
        return false;
    }

    public void atualizarStatus(EnumStatus novoStatus, String comentario, String responsavel) {
        if (!podeMudarPara(novoStatus)) {
            System.out.println("Transição de status inválida!");
            return;
        }
        this.status = novoStatus;
        historico.add(new HistoricoDoStatus(novoStatus, comentario, responsavel));
    }

    public void exibir() {
        System.out.println("\n--- DETALHES DA SOLICITAÇÃO ---");
        System.out.println("Protocolo: " + protocolo);
        System.out.println("Status: " + status);
        System.out.println("Prioridade: " + prioridade);
        System.out.println("Categoria: " + categoria);
        System.out.println("Bairro: " + bairro);
        System.out.println("Solicitante: " + nome);
        System.out.println("Descrição: " + descricao);
        System.out.println("-------------------------------");
    }

    public void mostrarHistorico() {
        System.out.println("\n>>> HISTÓRICO DE MOVIMENTAÇÕES:");
        for (HistoricoDoStatus h : historico) {
            h.exibir();
        }
    }

    // Getters necessários
    public int getProtocolo() { return protocolo; }
    public EnumStatus getStatus() { return status; }
    public String getBairro() { return bairro; }
}