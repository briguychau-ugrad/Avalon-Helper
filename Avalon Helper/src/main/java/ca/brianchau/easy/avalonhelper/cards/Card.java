package ca.brianchau.easy.avalonhelper.cards;

/**
 * Created by Brian on 2014-04-08.
 */
public abstract class Card {
    protected String cardname;
    protected String imageUrl;
    protected boolean good;
    protected Card(boolean good) {
        this.good = good;
    }
    public String getName() {
        return cardname;
    }
}
