package com.phattarapong.realmandrecycleview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.phattarapong.realmandrecycleview.R;
import com.phattarapong.realmandrecycleview.manager.RealmHelper;

import io.realm.Realm;

public class AddDataActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextID, editTextNAME, editTextSCORE;
    private Button btnSend;
    private Realm realm;
    private String name;
    private String id, score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        realm = Realm.getDefaultInstance();

        initInstance();

        btnSend = (Button) findViewById(R.id.btn_send);
        btnSend.setOnClickListener(this);
    }

    private void initInstance() {
        editTextID = (EditText) findViewById(R.id.edit_id_student);
        editTextNAME = (EditText) findViewById(R.id.edit_name_student);
        editTextSCORE = (EditText) findViewById(R.id.edit_score_student);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_send){
            id = editTextID.getText().toString();
            name = editTextNAME.getText().toString();
            score = editTextSCORE.getText().toString();
            RealmHelper realmHelper = new RealmHelper(realm);
            realmHelper.SaveData(id,name,score);
            onBackPressed();
        }
    }
}
