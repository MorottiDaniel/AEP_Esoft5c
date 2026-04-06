package models;

import java.time.LocalDateTime;

public class HistoricoDoStatus {

    private EnumStatus status;
    private String comentario;
    private String responsavel;
    private LocalDateTime data;

    public HistoricoDoStatus(EnumStatus status, String comentario, String responsavel) {
        this.status = status;
        this.comentario = comentario;
        this.responsavel = responsavel;
        this.data = LocalDateTime.now();
    }

    public void exibir(){
        System.out.println("Data:" + data);
        System.out.println("Status:" + status);
        System.out.println("Comentario:" + comentario);
        System.out.println("Responsavel:" + responsavel);
        System.out.println("============================");
    }
}
