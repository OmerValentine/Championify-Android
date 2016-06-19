package net.championify.championify;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class TabsActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private ChampionsDBHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        Locale.setDefault(new Locale("en", "US"));

        handler = new ChampionsDBHandler(this);

        handler.deleteAllChampions();

        //Last Skin Updated (The Three Galaxy Skins (Fizz, Kindred, Shyvana))  // http://na.leagueoflegends.com/en/news/champions-skins)
        //Last Champion Updated (Taliyah)
        //Last DDragon Version Updated 6.10.1
        handler.addChampion(new Champion("Aatrox", "The Darkin Blade", "Fighter", "Tank", "Aatrox", R.drawable.aatrox, R.drawable.aatrox_0, 4));
        handler.addChampion(new Champion("Ahri", "The Nine-Tailed Fox", "Mage", "Assassin", "Ahri", R.drawable.ahri, R.drawable.ahri_0, 7));
        handler.addChampion(new Champion("Akali", "The Fist Of Shadow", "Assassin", "Mage", "Akali", R.drawable.akali, R.drawable.akali_0, 8));
        handler.addChampion(new Champion("Alistar", "The Minotaur", "Tank", "Support", "Alistar", R.drawable.alistar, R.drawable.alistar_0, 9));
        handler.addChampion(new Champion("Amumu", "The Sad Mummy", "Tank", "Mage", "Amumu", R.drawable.amumu, R.drawable.amumu_0, 9));
        handler.addChampion(new Champion("Anivia", "The Cryophoenix", "Mage", "Support", "Anivia", R.drawable.anivia, R.drawable.anivia_0, 7));
        handler.addChampion(new Champion("Annie", "The Dark Child", "Mage", "Support", "Annie", R.drawable.annie, R.drawable.annie_0, 11));
        handler.addChampion(new Champion("Ashe", "The Frost Archer", "Marksman", "N/A", "Ashe", R.drawable.ashe, R.drawable.ashe_0, 8));
        handler.addChampion(new Champion("Aurelion Sol", "The Star Forger", "Mage", "N/A", "AurelionSol", R.drawable.aurelionsol, R.drawable.aurelionsol_0, 2));
        handler.addChampion(new Champion("Azir", "The Emperor Of The Sands", "Mage", "Marksman", "Azir", R.drawable.azir, R.drawable.azir_0, 3));
        handler.addChampion(new Champion("Bard", "The Wandering Caretaker", "Support", "Mage", "Bard", R.drawable.bard, R.drawable.bard_0, 3));
        handler.addChampion(new Champion("Blitzcrank", "The Great Steam Golem", "Tank", "Fighter", "Blitzcrank", R.drawable.blitzcrank, R.drawable.blitzcrank_0, 9));
        handler.addChampion(new Champion("Brand", "The Burning Vengeance", "Mage", "N/A", "Brand", R.drawable.brand, R.drawable.brand_0, 6));
        handler.addChampion(new Champion("Braum", "The Heart Of The Freljord", "Support", "Tank", "Braum", R.drawable.braum, R.drawable.braum_0, 4));
        handler.addChampion(new Champion("Caitlyn", "The Sheriff Of Piltover", "Marksman", "N/A", "Caitlyn", R.drawable.caitlyn, R.drawable.caitlyn_0, 8));
        handler.addChampion(new Champion("Cassiopeia", "The Serpents Embrace", "Mage", "N/A", "Cassiopeia", R.drawable.cassiopeia, R.drawable.cassiopeia_0, 5));
        handler.addChampion(new Champion("Cho'Gath", "The Terror Of The Void", "Tank", "Mage", "Chogath", R.drawable.chogath, R.drawable.chogath_0, 6));
        handler.addChampion(new Champion("Corki", "The Daring Bombardier", "Marksman", "N/A", "Corki", R.drawable.corki, R.drawable.corki_0, 8));
        handler.addChampion(new Champion("Darius", "The Hand Of Noxus", "Fighter", "Tank", "Darius", R.drawable.darius, R.drawable.darius_0, 6));
        handler.addChampion(new Champion("Diana", "Scorn Of The Moon", "Fighter", "Mage", "Diana", R.drawable.diana, R.drawable.diana_0, 4));
        handler.addChampion(new Champion("Dr. Mundo", "The Madman Of Zaun", "Fighter", "Tank", "DrMundo", R.drawable.drmundo, R.drawable.drmundo_0, 9));
        handler.addChampion(new Champion("Draven", "The Glorious Executioner", "Marksman", "N/A", "Draven", R.drawable.draven, R.drawable.draven_0, 7));
        handler.addChampion(new Champion("Ekko", "The Boy Who Shattered Time", "Assassin", "Fighter", "Ekko", R.drawable.ekko, R.drawable.ekko_0, 3));
        handler.addChampion(new Champion("Elise", "The Spider Queen", "Mage", "Fighter", "Elise", R.drawable.elise, R.drawable.elise_0, 4));
        handler.addChampion(new Champion("Evelynn", "The Widowmaker", "Assassin", "Mage", "Evelynn", R.drawable.evelynn, R.drawable.evelynn_0, 5));
        handler.addChampion(new Champion("Ezreal", "The Prodigal Explorer", "Marksman", "Mage", "Ezreal", R.drawable.ezreal, R.drawable.ezreal_0, 9));
        handler.addChampion(new Champion("Fiddlesticks", "The Harbinger Of Doom", "Mage", "Support", "FiddleSticks", R.drawable.fiddlesticks, R.drawable.fiddlesticks_0, 9));
        handler.addChampion(new Champion("Fiora", "The Grand Duelist", "Fighter", "Assassin", "Fiora", R.drawable.fiora, R.drawable.fiora_0, 5));
        handler.addChampion(new Champion("Fizz", "The Tidal Trickster", "Assassin", "Fighter", "Fizz", R.drawable.fizz, R.drawable.fizz_0, 7));
        handler.addChampion(new Champion("Galio", "The Sentinel's Sorrow", "Tank", "Mage", "Galio", R.drawable.galio, R.drawable.galio_0, 6));
        handler.addChampion(new Champion("Gangplank", "The Saltwater Scourge", "Fighter", "Tank", "Gangplank", R.drawable.gangplank, R.drawable.gangplank_0, 8));
        handler.addChampion(new Champion("Garen", "The Might Of Demacia", "Fighter", "Tank", "Garen", R.drawable.garen, R.drawable.garen_0, 8));
        handler.addChampion(new Champion("Gnar", "The Missing Link", "Fighter", "Tank", "Gnar",  R.drawable.gnar, R.drawable.gnar_0, 4));
        handler.addChampion(new Champion("Gragas", "The Rabble Rouser", "Fighter", "Mage", "Gragas", R.drawable.gragas, R.drawable.gragas_0, 10));
        handler.addChampion(new Champion("Graves", "The Outlaw", "Marksman", "N/A", "Graves", R.drawable.graves, R.drawable.graves_0, 7));
        handler.addChampion(new Champion("Hecarim", "The Shadow Of War", "Fighter", "Tank", "Hecarim", R.drawable.hecarim, R.drawable.hecarim_0, 6));
        handler.addChampion(new Champion("Heimerdinger", "The Revered Inventor", "Mage", "Support", "Heimerdinger", R.drawable.heimerdinger, R.drawable.heimerdinger_0, 6));
        handler.addChampion(new Champion("Illaoi", "The Kraken Priestess", "Fighter", "Tank", "Illaoi", R.drawable.illaoi, R.drawable.illaoi_0, 2));
        handler.addChampion(new Champion("Irelia", "The Will Of The Blades", "Fighter", "Assassin", "Irelia", R.drawable.irelia, R.drawable.irelia_0, 6));
        handler.addChampion(new Champion("Janna", "The Storm's Fury", "Support", "Mage", "Janna", R.drawable.janna, R.drawable.janna_0, 7));
        handler.addChampion(new Champion("Jarvan IV", "The Exemplar Of Demacia", "Tank", "Fighter", "JarvanIV", R.drawable.jarvaniv, R.drawable.jarvaniv_0, 7));
        handler.addChampion(new Champion("Jax", "The Grandmaster At Arms", "Fighter", "Assassin", "Jax", R.drawable.jax, R.drawable.jax_0, 10));
        handler.addChampion(new Champion("Jayce", "The Defender Of Tomorrow", "Fighter", "Marksman", "Jayce", R.drawable.jayce, R.drawable.jayce_0, 4));
        handler.addChampion(new Champion("Jhin", "The Virtuoso", "Marksman", "Assassin", "Jhin", R.drawable.jhin, R.drawable.jhin_0, 2));
        handler.addChampion(new Champion("Jinx", "The Loose Cannon", "Marksman", "N/A", "Jinx", R.drawable.jinx, R.drawable.jinx_0, 4));
        handler.addChampion(new Champion("Kalista", "The Spear Of Vengeance", "Marksman", "N/A", "Kalista", R.drawable.kalista, R.drawable.kalista_0, 3));
        handler.addChampion(new Champion("Karma", "The Enlightened One", "Mage", "Support", "Karma", R.drawable.karma, R.drawable.karma_0, 6));
        handler.addChampion(new Champion("Karthus", "The Deathstinger", "Mage", "N/A", "Karthus", R.drawable.karthus, R.drawable.karthus_0, 6));
        handler.addChampion(new Champion("Kassadin", "The Void Walker", "Assassin", "Mage", "Kassadin", R.drawable.kassadin, R.drawable.kassadin_0, 6));
        handler.addChampion(new Champion("Katarina", "The Sinister Blade", "Assassin", "Mage", "Katarina", R.drawable.katarina, R.drawable.katarina_0, 9));
        handler.addChampion(new Champion("Kayle", "The Judicator", "Fighter", "Support", "Kayle", R.drawable.kayle, R.drawable.kayle_0, 9));
        handler.addChampion(new Champion("Kennen", "The Heart Of The Tempest", "Mage", "Marksman", "Kennen", R.drawable.kennen, R.drawable.kennen_0, 7));
        handler.addChampion(new Champion("Kha'Zix", "The Voidreaver", "Assassin", "Fighter", "Khazix", R.drawable.khazix, R.drawable.khazix_0, 4));
        handler.addChampion(new Champion("Kindred", "The Eternal Hunters", "Marksman", "Assassin", "Kindred", R.drawable.kindred, R.drawable.kindred_0, 3));
        handler.addChampion(new Champion("Kog'Maw", "The Mouth Of The Abyss", "Marksman", "Mage", "KogMaw", R.drawable.kogmaw, R.drawable.kogmaw_0, 9));
        handler.addChampion(new Champion("LeBlanc", "The Deceiver", "Assassin", "Mage", "Leblanc", R.drawable.leblanc, R.drawable.leblanc_0, 6));
        handler.addChampion(new Champion("Lee Sin", "The Blind Monk", "Fighter", "Assassin", "LeeSin", R.drawable.leesin, R.drawable.leesin_0, 8));
        handler.addChampion(new Champion("Leona","The Radiant Dawn", "Tank", "Support", "Leona", R.drawable.leona, R.drawable.leona_0, 6));
        handler.addChampion(new Champion("Lissandra", "The Ice Witch", "Mage", "N/A", "Lissandra", R.drawable.lissandra,R.drawable.lissandra_0, 4));
        handler.addChampion(new Champion("Lucian", "The Purifier", "Marksman", "N/A", "Lucian", R.drawable.lucian, R.drawable.lucian_0, 4));
        handler.addChampion(new Champion("Lulu", "The Fae Sorceress", "Support", "Mage", "Lulu", R.drawable.lulu, R.drawable.lulu_0, 6));
        handler.addChampion(new Champion("Lux", "The Lady Of Luminosity", "Mage", "Support", "Lux", R.drawable.lux, R.drawable.lux_0, 7));
        handler.addChampion(new Champion("Malphite", "Shard Of The Monolith", "Tank", "Fighter", "Malphite", R.drawable.malphite, R.drawable.malphite_0, 8));
        handler.addChampion(new Champion("Malzahar", "The Prophet Of The Void", "Mage", "Assassin", "Malzahar", R.drawable.malzahar, R.drawable.malzahar_0, 6));
        handler.addChampion(new Champion("Maokai", "The Twisted Treant", "Tank", "Mage", "Maokai", R.drawable.maokai, R.drawable.maokai_0, 7));
        handler.addChampion(new Champion("Master Yi", "The Wuju Bladesman", "Assassin", "Fighter", "MasterYi", R.drawable.masteryi, R.drawable.masteryi_0, 7));
        handler.addChampion(new Champion("Miss Fortune", "The Bounty Hunter", "Marksman", "N/A", "MissFortune", R.drawable.missfortune, R.drawable.missfortune_0, 9));
        handler.addChampion(new Champion("Mordekaiser", "The Master Of Metal", "Marksman", "Fighter", "Mordekaiser", R.drawable.mordekaiser, R.drawable.mordekaiser_0, 6));
        handler.addChampion(new Champion("Morgana", "Fallen Angel", "Mage", "Support", "Morgana", R.drawable.morgana, R.drawable.morgana_0, 8));
        handler.addChampion(new Champion("Nami", "The Tidecaller", "Support", "Mage", "Nami", R.drawable.nami, R.drawable.nami_0, 4));
        handler.addChampion(new Champion("Nasus", "The Curator Of The Sands", "Fighter", "Tank", "Nasus", R.drawable.nasus, R.drawable.nasus_0, 7));
        handler.addChampion(new Champion("Nautilus", "The Titan Of The Depths", "Tank", "Fighter", "Nautilus", R.drawable.nautilus, R.drawable.nautilus_0, 5));
        handler.addChampion(new Champion("Nidalee", "The Bestial Huntress", "Assassin", "Fighter", "Nidalee", R.drawable.nidalee, R.drawable.nidalee_0, 8));
        handler.addChampion(new Champion("Nocturne", "The Eternal Nightmare", "Assassin", "Fighter", "Nocturne", R.drawable.nocturne, R.drawable.nocturne_0, 7));
        handler.addChampion(new Champion("Nunu", "The Yeti Rider", "Support", "Fighter", "Nunu", R.drawable.nunu, R.drawable.nunu_0, 8));
        handler.addChampion(new Champion("Olaf", "The Berserker", "Fighter", "Tank", "Olaf", R.drawable.olaf, R.drawable.olaf_0, 5));
        handler.addChampion(new Champion("Orianna", "The Lady Of Clockwork", "Mage", "Support", "Orianna", R.drawable.orianna, R.drawable.orianna_0, 7));
        handler.addChampion(new Champion("Pantheon", "The Artisan Of War", "Fighter", "Assassin", "Pantheon", R.drawable.pantheon, R.drawable.pantheon_0, 8));
        handler.addChampion(new Champion("Poppy", "The Iron Ambassador", "Tank", "Fighter", "Poppy", R.drawable.poppy, R.drawable.poppy_0, 7));
        handler.addChampion(new Champion("Quinn", "Demacia's Wings", "Marksman", "Fighter", "Quinn", R.drawable.quinn, R.drawable.quinn_0, 4));
        handler.addChampion(new Champion("Rammus", "The Armordillo", "Tank", "Fighter", "Rammus", R.drawable.rammus,R.drawable.rammus_0, 8));
        handler.addChampion(new Champion("Rek'Sai", "The Void Burrower", "Fighter", "Tank", "RekSai", R.drawable.reksai, R.drawable.reksai_0, 3));
        handler.addChampion(new Champion("Renekton", "The Butcher Of The Sands", "Fighter", "Tank", "Renekton", R.drawable.renekton, R.drawable.renekton_0, 8));
        handler.addChampion(new Champion("Rengar", "The Pridestalker", "Assassin", "Fighter", "Rengar", R.drawable.rengar, R.drawable.rengar_0, 4));
        handler.addChampion(new Champion("Riven", "The Exile", "Fighter", "Assassin", "Riven", R.drawable.riven, R.drawable.riven_0, 7));
        handler.addChampion(new Champion("Rumble", "The Mechanized Menace", "Fighter", "Mage", "Rumble", R.drawable.rumble, R.drawable.rumble_0, 4));
        handler.addChampion(new Champion("Ryze", "The Rogue Mage", "Mage", "Fighter", "Ryze", R.drawable.ryze, R.drawable.ryze_0, 10));
        handler.addChampion(new Champion("Sejuani", "The Winter's Wrath", "Tank", "Fighter", "Sejuani", R.drawable.sejuani, R.drawable.sejuani_0, 7));
        handler.addChampion(new Champion("Shaco", "The Demon Jester", "Assassin", "Fighter", "Shaco", R.drawable.shaco, R.drawable.shaco_0, 8));
        handler.addChampion(new Champion("Shen", "Eye Of Twilight", "Tank", "Fighter", "Shen", R.drawable.shen, R.drawable.shen_0, 7));
        handler.addChampion(new Champion("Shyvana", "The Half-Dragon", "Fighter", "Tank", "Shyvana", R.drawable.shyvana, R.drawable.shyvana_0, 7));
        handler.addChampion(new Champion("Singed", "The Mad Chemist", "Tank", "Fighter", "Singed", R.drawable.singed, R.drawable.singed_0, 9));
        handler.addChampion(new Champion("Sion", "The Undead Champion", "Tank", "Fighter", "Sion", R.drawable.sion, R.drawable.sion_0, 6));
        handler.addChampion(new Champion("Sivir", "The Battle Mistress", "Marksman", "N/A", "Sivir", R.drawable.sivir, R.drawable.sivir_0, 9));
        handler.addChampion(new Champion("Skarner", "The Crystal Vanguard", "Fighter", "Tank", "Skarner", R.drawable.skarner, R.drawable.skarner_0, 5));
        handler.addChampion(new Champion("Sona", "Maven Of The Strings", "Support", "Mage", "Sona", R.drawable.sona, R.drawable.sona_0, 8));
        handler.addChampion(new Champion("Soraka", "The Starchild", "Support", "Mage", "Soraka", R.drawable.soraka, R.drawable.soraka_0, 7));
        handler.addChampion(new Champion("Swain", "The Master Tactician", "Mage", "Fighter", "Swain", R.drawable.swain, R.drawable.swain_0, 4));
        handler.addChampion(new Champion("Syndra", "The Dark Sovereign", "Mage", "Support", "Syndra", R.drawable.syndra, R.drawable.syndra_0, 5));
        handler.addChampion(new Champion("Tahm Kench", "The River King", "Support", "Tank", "TahmKench", R.drawable.tahmkench, R.drawable.tahmkench_0, 3));
        handler.addChampion(new Champion("Taliyah", "The Stoneweaver", "Mage", "Support", "Taliyah", R.drawable.taliyah, R.drawable.taliyah_0, 2));
        handler.addChampion(new Champion("Talon", "The Blade's Shadow", "Assassin", "Fighter", "Talon", R.drawable.talon, R.drawable.talon_0, 5));
        handler.addChampion(new Champion("Taric", "The Gem Knight", "Support", "Fighter", "Taric", R.drawable.taric, R.drawable.taric_0, 4));
        handler.addChampion(new Champion("Teemo", "The Swift Scout", "Marksman", "Assassin", "Teemo", R.drawable.teemo, R.drawable.teemo_0, 9));
        handler.addChampion(new Champion("Thresh", "The Chain Warden", "Support", "Fighter", "Thresh", R.drawable.thresh, R.drawable.thresh_0, 5));
        handler.addChampion(new Champion("Tristana", "The Megling Gunner", "Marksman", "Assassin", "Tristana", R.drawable.tristana, R.drawable.tristana_0, 8));
        handler.addChampion(new Champion("Trundle", "The Troll King", "Fighter", "Tank", "Trundle", R.drawable.trundle, R.drawable.trundle_0, 5));
        handler.addChampion(new Champion("Tryndamere", "The Barbarian King", "Fighter", "Assassin", "Tryndamere", R.drawable.tryndamere, R.drawable.tryndamere_0, 9));
        handler.addChampion(new Champion("Twisted Fate", "The Card Master", "Mage", "N/A", "TwistedFate", R.drawable.twistedfate, R.drawable.twistedfate_0, 10));
        handler.addChampion(new Champion("Twitch", "The Plague Rat", "Marksman", "Assassin", "Twitch", R.drawable.twitch, R.drawable.twitch_0, 8));
        handler.addChampion(new Champion("Udyr", "The Spirit Walker", "Fighter", "Tank", "Udyr", R.drawable.udyr, R.drawable.udyr_0, 5));
        handler.addChampion(new Champion("Urgot", "The Headsman's Pride", "Marksman", "Fighter", "Urgot", R.drawable.urgot, R.drawable.urgot_0, 4));
        handler.addChampion(new Champion("Varus", "The Arrow Of Retribution", "Marksman", "Mage", "Varus", R.drawable.varus, R.drawable.varus_0, 6));
        handler.addChampion(new Champion("Vayne", "The Night Hunter", "Marksman", "Assassin", "Vayne", R.drawable.vayne, R.drawable.vayne_0, 7));
        handler.addChampion(new Champion("Veigar", "The Tiny Master Of Evil", "Mage", "N/A", "Veigar", R.drawable.veigar, R.drawable.veigar_0, 9));
        handler.addChampion(new Champion("Vel'Koz", "The Eye Of Void", "Mage", "N/A", "Velkoz", R.drawable.velkoz, R.drawable.velkoz_0, 4));
        handler.addChampion(new Champion("Vi", "The Piltover Enforcer", "Fighter", "Assassin", "Vi", R.drawable.vi, R.drawable.vi_0, 5));
        handler.addChampion(new Champion("Viktor", "The Machine Herald", "Mage", "N/A", "Viktor", R.drawable.viktor, R.drawable.viktor_0, 4));
        handler.addChampion(new Champion("Vladimir", "The Crimson Reaper", "Mage", "Tank", "Vladimir", R.drawable.vladimir, R.drawable.vladimir_0, 8));
        handler.addChampion(new Champion("Volibear", "The Thunder's Roar", "Fighter", "Tank", "Volibear", R.drawable.volibear, R.drawable.volibear_0, 5));
        handler.addChampion(new Champion("Warwick", "The Blood Hunter", "Fighter", "Tank", "Warwick", R.drawable.warwick, R.drawable.warwick_0, 9));
        handler.addChampion(new Champion("Wukong", "The Monkey King", "Fighter", "Tank", "MonkeyKing", R.drawable.wukong, R.drawable.wukong_0, 6));
        handler.addChampion(new Champion("Xerath", "The Magus Ascendant", "Mage", "Assassin", "Xerath", R.drawable.xerath, R.drawable.xerath_0, 5));
        handler.addChampion(new Champion("Xin Zhao", "The Seneschal Of Demacia", "Fighter", "Assassin", "XinZhao", R.drawable.xinzhao, R.drawable.xinzhao_0, 7));
        handler.addChampion(new Champion("Yasuo", "The Unforgiven", "Fighter", "Assassin", "Yasuo", R.drawable.yasuo, R.drawable.yasuo_0, 4));
        handler.addChampion(new Champion("Yorick", "The Gravedigger", "Fighter", "Mage", "Yorick", R.drawable.yorick, R.drawable.yorick_0, 3));
        handler.addChampion(new Champion("Zac", "The Secret Weapon", "Tank", "Fighter", "Zac", R.drawable.zac, R.drawable.zac_0, 3));
        handler.addChampion(new Champion("Zed", "The Master Of Shadows", "Assassin", "Fighter", "Zed", R.drawable.zed, R.drawable.zed_0, 4));
        handler.addChampion(new Champion("Ziggs", "The Hexplosives Expert", "Mage", "N/A", "Ziggs", R.drawable.ziggs, R.drawable.ziggs_0, 6));
        handler.addChampion(new Champion("Zilean", "The Chronokeeper", "Support", "Mage", "Zilean", R.drawable.zilean, R.drawable.zilean_0, 6));
        handler.addChampion(new Champion("Zyra", "Rise Of The Thorn", "Mage", "Support", "Zyra", R.drawable.zyra, R.drawable.zyra_0, 4));

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(1);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tabs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_donate) {
            Intent donateIntent = new Intent();
            donateIntent.setAction(Intent.ACTION_VIEW);
            donateIntent.addCategory(Intent.CATEGORY_BROWSABLE);
            donateIntent.setData(Uri.parse("https://salt.bountysource.com/teams/championify-android-app"));
            startActivity(donateIntent);
        }

        if (id == R.id.action_spelltimers) {
            Intent in = new Intent(TabsActivity.this, SpellTimersActivity.class);
            startActivity(in);
        }

        if (id == R.id.action_na) {
            GetServerStatus task = new GetServerStatus();
            task.execute("na");
        }

        if (id == R.id.action_euw) {
            GetServerStatus task = new GetServerStatus();
            task.execute("euw");
        }

        if (id == R.id.action_eune) {
            GetServerStatus task = new GetServerStatus();
            task.execute("eune");
        }

        if (id == R.id.action_lan) {
            GetServerStatus task = new GetServerStatus();
            task.execute("lan");
        }

        if (id == R.id.action_las) {
            GetServerStatus task = new GetServerStatus();
            task.execute("las");
        }

        if (id == R.id.action_b) {
            GetServerStatus task = new GetServerStatus();
            task.execute("br");
        }

        if (id == R.id.action_t) {
            GetServerStatus task = new GetServerStatus();
            task.execute("tr");
        }

        if (id == R.id.action_r) {
            GetServerStatus task = new GetServerStatus();
            task.execute("ru");
        }

        if (id == R.id.action_o) {
            GetServerStatus task = new GetServerStatus();
            task.execute("oce");
        }

        if (id == R.id.action_j) {
            GetServerStatus task = new GetServerStatus();
            task.execute("jp");
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {

        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_builds_and_counters, container, false);
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new BuildsAndCountersFragment();
                case 1:
                    return new BansFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Builds & Counters";
                case 1:
                    return "Ban Rates";
            }
            return null;
        }
    }

    public class GetServerStatus extends AsyncTask<String, Void, String> {

        private static final String SERVER_STATUS_URL = "http://status.leagueoflegends.com/shards/";

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;
            StringBuilder stringBuilder = new StringBuilder();

            try {
                URL url = new URL(SERVER_STATUS_URL + params[0]);
                connection = (HttpURLConnection) url.openConnection();

                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return null;
                } else {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String line;

                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            if (result == null) {
                Toast.makeText(TabsActivity.this, "Error getting data", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    JSONObject root = new JSONObject(result);
                    JSONArray services = root.getJSONArray("services");
                    JSONObject client = services.getJSONObject(3);
                    JSONObject game = services.getJSONObject(0);
                    JSONObject store = services.getJSONObject(1);

                    String clientStatus = client.getString("status");
                    String gameStatus = game.getString("status");
                    String storeStatus = store.getString("status");

                    Toast.makeText(TabsActivity.this, "Client Status: " + clientStatus + "\n" + "Game Status: " + gameStatus + "\n" + "Store Status:  " + storeStatus, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
