package Game;


import javax.swing.*;
import java.awt.*;

import java.util.LinkedList;
import Deck.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PlayerPanel extends JPanel {
    private Player player;
    private JLabel moneyLabel;
    private JLabel nameLabel;
    private boolean surrender = false;
    private LinkedList<BufferedImage> cardImages;


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


    @Override
    public String getName()
    {
        return player.getName();
    }

    public void updateMoney(int money)
    {
        player.setMoney(player.getMoney() + money);
        moneyLabel.setText("Money: " + player.getMoney()+ " $");
        repaint();
    }


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

    public void setSurrender(boolean surrender)
    {
        this.surrender = surrender;
    }

    public boolean isSurrender()
    {
        return surrender;
    }

    public void clearCards()
    {
        player.clearHand();
        cardImages.clear();
        repaint();
    }

    public Player getPlayer()
    {
        return player;
    }

    public int getCardValue()
    {
        int sum = 0;
        int aceCount = 0;
        for(Card card: player.getHand())
        {
            sum += card.getRank().getValue();
            if (card.getRank() == Rank.ACE)
                aceCount++;
        }

        while (sum > 21 && aceCount > 0)
        {
            sum -= 10;
            aceCount--;
        }
        return sum;
    }

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
