package back;

import java.util.Date;
import java.util.List;

/*
 * Projeto:
 * Esta classe representa um projeto e contém informações como id, nome, descrição,
 * tarefas, data de início, data de conclusão, orçamento e status.
 */

public class Projeto {
    private int id;
    private String nome;
    private String descricao;
    // private List<Tarefa> tarefas; // Comentado para evitar erro de compilação
    private double orcamento;
    private double status;
    private Date dataInicio;
    private Date dataConclusao;

    // Construtores, getters e setters
    /*
     * public Projeto(int id, String nome, String descricao, double orcamento, double
     * status, java.sql.Date dataInicio,java.sql.Date dataConclusao) {
     * 
     * this.id = id; this.nome = nome; this.descricao = descricao; this.orcamento =
     * orcamento; this.status = status; this.dataInicio = dataInicio;
     * this.dataConclusao = dataConclusao;
     * 
     * }
     */

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

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

    /*
     * public List<Tarefa> getTarefas() { return List<Tarefa>; } public void
     * setTarefas(List<Tarefa> tarefas) { this.tarefas = tarefas; }
     */

    public double getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(double orcamento) {
        this.orcamento = orcamento;
    }

    public double getStatus() {
        return status;
    }

    public void setStatus(double status) {
        this.status = status;
    }

}
