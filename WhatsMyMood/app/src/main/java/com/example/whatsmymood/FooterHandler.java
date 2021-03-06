package com.example.whatsmymood;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Malcolm on 2017-03-08.
 *@author mtfische
 *
 */

public class FooterHandler {
    /**
     * FooterHandler handles the footer view that is displayed in activities
     * Creates on click listners and handles page transitions
     *
     * @param v            The footer View to be passed in and handled
     * @param mContext     The context of the main activity which calls the handler
     * @param dialog       dialog is the dialog the handler displayes on certain button presses
     * @param dialogActive boolean to see if the dialog is showing :: DEPRECIATED
     */
    private View v;
    private Context mContext;
    private Dialog dialog;
    private AddMoodController moodController;
    private boolean dialogActive;

    /**
     * Blank Constructor
     */
    public FooterHandler(){};

    /**
     * Constructor to attach a view and context as well as initialize boolean values
     * build is called at the end to initialize the onclick listners
     *
     * @param mContext The context of the main activity which calls the handler
     * @param v        The footer View to be passed in and handled
     * @see build
     */
    public FooterHandler(Context mContext, View v){
        this.v = v;
        this.mContext = mContext;
        dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.add_mood_popup);
        moodController = new AddMoodController(mContext,dialog,v);
        build();
    }

    /**
     * Setter to set the context
     *
     * @param mContext The context of the main activity which calls the handler
     */
    public void setContext(Context mContext){
        this.mContext = mContext;
    }

    /**
     * Setter to set the view to be handled build is called after a view shift
     *
     * @param v The footer View to be passed in and handled
     * @see build
     */
    public void setView(View v){
        this.v = v;
        build();
    }

    /**
     * Builder function which initializes the onClickListeners for the footer buttons
     */
    private void build(){
        //TODO implementation
        this.v.findViewById(R.id.follow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO need a follow hub activity
                Log.d("intent", "intent follow");
                Intent intent = new Intent(mContext, FollowHubActivity.class);
                mContext.startActivity(intent);
            }
        });

        this.v.findViewById(R.id.profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO need a profile activity
                Log.d("intent", "intent profile");
            }
        });


        // http://stackoverflow.com/questions/5934050/check-whether-activity-is-active March 13, 2017 15:17
        this.v.findViewById(R.id.home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> runningactivities = new ArrayList<String>();

                ActivityManager activityManager = (ActivityManager) mContext.getSystemService (Context.ACTIVITY_SERVICE);

                List<ActivityManager.RunningTaskInfo> services = activityManager.getRunningTasks(Integer.MAX_VALUE);

                for (int i1 = 0; i1 < services.size(); i1++) {
                    runningactivities.add(0,services.get(i1).topActivity.toString());
                }

                if(runningactivities.contains("ComponentInfo{com.example.whatsmymood/com.example.whatsmymood.MainActivity}")==true){
                    Toast.makeText(mContext, "Currently In Home", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    mContext.startActivity(intent);
                }

            }
        });

        this.v.findViewById(R.id.map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("intent", "intent map");
                Intent intent = new Intent (mContext, MapsActivity.class);
                mContext.startActivity(intent);
            }
        });

        this.v.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("intent", "intent dialog");
                if(!dialog.isShowing()) {
                    dialog.show();
                }else{
                    dialog.dismiss();
                }
            }
        });
    }

}
