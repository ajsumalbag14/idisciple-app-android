package com.ph.idisciple.idiscipleapp.data.local.repository.Country;

import com.ph.idisciple.idiscipleapp.data.local.model.Country;

import java.util.ArrayList;
import java.util.List;

public interface ICountryRepository {

    ArrayList<Country> getContentList();

    Country findItemById(String id);

    void addItemList(List<Country> itemList);

    int size();

    void resetStorage();

}
