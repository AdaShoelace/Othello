import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Factory class with the purpose of making ImageIcons that in turn is used as the graphical
 * representation of the chips/game pieces
 * Created by Pierre Lejdbring on 9/13/17.
 */
public final class Chip extends ImageIcon {
    private static final int IMG_WIDTH = 100;
    private static final int IMG_HEIGHT = 100;
    private static final int GAP = 4;
    private static final Color HUMAN_COLOR = Color.WHITE;
    private static final Color AI_COLOR = Color.BLACK;

    private Chip(){}

    public static ImageIcon getChipInstance(int type) {
        BufferedImage circleImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = circleImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(type == Utils.HUMAN ? HUMAN_COLOR : AI_COLOR);
        int x = GAP;
        int y = x;
        int width = IMG_WIDTH -2 * x;
        int height = IMG_HEIGHT -2 * y;
        g2.fillOval(x, y, width, height);
        g2.dispose();
        return new ImageIcon(circleImage);
    }

}
