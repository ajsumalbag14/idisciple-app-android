package com.ph.idisciple.idiscipleapp.data.local.repository;

import io.realm.Realm;

public abstract class BaseRepository {

    protected Realm realm;

    public BaseRepository() {
        realm = Realm.getDefaultInstance();
    }

    public BaseRepository(Realm realm) {
        this.realm = realm;
    }
}
