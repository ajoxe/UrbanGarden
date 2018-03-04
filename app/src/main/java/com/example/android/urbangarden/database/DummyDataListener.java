package com.example.android.urbangarden.database;

import com.example.android.urbangarden.model.Garden;

import java.util.List;

/**
 * Created by amirahoxendine on 3/4/18.
 */

public interface DummyDataListener {
    void postExecute(List<Garden> gardenList);
}
