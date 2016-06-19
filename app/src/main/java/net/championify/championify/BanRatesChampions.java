package net.championify.championify;

public class BanRatesChampions {

    private String name, key, role, kDA;
    private int banRate, winRate, playPercent;

    public BanRatesChampions(String name, String key, String role, int banRate, int winRate, int playPercent, String kDA) {
        this.name = name;
        this.key = key;
        this.role = role;
        this.banRate = banRate;
        this.winRate = winRate;
        this.playPercent = playPercent;
        this.kDA = kDA;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getBanRate() {
        return banRate;
    }

    public void setBanRate(int banRate) {
        this.banRate = banRate;
    }

    public int getWinRate() {
        return winRate;
    }

    public void setWinRate(int winRate) {
        this.winRate = winRate;
    }

    public int getPlayPercent() {
        return playPercent;
    }

    public void setPlayPercent(int playPercent) {
        this.playPercent = playPercent;
    }

    public String getkDA() {
        return kDA;
    }

    public void setkDA(String kDA) {
        this.kDA = kDA;
    }
}