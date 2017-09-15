import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Pierre Lejdbring on 9/11/17.
 */
public class GUI extends JFrame{
    final static Dimension BOARDSIZE = new Dimension(800, 800);
    final static Dimension BUTTONSIZE = new Dimension((int)BOARDSIZE.getWidth()/8, (int)BOARDSIZE.getHeight()/8);
    private JLabel lblDepth;
    private JLabel lblCount;
    private JLabel lblInfo;
    private JLabel lblTimer;

    private long start;
    private Timer timer;

    private OthelloButton[][] buttonGrid;

    public GUI(String name){
        super(name);
        setPreferredSize(new Dimension(1000,1000));
        setResizable(false);
        JPanel outerPanel = new JPanel(new GridBagLayout());
        outerPanel.setBackground(Color.BLACK);
        outerPanel.setPreferredSize(new Dimension(1000, 1000));
        lblCount = new JLabel();
        lblCount.setText("Nodes checked: 0");
        add(lblCount);
        lblDepth = new JLabel();
        add(lblDepth);
        lblInfo = new JLabel();
        add(lblInfo);
        lblTimer = new JLabel();
        add(lblTimer);

        buttonGrid = new OthelloButton[Utils.ROWS][Utils.COLS];

        JPanel panel = new JPanel(new GridLayout(Utils.ROWS, Utils.COLS));
        panel.setBackground(new Color(0x0066cc));
        panel.setSize(BOARDSIZE);

        for(int i = 0; i < Utils.ROWS; ++i){
            for(int j = 0; j < Utils.COLS; ++j){
                buttonGrid[i][j] = new OthelloButton(new Coordinate(i,j));
                buttonGrid[i][j].setPreferredSize(BUTTONSIZE);
                panel.add(buttonGrid[i][j]);
            }
        }
        outerPanel.add(panel);
        add(outerPanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    private class OthelloButton extends JButton {
        private Coordinate coord;
        protected OthelloButton(Coordinate coord){
            super();
            this.coord = coord;
            this.addActionListener(new OthelloListener());
            setBackground(new Color(0, 99, 33));
        }

        Coordinate getCoordinate() {
            return this.coord;
        }
    }

    private class OthelloListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            OthelloButton button = (OthelloButton)actionEvent.getSource();
            int row = button.getCoordinate().getRow();
            int col = button.getCoordinate().getCol();
            //This code should probably be in engine
            buttonGrid[row][col].setIcon(Chip.getChipInstance(Engine.PlayerType.HUMAN));
            buttonGrid[row][col].setEnabled(false);
            System.out.println("clicked");
        }
    }

    public static void main(String[] args) {
        new GUI("hejsan");
    }
}
