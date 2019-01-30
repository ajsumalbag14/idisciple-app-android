package com.ph.idisciple.idiscipleapp.data.local.repository.workshop;

import com.ph.idisciple.idiscipleapp.data.local.model.Workshop;

import java.util.ArrayList;
import java.util.List;

public interface IWorkshopRepository {
    ArrayList<Workshop> getContentList();

    Workshop findItemById(String id);

    void addItemList(List<Workshop> itemList);

    int size();
}
