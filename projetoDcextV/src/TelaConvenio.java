import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class TelaConvenio extends TelaBase {

    public TelaConvenio() {
        super("Seleção de Convênio");

        JPanel panel = criarPainelGradiente();
        setContentPane(panel);
        panel.setLayout(new BorderLayout(0, 40));
        panel.setBorder(BorderFactory.createEmptyBorder(150, 50, 0, 50));

        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Qual é o seu convênio?");
        titulo.setFont(new Font("Montserrat", Font.BOLD, 60));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel descricao = new JLabel("Selecione o convênio que você possui ou escolha 'Particular' caso não tenha.");
        descricao.setFont(new Font("Montserrat", Font.PLAIN, 24));
        descricao.setForeground(new Color(220, 220, 240));
        descricao.setAlignmentX(Component.CENTER_ALIGNMENT);

        textPanel.add(titulo);
        textPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        textPanel.add(descricao);
        panel.add(textPanel, BorderLayout.NORTH);

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        botoesPanel.setOpaque(false);

        String[] convenios = {"Unimed", "Amil", "SulAmerica", "Hapvida", "Particular"};
        for (String nomeConvenio : convenios) {
            RoundedButton btn = new RoundedButton("", new Color(55, 48, 163, 150), new Color(94, 23, 235, 100), new Color(150, 100, 250, 150));
            btn.setPreferredSize(new Dimension(200, 120));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            if (nomeConvenio.equals("Particular")) {
                btn.setText("Particular");
            } else {
                try {
                    Image img;
                    if (nomeConvenio.equals("unimed")) {
                        img = new ImageIcon(getClass().getResource("/resources/" + nomeConvenio + ".png")).getImage().getScaledInstance(180, 80, Image.SCALE_SMOOTH);
                    } else {
                        img = new ImageIcon(getClass().getResource("/resources/" + nomeConvenio + ".png")).getImage().getScaledInstance(160, 80, Image.SCALE_SMOOTH);
                    }
                    btn.setIcon(new ImageIcon(img));
                } catch (Exception e) {
                    btn.setText(nomeConvenio);
                    System.err.println("Imagem não encontrada: " + nomeConvenio + ".png");
                }
            }
            btn.addActionListener(e -> {
                new TelaDor(nomeConvenio).setVisible(true);
                dispose();
            });
            botoesPanel.add(btn);
        }
        panel.add(botoesPanel, BorderLayout.CENTER);

        adicionarNavegacao(panel, true, new TelaInicial());
    }
}