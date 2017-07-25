package com.phattarapong.realmandrecycleview.manager;

import android.content.Context;
import android.widget.Toast;

import com.phattarapong.realmandrecycleview.Contextor;
import com.phattarapong.realmandrecycleview.model.Student;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {
    Realm mRealm;


    public RealmHelper(Realm mRealm) {
        this.mRealm = mRealm;
        mContext = Contextor.getInstance().getContext();
    }

    private Context mContext;

    public void SaveData(final String s_Id, final String s_Name, final String s_Score) {
        mRealm.executeTransactionAsync(
                new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Student student = realm.createObject(Student.class,s_Id);
                        //student.setIdNumber(s_Id);
                        student.setName(s_Name);
                        student.setScore(s_Score);
                    }
                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(Contextor.getInstance().getContext(), "Complated", Toast.LENGTH_SHORT)
                                .show();
                    }

                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        Toast.makeText(Contextor.getInstance().getContext(), error.getMessage().toString()
                                , Toast.LENGTH_SHORT)
                                .show();
                    }
                });
    }

    public RealmResults<Student> SelectData() {
        RealmResults<Student> resultStudent = mRealm.where(Student.class).findAll();
        return resultStudent;
    }
    public void delete(){
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                 RealmResults<Student> studentRealmResults = mRealm.where(Student.class).findAll();
                studentRealmResults.deleteLastFromRealm();
            }
        });

    }
}
