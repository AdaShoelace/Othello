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

    private JLabel aiStatus;
    private JLabel humanPoints;
    private JLabel aiPoints;
    private Coordinate lastButtonPressed;

    private OthelloButton[][] buttonGrid;

    public GUI(String name, GameState state){
        super(name);
        setPreferredSize(new Dimension(1000,1000));
        setResizable(false);
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBackground(Color.BLACK);
        outerPanel.setPreferredSize(new Dimension(1000, 1000));
        lastButtonPressed = null;
        buttonGrid = new OthelloButton[Utils.ROWS][Utils.COLS];

        JPanel panel = new JPanel(new GridLayout(Utils.ROWS, Utils.COLS));
        panel.setBackground(new Color(0x0066cc));
        panel.setSize(BOARDSIZE);
        populateButtonPanel(panel, state);
        JPanel statusPanel = new JPanel(new GridLayout(1,3));
        aiStatus = new JLabel("AI status: ");
        aiPoints = new JLabel("AI points: " + state.getAiScore());
        humanPoints = new JLabel("Human points: " + state.getPlayerScore());
        aiStatus.setForeground(Color.WHITE);
        aiPoints.setForeground(Color.WHITE);
        humanPoints.setForeground(Color.WHITE);
        statusPanel.add(humanPoints, 0, 0);
        statusPanel.add(aiPoints, 0, 1);
        statusPanel.add(aiStatus,0, 2);
        statusPanel.setBackground(Color.BLACK);
        statusPanel.setPreferredSize(new Dimension(100,100));
        outerPanel.add(panel, BorderLayout.CENTER);
        outerPanel.add(statusPanel, BorderLayout.NORTH);
        add(outerPanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    void populateButtonPanel(JPanel panel, GameState state) {
        for(int i = 0; i < Utils.ROWS; ++i){
            for(int j = 0; j < Utils.COLS; ++j){
                buttonGrid[i][j] = new OthelloButton(new Coordinate(i,j));
                buttonGrid[i][j].setPreferredSize(BUTTONSIZE);
                if(state.getGrid()[i][j] == Utils.AI) {
                    buttonGrid[i][j].setIcon(Chip.getChipInstance(Utils.AI));
                    buttonGrid[i][j].setDisabledIcon(Chip.getChipInstance(Utils.AI));
                } else if(state.getGrid()[i][j] == Utils.HUMAN) {
                    buttonGrid[i][j].setIcon(Chip.getChipInstance(Utils.HUMAN));
                    buttonGrid[i][j].setDisabledIcon(Chip.getChipInstance(Utils.HUMAN));
                }
                panel.add(buttonGrid[i][j]);
            }
        }
    }

    Coordinate getLastButtonPressed() {
        return lastButtonPressed;
    }

    void draw(GameState state) {
        for(int i = 0; i < Utils.ROWS; i++) {
            for(int j = 0; j < Utils.COLS; j++) {
                if(state.getGrid()[i][j] == Utils.AI) {
                    buttonGrid[i][j].setIcon(Chip.getChipInstance(Utils.AI));
                    buttonGrid[i][j].setDisabledIcon(Chip.getChipInstance(Utils.AI));
                    buttonGrid[i][j].setEnabled(false);
                } else if(state.getGrid()[i][j] == Utils.HUMAN) {
                    buttonGrid[i][j].setIcon(Chip.getChipInstance(Utils.HUMAN));
                    buttonGrid[i][j].setDisabledIcon(Chip.getChipInstance(Utils.HUMAN));
                    buttonGrid[i][j].setEnabled(false);
                }
            }
        }
        pack();
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
            GUI.this.lastButtonPressed = new Coordinate(row, col);
            button.setEnabled(false);
            System.out.println("clicked");
        }
    }
}
