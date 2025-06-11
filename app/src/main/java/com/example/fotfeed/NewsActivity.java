package com.example.fotfeed;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fotfeed.adapter.NewsAdapter;
import com.example.fotfeed.model.NewsItem;
import com.google.firebase.auth.FirebaseAuth;
import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private List<NewsItem> fullList;        // master list
    private ImageButton btnSport, btnAcademic, btnEvent;
    private List<NewsItem> newsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        newsList = new ArrayList<>();

        newsList.add(new NewsItem("Orientation Program 2021/22",
                "The inaugural ceremony for new entrants...",
                R.drawable.news_orientation,
                "academic"));

        newsList.add(new NewsItem("Annual Research Symposium 2022",
                "Held at the New Art Theater...",
                R.drawable.news_symposium,
                "events"));

        /* ── top nav click listeners ── */
        findViewById(R.id.navHome).setOnClickListener(v -> {/* stay */});
        findViewById(R.id.navProfile).setOnClickListener(v ->
                startActivity(new Intent(this, ProfileActivity.class)));
        findViewById(R.id.navAbout).setOnClickListener(v ->
                startActivity(new Intent(this, AboutUsActivity.class)));

        /* ── recycler ── */
        recyclerView = findViewById(R.id.newsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /* ── bottom bar buttons ── */
        btnSport    = findViewById(R.id.btnSport);
        btnAcademic = findViewById(R.id.btnAcademic);
        btnEvent    = findViewById(R.id.btnEvent);

        btnSport.setOnClickListener(v   -> filter("SPORT"));
        btnAcademic.setOnClickListener(v-> filter("ACADEMIC"));
        btnEvent.setOnClickListener(v   -> filter("EVENT"));
        /* extra: long-press any button to clear filter */
        btnSport.setOnLongClickListener(v -> { filter("ALL"); return true; });
        btnAcademic.setOnLongClickListener(v -> { filter("ALL"); return true; });
        btnEvent.setOnLongClickListener(v -> { filter("ALL"); return true; });

        /* sample data */
        fullList = buildSampleNews();
        adapter  = new NewsAdapter(this, new ArrayList<>(fullList));
        recyclerView.setAdapter(adapter);
    }

    private void filter(String cat) {
        List<NewsItem> filtered = new ArrayList<>();
        if (cat.equals("ALL")) {
            filtered.addAll(fullList);
        } else {
            for (NewsItem n : fullList)
                if (n.getCategory().equals(cat)) filtered.add(n);
        }
        adapter = new NewsAdapter(this, filtered);
        recyclerView.setAdapter(adapter);
    }

    private List<NewsItem> buildSampleNews() {
        List<NewsItem> list = new ArrayList<>();
        list.add(new NewsItem("Orientation Program 2021/22",
                "2025-08-02", R.drawable.news_orientation, "ACADEMIC"));
        list.add(new NewsItem("Annual Research Symposium 2022",
                "2025-04-10", R.drawable.news_symposium, "ACADEMIC"));
        list.add(new NewsItem("Inter-Faculty Hockey Finals",
                "2025-06-01", R.drawable.news_hockey, "SPORT"));
        list.add(new NewsItem("Tech Expo & Job Fair",
                "2025-07-15", R.drawable.news_tech_expo, "EVENT"));
        list.add(new NewsItem("Track & Field and Ground Marking Workshop",
                "2024-04-31", R.drawable.news_workshop, "SPORT"));
        list.add(new NewsItem("12th SpinUOC entrepreneurship event",
                "2025-07-15", R.drawable.news_spin, "EVENT"));
        return list;
    }
}