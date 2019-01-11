package com.ph.idisciple.idiscipleapp.data.local.repository.FamilyGroups;

import com.ph.idisciple.idiscipleapp.data.local.model.FamilyGroup;
import com.ph.idisciple.idiscipleapp.data.local.model.Profile;

import java.util.ArrayList;
import java.util.List;

public interface IFamilyGroupRepository {
    ArrayList<FamilyGroup> getContentList();

    FamilyGroup findItemById(int id);

    void addItemList(List<FamilyGroup> itemList);

    int size();
}
