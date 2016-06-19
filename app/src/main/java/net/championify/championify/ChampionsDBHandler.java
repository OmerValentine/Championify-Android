package net.championify.championify;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class ChampionsDBHandler {

    private ChampionsDBHelper helper;

    public ChampionsDBHandler(Context context) {
        helper = new ChampionsDBHelper(context);
    }

    public void addChampion(Champion champion) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ChampionsDBHelper.CHAMPIONS_NAME, champion.getName());
        values.put(ChampionsDBHelper.CHAMPIONS_TITLE, champion.getTitle());
        values.put(ChampionsDBHelper.CHAMPIONS_PRIMARYROLE, champion.getPrimaryRole());
        values.put(ChampionsDBHelper.CHAMPIONS_SECONDARYROLE, champion.getSecondaryRole());
        values.put(ChampionsDBHelper.CHAMPIONS_KEY, champion.getKey());
        values.put(ChampionsDBHelper.CHAMPIONS_ICON, champion.getIcon());
        values.put(ChampionsDBHelper.CHAMPIONS_IMAGE, champion.getImage());
        values.put(ChampionsDBHelper.CHAMPIONS_SKINS, champion.getNumOfSkins());

        db.insert(ChampionsDBHelper.CHAMPIONS_TABLE, null, values);
        db.close();
    }

    public void deleteAllChampions() {
        SQLiteDatabase db = helper.getWritableDatabase();

        db.delete(ChampionsDBHelper.CHAMPIONS_TABLE, null, null);
        db.close();
    }

    public Cursor getChampions() {
        SQLiteDatabase db = helper.getReadableDatabase();

        return db.rawQuery("SELECT * FROM " + ChampionsDBHelper.CHAMPIONS_TABLE, null);
    }

    public ArrayList<Champion> getAllChampions() {
        ArrayList<Champion> champions = new ArrayList<>();

        SQLiteDatabase db = helper.getReadableDatabase();
        
        Cursor c = db.rawQuery("SELECT * FROM champions", null);

        while(c.moveToNext()) {
            long id = c.getLong(c.getColumnIndex("_id"));
            String name = c.getString(c.getColumnIndex("name"));
            String title = c.getString(c.getColumnIndex("title"));
            String primaryRole = c.getString(c.getColumnIndex("primaryrole"));
            String secondaryRole = c.getString(c.getColumnIndex("secondaryrole"));
            String key = c.getString(c.getColumnIndex("key"));
            int icon = c.getInt(c.getColumnIndex("icon"));
            int image = c.getInt(c.getColumnIndex("image"));
            int skins = c.getInt(c.getColumnIndex("skins"));

            champions.add(new Champion(id, name, title, primaryRole, secondaryRole, key, icon, image, skins));
        }
        db.close();
        return champions;
    }

    public Champion getChampionById(long id) {
        Champion champion = null;

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(ChampionsDBHelper.CHAMPIONS_TABLE, null, ChampionsDBHelper.CHAMPIONS_ID + "=" + id, null, null, null, null);

        if(c.moveToNext()){
            String name = c.getString(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_NAME));
            String title = c.getString(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_TITLE));
            String primaryRole = c.getString(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_PRIMARYROLE));
            String secondaryRole = c.getString(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_SECONDARYROLE));
            String key = c.getString(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_KEY));
            int icon = c.getInt(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_ICON));
            int image = c.getInt(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_IMAGE));
            int skins = c.getInt(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_SKINS));

            champion = new Champion(name, title, primaryRole, secondaryRole, key, icon, image, skins);
        }
        db.close();
        return champion;
    }

    public int getChampionLoadingImage(String key) {
        int image = 0;

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(ChampionsDBHelper.CHAMPIONS_TABLE, null, ChampionsDBHelper.CHAMPIONS_KEY + "='" + key +"'", null, null, null, null);

        if(c.moveToFirst()) {
            image = c.getInt(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_IMAGE));
        }
        db.close();
        return image;
    }

    public int getChampionIconImage(String key) {
        int icon = 0;

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(ChampionsDBHelper.CHAMPIONS_TABLE, null, ChampionsDBHelper.CHAMPIONS_KEY + "='" + key +"'", null, null, null, null);

        if(c.moveToFirst()) {
            icon = c.getInt(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_ICON));
        }
        db.close();
        return icon;
    }

    public String getChampionName(String key) {
        String name = "";

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(ChampionsDBHelper.CHAMPIONS_TABLE, null, ChampionsDBHelper.CHAMPIONS_KEY + "='" + key + "'", null, null, null, null);

        if(c.moveToFirst()) {
            name = c.getString(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_NAME));
        }
        db.close();
        return name;
    }

    public ArrayList<Champion> searchChampionByName(String name) {
        ArrayList<Champion> champions = new ArrayList<>();

        SQLiteDatabase db = helper.getReadableDatabase();

        if(name.equals("")) {
            Cursor c = db.rawQuery("SELECT * FROM champions", null);

            while (c.moveToNext()) {
                long champId = c.getInt(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_ID));
                String champName = c.getString(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_NAME));
                String champTitle = c.getString(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_TITLE));
                String champPrimaryRole = c.getString(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_PRIMARYROLE));
                String champSecondaryRole = c.getString(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_SECONDARYROLE));
                String champKey = c.getString(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_KEY));
                int champIcon = c.getInt(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_ICON));
                int champImage = c.getInt(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_IMAGE));
                int champSkins = c.getInt(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_SKINS));

                champions.add(new Champion(champId, champName, champTitle, champPrimaryRole, champSecondaryRole, champKey, champIcon, champImage, champSkins));
            }
        } else {
            Cursor c = db.rawQuery("SELECT * FROM champions WHERE name = '" + name + "'", null);

            while (c.moveToNext()) {
                long champId = c.getInt(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_ID));
                String champName = c.getString(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_NAME));
                String champTitle = c.getString(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_TITLE));
                String champPrimaryRole = c.getString(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_PRIMARYROLE));
                String champSecondaryRole = c.getString(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_SECONDARYROLE));
                String champKey = c.getString(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_KEY));
                int champIcon = c.getInt(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_ICON));
                int champImage = c.getInt(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_IMAGE));
                int champSkins = c.getInt(c.getColumnIndex(ChampionsDBHelper.CHAMPIONS_SKINS));

                champions.add(new Champion(champId, champName, champTitle, champPrimaryRole, champSecondaryRole, champKey, champIcon, champImage, champSkins));
            }
        }
        db.close();
        return champions;
    }
}
