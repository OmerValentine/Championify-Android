package net.championify.championify;

public class ChampionCountersWeakAgainst implements Comparable<ChampionCountersWeakAgainst>{

    private String key;
    private double statScore;
    private int winRate;

    public ChampionCountersWeakAgainst(String key, double statScore, int winRate) {
        this.key = key;
        this.statScore = statScore ;
        this.winRate = winRate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public double getStatScore() {
        return statScore;
    }

    public void setStatScore(double statScore) {
        this.statScore = statScore;
    }

    public int getWinRate() {
        return winRate;
    }

    public void setWinRate(int winRate) {
        this.winRate = winRate;
    }


    @Override
    public int compareTo(ChampionCountersWeakAgainst another) {
        if (this.statScore == ((ChampionCountersWeakAgainst) another).statScore)
            return 0;

        else if ((this.statScore) > ((ChampionCountersWeakAgainst) another).statScore)
            return 1;

        else
            return -1;
    }
}
