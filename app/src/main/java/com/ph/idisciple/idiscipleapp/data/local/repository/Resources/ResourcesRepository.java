package com.ph.idisciple.idiscipleapp.data.local.repository.Resources;

import com.ph.idisciple.idiscipleapp.data.local.model.Resource;
import com.ph.idisciple.idiscipleapp.data.local.repository.BaseRepository;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class ResourcesRepository extends BaseRepository implements IResourcesRepository {
    @Override
    public ArrayList<Resource> getContentList() {
        RealmResults<Resource> realmResults = realm.where(Resource.class).findAll();
        ArrayList<Resource> arrayList = new ArrayList<>();
        arrayList.addAll(realmResults);
        return arrayList;
    }

    @Override
    public Resource findItemById(String id) {
        return realm.where(Resource.class).equalTo("id", id).findFirst();
    }

    @Override
    public void addItemList(final List<Resource> itemList) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(itemList);
            }
        });
    }

    @Override
    public int size() {
        return (int) realm.where(Resource.class).count();
    }
}
