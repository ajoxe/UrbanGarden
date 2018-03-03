package com.example.android.urbangarden.Networking;

import com.example.android.urbangarden.model.Garden;

/**
 * Created by amirahoxendine on 3/3/18.
 */


public interface RetrofitListener {
    void updateUI(Garden[] gardens);
    void onFailureAlert();



//    method to alert user of network failure

//    private void alertUserAboutError() {
//        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
//        alertDialogFragment.show(getFragmentManager(), "error_dialog");

//    }



}
