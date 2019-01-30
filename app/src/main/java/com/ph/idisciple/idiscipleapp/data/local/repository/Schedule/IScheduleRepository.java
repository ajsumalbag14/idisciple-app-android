package com.ph.idisciple.idiscipleapp.data.local.repository.Schedule;

import com.ph.idisciple.idiscipleapp.data.local.model.Schedule;

import java.util.ArrayList;
import java.util.List;

public interface IScheduleRepository {
    ArrayList<Schedule> getContentList();

    Schedule findItemById(String id);

    void addItemList(List<Schedule> itemList);

    int size();
}
