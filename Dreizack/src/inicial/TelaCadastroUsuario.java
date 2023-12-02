package inicial;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import back.BD;
import back.Usuario;
import back.UsuarioDAO;

/**
 * Classe que representa a tela de cadastro e alteração de usuário.
 */
public class TelaCadastroUsuario extends JFrame {

    public static final long serialVersionUID = 1L;
    public JLabel jcomp1;
    public JLabel txtCpf;
    public JLabel txtNome;
    public JLabel txtLogin;
    public JLabel txtSenha;
    public JLabel txtCargo;
    public static JTextField compCpf;
    public static JTextField compNome;
    public static JTextField compLogin;
    public static JTextField compSenha;
    public static JTextField compCargo;
    public JButton btnMenuInicial;
    public JButton btnEstoque;
    public JButton btnAlterar;
    public JButton btnLocalizar;
    public JButton btnNovoCadastro;
    private JButton btnExcluir;
    private JTextField textCampoFeed;

    /**
     * Construtor que cria a tela de cadastro de usuário.
     */
    public TelaCadastroUsuario() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        jcomp1 = new JLabel("Cadastro / Alteração de Usuario");
        txtCpf = new JLabel("Cpf:");
        txtNome = new JLabel("Nome:");
        txtLogin = new JLabel("Login:");
        txtSenha = new JLabel("Senha");
        txtCargo = new JLabel("Cargo:");

        compCpf = new JTextField(5);
        compNome = new JTextField(5);
        compLogin = new JTextField(5);
        compSenha = new JTextField(5);
        compCargo = new JTextField(5);

        btnMenuInicial = new JButton("Menu Incial");
        btnEstoque = new JButton("Estoque");
        btnAlterar = new JButton("Alterar");
        btnLocalizar = new JButton("Localizar");
        btnNovoCadastro = new JButton("Cadastrar Usuario");

        // Adjust size and set layout
        setPreferredSize(new Dimension(600, 300));
        contentPane.setLayout(null);

        // Add components
        contentPane.add(jcomp1);
        contentPane.add(txtCpf);
        contentPane.add(txtNome);
        contentPane.add(txtLogin);
        contentPane.add(txtSenha);
        contentPane.add(txtCargo);

        contentPane.add(compCpf);
        contentPane.add(compNome);
        contentPane.add(compLogin);
        contentPane.add(compSenha);
        contentPane.add(compCargo);

        contentPane.add(btnMenuInicial);
        contentPane.add(btnEstoque);
        contentPane.add(btnAlterar);
        contentPane.add(btnLocalizar);
        contentPane.add(btnNovoCadastro);

        // Set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds(35, 25, 190, 50);
        txtCpf.setBounds(35, 90, 100, 25);
        txtNome.setBounds(35, 120, 100, 25);
        txtLogin.setBounds(35, 165, 100, 25);
        txtSenha.setBounds(35, 195, 100, 25);
        txtCargo.setBounds(35, 225, 100, 25);

        compCpf.setBounds(95, 90, 162, 25);
        compNome.setBounds(95, 120, 162, 40);
        compLogin.setBounds(95, 165, 162, 25);
        compSenha.setBounds(95, 195, 162, 25);
        compCargo.setBounds(95, 225, 162, 25);

        btnMenuInicial.setBounds(35, 295, 120, 30);
        btnEstoque.setBounds(165, 295, 120, 30);
        btnAlterar.setBounds(270, 170, 150, 30);
        btnLocalizar.setBounds(270, 130, 150, 30);
        btnNovoCadastro.setBounds(270, 90, 150, 30);

        btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(270, 210, 150, 30);
        contentPane.add(btnExcluir);

        textCampoFeed = new JTextField();
        textCampoFeed.setBounds(56, 367, 420, 86);
        contentPane.add(textCampoFeed);
        textCampoFeed.setColumns(10);

        btnLocalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BD bd = new BD();
                bd.getConnection();
                String s = compCpf.getText();
                String sql = "select * from usuario where cpf like ?";
                try {
                    bd.st = bd.con.prepareStatement(sql);
                    bd.st.setString(1, "%" + s + "%");
                    bd.rs = bd.st.executeQuery();

                    // Limpa os campos antes de exibir novos resultados
                    limparCampos();

                    // Adiciona os resultados aos campos
                    while (bd.rs.next()) {
                        compCpf.setText(Long.toString(bd.rs.getLong(1)));
                        compNome.setText(bd.rs.getString(2));
                        compLogin.setText(bd.rs.getString(3));
                        compSenha.setText(bd.rs.getString(4));
                        compCargo.setText(bd.rs.getString(5));
                    }
                } catch (SQLException erro) {
                    System.out.println(erro);
                }
            }

            // Método para limpar os campos antes de exibir novos resultados
            private void limparCampos() {
                compCpf.setText("");
                compNome.setText("");
                compLogin.setText("");
                compSenha.setText("");
                compCargo.setText("");
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsuarioDAO.excluir(Long.toString(Long.parseLong(compCpf.getText())));
                String a = "Usuario Excluido com Sucesso";
                textCampoFeed.setText(a);
            }
        });

        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario u = new Usuario();

                u.setCpf(Long.parseLong(compCpf.getText()));
                u.setNome(compNome.getText());
                u.setLogin(compLogin.getText());
                u.setSenha(compSenha.getText());
                u.setCargo(compCargo.getText());

                UsuarioDAO.alterar(u);
                textCampoFeed.setText("item alterado com Sucesso");
            }
        });

        btnMenuInicial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaInicial q = new TelaInicial();
                q.setVisible(true);
                dispose();
            }
        });

        btnNovoCadastro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Usuario u = new Usuario();

                u.setCpf(Long.parseLong(compCpf.getText()));
                u.setNome(compNome.getText());
                u.setLogin(compLogin.getText());
                u.setSenha(compSenha.getText());
                u.setCargo(compCargo.getText());

                UsuarioDAO.inserir(u);
                textCampoFeed.setText("Usuario Cadastrado com Sucesso");
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaCadastroUsuario frame = new TelaCadastroUsuario();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
