package com.ph.idisciple.idiscipleapp.data.local.repository.Speaker;

import com.ph.idisciple.idiscipleapp.data.local.model.Speaker;

import java.util.ArrayList;
import java.util.List;

public interface ISpeakerRepository {
    ArrayList<Speaker> getContentList();

    Speaker findItemById(String id);

    void addItemList(List<Speaker> itemList);

    int size();
}
