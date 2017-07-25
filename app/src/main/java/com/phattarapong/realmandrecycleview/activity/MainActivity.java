package com.phattarapong.realmandrecycleview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.phattarapong.realmandrecycleview.R;
import com.phattarapong.realmandrecycleview.adapter.StudentAdapter;
import com.phattarapong.realmandrecycleview.manager.RealmHelper;

import io.realm.Realm;
import io.realm.RealmChangeListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FloatingActionButton fab1;
    private FloatingActionMenu floatingActionMenu;
    private Realm realm;
    private RealmHelper realmHelper;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RealmChangeListener realmListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Realm*/
        realm = Realm.getDefaultInstance();
        realmHelper = new RealmHelper(realm);
        /*RecycleView*/
        initInstance();
        UpdateView();


        realmListener = new RealmChangeListener() {
            @Override
            public void onChange(Object o) {
                UpdateView();
            }
        };
        realm.addChangeListener(realmListener);
    }

    private void UpdateView() {
        adapter = new StudentAdapter(realmHelper.SelectData());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initInstance() {
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        floatingActionMenu = (FloatingActionMenu) findViewById(R.id.float_menu);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        fab1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab1) {
            Toast.makeText(getApplicationContext(), "Click Add", Toast.LENGTH_SHORT)
                    .show();
            startActivity(new Intent(getApplicationContext(), AddDataActivity.class));
            floatingActionMenu.close(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_delelte_data:
               realmHelper.delete();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.removeChangeListener(realmListener);
        realm.close();
    }
}
