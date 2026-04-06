package models;

import models.EnumStatus;

import java.io.ObjectInputFilter;
import java.util.ArrayList;

public class Solicitacao {

    private int protocolo;
    private EnumStatus status;
    private String categoria;
    private String descricao;
    private String bairro;
    private String nome;
    private boolean anonimo;
    private int prioridade;

    private ArrayList<HistoricoDoStatus> historico;

    public Solicitacao(int protocolo,  String categoria, String descricao,
                       String bairro, String nome, boolean anonimo, int prioridade) {
        this.protocolo = protocolo;
        this.status = EnumStatus.ABERTO;
        this.categoria = categoria;
        this.descricao = descricao;
        this.bairro = bairro;
        this.anonimo = anonimo;
        this.prioridade = prioridade;

        if (anonimo) {
            this.nome = "ANONIMO";
        }else{
            this.nome = nome;
        }

        historico = new ArrayList<>();
        historico.add(new HistoricoDoStatus(EnumStatus.ABERTO, "CRIADO COM SUCESSO", "SISTEMA"));
    }

    public int getProtocolo() {
        return protocolo;
    }

    public EnumStatus getStatus() {
        return status;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void atualizarStatus(EnumStatus newStatus, String comentario, String responsavel){
        if(this.status == EnumStatus.ABERTO && newStatus != EnumStatus.TRIAGEM){
            System.out.println("De ABERTO só pode ir para TRIAGEM!");
            return;
        }

        if(this.status == EnumStatus.TRIAGEM && newStatus != EnumStatus.EM_EXECUCAO){
            System.out.println("De TRIAGEM só pode ir para EM_EXECUCAO!");
            return;
        }

        if(this.status == EnumStatus.EM_EXECUCAO && newStatus != EnumStatus.RESOLVIDO){
            System.out.println("De EM_EXECUCAO só pode ir para RESOLVIDO!");
            return;
        }

        if(this.status == EnumStatus.RESOLVIDO && newStatus != EnumStatus.ENCERRADO){
            System.out.println("De RESOLVIDO só pode ir para ENCERRADO!");
            return;
        }
        this.status = newStatus;
        historico.add(new HistoricoDoStatus(newStatus, comentario, responsavel));
    }

    public boolean confirmarAtualizacao(EnumStatus novoStatus){
        if(this.status == EnumStatus.ABERTO && novoStatus != EnumStatus.TRIAGEM){
            return false;
        }

        if(this.status == EnumStatus.TRIAGEM && novoStatus != EnumStatus.EM_EXECUCAO){
            return false;
        }

        if(this.status == EnumStatus.EM_EXECUCAO && novoStatus != EnumStatus.RESOLVIDO){
            return false;
        }

        if(this.status == EnumStatus.RESOLVIDO && novoStatus != EnumStatus.ENCERRADO){
            return false;
        }

        return true;
    }

    public void exibir(){
        System.out.println("Protocolo: " + this.protocolo);
        System.out.println("Status: " + this.status);
        System.out.println("Categoria: " + this.categoria);
        System.out.println("Descricao: " + this.descricao);
        System.out.println("Bairro: " + this.bairro);
        System.out.println("Prioridade: " + prioridade);
        System.out.println("=============================");
    }

    public void mostrarHistorico(){
        for (HistoricoDoStatus historicos : historico){
            historicos.exibir();
        }
    }
}
