package com.ph.idisciple.idiscipleapp.data.local.repository.FamilyGroups;

import com.ph.idisciple.idiscipleapp.data.local.model.FamilyGroup;
import com.ph.idisciple.idiscipleapp.data.local.repository.BaseRepository;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class FamilyGroupRepository extends BaseRepository implements IFamilyGroupRepository {
    @Override
    public ArrayList<FamilyGroup> getContentList() {
        RealmResults<FamilyGroup> realmResults = realm.where(FamilyGroup.class).findAll();
        ArrayList<FamilyGroup> arrayList = new ArrayList<>();
        arrayList.addAll(realmResults);
        return arrayList;
    }

    @Override
    public FamilyGroup findItemById(String id) {
        return realm.where(FamilyGroup.class).equalTo("id", id).findFirst();
    }

    @Override
    public void addItemList(final List<FamilyGroup> itemList) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(itemList);
            }
        });
    }

    @Override
    public int size() {
        return (int) realm.where(FamilyGroup.class).count();
    }
}
