package com.ph.idisciple.idiscipleapp.data.local.repository.Attendees;

import com.ph.idisciple.idiscipleapp.data.local.model.Profile;
import com.ph.idisciple.idiscipleapp.data.local.repository.BaseRepository;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class AttendeesRepository extends BaseRepository implements IAttendeesRepository {
    @Override
    public ArrayList<Profile> getContentList() {
        RealmResults<Profile> realmResults = realm.where(Profile.class).findAll();
        ArrayList<Profile> arrayList = new ArrayList<>();
        arrayList.addAll(realmResults);
        return arrayList;
    }

    @Override
    public Profile findItemById(String id) {
        return realm.where(Profile.class).equalTo("id", id).findFirst();
    }

    @Override
    public void addItemList(final List<Profile> itemList) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(itemList);
            }
        });
    }

    @Override
    public void addItem(final Profile item) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(item);
            }
        });
    }

    @Override
    public int size() {
        return (int) realm.where(Profile.class).count();
    }
}
