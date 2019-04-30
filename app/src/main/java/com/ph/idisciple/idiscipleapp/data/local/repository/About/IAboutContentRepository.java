package com.ph.idisciple.idiscipleapp.data.local.repository.About;

import com.ph.idisciple.idiscipleapp.data.local.model.AboutContent;

import java.util.ArrayList;
import java.util.List;

public interface IAboutContentRepository {
    ArrayList<AboutContent> getContentList();

    AboutContent findItemById(String id);

    void addItemList(List<AboutContent> itemList);

    int size();
}
