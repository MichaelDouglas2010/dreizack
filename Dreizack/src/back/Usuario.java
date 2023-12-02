package back;

/*
 * Usuário:
 * 
 * Nome (texto)
 * cpf (int)
 * Login (texto)
 * Senha (texto)
 * cargo (texto)
 */

public class Usuario {
    private long cpf;
    private String nome;
    private String login;
    private String senha;
    private String cargo;

    // Construtor padrão
    public Usuario() {
        // Pode ser vazio ou inicializado com valores padrão, se necessário
    }

    // Construtor com parâmetros
    public Usuario(long cpf, String nome, String login, String senha, String cargo) {
        this.cpf = cpf;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.cargo = cargo;
    }

    // Getters e setters para cada atributo

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    // Outros métodos, se necessário
}
