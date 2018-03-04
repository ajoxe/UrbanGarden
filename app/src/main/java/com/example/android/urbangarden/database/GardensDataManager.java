package com.example.android.urbangarden.database;

import android.os.AsyncTask;

/**
 * Created by amirahoxendine on 3/3/18.
 */

public class GardensDataManager {
    //populate db
    //get a list of saved
    //update saved
    //delete

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
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
