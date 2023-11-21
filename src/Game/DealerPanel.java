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
 * DealerPanel class
 * This class is used to display the dealer's hand
 * It extends JPanel
 */
public class DealerPanel extends JPanel {
    private LinkedList<BufferedImage> cardImages;
    private Dealer dealer;
    private JLabel DealerLabel;
    private BufferedImage cardBack;

    private boolean hideCard = true;

    /**
     * Constructor
     * @param dealer
     */
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

    /**
     * addCard method
     * This method adds a card to the dealer's hand
     * @param card
     */
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

    /**
     * getCardValue method
     * This method returns the value of the dealer's hand
     * @return
     */
    public int getCardValue()
    {
        return dealer.getHandValue();
    }

    /**
     * clearCards method
     * This method clears the dealer's hand
     */
    public void clearCards() {
        dealer.clearHand();
        cardImages.clear();
        repaint();
    }

    /**
     * getDealer method
     * This method returns the dealer name
     */
    public Dealer getDealer(){
        return dealer;
    }


    /**
     * setHideCard method
     * This method sets the hideCard variable
     * @param hideCard
     */
    public void setHideCard(boolean hideCard) {
        this.hideCard = hideCard;
        repaint();
    }


    /**
     * paintComponent method
     * This method paints the dealer's hand
     * @param g
     */
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
