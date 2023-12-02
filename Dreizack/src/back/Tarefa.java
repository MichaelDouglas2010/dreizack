package back;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/*
 * Tarefas: 
 * id (int)
 * Nome (texto) 
 * Descrição (texto) 
 * Data de início (data) 
 * Data de conclusão (data) 
 * Valor ($ decimal) 
 * % de avanço (inteiro) 
 * Estado (texto) 
 */

public class Tarefa {
    private int id;
    private String nome;
    private String descricao;
    private String responsavel;
    private double status;
    private Date dataInicio;
    private Date dataConclusao;
    private int idProjeto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter para a dataSql
    public void setDataInicio(String dataInicio) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date dataUtil = (Date) formato.parse(dataInicio);
            this.dataInicio = new java.sql.Date(dataUtil.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Erro na conversão da data");
        }
    }

    public java.sql.Date getDataInicio() {
        return dataInicio;
    }

    public void setDataConclusao(String dataConclusao) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date dataUtil = (Date) formato.parse(dataConclusao);
            this.dataConclusao = new java.sql.Date(dataUtil.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Erro na conversão da data");
        }
    }

    public java.sql.Date getDataConclusao() {
        return dataConclusao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public double getStatus() {
        return status;
    }

    public void setStatus(double status) {
        this.status = status;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }
}
