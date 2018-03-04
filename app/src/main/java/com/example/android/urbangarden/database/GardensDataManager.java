package com.example.android.urbangarden.database;

import android.os.AsyncTask;

import com.example.android.urbangarden.model.Garden;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amirahoxendine on 3/3/18.
 */

public class GardensDataManager {
    //populate db
    //get a list of saved
    //update saved
    //delete
    public void populateDBWithList(final List<Garden> gardenList, final GardensDatabase db){
        new GardensDBTasks(new DatabaseTaskListener() {
            @Override
            public void backgroundTask() {
                db.gardensDao().insertAllGardens(gardenList);
            }

            @Override
            public void taskPostExecute() {

            }
        }).execute();
    }

    public List<Garden> getSavedGardens(final GardensDatabase db){
        final List<Garden> savedGardens = new ArrayList<>();
        new GardensDBTasks(new DatabaseTaskListener() {
            @Override
            public void backgroundTask() {
               savedGardens.addAll(db.gardensDao().getSaved());
            }

            @Override
            public void taskPostExecute() {

            }
        }).execute();
        return savedGardens;
    }



    public interface DatabaseTaskListener{
        void backgroundTask();
        void taskPostExecute();
    }

    public class GardensDBTasks extends AsyncTask<Void, Void, Void>{
        DatabaseTaskListener taskListener;

        public GardensDBTasks(DatabaseTaskListener listener){
            this.taskListener = listener;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskListener.backgroundTask();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            taskListener.taskPostExecute();
        }
    }
}
