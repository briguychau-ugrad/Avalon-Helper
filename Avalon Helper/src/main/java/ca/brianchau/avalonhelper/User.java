package ca.brianchau.avalonhelper;

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
    private int recent;

    public User(String username) {
        username = username.trim();
        if (username == null || username.equals(""))
            throw new InvalidParameterException("Unable to create a user with no name");
        if (username.charAt(0) == '_')
            throw new InvalidParameterException("Username cannot begin with the underscore character.");
        this.username = username;
        wins = 0;
        losses = 0;
        good = true;
        streak = 0;
        recent = 0;
    }

    public User(String username, int wins, int losses, boolean good, int streak, int recent) {
        username = username.trim();
        if (username == null || username.equals(""))
            throw new InvalidParameterException("Unable to create a user with no name");
        if (username.charAt(0) == '_')
            throw new InvalidParameterException("Username cannot begin with the underscore character.");
        this.username = username;
        this.wins = wins;
        this.losses = losses;
        this.good = good;
        this.streak = streak;
        this.recent = recent;
    }

    public String getName() {
        return username;
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

    public int getRecent() {
        return recent;
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
        recent = 0;
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
