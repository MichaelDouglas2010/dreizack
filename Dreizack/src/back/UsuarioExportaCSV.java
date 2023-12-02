package back;


import java.util.List;

public class UsuarioExportaCSV {

    /**
     * Converte uma lista de usuarios para uma string no formato CSV.
     *
     * @param usuarios A lista de tarefa a ser convertida.
     * @return Uma string no formato CSV representando os usuarios.
     */
    public static String toCSV(List<Usuario> usuarios) {
        StringBuilder csv = new StringBuilder();

        // Adiciona cabeçalho
        csv.append("cpf,nome,login,senha,cargo\n");

        // Adiciona os dados de cada usuario
        for (Usuario usuario : usuarios) {
            csv.append(usuario.getCpf()).append(",");
            csv.append(usuario.getNome()).append(",");
            csv.append(usuario.getLogin()).append(",");
            csv.append(usuario.getSenha()).append(",");
            csv.append(usuario.getCargo()).append("\n");
        }

        return csv.toString();
    }

    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> usuarios = usuarioDAO.listarUsuarios();
        System.out.println(toCSV(usuarios));
    }
}
