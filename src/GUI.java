import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Pierre Lejdbring on 9/11/17.
 */
public class GUI extends JFrame{
    final static Dimension BOARDSIZE = new Dimension(800, 800);
    final static Dimension BUTTONSIZE = new Dimension((int)BOARDSIZE.getWidth()/Utils.ROWS,
            (int)BOARDSIZE.getHeight()/Utils.COLS);

    private JLabel aiStatus;
    private JLabel nodes;
    private JLabel currentPlayer;
    String playerString = "WHITE";
    private Othello othello;
    private String nodeString = "";
    private boolean isMultiplayer = false;

    private OthelloButton[][] buttonGrid;

    public GUI(String name, GameState state, Othello othello){
        super(name);
        int choice = JOptionPane.showConfirmDialog(null,
                "Do you wish to play against a friend on the same computer",
                "Game option",
                JOptionPane.YES_NO_OPTION);
        if(choice == 0) {
            isMultiplayer = true;
        }
        setPreferredSize(new Dimension(1000,1000));
        setResizable(false);
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBackground(Color.BLACK);
        outerPanel.setPreferredSize(new Dimension(1000, 1000));
        this.othello = othello;
        buttonGrid = new OthelloButton[Utils.ROWS][Utils.COLS];

        JPanel panel = new JPanel(new GridLayout(Utils.ROWS, Utils.COLS));
        panel.setBackground(new Color(0x0066cc));
        panel.setSize(BOARDSIZE);
        populateButtonPanel(panel, state);
        JPanel statusPanel = new JPanel(new GridLayout(1,2));
        aiStatus = new JLabel("AI status: ");
        nodes = new JLabel("Nodes: " + nodeString);
        aiStatus.setForeground(Color.WHITE);
        nodes.setForeground(Color.WHITE);
        if(!isMultiplayer) {
            statusPanel.add(nodes, 0, 0);
            statusPanel.add(aiStatus,0, 1);
        } else {
            currentPlayer = new JLabel();
            currentPlayer.setText("Current player: " + playerString);
            currentPlayer.setForeground(Color.WHITE);
            statusPanel.add(currentPlayer, 0, 0);
        }
        statusPanel.setBackground(Color.BLACK);
        statusPanel.setPreferredSize(new Dimension(100,100));
        outerPanel.add(panel, BorderLayout.CENTER);
        outerPanel.add(statusPanel, BorderLayout.NORTH);
        add(outerPanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
    public void setNodes(long count) {
        nodeString = String.valueOf(count);
        SwingUtilities.invokeLater(() -> {
            nodes.setText("Nodes: " + nodeString);
        });
    }
    private void populateButtonPanel(JPanel panel, GameState state) {
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

    /**
     * Draws the board based on the given state
     * @param state
     */
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
    }

    public void disableButtons() {
        for(OthelloButton[] b : buttonGrid) {
            for(OthelloButton ob : b) {
                ob.setEnabled(false);
            }
        }
    }

    public void enableButtons() {
         for(OthelloButton[] b : buttonGrid) {
            for(OthelloButton ob : b) {
                ob.setEnabled(true);
            }
        }
    }

    /**
     * Class to represent the buttons
     */
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

    void setPlayerString(String s) {
        playerString = s;
        currentPlayer.setText("Current player: " + playerString);
    }

    private class OthelloListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            OthelloButton button = (OthelloButton)actionEvent.getSource();
            if(isMultiplayer) {
                othello.localMultiplayerButtonPressed(button.getCoordinate());
            } else {
                othello.buttonPressed(button.getCoordinate());
            }
        }
    }
}
