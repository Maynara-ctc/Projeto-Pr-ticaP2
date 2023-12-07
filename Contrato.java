import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Contrato extends JFrame implements ActionListener {

    JTextField text1;
    JTextField text2;
    JTextField text3;
    JTextField text4;

   
    static final String JDBC_URL = "jdbc:sqlserver://regulus.cotuca.unicamp.br:1433;databaseName=IMOB";
    static final String USER = "BD23312";
    static final String PASSWORD = "BD23312";

    public Contrato() {
        setSize(800, 500);
        setTitle("Contrato");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        text1 = new JTextField(10);
        text1.setFont(new Font("Arial", Font.ITALIC, 20));
        panel.add(createLabelAndTextField("Cliente:", text1));

        text2 = new JTextField(10);
        text2.setFont(new Font("Arial", Font.ITALIC, 20));
        panel.add(createLabelAndTextField("Imóvel:", text2));

        text3 = new JTextField(10);
        text3.setFont(new Font("Arial", Font.ITALIC, 20));
        panel.add(createLabelAndTextField("Data início:", text3));

        text4 = new JTextField(10);
        text4.setFont(new Font("Arial", Font.ITALIC, 20));
        panel.add(createLabelAndTextField("Data Fim:", text4));

        JLabel title = new JLabel("Gerador de Contratos");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        add(title, BorderLayout.NORTH);

        add(panel, BorderLayout.CENTER);

        JButton jButton = new JButton("Gerar Contrato ");
        jButton.setFont(new Font("Arial", Font.BOLD, 20));
        jButton.setForeground(new Color(10, 10, 9));
        jButton.setBackground(new Color(250, 235, 215));
        jButton.addActionListener(this);
        add(jButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createLabelAndTextField(String labelText, JTextField textField) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(label);
        panel.add(textField);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String texto1 = text1.getText();
        String texto2 = text2.getText();
        String texto3 = text3.getText();
        String texto4 = text4.getText();

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

           
            String insertSQL = "INSERT INTO IMOB.contrato (cliente, imovel, data_inicio, data_fim) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
                preparedStatement.setString(1, texto1);
                preparedStatement.setString(2, texto2);
                preparedStatement.setString(3, texto3);
                preparedStatement.setString(4, texto4);

                preparedStatement.executeUpdate();

                JOptionPane.showMessageDialog(
                        null, "Contrato gerado e salvo no banco de dados.", "Contrato", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    null, "JDBC não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        new Contrato();
    }
}
