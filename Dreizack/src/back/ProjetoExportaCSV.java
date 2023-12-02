package back;

import java.util.List;

public class ProjetoExportaCSV {

    /**
     * Converte uma lista de projetos para uma string no formato CSV.
     *
     * @param projetos A lista de projetos a ser convertida.
     * @return Uma string no formato CSV representando os projetos.
     */
    public static String toCSV(List<Projeto> projetos) {
        StringBuilder csv = new StringBuilder();

        // Adiciona cabeçalho
        csv.append("id,nome,descricao,orcamento,status\n");

        // Adiciona os dados de cada projeto
        for (Projeto projeto : projetos) {
            csv.append(projeto.getId()).append(",");
            csv.append(projeto.getNome()).append(",");
            csv.append(projeto.getDescricao()).append(",");
            csv.append(projeto.getOrcamento()).append(",");
            csv.append(projeto.getStatus()).append("\n");
        }

        return csv.toString();
    }

    public static void main(String[] args) {
        ProjetoDAO projetoDAO = new ProjetoDAO();
        List<Projeto> projetos = projetoDAO.listarProjetos();
        System.out.println(toCSV(projetos));
    }
}
