package com.ph.idisciple.idiscipleapp.data.local.repository.About;

import com.ph.idisciple.idiscipleapp.data.local.model.AboutContent;
import com.ph.idisciple.idiscipleapp.data.local.repository.BaseRepository;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class AboutContentRepository extends BaseRepository implements IAboutContentRepository {
    @Override
    public ArrayList<AboutContent> getContentList() {
        RealmResults<AboutContent> realmResults = realm.where(AboutContent.class).findAll();
        ArrayList<AboutContent> arrayList = new ArrayList<>();
        arrayList.addAll(realmResults);
        return arrayList;
    }

    @Override
    public AboutContent findItemById(String id) {
        return realm.where(AboutContent.class).equalTo("id", id).findFirst();
    }

    @Override
    public void addItemList(final List<AboutContent> itemList) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(itemList);
            }
        });
    }

    @Override
    public int size() {
        return (int) realm.where(AboutContent.class).count();
    }

    @Override
    public void resetStorage() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<AboutContent> storage = realm.where(AboutContent.class).findAll();
                storage.deleteAllFromRealm();
            }
        });
    }
}
