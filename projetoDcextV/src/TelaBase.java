import javax.swing.*;
import java.awt.*;

public class TelaBase extends JFrame {

    public TelaBase(String title) {
        setTitle(title);
        setSize(1440, 960);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    protected void adicionarNavegacao(JPanel painelPrincipal, boolean mostrarVoltar, JFrame telaAnterior) {
        ImageIcon handIcon = null;
        try {
            Image img = new ImageIcon(getClass().getResource("/resources/libras_hand.png")).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            handIcon = new ImageIcon(img);
        } catch (Exception e) { System.err.println("Imagem 'libras_hand.png' não encontrada."); }

        JLabel librasIconLabel = new JLabel(handIcon);
        JLabel librasTextLabel = new JLabel("Libras");
        librasTextLabel.setFont(new Font("Montserrat", Font.BOLD, 18));
        librasTextLabel.setForeground(Color.WHITE);

        JPanel librasPanel = new JPanel();
        librasPanel.setOpaque(false);
        librasPanel.setLayout(new BoxLayout(librasPanel, BoxLayout.Y_AXIS));
        librasPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        librasIconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        librasTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        librasPanel.add(librasIconLabel);
        librasPanel.add(librasTextLabel);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setOpaque(false);
        rightPanel.add(librasPanel);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 40));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setOpaque(false);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 20, 0));

        if (mostrarVoltar) {
            ImageIcon backIcon = null;
            try {
                Image img = new ImageIcon(getClass().getResource("/resources/voltar.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                backIcon = new ImageIcon(img);
            } catch (Exception e) { System.err.println("Imagem 'voltar.png' não encontrada."); }

            RoundedButton voltarBtn = new RoundedButton("Voltar", new Color(40, 40, 80), new Color(30, 30, 60), new Color(80, 80, 120));
            voltarBtn.setIcon(backIcon);
            voltarBtn.setFont(new Font("Montserrat", Font.BOLD, 18));
            voltarBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            voltarBtn.addActionListener(e -> {
                telaAnterior.setVisible(true);
                dispose();
            });
            leftPanel.add(voltarBtn);
        }

        JPanel southContainer = new JPanel(new BorderLayout());
        southContainer.setOpaque(false);
        southContainer.add(leftPanel, BorderLayout.WEST);
        southContainer.add(rightPanel, BorderLayout.EAST);

        painelPrincipal.add(southContainer, BorderLayout.SOUTH);
    }

    protected JPanel criarPainelGradiente() {
        return new JPanel(new BorderLayout()) {
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
    }
}