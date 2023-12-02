package inicial;

import javax.swing.*;

import back.Usuario;
import back.UsuarioDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaLogin extends JFrame {

    private JTextField textFieldUsuario;
    private JPasswordField passwordFieldSenha;

    public TelaLogin() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tela de Login");
        setSize(300, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel labelUsuario = new JLabel("Usuário:");
        labelUsuario.setBounds(10, 20, 80, 25);
        panel.add(labelUsuario);

        textFieldUsuario = new JTextField(20);
        textFieldUsuario.setBounds(100, 20, 160, 25);
        panel.add(textFieldUsuario);

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(10, 50, 80, 25);
        panel.add(labelSenha);

        passwordFieldSenha = new JPasswordField(20);
        passwordFieldSenha.setBounds(100, 50, 160, 25);
        panel.add(passwordFieldSenha);

        JButton buttonLogin = new JButton("Login");
        buttonLogin.setBounds(10, 80, 80, 25);
        panel.add(buttonLogin);

        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarLogin();
            }
        });
    }

    private void realizarLogin() {
        String usuario = textFieldUsuario.getText();
        char[] senhaChars = passwordFieldSenha.getPassword();
        String senha = new String(senhaChars);

        // Adicione aqui a lógica de validação de usuário e senha, por exemplo, comparando com o banco de dados.
        // Aqui, estou usando uma validação simples para fins de exemplo.

        if (usuario.equals(UsuarioDAO.consultaLog(senha)) && String.valueOf(usuario).equals(UsuarioDAO.consultaSenha(usuario))) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
            // Adicione aqui a lógica para abrir a próxima tela ou realizar outras ações após o login.
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin();
            }
        });
    }
}
