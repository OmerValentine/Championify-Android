package net.championify.championify;

public class Champion {

    private String name, title, primaryRole, secondaryRole, key;
    private int icon, image, numOfSkins;
    private long id;

    public Champion(String name, String title, String primaryRole, String secondaryRole, String key, int icon, int image, int numOfSkins) {
        this.name = name;
        this.title = title;
        this.primaryRole = primaryRole;
        this.secondaryRole = secondaryRole;
        this.key = key;
        this.icon = icon;
        this.image = image;
        this.numOfSkins = numOfSkins;
    }

    public Champion(long id, String name, String title, String primaryRole, String secondaryRole, String key, int icon, int image, int numOfSkins) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.primaryRole = primaryRole;
        this.secondaryRole = secondaryRole;
        this.key = key;
        this.icon = icon;
        this.image = image;
        this.numOfSkins = numOfSkins;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrimaryRole() {
        return primaryRole;
    }

    public void setPrimaryRole(String primaryRole) {
        this.primaryRole = primaryRole;
    }

    public String getSecondaryRole() {
        return secondaryRole;
    }

    public void setSecondaryRole(String secondaryRole) {
        this.secondaryRole = secondaryRole;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getNumOfSkins() {
        return numOfSkins;
    }

    public void setNumOfSkins(int numOfSkins) {
        this.numOfSkins = numOfSkins;
    }
}
