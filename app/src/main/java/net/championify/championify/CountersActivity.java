package net.championify.championify;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class CountersActivity extends AppCompatActivity implements View.OnClickListener {

    private ChampionsDBHandler handler;
    private long championId;
    private String key;
    private ImageView imgChampion, imgChampionBig;
    private TextView tvChampionName, tvChampionTitle, tvChampionPrimaryRole, tvChampionSecondaryRole, tvCountersListEmptyView;
    private Button btnChampionRoleTop, btnChampionRoleJungle, btnChampionRoleMid, btnChampionRoleSupport, btnChampionRoleADC;
    private RecyclerView rvChampionImage, rvChampionCountersWeakAgainstInfo, rvChampionCountersStrongAgainstInfo;
    private ChampionCountersWeakAgainstRecyclerViewAdapter adapterWeakAgainst;
    private ChampionCountersStrongAgainstRecyclerViewAdapter adapterStrongAgainst;
    private ArrayList<ChampionCountersImageLinks> championImagesList = new ArrayList<>();
    private List<ChampionCountersWeakAgainst> championCountersWeakAgainstList = new ArrayList<>();
    private List<ChampionCountersStrongAgainst> championCountersStrongAgainstList = new ArrayList<>();
    private RadioGroup rgStrongWeakAgainst;
    private RadioButton rbStrongAgainst, rbWeakAgainst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counters);

        Locale.setDefault(new Locale("en", "US"));

        handler = new ChampionsDBHandler(this);

        findViewById(R.id.btn_champion_role_top_actcounters).setOnClickListener(this);
        findViewById(R.id.btn_champion_role_jungle_actcounters).setOnClickListener(this);
        findViewById(R.id.btn_champion_role_mid_actcounters).setOnClickListener(this);
        findViewById(R.id.btn_champion_role_support_actcounters).setOnClickListener(this);
        findViewById(R.id.btn_champion_role_adc_actcounters).setOnClickListener(this);
        findViewById(R.id.btn_view_champion_skins).setOnClickListener(this);

        rgStrongWeakAgainst = (RadioGroup) findViewById(R.id.rg_strong_weak_actcounters);
        rbStrongAgainst = (RadioButton) findViewById(R.id.rb_strong_against_actcounters);
        rbWeakAgainst = (RadioButton) findViewById(R.id.rb_weak_against_actcounters);

        btnChampionRoleTop = (Button) findViewById(R.id.btn_champion_role_top_actcounters);
        btnChampionRoleJungle = (Button) findViewById(R.id.btn_champion_role_jungle_actcounters);
        btnChampionRoleMid= (Button) findViewById(R.id.btn_champion_role_mid_actcounters);
        btnChampionRoleSupport = (Button) findViewById(R.id.btn_champion_role_support_actcounters);
        btnChampionRoleADC = (Button) findViewById(R.id.btn_champion_role_adc_actcounters);

        imgChampion = (ImageView) findViewById(R.id.img_champion_actcounters);
        imgChampionBig = (ImageView) findViewById(R.id.img_championbig_actcounters);

        tvCountersListEmptyView = (TextView) findViewById(R.id.tv_counters_list_empty_view_actbuilds);
        tvChampionName = (TextView) findViewById(R.id.tv_champion_name_actcounters);
        tvChampionTitle = (TextView) findViewById(R.id.tv_champion_title_actcounters);
        tvChampionPrimaryRole = (TextView) findViewById(R.id.tv_champion_primaryrole_actcounters);
        tvChampionSecondaryRole = (TextView) findViewById(R.id.tv_champion_secondaryrole_actcounters);

        rvChampionImage = (RecyclerView) findViewById(R.id.rv_champion_image_actcounters);
        rvChampionImage.setLayoutManager(new LinearLayoutManager(this, 0, false));

        rvChampionCountersWeakAgainstInfo = (RecyclerView) findViewById(R.id.rv_champion_counters_weakagainst);
        rvChampionCountersWeakAgainstInfo.setLayoutManager(new LinearLayoutManager(this));

        adapterWeakAgainst = new ChampionCountersWeakAgainstRecyclerViewAdapter(championCountersWeakAgainstList);
        rvChampionCountersWeakAgainstInfo.setAdapter(adapterWeakAgainst);

        championCountersWeakAgainstList.clear();
        adapterWeakAgainst.notifyDataSetChanged();

        rvChampionCountersStrongAgainstInfo = (RecyclerView) findViewById(R.id.rv_champion_counters_strongagainst);
        rvChampionCountersStrongAgainstInfo.setLayoutManager(new LinearLayoutManager(this));

        adapterStrongAgainst = new ChampionCountersStrongAgainstRecyclerViewAdapter(championCountersStrongAgainstList);
        rvChampionCountersStrongAgainstInfo.setAdapter(adapterStrongAgainst);

        championCountersStrongAgainstList.clear();
        adapterStrongAgainst.notifyDataSetChanged();

        Intent in = getIntent();
        championId = in.getLongExtra("champion_id_key", -1);

        if(championId != -1) {
            Champion champion = handler.getChampionById(championId);

            key = champion.getKey();
            int numOfSkins = champion.getNumOfSkins();

            tvChampionName.setText(champion.getName());
            tvChampionTitle.setText(champion.getTitle());
            tvChampionPrimaryRole.setText(String.valueOf(champion.getPrimaryRole() + " / "));
            tvChampionSecondaryRole.setText(champion.getSecondaryRole());
            imgChampion.setImageBitmap(BitmapDecoder.decodeSampledBitmapFromResource(getResources(), champion.getImage(), 150, 150));

            final String image_url = "http://ddragon.leagueoflegends.com/cdn/img/champion/splash/" + key + "_0.jpg";
            new Thread(new Runnable() {
                public void run() {
                    try {
                        final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(image_url).getContent());
                        imgChampionBig.post(new Runnable() {
                            public void run() {
                                if(bitmap != null) {
                                    imgChampionBig.setImageBitmap(bitmap);
                                }
                            }
                        });
                    }
                    catch (Exception e) {

                    }
                }
            }).start();

            championImagesList.clear();
            for (int i = 0; i < numOfSkins; i++) {
                    final String image_url_b = "http://ddragon.leagueoflegends.com/cdn/img/champion/loading/" + key + "_" + i + ".jpg";
                    championImagesList.add(new ChampionCountersImageLinks(image_url_b));
            }
        }
        ObjectAnimator fadeInChampImageBig = ObjectAnimator.ofFloat(imgChampionBig, "alpha", 1);
        fadeInChampImageBig.setDuration(2500);
        fadeInChampImageBig.start();
        ObjectAnimator fadeInChampImage = ObjectAnimator.ofFloat(imgChampion, "alpha", 1);
        fadeInChampImage.setDuration(2500);
        fadeInChampImage.start();

        rgStrongWeakAgainst.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rbStrongAgainst.isChecked()) {
                    rvChampionCountersStrongAgainstInfo.setVisibility(View.VISIBLE);
                    rvChampionCountersWeakAgainstInfo.setVisibility(View.GONE);
                } else if (rbWeakAgainst.isChecked()) {
                    rvChampionCountersWeakAgainstInfo.setVisibility(View.VISIBLE);
                    rvChampionCountersStrongAgainstInfo.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_view_champion_skins:
                ChampionImageRecyclerViewAdapter adapterChampionImage = new ChampionImageRecyclerViewAdapter(championImagesList);
                rvChampionImage.setAdapter(adapterChampionImage);

                rvChampionImage.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_champion_role_top_actcounters:
                btnChampionRoleTop.setBackgroundColor(Color.parseColor("#006e9e"));
                btnChampionRoleTop.setTextColor(Color.parseColor("#d5d5d5"));

                btnChampionRoleJungle.setBackgroundColor(Color.parseColor("#232323"));
                btnChampionRoleJungle.setTextColor(Color.parseColor("#006e9e"));

                btnChampionRoleMid.setBackgroundColor(Color.parseColor("#232323"));
                btnChampionRoleMid.setTextColor(Color.parseColor("#006e9e"));

                btnChampionRoleSupport.setBackgroundColor(Color.parseColor("#232323"));
                btnChampionRoleSupport.setTextColor(Color.parseColor("#006e9e"));

                btnChampionRoleADC.setBackgroundColor(Color.parseColor("#232323"));
                btnChampionRoleADC.setTextColor(Color.parseColor("#006e9e"));

                championCountersStrongAgainstList.clear();
                adapterStrongAgainst.notifyDataSetChanged();
                championCountersWeakAgainstList.clear();
                adapterWeakAgainst.notifyDataSetChanged();
                GetChampionCountersTop taskTop = new GetChampionCountersTop();
                taskTop.execute(key);
                break;
            case R.id.btn_champion_role_jungle_actcounters:
                btnChampionRoleJungle.setBackgroundColor(Color.parseColor("#006e9e"));
                btnChampionRoleJungle.setTextColor(Color.parseColor("#d5d5d5"));

                btnChampionRoleTop.setBackgroundColor(Color.parseColor("#232323"));
                btnChampionRoleTop.setTextColor(Color.parseColor("#006e9e"));

                btnChampionRoleMid.setBackgroundColor(Color.parseColor("#232323"));
                btnChampionRoleMid.setTextColor(Color.parseColor("#006e9e"));

                btnChampionRoleSupport.setBackgroundColor(Color.parseColor("#232323"));
                btnChampionRoleSupport.setTextColor(Color.parseColor("#006e9e"));

                btnChampionRoleADC.setBackgroundColor(Color.parseColor("#232323"));
                btnChampionRoleADC.setTextColor(Color.parseColor("#006e9e"));

                championCountersStrongAgainstList.clear();
                adapterStrongAgainst.notifyDataSetChanged();
                championCountersWeakAgainstList.clear();
                adapterWeakAgainst.notifyDataSetChanged();
                GetChampionCountersJungle taskJungle = new GetChampionCountersJungle();
                taskJungle.execute(key);
                break;
            case R.id.btn_champion_role_mid_actcounters:
                btnChampionRoleMid.setBackgroundColor(Color.parseColor("#006e9e"));
                btnChampionRoleMid.setTextColor(Color.parseColor("#d5d5d5"));

                btnChampionRoleTop.setBackgroundColor(Color.parseColor("#232323"));
                btnChampionRoleTop.setTextColor(Color.parseColor("#006e9e"));

                btnChampionRoleJungle.setBackgroundColor(Color.parseColor("#232323"));
                btnChampionRoleJungle.setTextColor(Color.parseColor("#006e9e"));

                btnChampionRoleSupport.setBackgroundColor(Color.parseColor("#232323"));
                btnChampionRoleSupport.setTextColor(Color.parseColor("#006e9e"));

                btnChampionRoleADC.setBackgroundColor(Color.parseColor("#232323"));
                btnChampionRoleADC.setTextColor(Color.parseColor("#006e9e"));

                championCountersStrongAgainstList.clear();
                adapterStrongAgainst.notifyDataSetChanged();
                championCountersWeakAgainstList.clear();
                adapterWeakAgainst.notifyDataSetChanged();
                GetChampionCountersMid taskMid = new GetChampionCountersMid();
                taskMid.execute(key);
                break;
            case R.id.btn_champion_role_support_actcounters:
                btnChampionRoleSupport.setBackgroundColor(Color.parseColor("#006e9e"));
                btnChampionRoleSupport.setTextColor(Color.parseColor("#d5d5d5"));

                btnChampionRoleTop.setBackgroundColor(Color.parseColor("#232323"));
                btnChampionRoleTop.setTextColor(Color.parseColor("#006e9e"));

                btnChampionRoleJungle.setBackgroundColor(Color.parseColor("#232323"));
                btnChampionRoleJungle.setTextColor(Color.parseColor("#006e9e"));

                btnChampionRoleMid.setBackgroundColor(Color.parseColor("#232323"));
                btnChampionRoleMid.setTextColor(Color.parseColor("#006e9e"));

                btnChampionRoleADC.setBackgroundColor(Color.parseColor("#232323"));
                btnChampionRoleADC.setTextColor(Color.parseColor("#006e9e"));

                championCountersStrongAgainstList.clear();
                adapterStrongAgainst.notifyDataSetChanged();
                championCountersWeakAgainstList.clear();
                adapterWeakAgainst.notifyDataSetChanged();
                GetChampionCountersSupport taskSupport = new GetChampionCountersSupport();
                taskSupport.execute(key);
                break;
            case R.id.btn_champion_role_adc_actcounters:
                btnChampionRoleADC.setBackgroundColor(Color.parseColor("#006e9e"));
                btnChampionRoleADC.setTextColor(Color.parseColor("#d5d5d5"));

                btnChampionRoleTop.setBackgroundColor(Color.parseColor("#232323"));
                btnChampionRoleTop.setTextColor(Color.parseColor("#006e9e"));

                btnChampionRoleJungle.setBackgroundColor(Color.parseColor("#232323"));
                btnChampionRoleJungle.setTextColor(Color.parseColor("#006e9e"));

                btnChampionRoleMid.setBackgroundColor(Color.parseColor("#232323"));
                btnChampionRoleMid.setTextColor(Color.parseColor("#006e9e"));

                btnChampionRoleSupport.setBackgroundColor(Color.parseColor("#232323"));
                btnChampionRoleSupport.setTextColor(Color.parseColor("#006e9e"));

                championCountersStrongAgainstList.clear();
                adapterStrongAgainst.notifyDataSetChanged();
                championCountersWeakAgainstList.clear();
                adapterWeakAgainst.notifyDataSetChanged();
                GetChampionCountersAdc taskAdc = new GetChampionCountersAdc();
                taskAdc.execute(key);
                break;
        }
    }

    public class ChampionImageRecyclerViewAdapter extends RecyclerView.Adapter<ChampionImageRecyclerViewAdapter.Holder> {

        ArrayList<ChampionCountersImageLinks> championImagesList;

        public ChampionImageRecyclerViewAdapter(ArrayList<ChampionCountersImageLinks> championImagesList) {
            this.championImagesList = championImagesList;
        }

        @Override
        public ChampionImageRecyclerViewAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View oneRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.champion_image_item_list, parent, false);

            Holder viewHolder =  new Holder(oneRow);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ChampionImageRecyclerViewAdapter.Holder holder, int position) {
            ChampionCountersImageLinks championCountersImageLinks = championImagesList.get(position);
            holder.bind(championCountersImageLinks);
        }

        @Override
        public int getItemCount() {
            return championImagesList.size();
        }

        public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private ChampionCountersImageLinks championCountersImageLinks;
            private ImageView imgChampionA;

            public Holder(View itemView) {
                super(itemView);
                imgChampionA = (ImageView) itemView.findViewById(R.id.img_champion_actcounters);

                itemView.setOnClickListener(this);
            }

            public void bind(final ChampionCountersImageLinks championCountersImageLinks) {
                this.championCountersImageLinks = championCountersImageLinks;

                new Thread(new Runnable() {
                    public void run() {
                        try {
                            final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(championCountersImageLinks.getImgUrl()).getContent());
                            imgChampionA.post(new Runnable() {
                                public void run() {
                                    try {
                                        if(bitmap != null) {
                                            imgChampionA.setImageBitmap(bitmap);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                        catch (Exception e) {

                        }
                    }
                }).start();
            }

            @Override
            public void onClick(View v) {
                rvChampionImage.setVisibility(View.GONE);
            }
        }
    }

    public class ChampionCountersWeakAgainstRecyclerViewAdapter extends RecyclerView.Adapter<ChampionCountersWeakAgainstRecyclerViewAdapter.Holder> {

        private List<ChampionCountersWeakAgainst> championCountersWeakAgainstList;

        public ChampionCountersWeakAgainstRecyclerViewAdapter(List<ChampionCountersWeakAgainst> championCountersWeakAgainstList) {
            this.championCountersWeakAgainstList = championCountersWeakAgainstList;
        }

        @Override
        public ChampionCountersWeakAgainstRecyclerViewAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View oneRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.counters_item_list, parent, false);

            Holder viewHolder =  new Holder(oneRow);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ChampionCountersWeakAgainstRecyclerViewAdapter.Holder holder, int position) {
            ChampionCountersWeakAgainst championCountersWeakAgainst = championCountersWeakAgainstList.get(position);
            holder.bind(championCountersWeakAgainst);
        }

        @Override
        public int getItemCount() {
            return championCountersWeakAgainstList.size();
        }

        public class Holder extends RecyclerView.ViewHolder {

            private ChampionCountersWeakAgainst championCountersWeakAgainst;
            private ImageView imgChampion;
            private TextView tvName, tvWinRate, tvStatScore;
            private String key;

            public Holder(View itemView) {
                super(itemView);
                imgChampion = (ImageView) itemView.findViewById(R.id.img_champion_counters);

                tvName = (TextView) itemView.findViewById(R.id.tv_champion_name_actcounters);
                tvWinRate = (TextView) itemView.findViewById(R.id.tv_champion_winrate_actcounters);
                tvStatScore = (TextView) itemView.findViewById(R.id.tv_champion_statscore_actcounters);
            }

            public void bind(ChampionCountersWeakAgainst championCountersWeakAgainst) {
                this.championCountersWeakAgainst = championCountersWeakAgainst;

                key = championCountersWeakAgainst.getKey();

                imgChampion.setImageBitmap(BitmapDecoder.decodeSampledBitmapFromResource(getResources(), handler.getChampionIconImage(key), 100, 100));

                tvName.setText(handler.getChampionName(key));
                tvWinRate.setText(String.valueOf(championCountersWeakAgainst.getWinRate() + "%"));
                tvStatScore.setText(String.valueOf(championCountersWeakAgainst.getStatScore()));
            }
        }
    }

    public class ChampionCountersStrongAgainstRecyclerViewAdapter extends RecyclerView.Adapter<ChampionCountersStrongAgainstRecyclerViewAdapter.Holder> {

        private List<ChampionCountersStrongAgainst> championCountersStrongAgainstList;

        public ChampionCountersStrongAgainstRecyclerViewAdapter(List<ChampionCountersStrongAgainst> championCountersStrongAgainstList) {
            this.championCountersStrongAgainstList = championCountersStrongAgainstList;
        }

        @Override
        public ChampionCountersStrongAgainstRecyclerViewAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View oneRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.counters_item_list, parent, false);

            Holder viewHolder =  new Holder(oneRow);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ChampionCountersStrongAgainstRecyclerViewAdapter.Holder holder, int position) {
            ChampionCountersStrongAgainst championCountersStrongAgainst = championCountersStrongAgainstList.get(position);
            holder.bind(championCountersStrongAgainst);
        }

        @Override
        public int getItemCount() {
            return championCountersWeakAgainstList.size();
        }

        public class Holder extends RecyclerView.ViewHolder {

            private ChampionCountersStrongAgainst championCountersStrongAgainst;
            private ImageView imgChampion;
            private TextView tvName, tvWinRate, tvStatScore;
            private String key;

            public Holder(View itemView) {
                super(itemView);
                imgChampion = (ImageView) itemView.findViewById(R.id.img_champion_counters);

                tvName = (TextView) itemView.findViewById(R.id.tv_champion_name_actcounters);
                tvWinRate = (TextView) itemView.findViewById(R.id.tv_champion_winrate_actcounters);
                tvStatScore = (TextView) itemView.findViewById(R.id.tv_champion_statscore_actcounters);
            }

            public void bind(ChampionCountersStrongAgainst championCountersStrongAgainst) {
                this.championCountersStrongAgainst = championCountersStrongAgainst;

                key = championCountersStrongAgainst.getKey();

                imgChampion.setImageBitmap(BitmapDecoder.decodeSampledBitmapFromResource(getResources(), handler.getChampionIconImage(key), 100, 100));

                tvName.setText(handler.getChampionName(key));
                tvWinRate.setText(String.valueOf(championCountersStrongAgainst.getWinRate() + "%"));
                tvStatScore.setText(String.valueOf(championCountersStrongAgainst.getStatScore()));
            }
        }
    }

    public class GetChampionCountersTop extends AsyncTask<String, Void, String> {

        private final String CHAMPION_COUNTERS_URL_START = "http://api.champion.gg/champion/";
        private final String CHAMPION_COUNTERS_URL_END = "/matchup?api_key=" + getResources().getString(R.string.championgg);

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            StringBuilder stringBuilder = new StringBuilder();

            try {
                URL url = new URL(CHAMPION_COUNTERS_URL_START + params[0] + CHAMPION_COUNTERS_URL_END);
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
                Toast.makeText(CountersActivity.this, getResources().getString(R.string.championgg_error_message), Toast.LENGTH_SHORT).show();
            } else {
                try {
                    JSONArray root = new JSONArray(result);

                    for (int i = 0; i < root.length(); i++) {
                        JSONObject data = root.getJSONObject(i);
                        String role = data.getString("role");

                        if (role.equals("Top")) {
                            JSONArray matchups = data.getJSONArray("matchups");

                            for (int j = 0; j < matchups.length(); j++) {
                                JSONObject champion = matchups.getJSONObject(j);
                                String key = champion.getString("key");
                                double statScore = champion.getDouble("statScore");
                                int winRate = champion.getInt("winRate");

//                                DecimalFormat decimalFormat = new DecimalFormat("#.#");
//                                double statScoreFormated = Double.valueOf(decimalFormat.format(statScore));

                                championCountersStrongAgainstList.add(new ChampionCountersStrongAgainst(key, statScore, winRate));
                                Collections.sort(championCountersStrongAgainstList);
                                adapterStrongAgainst.notifyDataSetChanged();
                                championCountersWeakAgainstList.add(new ChampionCountersWeakAgainst(key, statScore, winRate));
                                Collections.sort(championCountersWeakAgainstList);
                                adapterWeakAgainst.notifyDataSetChanged();
                            }
                        }
                        else {

                        }
                    }
                } catch (JSONException e) {

                }
            }
        }
    }

    public class GetChampionCountersJungle extends AsyncTask<String, Void, String> {

        private final String CHAMPION_COUNTERS_URL_START = "http://api.champion.gg/champion/";
        private final String CHAMPION_COUNTERS_URL_END = "/matchup?api_key=" + getResources().getString(R.string.championgg);

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            StringBuilder stringBuilder = new StringBuilder();

            try {
                URL url = new URL(CHAMPION_COUNTERS_URL_START + params[0] + CHAMPION_COUNTERS_URL_END);
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
                Toast.makeText(CountersActivity.this, getResources().getString(R.string.championgg_error_message), Toast.LENGTH_SHORT).show();
            } else {
                try {
                    JSONArray root = new JSONArray(result);

                    for (int i = 0; i < root.length(); i++) {
                        JSONObject data = root.getJSONObject(i);
                        String role = data.getString("role");

                        if (role.equals("Jungle")) {
                            JSONArray matchups = data.getJSONArray("matchups");

                            for (int j = 0; j < matchups.length(); j++) {
                                JSONObject champion = matchups.getJSONObject(j);
                                String key = champion.getString("key");
                                double statScore = champion.getDouble("statScore");
                                int winRate = champion.getInt("winRate");

//                                DecimalFormat decimalFormat = new DecimalFormat("#.#");
//                                double statScoreFormated = Double.valueOf(decimalFormat.format(statScore));

                                championCountersStrongAgainstList.add(new ChampionCountersStrongAgainst(key, statScore, winRate));
                                Collections.sort(championCountersStrongAgainstList);
                                adapterStrongAgainst.notifyDataSetChanged();
                                championCountersWeakAgainstList.add(new ChampionCountersWeakAgainst(key, statScore, winRate));
                                Collections.sort(championCountersWeakAgainstList);
                                adapterWeakAgainst.notifyDataSetChanged();
                            }
                        }
                        else {

                        }
                    }
                } catch (JSONException e) {

                }
            }
        }
    }

    public class GetChampionCountersMid extends AsyncTask<String, Void, String> {

        private final String CHAMPION_COUNTERS_URL_START = "http://api.champion.gg/champion/";
        private final String CHAMPION_COUNTERS_URL_END = "/matchup?api_key=" + getResources().getString(R.string.championgg);

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            StringBuilder stringBuilder = new StringBuilder();

            try {
                URL url = new URL(CHAMPION_COUNTERS_URL_START + params[0] + CHAMPION_COUNTERS_URL_END);
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
                Toast.makeText(CountersActivity.this, getResources().getString(R.string.championgg_error_message), Toast.LENGTH_SHORT).show();
            } else {
                try {
                    JSONArray root = new JSONArray(result);

                    for (int i = 0; i < root.length(); i++) {
                        JSONObject data = root.getJSONObject(i);
                        String role = data.getString("role");

                        if (role.equals("Middle")) {
                            JSONArray matchups = data.getJSONArray("matchups");

                            for (int j = 0; j < matchups.length(); j++) {
                                JSONObject champion = matchups.getJSONObject(j);
                                String key = champion.getString("key");
                                double statScore = champion.getDouble("statScore");
                                int winRate = champion.getInt("winRate");

//                                DecimalFormat decimalFormat = new DecimalFormat("#.#");
//                                double statScoreFormated = Double.valueOf(decimalFormat.format(statScore));

                                championCountersStrongAgainstList.add(new ChampionCountersStrongAgainst(key, statScore, winRate));
                                Collections.sort(championCountersStrongAgainstList);
                                adapterStrongAgainst.notifyDataSetChanged();
                                championCountersWeakAgainstList.add(new ChampionCountersWeakAgainst(key, statScore, winRate));
                                Collections.sort(championCountersWeakAgainstList);
                                adapterWeakAgainst.notifyDataSetChanged();
                            }
                        }
                        else {

                        }
                    }
                } catch (JSONException e) {

                }
            }
        }
    }

    public class GetChampionCountersSupport extends AsyncTask<String, Void, String> {

        private final String CHAMPION_COUNTERS_URL_START = "http://api.champion.gg/champion/";
        private final String CHAMPION_COUNTERS_URL_END = "/matchup?api_key=" + getResources().getString(R.string.championgg);

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            StringBuilder stringBuilder = new StringBuilder();

            try {
                URL url = new URL(CHAMPION_COUNTERS_URL_START + params[0] + CHAMPION_COUNTERS_URL_END);
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
                Toast.makeText(CountersActivity.this, getResources().getString(R.string.championgg_error_message), Toast.LENGTH_SHORT).show();
            } else {
                try {
                    JSONArray root = new JSONArray(result);

                    for (int i = 0; i < root.length(); i++) {
                        JSONObject data = root.getJSONObject(i);
                        String role = data.getString("role");

                        if (role.equals("Support")) {
                            JSONArray matchups = data.getJSONArray("matchups");

                            for (int j = 0; j < matchups.length(); j++) {
                                JSONObject champion = matchups.getJSONObject(j);
                                String key = champion.getString("key");
                                double statScore = champion.getDouble("statScore");
                                int winRate = champion.getInt("winRate");

//                                DecimalFormat decimalFormat = new DecimalFormat("#.#");
//                                double statScoreFormated = Double.valueOf(decimalFormat.format(statScore));

                                championCountersStrongAgainstList.add(new ChampionCountersStrongAgainst(key, statScore, winRate));
                                Collections.sort(championCountersStrongAgainstList);
                                adapterStrongAgainst.notifyDataSetChanged();
                                championCountersWeakAgainstList.add(new ChampionCountersWeakAgainst(key, statScore, winRate));
                                Collections.sort(championCountersWeakAgainstList);
                                adapterWeakAgainst.notifyDataSetChanged();
                            }
                        }
                        else {

                        }
                    }
                } catch (JSONException e) {

                }
            }
        }
    }

    public class GetChampionCountersAdc extends AsyncTask<String, Void, String> {

        private final String CHAMPION_COUNTERS_URL_START = "http://api.champion.gg/champion/";
        private final String CHAMPION_COUNTERS_URL_END = "/matchup?api_key=" + getResources().getString(R.string.championgg);

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            StringBuilder stringBuilder = new StringBuilder();

            try {
                URL url = new URL(CHAMPION_COUNTERS_URL_START + params[0] + CHAMPION_COUNTERS_URL_END);
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
                Toast.makeText(CountersActivity.this, getResources().getString(R.string.championgg_error_message), Toast.LENGTH_SHORT).show();
            } else {
                try {
                    JSONArray root = new JSONArray(result);

                    for (int i = 0; i < root.length(); i++) {
                        JSONObject data = root.getJSONObject(i);
                        String role = data.getString("role");

                        if (role.equals("ADC")) {
                            JSONArray matchups = data.getJSONArray("matchups");

                            for (int j = 0; j < matchups.length(); j++) {
                                JSONObject champion = matchups.getJSONObject(j);
                                String key = champion.getString("key");
                                double statScore = champion.getDouble("statScore");
                                int winRate = champion.getInt("winRate");

//                                DecimalFormat decimalFormat = new DecimalFormat("#.#");
//                                double statScoreFormated = Double.valueOf(decimalFormat.format(statScore));

                                championCountersStrongAgainstList.add(new ChampionCountersStrongAgainst(key, statScore, winRate));
                                Collections.sort(championCountersStrongAgainstList);
                                adapterStrongAgainst.notifyDataSetChanged();
                                championCountersWeakAgainstList.add(new ChampionCountersWeakAgainst(key, statScore, winRate));
                                Collections.sort(championCountersWeakAgainstList);
                                adapterWeakAgainst.notifyDataSetChanged();
                            }
                        }
                        else {

                        }
                    }
                } catch (JSONException e) {

                }
            }
        }
    }
}
