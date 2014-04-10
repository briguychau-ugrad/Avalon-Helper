package ca.brianchau.avalonhelper.cards;

/**
 * Created by Brian on 2014-04-08.
 */
public enum Card {
    MERLIN          (true, true, false, false, false, true, false, "Merlin", "You know the Evil (except Mordred) and must stay hidden."),
    PERCIVAL        (true, true, false, false, false, false, false, "Percival", "You know the two people who appear to be Merlin."),
    MORDRED         (false, false, true, true, false, false, false, "Mordred", "Merlin does not know that you're Evil."),
    MORGANA         (false, false, true, true, true, true, false, "Morgana", "You appear to be Merlin to Percival"),
    ASSASSIN        (false, false, true, true, true, false, false, "Assassin", "You try to assassinate Merlin if the Evil side loses."),
    EVIL_LANCELOT   (false, false, false, true, true, false, true, "Evil Lancelot", "You may switch to become Good Lancelot"),
    GOOD_LANCELOT   (true, true, false, false, false, false, true, "Good Lancelot", "You may switch to become Evil Lancelot"),
    OBERON          (false, false, true, false, true, false, false, "Oberon", "Nobody knows that you are Evil."), // TODO Merlin knows Oberon?
    JHERI           (true, false, false, false, false, false, false, "Jheri", "You know the Lancelots. You appear to be Evil to the Lady of the Lake."),
    RICK            (true, true, false, false, false, false, false, "Rick", "You know who James is."),
    JAMES           (true, true, false, false, false, false, false, "James", "You know who Rick is."),
    INFILTRATOR     (false, true, true, true, true, false, false, "Infiltrator", "You appear to be Good to the Lady of the Lake."),
    MINION          (false, false, true, true, true, false, false, "Minion of Mordred", "You have no special ability"),
    SERVANT         (true, true, false, false, false, false, false, "Loyal Servant of Arthur", "You have no special ability");

    public final boolean good;
    public final boolean appearsGoodToLady;
    public final boolean seesMinions;
    public final boolean showToMinions;
    public final boolean showToMerlin;
    public final boolean showToPercival;
    public final boolean isLancelot;
    public final String name;
    public final String description;

    Card(boolean good, boolean appearsGoodToLady, boolean seesMinions, boolean showToMinions,
         boolean showToMerlin, boolean showToPercival, boolean isLancelot, String name, String description) {
        this.good = good;
        this.appearsGoodToLady = appearsGoodToLady;
        this.seesMinions = seesMinions;
        this.showToMinions = showToMinions;
        this.showToMerlin = showToMerlin;
        this.showToPercival = showToPercival;
        this.isLancelot = isLancelot;
        this.name = name;
        this.description = description;
    }
}
