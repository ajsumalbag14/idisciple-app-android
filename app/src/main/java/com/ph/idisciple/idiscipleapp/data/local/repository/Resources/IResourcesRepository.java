package com.ph.idisciple.idiscipleapp.data.local.repository.Resources;

import com.ph.idisciple.idiscipleapp.data.local.model.Resource;

import java.util.ArrayList;
import java.util.List;

public interface IResourcesRepository {

    ArrayList<Resource> getContentList();

    Resource findItemById(String id);

    void addItemList(List<Resource> itemList);

    int size();

    void resetStorage();

}
