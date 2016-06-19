package net.championify.championify;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Locale;

import static net.championify.championify.BitmapDecoder.decodeSampledBitmapFromResource;

public class BuildsAndCountersFragment extends Fragment implements View.OnClickListener {

    private ChampionsDBHandler handler;
    private RecyclerView rvChampions;
    private ChampionsRecyclerViewAdapter adapter;
    private Button btnSearch, btnBuilds, btnCounters, btnDismissWelcome;
    private TextView tvWelcome, tvAppStartInstructions;
    private EditText etSearch;
    private ImageView imgHideSearch;

    public BuildsAndCountersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_builds_and_counters, container, false);

        Locale.setDefault(new Locale("en", "US"));

        handler = new ChampionsDBHandler(this.getActivity());

        imgHideSearch = (ImageView) v.findViewById(R.id.img_hidesearch_fragbuildscounters);
        tvAppStartInstructions = (TextView) v.findViewById(R.id.tv_appstart_instructions_fragbuildscounters);
        tvWelcome = (TextView) v.findViewById(R.id.tv_welcome_fragbuildscounters);
        etSearch = (EditText) v.findViewById(R.id.et_search_fragbuildscounters);

        btnSearch = (Button) v.findViewById(R.id.btn_search_fragbuildscounters);
        btnDismissWelcome = (Button) v.findViewById(R.id.btn_dismiss_welcome_fragbuildscounters);

        rvChampions = (RecyclerView) v.findViewById(R.id.rv_champions_builds);
        rvChampions.setLayoutManager(new GridLayoutManager(this.getActivity(), 4));
        adapter = new ChampionsRecyclerViewAdapter(handler.getAllChampions());
        rvChampions.setAdapter(adapter);

        v.findViewById(R.id.btn_dismiss_welcome_fragbuildscounters).setOnClickListener(this);
        v.findViewById(R.id.btn_search_fragbuildscounters).setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_dismiss_welcome_fragbuildscounters:
                ObjectAnimator fadeOutWelcome = ObjectAnimator.ofFloat(tvWelcome, "alpha", 0);
                fadeOutWelcome.setDuration(0);
                fadeOutWelcome.start();
                ObjectAnimator fadeOutInstructions = ObjectAnimator.ofFloat(tvAppStartInstructions, "alpha", 0);
                fadeOutInstructions.setDuration(0);
                fadeOutInstructions.start();

                ObjectAnimator fadeInAppStart = ObjectAnimator.ofFloat(rvChampions, "alpha", 1);
                fadeInAppStart.setDuration(1000);
                fadeInAppStart.start();

                btnDismissWelcome.setVisibility(View.GONE);
                imgHideSearch.setVisibility(View.GONE);
                break;
            case R.id.btn_search_fragbuildscounters:
                String champName = etSearch.getText().toString();

                if (champName.equals("Cho'Gath") || champName.equals("Cho'gath")) {
                    adapter = new ChampionsRecyclerViewAdapter(handler.searchChampionByName("Cho''Gath"));
                    rvChampions.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

                else if (champName.equals("Kha'Zix") || champName.equals("Kha'zix")) {
                    adapter = new ChampionsRecyclerViewAdapter(handler.searchChampionByName("Kha''Zix"));
                    rvChampions.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

                else if (champName.equals("Kog'Maw") || champName.equals("Kog'maw")) {
                    adapter = new ChampionsRecyclerViewAdapter(handler.searchChampionByName("Kog''Maw"));
                    rvChampions.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

                else if (champName.equals("Rek'Sai") || champName.equals("Rek'sai")) {
                    adapter = new ChampionsRecyclerViewAdapter(handler.searchChampionByName("Rek''Sai"));
                    rvChampions.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

                else if (champName.equals("Vel'Koz") || champName.equals("Vel'koz")) {
                    adapter = new ChampionsRecyclerViewAdapter(handler.searchChampionByName("Vel''Koz"));
                    rvChampions.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

                else if (champName.equals("Jarvan Iv")) {
                    adapter = new ChampionsRecyclerViewAdapter(handler.searchChampionByName("Jarvan IV"));
                    rvChampions.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

                else if (champName.equals("Leblanc") || champName.equals("Le Blanc")) {
                    adapter = new ChampionsRecyclerViewAdapter(handler.searchChampionByName("LeBlanc"));
                    rvChampions.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

                else {
                    adapter = new ChampionsRecyclerViewAdapter(handler.searchChampionByName(champName));
                    rvChampions.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }

    public class ChampionsRecyclerViewAdapter extends RecyclerView.Adapter<ChampionsRecyclerViewAdapter.Holder> {

        private ArrayList<Champion> championsList;

        public ChampionsRecyclerViewAdapter(ArrayList<Champion> championsList) {
            this.championsList = championsList;
        }

        public void setChampion(ArrayList<Champion> championsList) {
            this.championsList = championsList;
        }

        @Override
        public ChampionsRecyclerViewAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View oneRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.champions_item_list, parent, false);

            Holder viewHolder =  new Holder(oneRow);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ChampionsRecyclerViewAdapter.Holder holder, int position) {
            Champion champion = championsList.get(position);
            holder.bind(champion);
        }

        @Override
        public int getItemCount() {
            return championsList.size();
        }

        public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private Champion champion;
            private ImageView imgChampion;
            private TextView tvName;

            public Holder(View itemView) {
                super(itemView);
                imgChampion = (ImageView) itemView.findViewById(R.id.img_champion_fragbuilds);
                tvName = (TextView) itemView.findViewById(R.id.tv_champion_name_fragbuilds);

                itemView.setOnClickListener(this);
            }

            public void bind(Champion champion) {
                this.champion = champion;

                imgChampion.setImageBitmap(decodeSampledBitmapFromResource(getResources(), champion.getIcon(), 150, 150));
                tvName.setText(champion.getName());
            }

            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.custom_dialog_builds_counters);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                btnBuilds = (Button) dialog.findViewById(R.id.btn_builds_dialog);
                btnCounters = (Button) dialog.findViewById(R.id.btn_counters_dialog);

                btnBuilds.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        long id = champion.getId();

                        Intent in = new Intent(getActivity(), BuildsActivity.class);
                        in.putExtra("champion_id_key", id);
                        startActivity(in);
                        dialog.dismiss();
                    }
                });

                btnCounters.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        long id = champion.getId();

                        Intent in = new Intent(getActivity(), CountersActivity.class);
                        in.putExtra("champion_id_key", id);
                        startActivity(in);
                        dialog.dismiss();
                    }
                });
            }
        }
    }
}
