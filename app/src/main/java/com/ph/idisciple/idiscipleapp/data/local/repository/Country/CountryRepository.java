package com.ph.idisciple.idiscipleapp.data.local.repository.Country;

import com.ph.idisciple.idiscipleapp.data.local.model.Country;
import com.ph.idisciple.idiscipleapp.data.local.model.Speaker;
import com.ph.idisciple.idiscipleapp.data.local.repository.BaseRepository;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class CountryRepository extends BaseRepository implements ICountryRepository {
    @Override
    public ArrayList<Country> getContentList() {
        RealmResults<Country> realmResults = realm.where(Country.class).findAll();
        ArrayList<Country> arrayList = new ArrayList<>();
        arrayList.addAll(realmResults);
        return arrayList;
    }

    @Override
    public Country findItemById(String id) {
        return realm.where(Country.class).equalTo("id", id).findFirst();
    }

    @Override
    public void addItemList(final List<Country> itemList) {
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
}
