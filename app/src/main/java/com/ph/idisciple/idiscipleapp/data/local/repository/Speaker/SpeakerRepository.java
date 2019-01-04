package com.ph.idisciple.idiscipleapp.data.local.repository.Speaker;

import com.ph.idisciple.idiscipleapp.data.local.model.Speaker;
import com.ph.idisciple.idiscipleapp.data.local.repository.BaseRepository;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class SpeakerRepository extends BaseRepository implements ISpeakerRepository {
    @Override
    public ArrayList<Speaker> getContentList() {
        RealmResults<Speaker> realmResults = realm.where(Speaker.class).findAll();
        ArrayList<Speaker> arrayList = new ArrayList<>();
        arrayList.addAll(realmResults);
        return arrayList;
    }

    @Override
    public Speaker findItemById(int speakerId) {
        return realm.where(Speaker.class).equalTo("id", speakerId).findFirst();
    }

    @Override
    public void addItemList(final List<Speaker> speakers) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(speakers);
            }
        });
    }

    @Override
    public int size() {
        return (int) realm.where(Speaker.class).count();
    }
}
