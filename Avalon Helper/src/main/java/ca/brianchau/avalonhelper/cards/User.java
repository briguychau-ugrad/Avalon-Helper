package ca.brianchau.avalonhelper.cards;

import ca.brianchau.avalonhelper.exceptions.InvalidParameterException;

/**
 * Created by Brian on 2014-04-08.
 */
public class User {
    private String username;
    private int wins;
    private int losses;
    private boolean good;
    private int streak;

    public User(String username) {
        if (username == null || username.equals(""))
            throw new InvalidParameterException("Unable to create a user with no name");
        this.username = username;
        wins = 0;
        losses = 0;
        good = true;
        streak = 0;
    }

    public User(String username, int wins, int losses, boolean good, int streak) {
        if (username == null || username.equals(""))
            throw new InvalidParameterException("Unable to create a user with no name");
        this.username = username;
        this.wins = wins;
        this.losses = losses;
        this.good = good;
        this.streak = streak;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getStreak() {
        return streak;
    }

    public boolean getGood() {
        return good;
    }

    public void setGood(boolean good) {
        this.good = good;
    }

    public void setGameResult(boolean win) {
        if (win) {
            if (streak < 0) {
                streak = 1;
            } else {
                streak++;
            }
            wins++;
        } else {
            if (streak > 0) {
                streak = -1;
            } else {
                streak--;
            }
            losses++;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (this == o)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User u = (User)o;
        return u.username.equals(this.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
