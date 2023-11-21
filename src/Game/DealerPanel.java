package Game;

import javax.swing.*;
import java.awt.*;

import java.util.LinkedList;
import Deck.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DealerPanel extends JPanel {
    private LinkedList<BufferedImage> cardImages;
    private Dealer dealer;
    private JLabel DealerLabel;
    private BufferedImage cardBack;

    private boolean hideCard = true;

    DealerPanel(Dealer dealer) {
        setPreferredSize(new Dimension(200, 170));
        this.dealer = dealer;
        cardImages = new LinkedList<>();

        DealerLabel = new JLabel("Dealer");
        add(DealerLabel);

        try {
            cardBack = ImageIO.read(new File("src/Game/img/back.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addCard(Card card) {
        dealer.addCard(card);
        String imagePath = "src/Game/img/" + card.getSuit() + "_" + card.getRank() + ".png";
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            cardImages.add(image);
            repaint();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public int getCardValue()
    {
        int sum = 0;
        int aceCount = 0;
        for(Card card: dealer.getHand())
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

    public void clearCards() {
        dealer.clearHand();
        cardImages.clear();
        repaint();
    }

    public Dealer getDealer(){
        return dealer;
    }

    public void setHideCard(boolean hideCard) {
        this.hideCard = hideCard;
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 0;
        int y = 0;

        for (BufferedImage image : cardImages) {
            if(hideCard && image == cardImages.getLast())
                g.drawImage(cardBack, x, y, 82, 111,this);
            else
                g.drawImage(image, x, y, 82, 111,this);
            x += 20;
        }

        DealerLabel.setBounds(0, 120, 140, 20);
    }

}
