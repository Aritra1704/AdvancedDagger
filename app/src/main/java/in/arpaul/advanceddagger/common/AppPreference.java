package in.arpaul.advanceddagger.common;

import android.content.Context;
import android.content.SharedPreferences;

import in.arpaul.advanceddagger.R;

import static android.content.Context.MODE_PRIVATE;

public class AppPreference {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public AppPreference(Context context) {
        preferences	=	context.getSharedPreferences(context.getString(R.string.app_name), MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setData(String strKey, String strValue) {
        editor.putString(strKey, strValue);
        commitPreference();
    }

    public void removeFromPreference(String strKey) {
        editor.remove(strKey);
    }

    public void commitPreference() {
        editor.commit();
    }

    public String getDataPref(String strKey) {
        return preferences.getString(strKey, "");
    }

    public String getDataPref(String strKey, String defaultValue) {
        return preferences.getString(strKey, defaultValue);
    }

    public void  clear() {
        editor.clear();
        commitPreference();
    }
}
