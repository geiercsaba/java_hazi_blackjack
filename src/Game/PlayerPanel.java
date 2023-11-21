package Game;


import javax.swing.*;
import java.awt.*;

import java.util.LinkedList;
import Deck.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * PlayerPanel class is a JPanel that represents a player in the game.
 */
public class PlayerPanel extends JPanel {
    private Player player;
    private JLabel moneyLabel;
    private JLabel nameLabel;
    private boolean surrender = false;
    private LinkedList<BufferedImage> cardImages;


    /**
     * Constructor for PlayerPanel class.
     * @param player Player object that is represented by this panel.
     */
    public PlayerPanel(Player player)
    {
        setPreferredSize(new Dimension(180, 180));
        this.player = player;
        cardImages = new LinkedList<>();
        moneyLabel = new JLabel();
        nameLabel = new JLabel(player.getName());
        updateMoney(player.getMoney());
        add(nameLabel);
        add(moneyLabel);
        setOpaque(false);
    }


    /**
     * getName method returns the name of the player.
     * @return String name of the player.
     */
    @Override
    public String getName()
    {
        return player.getName();
    }

    /**
     * updateMoney method updates the money label of the player and adds the given money to the player's money.(can be negative)
     * @param money int money to be added to the player's money.
     */
    public void updateMoney(int money)
    {
        player.setMoney(player.getMoney() + money);
        moneyLabel.setText("Money: " + player.getMoney()+ " $");
        repaint();
    }


    /**
     * addCard method adds the given card to the player's hand and adds the card image to the panel.
     * @param card Card object to be added to the player's hand.
     */
    public void addCard(Card card) {
        player.addCard(card);
        String imagePath = "src/Game/img/" + card.getSuit() + "_" + card.getRank() + ".png";
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            cardImages.add(image);
            repaint();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * setSurrender method sets the surrender status of the player.
     * @param surrender boolean surrender status of the player.
     */
    public void setSurrender(boolean surrender)
    {
        this.surrender = surrender;
    }

    /**
     * isSurrender method returns the surrender status of the player.
     * @return boolean surrender status of the player.
     */
    public boolean isSurrender()
    {
        return surrender;
    }

    /**
     * clearCards method clears the player's hand and removes the card images from the panel.
     */
    public void clearCards()
    {
        player.clearHand();
        cardImages.clear();
        repaint();
    }

    /**
     * getPlayer method returns the player object that is represented by this panel.
     * @return Player object that is represented by this panel.
     */
    public Player getPlayer()
    {
        return player;
    }

    /**
     * getCardValue method returns the value of the player's hand.
     * @return int value of the player's hand.
     */
    public int getCardValue()
    {
        return player.getHandValue();
    }

    /**
     * paintComponent method paints the card images, money and name labels of the player.
     * @param g Graphics object.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 0;
        int y = 0;

        for (BufferedImage image : cardImages) {
            g.drawImage(image, x, y, 82, 111,this);
            x += 20;
        }

        moneyLabel.setBounds(0, 120, 140, 20);
        nameLabel.setBounds(0, 140, 140, 20);
    }
}
