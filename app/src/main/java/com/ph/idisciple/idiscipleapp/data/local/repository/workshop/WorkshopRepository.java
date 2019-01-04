package com.ph.idisciple.idiscipleapp.data.local.repository.workshop;

import com.ph.idisciple.idiscipleapp.data.local.model.Workshop;
import com.ph.idisciple.idiscipleapp.data.local.repository.BaseRepository;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class WorkshopRepository extends BaseRepository implements IWorkshopRepository {
    @Override
    public ArrayList<Workshop> getContentList() {
        RealmResults<Workshop> realmResults = realm.where(Workshop.class).findAll();
        ArrayList<Workshop> arrayList = new ArrayList<>();
        arrayList.addAll(realmResults);
        return arrayList;
    }

    @Override
    public Workshop findItemById(int speakerId) {
        return realm.where(Workshop.class).equalTo("id", speakerId).findFirst();
    }

    @Override
    public void addItemList(final List<Workshop> speakers) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(speakers);
            }
        });
    }

    @Override
    public int size() {
        return (int) realm.where(Workshop.class).count();
    }
}
