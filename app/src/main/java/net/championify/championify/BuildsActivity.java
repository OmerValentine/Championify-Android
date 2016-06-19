package net.championify.championify;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import static net.championify.championify.BitmapDecoder.decodeSampledBitmapFromResource;

public class BuildsActivity extends AppCompatActivity {

    private ChampionsDBHandler handler;
    private long championId;
    private ImageView imgChampion, imgChampionBig, imgChampionBackground;
    private TextView tvChampionName, tvChampionTitle, tvChampionPrimaryRole, tvChampionSecondaryRole,
            tvAbilitiesOrderWinsA, tvAbilitiesOrderFrequentA,
            tvAbilitiesOrderWinsB, tvAbilitiesOrderFrequentB,
            tvAbilitiesOrderWinsC, tvAbilitiesOrderFrequentC,
            tvAbilitiesOrderWinsD, tvAbilitiesOrderFrequentD,
            tvItemsOrderWinsA, tvItemsOrderFrequentA,
            tvItemsOrderWinsB, tvItemsOrderFrequentB,
            tvItemsOrderWinsC, tvItemsOrderFrequentC,
            tvItemsOrderWinsD, tvItemsOrderFrequentD;
    private ImageView imgItem1A, imgItem2A, imgItem3A, imgItem4A, imgItem5A, imgItem6A,
            imgItem1B, imgItem2B, imgItem3B, imgItem4B, imgItem5B, imgItem6B,
            imgItem1C, imgItem2C, imgItem3C, imgItem4C, imgItem5C, imgItem6C,
            imgItem1D, imgItem2D, imgItem3D, imgItem4D, imgItem5D, imgItem6D,
            imgItem1E, imgItem2E, imgItem3E, imgItem4E, imgItem5E, imgItem6E,
            imgItem1F, imgItem2F, imgItem3F, imgItem4F, imgItem5F, imgItem6F,
            imgItem1G, imgItem2G, imgItem3G, imgItem4G, imgItem5G, imgItem6G,
            imgItem1H, imgItem2H, imgItem3H, imgItem4H, imgItem5H, imgItem6H;
    private ImageView imgSummonerSpell1A, imgSummonerSpell2A, imgSummonerSpell1B, imgSummonerSpell2B,
            imgSummonerSpell1C, imgSummonerSpell2C, imgSummonerSpell1D, imgSummonerSpell2D,
            imgSummonerSpell1E, imgSummonerSpell2E, imgSummonerSpell1F, imgSummonerSpell2F,
            imgSummonerSpell1G, imgSummonerSpell2G, imgSummonerSpell1H, imgSummonerSpell2H;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_builds);

        Locale.setDefault(new Locale("en", "US"));

        handler = new ChampionsDBHandler(this);

        imgChampion = (ImageView) findViewById(R.id.img_champion_actbuilds);
        imgChampionBig = (ImageView) findViewById(R.id.img_championbig_actbuilds);
        imgChampionBackground = (ImageView) findViewById(R.id.img_champion_background_actbuilds);

        tvChampionName = (TextView) findViewById(R.id.tv_champion_name_actbuilds);
        tvChampionTitle = (TextView) findViewById(R.id.tv_champion_title_actbuilds);
        tvChampionPrimaryRole = (TextView) findViewById(R.id.tv_champion_primaryrole_actbuilds);
        tvChampionSecondaryRole = (TextView) findViewById(R.id.tv_champion_secondaryrole_actbuilds);

        tvAbilitiesOrderWinsA = (TextView) findViewById(R.id.tv_abilities_order_wins_a_actbuilds);
        tvAbilitiesOrderFrequentA = (TextView) findViewById(R.id.tv_abilities_order_frequent_a_actbuilds);
        tvAbilitiesOrderWinsB = (TextView) findViewById(R.id.tv_abilities_order_wins_b_actbuilds);
        tvAbilitiesOrderFrequentB = (TextView) findViewById(R.id.tv_abilities_order_frequent_b_actbuilds);
        tvAbilitiesOrderWinsC = (TextView) findViewById(R.id.tv_abilities_order_wins_c_actbuilds);
        tvAbilitiesOrderFrequentC = (TextView) findViewById(R.id.tv_abilities_order_frequent_c_actbuilds);
        tvAbilitiesOrderWinsD = (TextView) findViewById(R.id.tv_abilities_order_wins_d_actbuilds);
        tvAbilitiesOrderFrequentD = (TextView) findViewById(R.id.tv_abilities_order_frequent_d_actbuilds);

        tvItemsOrderWinsA = (TextView) findViewById(R.id.tv_items_wins_a_actbuilds);
        tvItemsOrderFrequentA = (TextView) findViewById(R.id.tv_items_frequent_a_actbuilds);
        tvItemsOrderWinsB = (TextView) findViewById(R.id.tv_items_wins_b_actbuilds);
        tvItemsOrderFrequentB = (TextView) findViewById(R.id.tv_items_frequent_b_actbuilds);
        tvItemsOrderWinsC = (TextView) findViewById(R.id.tv_items_wins_c_actbuilds);
        tvItemsOrderFrequentC = (TextView) findViewById(R.id.tv_items_frequent_c_actbuilds);
        tvItemsOrderWinsD = (TextView) findViewById(R.id.tv_items_wins_d_actbuilds);
        tvItemsOrderFrequentD = (TextView) findViewById(R.id.tv_items_frequent_d_actbuilds);

        imgItem1A = (ImageView) findViewById(R.id.img_item1a);
        imgItem2A = (ImageView) findViewById(R.id.img_item2a);
        imgItem3A = (ImageView) findViewById(R.id.img_item3a);
        imgItem4A = (ImageView) findViewById(R.id.img_item4a);
        imgItem5A = (ImageView) findViewById(R.id.img_item5a);
        imgItem6A = (ImageView) findViewById(R.id.img_item6a);
        imgItem1B = (ImageView) findViewById(R.id.img_item1b);
        imgItem2B = (ImageView) findViewById(R.id.img_item2b);
        imgItem3B = (ImageView) findViewById(R.id.img_item3b);
        imgItem4B = (ImageView) findViewById(R.id.img_item4b);
        imgItem5B = (ImageView) findViewById(R.id.img_item5b);
        imgItem6B = (ImageView) findViewById(R.id.img_item6b);
        imgItem1C = (ImageView) findViewById(R.id.img_item1c);
        imgItem2C = (ImageView) findViewById(R.id.img_item2c);
        imgItem3C = (ImageView) findViewById(R.id.img_item3c);
        imgItem4C = (ImageView) findViewById(R.id.img_item4c);
        imgItem5C = (ImageView) findViewById(R.id.img_item5c);
        imgItem6C = (ImageView) findViewById(R.id.img_item6c);
        imgItem1D = (ImageView) findViewById(R.id.img_item1d);
        imgItem2D = (ImageView) findViewById(R.id.img_item2d);
        imgItem3D = (ImageView) findViewById(R.id.img_item3d);
        imgItem4D = (ImageView) findViewById(R.id.img_item4d);
        imgItem5D = (ImageView) findViewById(R.id.img_item5d);
        imgItem6D = (ImageView) findViewById(R.id.img_item6d);
        imgItem1E = (ImageView) findViewById(R.id.img_item1e);
        imgItem2E = (ImageView) findViewById(R.id.img_item2e);
        imgItem3E = (ImageView) findViewById(R.id.img_item3e);
        imgItem4E = (ImageView) findViewById(R.id.img_item4e);
        imgItem5E = (ImageView) findViewById(R.id.img_item5e);
        imgItem6E = (ImageView) findViewById(R.id.img_item6e);
        imgItem1F = (ImageView) findViewById(R.id.img_item1f);
        imgItem2F = (ImageView) findViewById(R.id.img_item2f);
        imgItem3F = (ImageView) findViewById(R.id.img_item3f);
        imgItem4F = (ImageView) findViewById(R.id.img_item4f);
        imgItem5F = (ImageView) findViewById(R.id.img_item5f);
        imgItem6F = (ImageView) findViewById(R.id.img_item6f);
        imgItem1G = (ImageView) findViewById(R.id.img_item1g);
        imgItem2G = (ImageView) findViewById(R.id.img_item2g);
        imgItem3G = (ImageView) findViewById(R.id.img_item3g);
        imgItem4G = (ImageView) findViewById(R.id.img_item4g);
        imgItem5G = (ImageView) findViewById(R.id.img_item5g);
        imgItem6G = (ImageView) findViewById(R.id.img_item6g);
        imgItem1H = (ImageView) findViewById(R.id.img_item1h);
        imgItem2H = (ImageView) findViewById(R.id.img_item2h);
        imgItem3H = (ImageView) findViewById(R.id.img_item3h);
        imgItem4H = (ImageView) findViewById(R.id.img_item4h);
        imgItem5H = (ImageView) findViewById(R.id.img_item5h);
        imgItem6H = (ImageView) findViewById(R.id.img_item6h);

        imgSummonerSpell1A = (ImageView) findViewById(R.id.img_summonerspell1a);
        imgSummonerSpell2A = (ImageView) findViewById(R.id.img_summonerspell2a);
        imgSummonerSpell1B = (ImageView) findViewById(R.id.img_summonerspell1b);
        imgSummonerSpell2B = (ImageView) findViewById(R.id.img_summonerspell2b);
        imgSummonerSpell1C = (ImageView) findViewById(R.id.img_summonerspell1c);
        imgSummonerSpell2C = (ImageView) findViewById(R.id.img_summonerspell2c);
        imgSummonerSpell1D = (ImageView) findViewById(R.id.img_summonerspell1d);
        imgSummonerSpell2D = (ImageView) findViewById(R.id.img_summonerspell2d);
        imgSummonerSpell1E = (ImageView) findViewById(R.id.img_summonerspell1e);
        imgSummonerSpell2E = (ImageView) findViewById(R.id.img_summonerspell2e);
        imgSummonerSpell1F = (ImageView) findViewById(R.id.img_summonerspell1f);
        imgSummonerSpell2F = (ImageView) findViewById(R.id.img_summonerspell2f);
        imgSummonerSpell1G = (ImageView) findViewById(R.id.img_summonerspell1g);
        imgSummonerSpell2G = (ImageView) findViewById(R.id.img_summonerspell2g);
        imgSummonerSpell1H = (ImageView) findViewById(R.id.img_summonerspell1h);
        imgSummonerSpell2H = (ImageView) findViewById(R.id.img_summonerspell2h);

        Intent in = getIntent();
        championId = in.getLongExtra("champion_id_key", -1);

        if(championId != -1) {
            Champion champion = handler.getChampionById(championId);

            String key = champion.getKey();

            tvChampionName.setText(champion.getName());
            tvChampionTitle.setText(champion.getTitle());
            tvChampionPrimaryRole.setText(String.valueOf(champion.getPrimaryRole() + " / "));
            tvChampionSecondaryRole.setText(champion.getSecondaryRole());
            imgChampion.setImageBitmap(decodeSampledBitmapFromResource(getResources(), champion.getIcon(), 150, 150));

            GetChampionsMostWinsAbilitiesOrder taskFrequentAbilities = new GetChampionsMostWinsAbilitiesOrder();
            taskFrequentAbilities.execute(key);

            GetChampionsMostFrequentAbilitiesOrder taskWinsAbilities = new GetChampionsMostFrequentAbilitiesOrder();
            taskWinsAbilities.execute(key);

            GetChampionsMostWinsItemsOrder taskWinsItems = new GetChampionsMostWinsItemsOrder();
            taskWinsItems.execute(key);

            GetChampionsMostFrequentItemsOrder taskFrequentItems = new GetChampionsMostFrequentItemsOrder();
            taskFrequentItems.execute(key);

            GetChampionsMostWinsSummonerSpells taskWinsSummonerSpells = new GetChampionsMostWinsSummonerSpells();
            taskWinsSummonerSpells.execute(key);

            GetChampionsMostFrequentSummonerSpells taskFrequentSummonerSpells = new GetChampionsMostFrequentSummonerSpells();
            taskFrequentSummonerSpells.execute(key);

            final String image_url = "http://ddragon.leagueoflegends.com/cdn/img/champion/splash/" + key + "_0.jpg";
            new Thread(new Runnable() {
                public void run() {
                    try {
                        final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(image_url).getContent());
                        imgChampionBig.post(new Runnable() {
                            public void run() {
                                if(bitmap !=null) {
                                    imgChampionBig.setImageBitmap(bitmap);
                                }
                            }
                        });
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            final String image_background_url = "http://ddragon.leagueoflegends.com/cdn/img/champion/loading/" + key + "_0.jpg";
            new Thread(new Runnable() {
                public void run() {
                    try {
                        final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(image_background_url).getContent());
                        imgChampionBackground.post(new Runnable() {
                            public void run() {
                                if(bitmap !=null) {
                                    imgChampionBackground.setImageBitmap(bitmap);
                                }
                            }
                        });
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        ObjectAnimator fadeInChampBackgroundImage = ObjectAnimator.ofFloat(imgChampionBackground, "alpha", 0.2f);
        fadeInChampBackgroundImage.setDuration(2500);
        fadeInChampBackgroundImage.start();

        ObjectAnimator fadeInChampImageBig = ObjectAnimator.ofFloat(imgChampionBig, "alpha", 1);
        fadeInChampImageBig.setDuration(2500);
        fadeInChampImageBig.start();

        ObjectAnimator slideChampIcon = ObjectAnimator.ofFloat(imgChampion, "translationX", -200f, 0f);
        slideChampIcon.setDuration(2500);
        slideChampIcon.start();
        ObjectAnimator fadeInChampIcon = ObjectAnimator.ofFloat(imgChampion, "alpha", 1);
        fadeInChampIcon.setDuration(2500);
        fadeInChampIcon.start();
    }

    public class GetChampionsMostWinsAbilitiesOrder extends AsyncTask<String, Void, String> {

        private final String ABILITIES_ORDER_URL_START = "http://api.champion.gg/champion/";
        private final String ABILITIES_ORDER_URL_END = "/skills/mostWins?api_key=" + getResources().getString(R.string.championgg);

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader =  null;
            StringBuilder stringBuilder = new StringBuilder();

            try {
                URL url = new URL(ABILITIES_ORDER_URL_START + params[0] + ABILITIES_ORDER_URL_END);
                connection = (HttpURLConnection) url.openConnection();

                if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return null;
                }
                else {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String line;

                    while((line = reader.readLine()) != null) {
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
            if(result == null) {
                Toast.makeText(BuildsActivity.this, getResources().getString(R.string.championgg_error_message), Toast.LENGTH_SHORT).show();
            }
            else {
                try {
                    JSONArray root = new JSONArray(result);

                    JSONObject obj = root.getJSONObject(0);
                    JSONArray order = obj.getJSONArray("order");
                    String role = obj.getString("role");

                    tvAbilitiesOrderWinsA.setText("");
                    tvItemsOrderWinsA.setText(String.valueOf("Most Wins " + role + ":"));

                    String s = order.getString(0) + order.getString(1) + order.getString(2) + order.getString(3) + order.getString(4) + order.getString(5) + order.getString(6) + order.getString(7);

                    int num1 = 0;
                    int num2 = 0;
                    int num3 = 0;

                    for (int j = 0; j < s.length(); j++) {
                        if (s.charAt(j) == 'Q') {
                            num1++;
                        }

                        if (s.charAt(j) == 'W') {
                            num2++;
                        }

                        if (s.charAt(j) == 'E') {
                            num3++;
                        }
                    }

                    if (num1 > num2 && num1 > num3) {
                        if (num2 > num3) {
                            tvAbilitiesOrderWinsA.setText(String.valueOf("[Q>" + "W>" + "E]"));
                        } else {
                            tvAbilitiesOrderWinsA.setText(String.valueOf("[Q>" + "E>" + "W]"));
                        }
                    }

                    else if (num2 > num1 && num2 > num3) {
                        if (num1 > num3) {
                            tvAbilitiesOrderWinsA.setText(String.valueOf("[W>" + "Q>" + "E]"));
                        } else {
                            tvAbilitiesOrderWinsA.setText(String.valueOf("[W>" + "E>" + "Q]"));
                        }
                    }

                    else if (num3 > num1 && num3 > num2) {
                        if (num1 > num2) {
                            tvAbilitiesOrderWinsA.setText(String.valueOf("[E>" + "Q>" + "W]"));
                        } else {
                            tvAbilitiesOrderWinsA.setText(String.valueOf("[E>" + "W>" + "Q]"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONArray root = new JSONArray(result);

                    JSONObject objTwo = root.getJSONObject(1);
                    JSONArray orderTwo = objTwo.getJSONArray("order");
                    String roleTwo = objTwo.getString("role");

                    tvAbilitiesOrderWinsB.setText("");
                    tvItemsOrderWinsB.setText(String.valueOf("Most Wins " + roleTwo + ":"));

                    String s = orderTwo.getString(0) + orderTwo.getString(1) + orderTwo.getString(2) + orderTwo.getString(3) + orderTwo.getString(4) + orderTwo.getString(5) + orderTwo.getString(6) + orderTwo.getString(7);

                    int num1 = 0;
                    int num2 = 0;
                    int num3 = 0;

                    for (int j = 0; j < s.length(); j++) {
                        if (s.charAt(j) == 'Q') {
                            num1++;
                        }

                        if (s.charAt(j) == 'W') {
                            num2++;
                        }

                        if (s.charAt(j) == 'E') {
                            num3++;
                        }
                    }

                    if (num1 > num2 && num1 > num3) {
                        if (num2 > num3) {
                            tvAbilitiesOrderWinsB.setText(String.valueOf("[Q>" + "W>" + "E]"));
                        } else {
                            tvAbilitiesOrderWinsB.setText(String.valueOf("[Q>" + "E>" + "W]"));
                        }
                    }

                    else if (num2 > num1 && num2 > num3) {
                        if (num1 > num3) {
                            tvAbilitiesOrderWinsB.setText(String.valueOf("[W>" + "Q>" + "E]"));
                        } else {
                            tvAbilitiesOrderWinsB.setText(String.valueOf("[W>" + "E>" + "Q]"));
                        }
                    }

                    else if (num3 > num1 && num3 > num2) {
                        if (num1 > num2) {
                            tvAbilitiesOrderWinsB.setText(String.valueOf("[E>" + "Q>" + "W]"));
                        } else {
                            tvAbilitiesOrderWinsB.setText(String.valueOf("[E>" + "W>" + "Q]"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONArray root = new JSONArray(result);

                    JSONObject objThree = root.getJSONObject(2);
                    JSONArray orderThree = objThree.getJSONArray("order");
                    String roleThree = objThree.getString("role");

                    tvAbilitiesOrderWinsC.setText("");
                    tvItemsOrderWinsC.setText(String.valueOf("Most Wins " + roleThree + ":"));

                    String s = orderThree.getString(0) + orderThree.getString(1) + orderThree.getString(2) + orderThree.getString(3) + orderThree.getString(4) + orderThree.getString(5) + orderThree.getString(6) + orderThree.getString(7);

                    int num1 = 0;
                    int num2 = 0;
                    int num3 = 0;

                    for (int j = 0; j < s.length(); j++) {
                        if (s.charAt(j) == 'Q') {
                            num1++;
                        }

                        if (s.charAt(j) == 'W') {
                            num2++;
                        }

                        if (s.charAt(j) == 'E') {
                            num3++;
                        }
                    }

                    if (num1 > num2 && num1 > num3) {
                        if (num2 > num3) {
                            tvAbilitiesOrderWinsC.setText(String.valueOf("[Q>" + "W>" + "E]"));
                        } else {
                            tvAbilitiesOrderWinsC.setText(String.valueOf("[Q>" + "E>" + "W]"));
                        }
                    }

                    else if (num2 > num1 && num2 > num3) {
                        if (num1 > num3) {
                            tvAbilitiesOrderWinsC.setText(String.valueOf("[W>" + "Q>" + "E]"));
                        } else {
                            tvAbilitiesOrderWinsC.setText(String.valueOf("[W>" + "E>" + "Q]"));
                        }
                    }

                    else if (num3 > num1 && num3 > num2) {
                        if (num1 > num2) {
                            tvAbilitiesOrderWinsC.setText(String.valueOf("[E>" + "Q>" + "W]"));
                        } else {
                            tvAbilitiesOrderWinsC.setText(String.valueOf("[E>" + "W>" + "Q]"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONArray root = new JSONArray(result);

                    JSONObject objFour = root.getJSONObject(3);
                    JSONArray orderFour = objFour.getJSONArray("order");
                    String roleFour = objFour.getString("role");

                    tvAbilitiesOrderWinsD.setText("");
                    tvItemsOrderWinsD.setText(String.valueOf("Most Wins " + roleFour + ":"));

                    String s = orderFour.getString(0) + orderFour.getString(1) + orderFour.getString(2) + orderFour.getString(3) + orderFour.getString(4) + orderFour.getString(5) + orderFour.getString(6) + orderFour.getString(7);

                    int num1 = 0;
                    int num2 = 0;
                    int num3 = 0;

                    for (int j = 0; j < s.length(); j++) {
                        if (s.charAt(j) == 'Q') {
                            num1++;
                        }

                        if (s.charAt(j) == 'W') {
                            num2++;
                        }

                        if (s.charAt(j) == 'E') {
                            num3++;
                        }
                    }

                    if (num1 > num2 && num1 > num3) {
                        if (num2 > num3) {
                            tvAbilitiesOrderWinsD.setText(String.valueOf("[Q>" + "W>" + "E]"));
                        } else {
                            tvAbilitiesOrderWinsD.setText(String.valueOf("[Q>" + "E>" + "W]"));
                        }
                    }

                    else if (num2 > num1 && num2 > num3) {
                        if (num1 > num3) {
                            tvAbilitiesOrderWinsD.setText(String.valueOf("[W>" + "Q>" + "E]"));
                        } else {
                            tvAbilitiesOrderWinsD.setText(String.valueOf("[W>" + "E>" + "Q]"));
                        }
                    }

                    else if (num3 > num1 && num3 > num2) {
                        if (num1 > num2) {
                            tvAbilitiesOrderWinsD.setText(String.valueOf("[E>" + "Q>" + "W]"));
                        } else {
                            tvAbilitiesOrderWinsD.setText(String.valueOf("[E>" + "W>" + "Q]"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class GetChampionsMostFrequentAbilitiesOrder extends AsyncTask<String, Void, String> {

        private final String ABILITIES_ORDER_URL_START = "http://api.champion.gg/champion/";
        private final String ABILITIES_ORDER_URL_END = "/skills/mostPopular?api_key=" + getResources().getString(R.string.championgg);

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader =  null;
            StringBuilder stringBuilder = new StringBuilder();

            try {
                URL url = new URL(ABILITIES_ORDER_URL_START + params[0] + ABILITIES_ORDER_URL_END);
                connection = (HttpURLConnection) url.openConnection();

                if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return null;
                }
                else {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String line;

                    while((line = reader.readLine()) != null) {
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
            if(result == null) {
                Toast.makeText(BuildsActivity.this, getResources().getString(R.string.championgg_error_message), Toast.LENGTH_SHORT).show();
            }
            else {
                try {
                    JSONArray root = new JSONArray(result);

                    JSONObject obj = root.getJSONObject(0);
                    JSONArray order = obj.getJSONArray("order");
                    String role = obj.getString("role");

                    tvAbilitiesOrderFrequentA.setText("");
                    tvItemsOrderFrequentA.setText(String.valueOf("Most Frequent " + role + ":"));

                    String s = order.getString(0) + order.getString(1) + order.getString(2) + order.getString(3) + order.getString(4) + order.getString(5) + order.getString(6) + order.getString(7);

                    int num1 = 0;
                    int num2 = 0;
                    int num3 = 0;

                    for (int j = 0; j < s.length(); j++) {
                        if (s.charAt(j) == 'Q') {
                            num1++;
                        }

                        if (s.charAt(j) == 'W') {
                            num2++;
                        }

                        if (s.charAt(j) == 'E') {
                            num3++;
                        }
                    }

                    if (num1 > num2 && num1 > num3) {
                        if (num2 > num3) {
                            tvAbilitiesOrderFrequentA.setText(String.valueOf("[Q>" + "W>" + "E]"));
                        } else {
                            tvAbilitiesOrderFrequentA.setText(String.valueOf("[Q>" + "E>" + "W]"));
                        }
                    }

                    else if (num2 > num1 && num2 > num3) {
                        if (num1 > num3) {
                            tvAbilitiesOrderFrequentA.setText(String.valueOf("[W>" + "Q>" + "E]"));
                        } else {
                            tvAbilitiesOrderFrequentA.setText(String.valueOf("[W>" + "E>" + "Q]"));
                        }
                    }

                    else if (num3 > num1 && num3 > num2) {
                        if (num1 > num2) {
                            tvAbilitiesOrderFrequentA.setText(String.valueOf("[E>" + "Q>" + "W]"));
                        } else {
                            tvAbilitiesOrderFrequentA.setText(String.valueOf("[E>" + "W>" + "Q]"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONArray root = new JSONArray(result);

                    JSONObject objTwo = root.getJSONObject(1);
                    JSONArray orderTwo = objTwo.getJSONArray("order");
                    String roleTwo = objTwo.getString("role");

                    tvAbilitiesOrderFrequentB.setText("");
                    tvItemsOrderFrequentB.setText(String.valueOf("Most Frequent " + roleTwo + ":"));

                    String s = orderTwo.getString(0) + orderTwo.getString(1) + orderTwo.getString(2) + orderTwo.getString(3) + orderTwo.getString(4) + orderTwo.getString(5) + orderTwo.getString(6) + orderTwo.getString(7);

                    int num1 = 0;
                    int num2 = 0;
                    int num3 = 0;

                    for (int j = 0; j < s.length(); j++) {
                        if (s.charAt(j) == 'Q') {
                            num1++;
                        }

                        if (s.charAt(j) == 'W') {
                            num2++;
                        }

                        if (s.charAt(j) == 'E') {
                            num3++;
                        }
                    }

                    if (num1 > num2 && num1 > num3) {
                        if (num2 > num3) {
                            tvAbilitiesOrderFrequentB.setText(String.valueOf("[Q>" + "W>" + "E]"));
                        } else {
                            tvAbilitiesOrderFrequentB.setText(String.valueOf("[Q>" + "E>" + "W]"));
                        }
                    }

                    else if (num2 > num1 && num2 > num3) {
                        if (num1 > num3) {
                            tvAbilitiesOrderFrequentB.setText(String.valueOf("[W>" + "Q>" + "E]"));
                        } else {
                            tvAbilitiesOrderFrequentB.setText(String.valueOf("[W>" + "E>" + "Q]"));
                        }
                    }

                    else if (num3 > num1 && num3 > num2) {
                        if (num1 > num2) {
                            tvAbilitiesOrderFrequentB.setText(String.valueOf("[E>" + "Q>" + "W]"));
                        } else {
                            tvAbilitiesOrderFrequentB.setText(String.valueOf("[E>" + "W>" + "Q]"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONArray root = new JSONArray(result);

                    JSONObject objThree = root.getJSONObject(2);
                    JSONArray orderThree = objThree.getJSONArray("order");
                    String roleThree = objThree.getString("role");

                    tvAbilitiesOrderFrequentC.setText("");
                    tvItemsOrderFrequentC.setText(String.valueOf("Most Frequent " + roleThree + ":"));

                    String s = orderThree.getString(0) + orderThree.getString(1) + orderThree.getString(2) + orderThree.getString(3) + orderThree.getString(4) + orderThree.getString(5) + orderThree.getString(6) + orderThree.getString(7);

                    int num1 = 0;
                    int num2 = 0;
                    int num3 = 0;

                    for (int j = 0; j < s.length(); j++) {
                        if (s.charAt(j) == 'Q') {
                            num1++;
                        }

                        if (s.charAt(j) == 'W') {
                            num2++;
                        }

                        if (s.charAt(j) == 'E') {
                            num3++;
                        }
                    }

                    if (num1 > num2 && num1 > num3) {
                        if (num2 > num3) {
                            tvAbilitiesOrderFrequentC.setText(String.valueOf("[Q>" + "W>" + "E]"));
                        } else {
                            tvAbilitiesOrderFrequentC.setText(String.valueOf("[Q>" + "E>" + "W]"));
                        }
                    }

                    else if (num2 > num1 && num2 > num3) {
                        if (num1 > num3) {
                            tvAbilitiesOrderFrequentC.setText(String.valueOf("[W>" + "Q>" + "E]"));
                        } else {
                            tvAbilitiesOrderFrequentC.setText(String.valueOf("[W>" + "E>" + "Q]"));
                        }
                    }

                    else if (num3 > num1 && num3 > num2) {
                        if (num1 > num2) {
                            tvAbilitiesOrderFrequentC.setText(String.valueOf("[E>" + "Q>" + "W]"));
                        } else {
                            tvAbilitiesOrderFrequentC.setText(String.valueOf("[E>" + "W>" + "Q]"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONArray root = new JSONArray(result);

                    JSONObject objFour = root.getJSONObject(3);
                    JSONArray orderFour = objFour.getJSONArray("order");
                    String roleFour = objFour.getString("role");

                    tvAbilitiesOrderFrequentD.setText("");
                    tvItemsOrderFrequentD.setText(String.valueOf("Most Frequent " + roleFour + ":"));

                    String s = orderFour.getString(0) + orderFour.getString(1) + orderFour.getString(2) + orderFour.getString(3) + orderFour.getString(4) + orderFour.getString(5) + orderFour.getString(6) + orderFour.getString(7);

                    int num1 = 0;
                    int num2 = 0;
                    int num3 = 0;

                    for (int j = 0; j < s.length(); j++) {
                        if (s.charAt(j) == 'Q') {
                            num1++;
                        }

                        if (s.charAt(j) == 'W') {
                            num2++;
                        }

                        if (s.charAt(j) == 'E') {
                            num3++;
                        }
                    }

                    if (num1 > num2 && num1 > num3) {
                        if (num2 > num3) {
                            tvAbilitiesOrderFrequentD.setText(String.valueOf("[Q>" + "W>" + "E]"));
                        } else {
                            tvAbilitiesOrderFrequentD.setText(String.valueOf("[Q>" + "E>" + "W]"));
                        }
                    }

                    else if (num2 > num1 && num2 > num3) {
                        if (num1 > num3) {
                            tvAbilitiesOrderFrequentD.setText(String.valueOf("[W>" + "Q>" + "E]"));
                        } else {
                            tvAbilitiesOrderFrequentD.setText(String.valueOf("[W>" + "E>" + "Q]"));
                        }
                    }

                    else if (num3 > num1 && num3 > num2) {
                        if (num1 > num2) {
                            tvAbilitiesOrderFrequentD.setText(String.valueOf("[E>" + "Q>" + "W]"));
                        } else {
                            tvAbilitiesOrderFrequentD.setText(String.valueOf("[E>" + "W>" + "Q]"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public class GetChampionsMostWinsItemsOrder extends AsyncTask<String, Void, String> {

        private final String ABILITIES_ORDER_URL_START = "http://api.champion.gg/champion/";
        private final String ABILITIES_ORDER_URL_END = "/items/finished/mostWins?api_key=" + getResources().getString(R.string.championgg);

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader =  null;
            StringBuilder stringBuilder = new StringBuilder();

            try {
                URL url = new URL(ABILITIES_ORDER_URL_START + params[0] + ABILITIES_ORDER_URL_END);
                connection = (HttpURLConnection) url.openConnection();

                if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return null;
                }
                else {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String line;

                    while((line = reader.readLine()) != null) {
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
            if(result == null) {
                Toast.makeText(BuildsActivity.this, getResources().getString(R.string.championgg_error_message), Toast.LENGTH_SHORT).show();
            }
            else {
                try {
                    JSONArray root = new JSONArray(result);

                    JSONObject obj = root.getJSONObject(0);
                    JSONArray itemsOne = obj.getJSONArray("items");

                    int item1 = itemsOne.getInt(0);
                    int item2 = itemsOne.getInt(1);
                    int item3 = itemsOne.getInt(2);
                    int item4 = itemsOne.getInt(3);
                    int item5 = itemsOne.getInt(4);
                    int item6 = itemsOne.getInt(5);

                    final String item1_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item1) + ".png";
                    final String item2_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item2) + ".png";
                    final String item3_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item3) + ".png";
                    final String item4_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item4) + ".png";
                    final String item5_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item5) + ".png";
                    final String item6_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item6) + ".png";

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item1_url).getContent());
                                imgItem1A.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem1A.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item2_url).getContent());
                                imgItem2A.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem2A.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item3_url).getContent());
                                imgItem3A.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem3A.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item4_url).getContent());
                                imgItem4A.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem4A.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item5_url).getContent());
                                imgItem5A.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem5A.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item6_url).getContent());
                                imgItem6A.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem6A.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONArray root = new JSONArray(result);

                    JSONObject obj = root.getJSONObject(1);
                    JSONArray itemsTwo = obj.getJSONArray("items");

                    int item1 = itemsTwo.getInt(0);
                    int item2 = itemsTwo.getInt(1);
                    int item3 = itemsTwo.getInt(2);
                    int item4 = itemsTwo.getInt(3);
                    int item5 = itemsTwo.getInt(4);
                    int item6 = itemsTwo.getInt(5);

                    final String item1_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item1) + ".png";
                    final String item2_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item2) + ".png";
                    final String item3_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item3) + ".png";
                    final String item4_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item4) + ".png";
                    final String item5_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item5) + ".png";
                    final String item6_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item6) + ".png";

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item1_url).getContent());
                                imgItem1C.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem1C.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item2_url).getContent());
                                imgItem2C.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem2C.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item3_url).getContent());
                                imgItem3C.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem3C.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item4_url).getContent());
                                imgItem4C.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem4C.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item5_url).getContent());
                                imgItem5C.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem5C.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item6_url).getContent());
                                imgItem6C.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem6C.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONArray root = new JSONArray(result);

                    JSONObject obj = root.getJSONObject(2);
                    JSONArray itemsThree = obj.getJSONArray("items");

                    int item1 = itemsThree.getInt(0);
                    int item2 = itemsThree.getInt(1);
                    int item3 = itemsThree.getInt(2);
                    int item4 = itemsThree.getInt(3);
                    int item5 = itemsThree.getInt(4);
                    int item6 = itemsThree.getInt(5);

                    final String item1_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item1) + ".png";
                    final String item2_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item2) + ".png";
                    final String item3_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item3) + ".png";
                    final String item4_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item4) + ".png";
                    final String item5_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item5) + ".png";
                    final String item6_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item6) + ".png";

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item1_url).getContent());
                                imgItem1E.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem1E.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item2_url).getContent());
                                imgItem2E.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem2E.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item3_url).getContent());
                                imgItem3E.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem3E.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item4_url).getContent());
                                imgItem4E.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem4E.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item5_url).getContent());
                                imgItem5E.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem5E.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item6_url).getContent());
                                imgItem6E.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem6E.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONArray root = new JSONArray(result);

                    JSONObject obj = root.getJSONObject(3);
                    JSONArray itemsFour = obj.getJSONArray("items");

                    int item1 = itemsFour.getInt(0);
                    int item2 = itemsFour.getInt(1);
                    int item3 = itemsFour.getInt(2);
                    int item4 = itemsFour.getInt(3);
                    int item5 = itemsFour.getInt(4);
                    int item6 = itemsFour.getInt(5);

                    final String item1_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item1) + ".png";
                    final String item2_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item2) + ".png";
                    final String item3_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item3) + ".png";
                    final String item4_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item4) + ".png";
                    final String item5_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item5) + ".png";
                    final String item6_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item6) + ".png";

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item1_url).getContent());
                                imgItem1G.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem1G.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item2_url).getContent());
                                imgItem2G.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem2G.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item3_url).getContent());
                                imgItem3G.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem3G.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item4_url).getContent());
                                imgItem4G.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem4G.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item5_url).getContent());
                                imgItem5G.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem5G.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item6_url).getContent());
                                imgItem6G.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem6G.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class GetChampionsMostFrequentItemsOrder extends AsyncTask<String, Void, String> {

        private final String ABILITIES_ORDER_URL_START = "http://api.champion.gg/champion/";
        private final String ABILITIES_ORDER_URL_END = "/items/finished/mostPopular?api_key=" + getResources().getString(R.string.championgg);

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader =  null;
            StringBuilder stringBuilder = new StringBuilder();

            try {
                URL url = new URL(ABILITIES_ORDER_URL_START + params[0] + ABILITIES_ORDER_URL_END);
                connection = (HttpURLConnection) url.openConnection();

                if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return null;
                }
                else {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String line;

                    while((line = reader.readLine()) != null) {
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
            if(result == null) {
                Toast.makeText(BuildsActivity.this, getResources().getString(R.string.championgg_error_message), Toast.LENGTH_SHORT).show();
            }
            else {
                try {
                    JSONArray root = new JSONArray(result);

                    JSONObject obj = root.getJSONObject(0);
                    JSONArray itemsOne = obj.getJSONArray("items");

                    int item1 = itemsOne.getInt(0);
                    int item2 = itemsOne.getInt(1);
                    int item3 = itemsOne.getInt(2);
                    int item4 = itemsOne.getInt(3);
                    int item5 = itemsOne.getInt(4);
                    int item6 = itemsOne.getInt(5);

                    final String item1_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item1) + ".png";
                    final String item2_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item2) + ".png";
                    final String item3_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item3) + ".png";
                    final String item4_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item4) + ".png";
                    final String item5_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item5) + ".png";
                    final String item6_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item6) + ".png";

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item1_url).getContent());
                                imgItem1B.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem1B.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item2_url).getContent());
                                imgItem2B.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem2B.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item3_url).getContent());
                                imgItem3B.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem3B.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item4_url).getContent());
                                imgItem4B.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem4B.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item5_url).getContent());
                                imgItem5B.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem5B.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item6_url).getContent());
                                imgItem6B.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem6B.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONArray root = new JSONArray(result);

                    JSONObject obj = root.getJSONObject(1);
                    JSONArray itemsTwo = obj.getJSONArray("items");

                    int item1 = itemsTwo.getInt(0);
                    int item2 = itemsTwo.getInt(1);
                    int item3 = itemsTwo.getInt(2);
                    int item4 = itemsTwo.getInt(3);
                    int item5 = itemsTwo.getInt(4);
                    int item6 = itemsTwo.getInt(5);

                    final String item1_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item1) + ".png";
                    final String item2_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item2) + ".png";
                    final String item3_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item3) + ".png";
                    final String item4_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item4) + ".png";
                    final String item5_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item5) + ".png";
                    final String item6_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item6) + ".png";

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item1_url).getContent());
                                imgItem1D.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem1D.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item2_url).getContent());
                                imgItem2D.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem2D.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item3_url).getContent());
                                imgItem3D.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem3D.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item4_url).getContent());
                                imgItem4D.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem4D.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item5_url).getContent());
                                imgItem5D.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem5D.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item6_url).getContent());
                                imgItem6D.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem6D.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONArray root = new JSONArray(result);

                    JSONObject obj = root.getJSONObject(2);
                    JSONArray itemsThree = obj.getJSONArray("items");

                    int item1 = itemsThree.getInt(0);
                    int item2 = itemsThree.getInt(1);
                    int item3 = itemsThree.getInt(2);
                    int item4 = itemsThree.getInt(3);
                    int item5 = itemsThree.getInt(4);
                    int item6 = itemsThree.getInt(5);

                    final String item1_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item1) + ".png";
                    final String item2_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item2) + ".png";
                    final String item3_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item3) + ".png";
                    final String item4_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item4) + ".png";
                    final String item5_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item5) + ".png";
                    final String item6_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item6) + ".png";

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item1_url).getContent());
                                imgItem1F.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem1F.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item2_url).getContent());
                                imgItem2F.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem2F.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item3_url).getContent());
                                imgItem3F.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem3F.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item4_url).getContent());
                                imgItem4F.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem4F.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item5_url).getContent());
                                imgItem5F.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem5F.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item6_url).getContent());
                                imgItem6F.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem6F.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONArray root = new JSONArray(result);

                    JSONObject obj = root.getJSONObject(3);
                    JSONArray itemsFour = obj.getJSONArray("items");

                    int item1 = itemsFour.getInt(0);
                    int item2 = itemsFour.getInt(1);
                    int item3 = itemsFour.getInt(2);
                    int item4 = itemsFour.getInt(3);
                    int item5 = itemsFour.getInt(4);
                    int item6 = itemsFour.getInt(5);

                    final String item1_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item1) + ".png";
                    final String item2_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item2) + ".png";
                    final String item3_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item3) + ".png";
                    final String item4_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item4) + ".png";
                    final String item5_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item5) + ".png";
                    final String item6_url = "http://ddragon.leagueoflegends.com/cdn/6.10.1/img/item/" + String.valueOf(item6) + ".png";

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item1_url).getContent());
                                imgItem1H.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem1H.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item2_url).getContent());
                                imgItem2H.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem2H.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item3_url).getContent());
                                imgItem3H.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem3H.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item4_url).getContent());
                                imgItem4H.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem4H.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item5_url).getContent());
                                imgItem5H.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem5H.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(item6_url).getContent());
                                imgItem6H.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgItem6H.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class GetChampionsMostWinsSummonerSpells extends AsyncTask<String, Void, String> {

        private final String SUMMONER_SPELLS_URL_START = "http://api.champion.gg/champion/";
        private final String SUMMONER_SPELLS_URL_END = "/summoners/mostWins?api_key=" + getResources().getString(R.string.championgg);

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader =  null;
            StringBuilder stringBuilder = new StringBuilder();

            try {
                URL url = new URL(SUMMONER_SPELLS_URL_START + params[0] + SUMMONER_SPELLS_URL_END);
                connection = (HttpURLConnection) url.openConnection();

                if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return null;
                }
                else {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String line;

                    while((line = reader.readLine()) != null) {
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
            if(result == null) {
                Toast.makeText(BuildsActivity.this, getResources().getString(R.string.championgg_error_message), Toast.LENGTH_SHORT).show();
            }
            else {
                try {
                    JSONArray root = new JSONArray(result);

                    JSONObject obj = root.getJSONObject(0);
                    String summonerOne = obj.getString("summoner1");
                    String summonerTwo = obj.getString("summoner2");

                    final String summoner1_url = "http://www.mobafire.com/images/summoner-spell/" + String.valueOf(summonerOne) + ".png";
                    final String summoner2_url = "http://www.mobafire.com/images/summoner-spell/" + String.valueOf(summonerTwo) + ".png";

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(summoner1_url).getContent());
                                imgSummonerSpell1A.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgSummonerSpell1A.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(summoner2_url).getContent());
                                imgSummonerSpell2A.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgSummonerSpell2A.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONArray root = new JSONArray(result);

                    JSONObject obj = root.getJSONObject(1);
                    String summonerOne = obj.getString("summoner1");
                    String summonerTwo = obj.getString("summoner2");

                    final String summoner1_url = "http://www.mobafire.com/images/summoner-spell/" + String.valueOf(summonerOne) + ".png";
                    final String summoner2_url = "http://www.mobafire.com/images/summoner-spell/" + String.valueOf(summonerTwo) + ".png";

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(summoner1_url).getContent());
                                imgSummonerSpell1C.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgSummonerSpell1C.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(summoner2_url).getContent());
                                imgSummonerSpell2C.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgSummonerSpell2C.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONArray root = new JSONArray(result);

                    JSONObject obj = root.getJSONObject(2);
                    String summonerOne = obj.getString("summoner1");
                    String summonerTwo = obj.getString("summoner2");

                    final String summoner1_url = "http://www.mobafire.com/images/summoner-spell/" + String.valueOf(summonerOne) + ".png";
                    final String summoner2_url = "http://www.mobafire.com/images/summoner-spell/" + String.valueOf(summonerTwo) + ".png";

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(summoner1_url).getContent());
                                imgSummonerSpell1E.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgSummonerSpell1E.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(summoner2_url).getContent());
                                imgSummonerSpell2E.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgSummonerSpell2E.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONArray root = new JSONArray(result);

                    JSONObject obj = root.getJSONObject(3);
                    String summonerOne = obj.getString("summoner1");
                    String summonerTwo = obj.getString("summoner2");

                    final String summoner1_url = "http://www.mobafire.com/images/summoner-spell/" + String.valueOf(summonerOne) + ".png";
                    final String summoner2_url = "http://www.mobafire.com/images/summoner-spell/" + String.valueOf(summonerTwo) + ".png";

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(summoner1_url).getContent());
                                imgSummonerSpell1G.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgSummonerSpell1G.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(summoner2_url).getContent());
                                imgSummonerSpell2G.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgSummonerSpell2G.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class GetChampionsMostFrequentSummonerSpells extends AsyncTask<String, Void, String> {

        private final String SUMMONER_SPELLS_URL_START = "http://api.champion.gg/champion/";
        private final String SUMMONER_SPELLS_URL_END = "/summoners/mostPopular?api_key=" + getResources().getString(R.string.championgg);

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader =  null;
            StringBuilder stringBuilder = new StringBuilder();

            try {
                URL url = new URL(SUMMONER_SPELLS_URL_START + params[0] + SUMMONER_SPELLS_URL_END);
                connection = (HttpURLConnection) url.openConnection();

                if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return null;
                }
                else {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String line;

                    while((line = reader.readLine()) != null) {
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
            if(result == null) {
                Toast.makeText(BuildsActivity.this, getResources().getString(R.string.championgg_error_message), Toast.LENGTH_SHORT).show();
            }
            else {
                try {
                    JSONArray root = new JSONArray(result);

                    JSONObject obj = root.getJSONObject(0);
                    String summonerOne = obj.getString("summoner1");
                    String summonerTwo = obj.getString("summoner2");

                    final String summoner1_url = "http://www.mobafire.com/images/summoner-spell/" + String.valueOf(summonerOne) + ".png";
                    final String summoner2_url = "http://www.mobafire.com/images/summoner-spell/" + String.valueOf(summonerTwo) + ".png";

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(summoner1_url).getContent());
                                imgSummonerSpell1B.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgSummonerSpell1B.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(summoner2_url).getContent());
                                imgSummonerSpell2B.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgSummonerSpell2B.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONArray root = new JSONArray(result);

                    JSONObject obj = root.getJSONObject(1);
                    String summonerOne = obj.getString("summoner1");
                    String summonerTwo = obj.getString("summoner2");

                    final String summoner1_url = "http://www.mobafire.com/images/summoner-spell/" + String.valueOf(summonerOne) + ".png";
                    final String summoner2_url = "http://www.mobafire.com/images/summoner-spell/" + String.valueOf(summonerTwo) + ".png";

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(summoner1_url).getContent());
                                imgSummonerSpell1D.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgSummonerSpell1D.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(summoner2_url).getContent());
                                imgSummonerSpell2D.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgSummonerSpell2D.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONArray root = new JSONArray(result);

                    JSONObject obj = root.getJSONObject(2);
                    String summonerOne = obj.getString("summoner1");
                    String summonerTwo = obj.getString("summoner2");

                    final String summoner1_url = "http://www.mobafire.com/images/summoner-spell/" + String.valueOf(summonerOne) + ".png";
                    final String summoner2_url = "http://www.mobafire.com/images/summoner-spell/" + String.valueOf(summonerTwo) + ".png";

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(summoner1_url).getContent());
                                imgSummonerSpell1F.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgSummonerSpell1F.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(summoner2_url).getContent());
                                imgSummonerSpell2F.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgSummonerSpell2F.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONArray root = new JSONArray(result);

                    JSONObject obj = root.getJSONObject(3);
                    String summonerOne = obj.getString("summoner1");
                    String summonerTwo = obj.getString("summoner2");

                    final String summoner1_url = "http://www.mobafire.com/images/summoner-spell/" + String.valueOf(summonerOne) + ".png";
                    final String summoner2_url = "http://www.mobafire.com/images/summoner-spell/" + String.valueOf(summonerTwo) + ".png";

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(summoner1_url).getContent());
                                imgSummonerSpell1H.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgSummonerSpell1H.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(summoner2_url).getContent());
                                imgSummonerSpell2H.post(new Runnable() {
                                    public void run() {
                                        if(bitmap !=null) {
                                            imgSummonerSpell2H.setImageBitmap(bitmap);
                                        }
                                    }
                                });
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
