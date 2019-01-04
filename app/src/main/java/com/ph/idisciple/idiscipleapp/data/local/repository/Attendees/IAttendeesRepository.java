package com.ph.idisciple.idiscipleapp.data.local.repository.Attendees;

import com.ph.idisciple.idiscipleapp.data.local.model.Profile;

import java.util.ArrayList;
import java.util.List;

public interface IAttendeesRepository {
    ArrayList<Profile> getContentList();

    Profile findItemById(int id);

    void addItemList(List<Profile> itemList);

    int size();
}
