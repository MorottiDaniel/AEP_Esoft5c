package Services;

import models.EnumStatus;
import models.Solicitacao;
import java.util.ArrayList;
import java.util.List;

public class ServicoSolicitacoes {
    private List<Solicitacao> lista = new ArrayList<>();
    private int proximoId = 1;

    public void criar(String categoria, String descricao, String bairro, boolean anonimo, String nome, int prioridade) {
        // Clean Code: Guard Clauses (Falha rápido para evitar aninhamento de IFs)
        if (categoria.isEmpty() || bairro.isEmpty() || descricao.length() < 5) {
            System.out.println("Erro: Verifique os campos obrigatórios e o tamanho da descrição.");
            return;
        }

        Solicitacao nova = new Solicitacao(proximoId++, categoria, descricao, bairro, nome, anonimo, prioridade);
        lista.add(nova);
        System.out.println("Solicitação criada com sucesso! Protocolo: " + nova.getProtocolo());
    }

    public Solicitacao buscarPorProtocolo(int protocolo) {
        for (Solicitacao s : lista) {
            if (s.getProtocolo() == protocolo) return s;
        }
        return null;
    }

    public void listarTodas() {
        if (lista.isEmpty()) {
            System.out.println("Nenhuma solicitação cadastrada.");
            return;
        }
        for (Solicitacao s : lista) s.exibir();
    }

    public void atualizarStatus(int protocolo, EnumStatus novoStatus, String comentario, String responsavel) {
        Solicitacao s = buscarPorProtocolo(protocolo);
        if (s != null) {
            s.atualizarStatus(novoStatus, comentario, responsavel);
        } else {
            System.out.println("Erro: Protocolo não encontrado.");
        }
    }
}