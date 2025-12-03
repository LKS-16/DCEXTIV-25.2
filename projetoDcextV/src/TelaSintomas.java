import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TelaSintomas extends TelaBase {

    private String convenio;
    private String regiao;

    public TelaSintomas(String convenio, String regiao) {
        super("Sintomas");

        this.convenio = convenio;
        this.regiao = regiao;

        JPanel panel = criarPainelGradiente();
        setContentPane(panel);
        panel.setLayout(new BorderLayout(0, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 120, 40, 120));

        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Quais sintomas você está sentindo?");
        titulo.setFont(new Font("Montserrat", Font.BOLD, 48));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel descricao = new JLabel("Selecione o sintoma que melhor descreve seu desconforto.");
        descricao.setFont(new Font("Montserrat", Font.PLAIN, 20));
        descricao.setForeground(new Color(220, 220, 240));
        descricao.setAlignmentX(Component.CENTER_ALIGNMENT);

        textPanel.add(titulo);
        textPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        textPanel.add(descricao);

        panel.add(textPanel, BorderLayout.NORTH);

        String[] listaSintomas = {
                "Dor Aguda", "Formigamento", "Inchaço",
                "Queimação", "Fisgada", "Pressão",
                "Latejamento", "Fraqueza"
        };

        JPanel botoesPanel = new JPanel(new GridBagLayout());
        botoesPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        for (int i = 0; i < listaSintomas.length; i++) {
            String sintoma = listaSintomas[i];

            RoundedButton btn = new RoundedButton(
                    sintoma,
                    new Color(70, 60, 180, 160),
                    new Color(94, 23, 235, 160),
                    new Color(150, 100, 250, 150)
            );

            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setFont(new Font("Montserrat", Font.BOLD, 20));
            btn.setPreferredSize(new Dimension(280, 90));

            btn.addActionListener(e -> {

                btn.setBackground(new Color(130, 110, 255, 220));
                btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));

                Timer t = new Timer(120, ev -> {
                    List<String> lista = new ArrayList<>();
                    lista.add(sintoma);

                    new TelaResumo(convenio, regiao, lista).setVisible(true);
                    dispose();
                });

                t.setRepeats(false);
                t.start();
            });

            gbc.gridx = i % 2;
            gbc.gridy = i / 2;
            gbc.insets = new Insets(12, 22, 12, 22);
            gbc.fill = GridBagConstraints.BOTH;

            botoesPanel.add(btn, gbc);
        }

        panel.add(botoesPanel, BorderLayout.CENTER);

        adicionarNavegacao(panel, true, new TelaTempoDor(convenio, regiao));
    }
}
