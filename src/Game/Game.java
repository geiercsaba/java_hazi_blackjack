package Game;

import Deck.*;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Game extends JFrame
{
    private LinkedList<PlayerPanel> playerPanels;
    private DealerPanel dealerPanel;
    private Deck deck;
    private JButton btnNewRound;
    private JButton btnHit;
    private JButton btnStand;
    private JButton btnDouble;

    private PlayerPanel activePlayer;
    private JLabel activePlayerLabel;

    private GridBagConstraints c = new GridBagConstraints();


    public Game()
    {
        super("Blackjack");
        setPreferredSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playerPanels = new LinkedList<>();
        dealerPanel = new DealerPanel(new Dealer());
        deck = new Deck();
        setUp();
    }


    private void setUp()
    {
        setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.HORIZONTAL;

        JButton btnAddPlayer = new JButton("New Player");
        c.gridx = 2;
        c.gridy = 0;
        add(btnAddPlayer, c);

        btnAddPlayer.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter your name");
            Player player = new Player(name);
            addPlayer(player);

            if (playerPanels.size() == 3)
                remove(btnAddPlayer);
        });

        c.gridx = 1;
        c.gridy = 0;
        add(dealerPanel, c);

        btnNewRound = new JButton("New Round");
        c.gridx = 1;
        c.gridy = 2;
        add(btnNewRound, c);
        btnNewRound.addActionListener(e -> newRound());

        c.gridy = 1;
        c.gridx = 0;

        btnHit = new JButton("Hit");
        btnStand = new JButton("Stand");
        btnDouble = new JButton("Double");
        activePlayerLabel = new JLabel("Active Player: ");

        btnHit.addActionListener(e -> hit());
        btnStand.addActionListener(e -> stand());
        btnDouble.addActionListener(e -> doubleBet());

    }


    private void addPlayer(Player player)
    {
        PlayerPanel playerPanel = new PlayerPanel(player);
        playerPanels.add(playerPanel);
        c.gridx++;
        add(playerPanel, c);
        revalidate();
        repaint();
    }

    private void newRound()
    {
        if(playerPanels.size() == 0)
        {
            JOptionPane.showMessageDialog(null, "You need at least one player to play");
            return;
        }

        deck = new Deck();
        dealerPanel.clearCards();
        dealerPanel.setHideCard(true);

        for(PlayerPanel panel: playerPanels)
        {
            panel.clearCards();
        }

        remove(btnNewRound);
        repaint();

        activePlayer = playerPanels.getFirst();

        for(PlayerPanel panel: playerPanels) {
            Player player = panel.getPlayer();

            int bet = Integer.parseInt(JOptionPane.showInputDialog("Enter your bet " + player.getName()));

            int playerMoney = player.getMoney();

            if (bet < 0 || bet > playerMoney)
            {
                JOptionPane.showMessageDialog(null, "You can't bet that amount. You play with 0 $ in this round");
                bet = 0;
            }

            player.setCurrentBet(bet);
            panel.updateMoney(-bet);
            panel.addCard(deck.getCard());
            panel.addCard(deck.getCard());
        }

        dealerPanel.addCard(deck.getCard());
        dealerPanel.addCard(deck.getCard());

        c.gridx = 0;
        c.gridy = 2;
        add(btnHit, c);

        c.gridx = 1;
        add(btnStand, c);

        c.gridx = 2;
        add(btnDouble, c);


        activePlayerLabel.setText("Active Player: " + activePlayer.getName());
        c.gridx = 2;
        c.gridy = 3;
        add(activePlayerLabel, c);
        revalidate();
        repaint();

    }

    private void hit()
    {
        activePlayer.addCard(deck.getCard());
        int cards = activePlayer.getCardValue();

        if (cards >= 21)
        {
            nextPlayer();
        }

    }
    private void stand()
    {
        nextPlayer();
    }
    private void doubleBet()
    {
        Player player = activePlayer.getPlayer();
        if (player.getHand().size() != 2){
            JOptionPane.showMessageDialog(null, "You can only double on your first turn");
            return;
        }
        int bet = player.getCurrentBet();
        if (bet > player.getMoney())
        {
            JOptionPane.showMessageDialog(null, "You don't have enough money to double");
            return;
        }
        player.setCurrentBet(bet * 2);
        activePlayer.updateMoney(-bet);
        hit();
        if(player == activePlayer.getPlayer())
        {
            nextPlayer();
        }
    }

    private void nextPlayer()
    {
        if(playerPanels.getLast().equals(activePlayer))
        {
            activePlayer = null;
            activePlayerLabel.setText("Active Player: Dealer");
            remove(btnHit);
            remove(btnStand);
            remove(btnDouble);
            dealerPlay();

            c.gridx = 1;
            c.gridy = 2;
            add(btnNewRound, c);
            repaint();
            return;
        }
        activePlayer = playerPanels.get(playerPanels.indexOf(activePlayer) + 1);
        activePlayerLabel.setText("Active Player: " + activePlayer.getName());
        repaint();
    }

    private void dealerPlay()
    {
        dealerPanel.setHideCard(false);
        while (dealerPanel.getCardValue() < 17)
        {
            dealerPanel.addCard(deck.getCard());
        }

        int dealerCards = dealerPanel.getCardValue();
        for (PlayerPanel panel: playerPanels)
        {
            Player player = panel.getPlayer();
            int currentBet = player.getCurrentBet();

            if(panel.getCardValue() > 21)
            {
                JOptionPane.showMessageDialog(null, player.getName() + " loses");
                continue;
            }


            if(panel.getCardValue() == 21 && player.getHand().size() == 2 && !(dealerPanel.getDealer().getHand().size() == 2 && dealerCards == 21))
            {
                panel.updateMoney((int) (currentBet * 2.5));
                JOptionPane.showMessageDialog(null, "Blackjack! "+ player.getName() + " wins");
                continue;
            }

            if(dealerPanel.getCardValue() > 21)
            {
                panel.updateMoney(currentBet * 2);
                JOptionPane.showMessageDialog(null, player.getName() + " wins");
                continue;
            }

            if (panel.getCardValue() > dealerCards)
            {
                panel.updateMoney(currentBet * 2);
                JOptionPane.showMessageDialog(null, player.getName() + " wins");
                continue;
            }

            if (panel.getCardValue() < dealerCards)
            {
                JOptionPane.showMessageDialog(null, player.getName() + " loses");
                continue;
            }

            panel.updateMoney(currentBet);
            JOptionPane.showMessageDialog(null, "Draw " + player.getName() + " gets his bet back");
        }
    }


    public static void main(String[] args)
    {
        Game game = new Game();

        game.pack();
        game.setVisible(true);
    }

}
