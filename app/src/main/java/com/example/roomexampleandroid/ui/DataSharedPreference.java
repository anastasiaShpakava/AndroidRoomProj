package com.example.roomexampleandroid.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class DataSharedPreference {
    private static DataSharedPreference spInstance = null;
    private static final String APP_PREFERENCES = "myPreferences";

    public static DataSharedPreference getSPInstance(){
        if (spInstance == null)
            spInstance = new DataSharedPreference();
        return spInstance;
    }
   protected void saveInfo( Context context,String savedApiKey){
        SharedPreferences  sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
       editor.putString("saved_text", savedApiKey);
       editor.apply();
       Toast.makeText(context, "Text saved", Toast.LENGTH_SHORT).show();
       if (!sharedPreferences.contains(savedApiKey)) {
           editor.apply();
           Toast.makeText(context, "Text saved", Toast.LENGTH_SHORT).show();
       } else {
           Toast.makeText(context, "Text saved already", Toast.LENGTH_SHORT).show();
       }
   }

    protected String loadText(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getString("saved_text", null);
    }
}
