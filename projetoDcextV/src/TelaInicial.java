import javax.swing.*;
import java.awt.*;

public class TelaInicial extends JFrame {

    public TelaInicial() {
        setTitle("Autoatendimento");
        setSize(1440, 960);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                Color color1 = new Color(79, 70, 229);
                Color color2 = new Color(147, 51, 234);
                GradientPaint gp = new GradientPaint(0, 0, color1, width, height, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, width, height);
            }
        };
        setContentPane(panel);

        JPanel contentContainer = new JPanel();
        contentContainer.setOpaque(false);
        contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
        contentContainer.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titulo = new JLabel("Bem-vindo ao");
        titulo.setFont(new Font("Montserrat", Font.BOLD, 60));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titulo2 = new JLabel("Autoatendimento");
        titulo2.setFont(new Font("Montserrat", Font.BOLD, 60));
        titulo2.setForeground(Color.WHITE);
        titulo2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel descricao = new JLabel("Aqui você pode registrar seu atendimento de forma rápida e acessível");
        descricao.setFont(new Font("Montserrat", Font.PLAIN, 24));
        descricao.setForeground(Color.WHITE);
        descricao.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton iniciarBtn = new JButton("Iniciar Atendimento");
        iniciarBtn.setFont(new Font("Montserrat", Font.BOLD, 28));
        iniciarBtn.setForeground(Color.WHITE);
        iniciarBtn.setBackground(new Color(94, 23, 235));
        iniciarBtn.setFocusPainted(false);
        iniciarBtn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(150, 100, 250), 2),
                BorderFactory.createEmptyBorder(20, 40, 20, 40)
        ));
        iniciarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        iniciarBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        iniciarBtn.addActionListener(e -> {
            new TelaConvenio().setVisible(true);
            dispose();
        });

        contentContainer.add(titulo);
        contentContainer.add(titulo2);
        contentContainer.add(Box.createRigidArea(new Dimension(0, 20)));
        contentContainer.add(descricao);
        contentContainer.add(Box.createRigidArea(new Dimension(0, 60)));
        contentContainer.add(iniciarBtn);

        panel.add(contentContainer);
    }
}