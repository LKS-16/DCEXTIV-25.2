import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedButton extends JButton {
    private Color startColor;
    private Color endColor;
    private Color borderColor;

    public RoundedButton(String text, Color start, Color end, Color border) {
        super(text);
        this.startColor = start;
        this.endColor = end;
        this.borderColor = border;
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setFont(new Font("Montserrat", Font.BOLD, 22));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint gp = new GradientPaint(0, 0, startColor, 0, getHeight(), endColor);
        g2.setPaint(gp);
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));

        g2.setColor(borderColor);
        g2.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, 30, 30));

        g2.dispose();
        super.paintComponent(g);
    }
}