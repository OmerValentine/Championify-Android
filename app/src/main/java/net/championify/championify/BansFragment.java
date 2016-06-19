package net.championify.championify;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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
import java.util.ArrayList;
import java.util.Locale;

public class BansFragment extends Fragment {

    private ChampionsDBHandler handler;
    private RecyclerView rvBanRatesInfo;
    private BanRatesInfoRecyclerViewAdapter adapter;
    private ArrayList<BanRatesChampions> banRatesChampionsList = new ArrayList<>();

    public BansFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bans, container, false);

        Locale.setDefault(new Locale("en", "US"));

        handler = new ChampionsDBHandler(getContext());

        GetBanRatesForChampions task = new GetBanRatesForChampions();
        task.execute();

        rvBanRatesInfo = (RecyclerView) v.findViewById(R.id.rv_banratesinfo);
        rvBanRatesInfo.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        adapter = new BanRatesInfoRecyclerViewAdapter(banRatesChampionsList);
        rvBanRatesInfo.setAdapter(adapter);

        banRatesChampionsList.clear();
        adapter.notifyDataSetChanged();

        return v;
    }

    public class BanRatesInfoRecyclerViewAdapter extends RecyclerView.Adapter<BanRatesInfoRecyclerViewAdapter.Holder> {

        private ArrayList<BanRatesChampions> banRatesChampionsList;

        public BanRatesInfoRecyclerViewAdapter(ArrayList<BanRatesChampions> banRatesChampionsList) {
            this.banRatesChampionsList = banRatesChampionsList;
        }

        @Override
        public BanRatesInfoRecyclerViewAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View oneRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.ban_rates_item_list, parent, false);

            Holder viewHolder =  new Holder(oneRow);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(BanRatesInfoRecyclerViewAdapter.Holder holder, int position) {
            BanRatesChampions banRatesChampions = banRatesChampionsList.get(position);
            holder.bind(banRatesChampions);
        }

        @Override
        public int getItemCount() {
            return banRatesChampionsList.size();
        }

        public class Holder extends RecyclerView.ViewHolder {

            private BanRatesChampions banRatesChampions;
            private ImageView imgChampion;
            private TextView tvName, tvRole, tvBanRate, tvWinRate, tvPlayRate, tvKDA;
            private String key;

            public Holder(View itemView) {
                super(itemView);
                imgChampion = (ImageView) itemView.findViewById(R.id.img_champion_fragbanrates);

                tvName = (TextView) itemView.findViewById(R.id.tv_champion_name_fragbanrates);
                tvRole = (TextView) itemView.findViewById(R.id.tv_champion_role_fragbanrates);
                tvBanRate = (TextView) itemView.findViewById(R.id.tv_champion_banrate_fragbanrates);
                tvWinRate = (TextView) itemView.findViewById(R.id.tv_champion_winrate_fragbanrates);
                tvPlayRate = (TextView) itemView.findViewById(R.id.tv_champion_playrate_fragbanrates);
                tvKDA = (TextView) itemView.findViewById(R.id.tv_champion_kda_fragbanrates);
            }

            public void bind(BanRatesChampions banRatesChampions) {
                this.banRatesChampions = banRatesChampions;

                key = banRatesChampions.getKey();

                imgChampion.setImageBitmap(BitmapDecoder.decodeSampledBitmapFromResource(getResources(), handler.getChampionLoadingImage(key), 150, 150));

                tvName.setText(banRatesChampions.getName());
                tvRole.setText(banRatesChampions.getRole());
                tvBanRate.setText(String.valueOf(banRatesChampions.getBanRate() + "%"));
                tvWinRate.setText(String.valueOf(banRatesChampions.getWinRate() + "%"));
                tvPlayRate.setText(String.valueOf(banRatesChampions.getPlayPercent() + "%"));
                tvKDA.setText(banRatesChampions.getkDA());
            }
        }
    }

    public class GetBanRatesForChampions extends AsyncTask<String, Void, String> {

        private final String BAN_RATES_URL = "http://api.champion.gg/stats/champs/mostBanned?api_key=" + getResources().getString(R.string.championgg) + "&page=1&limit=15";

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;
            StringBuilder stringBuilder = new StringBuilder();

            try {
                URL url = new URL(BAN_RATES_URL);
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
                Toast.makeText(getActivity(), getResources().getString(R.string.championgg_error_message), Toast.LENGTH_SHORT).show();
            } else {
                try {
                    JSONObject root = new JSONObject(result);
                    JSONArray data = root.getJSONArray("data");

                    for (int i = 0; i < data.length(); i++) {
                        JSONObject champion = data.getJSONObject(i);
                        String name = champion.getString("name");
                        String key = champion.getString("key");
                        String role = champion.getString("role");

                        JSONObject info = champion.getJSONObject("general");
                        int banRate = info.getInt("banRate");
                        int winRate = info.getInt("winPercent");
                        int playRate = info.getInt("playPercent");
                        int kills = info.getInt("kills");
                        int deaths = info.getInt("deaths");
                        int assists = info.getInt("assists");

                        String kDA = kills + "/" + deaths + "/" + assists;

                        banRatesChampionsList.add(new BanRatesChampions(name, key, role, banRate, winRate, playRate, kDA));
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
