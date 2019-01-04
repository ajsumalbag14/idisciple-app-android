package com.ph.idisciple.idiscipleapp.data.local.repository.Schedule;

import com.ph.idisciple.idiscipleapp.data.local.model.Schedule;
import com.ph.idisciple.idiscipleapp.data.local.repository.BaseRepository;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class ScheduleRepository extends BaseRepository implements IScheduleRepository {
    @Override
    public ArrayList<Schedule> getContentList() {
        RealmResults<Schedule> realmResults = realm.where(Schedule.class).findAll();
        ArrayList<Schedule> arrayList = new ArrayList<>();
        arrayList.addAll(realmResults);
        return arrayList;
    }

    @Override
    public Schedule findItemById(int speakerId) {
        return realm.where(Schedule.class).equalTo("id", speakerId).findFirst();
    }

    @Override
    public void addItemList(final List<Schedule> schedules) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(schedules);
            }
        });
    }

    @Override
    public int size() {
        return (int) realm.where(Schedule.class).count();
    }
}
