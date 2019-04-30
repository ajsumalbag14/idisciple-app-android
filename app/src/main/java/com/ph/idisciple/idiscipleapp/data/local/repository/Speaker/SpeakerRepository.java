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
    public Speaker findItemById(String id) {
        return realm.where(Speaker.class).equalTo("id", id).findFirst();
    }

    @Override
    public void addItemList(final List<Speaker> itemList) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(itemList);
            }
        });
    }

    @Override
    public int size() {
        return (int) realm.where(Speaker.class).count();
    }

    @Override
    public void resetStorage() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Speaker> storage = realm.where(Speaker.class).findAll();
                storage.deleteAllFromRealm();
            }
        });
    }
}
