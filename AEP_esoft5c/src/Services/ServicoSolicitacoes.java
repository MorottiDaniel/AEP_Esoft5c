package Services;

import models.EnumStatus;
import models.Solicitacao;

import java.util.ArrayList;

public class ServicoSolicitacoes {

    private ArrayList<Solicitacao> lista;
    public int id = 1;

    public ServicoSolicitacoes(){
        lista = new ArrayList<>();
    }

    public void criar(String categoria, String descricao, String bairro, boolean anonimo, String nome, int prioridade){
        if(categoria.isEmpty() || bairro.isEmpty()){
            System.out.println("Campos obrigatorios");
            return;
        }

        if (descricao.length() < 5) {
            System.out.println("Descrição muito curta!");
            return;
        }

        Solicitacao solicitacao = new Solicitacao(id++, categoria, descricao, bairro, nome, anonimo, prioridade);
            lista.add(solicitacao);

            System.out.println("Uma Solicitação foi criada com protocolo: " + solicitacao.getProtocolo());
    }

    public Solicitacao buscarProtocolo(int protocolo){
        for (Solicitacao solicitacao : lista){
            if(solicitacao.getProtocolo() == protocolo){
                return solicitacao;
            }
        }
        return null;
    }

    public void listarTodos(){
        for(Solicitacao solicitacao : lista){
            solicitacao.exibir();
        }
    }

    public void atualizarStatus(int protocolo, EnumStatus status, String comentario, String responsavel) {
        Solicitacao solicitacao = buscarProtocolo(protocolo);

        if (solicitacao != null) {
            solicitacao.atualizarStatus(status, comentario, responsavel);
        } else {
            System.out.println("Protocolo/id não foi encontrado!");
        }
    }

    public void listarPorBairro(String bairro) {
        for (Solicitacao solicitacao : lista) {
            if (solicitacao.getBairro().equalsIgnoreCase(bairro)) {
                solicitacao.exibir();
            }
        }
    }
}
