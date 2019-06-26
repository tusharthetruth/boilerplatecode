package in.boilerplatecode.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class PrefrenceUtils {

    private static SharedPreferences mPref;
    private static SharedPreferences.Editor mPrefEditor;

    public static void init(Context context) {
        mPref = PreferenceManager.getDefaultSharedPreferences(context);
        mPrefEditor = mPref.edit();

    }

    public static void destroy() {
        mPrefEditor.apply();
        mPrefEditor = null;
        mPref = null;
    }

    public static SharedPreferences getPrefs() {
        return mPref;
    }

    public static void registerListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        mPref.registerOnSharedPreferenceChangeListener(listener);
    }

    public static void unregisterListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        mPref.unregisterOnSharedPreferenceChangeListener(listener);
    }

    public static SharedPreferences.Editor put(String key, Boolean value) {
        if (value == null) {
            mPrefEditor.remove(key);
        } else {
            mPrefEditor.putBoolean(key, value);
        }
        return mPrefEditor;
    }

    public static SharedPreferences.Editor put(String key, Float value) {
        if (value == null) {
            mPrefEditor.remove(key);
        } else {
            mPrefEditor.putFloat(key, value);
        }
        return mPrefEditor;
    }

    public static SharedPreferences.Editor put(String key, Integer value) {
        if (value == null) {
            mPrefEditor.remove(key);
        } else {
            mPrefEditor.putInt(key, value);
        }
        return mPrefEditor;
    }

    public static SharedPreferences.Editor put(String key, Long value) {
        if (value == null) {
            mPrefEditor.remove(key);
        } else {
            mPrefEditor.putLong(key, value);
        }
        return mPrefEditor;
    }

    public static SharedPreferences.Editor put(String key, String value) {
        if (TextUtils.isEmpty(value)) {
            mPrefEditor.remove(key);
        } else {
            mPrefEditor.putString(key, value);
        }
        return mPrefEditor;
    }

    public static SharedPreferences.Editor addToSet(String key, String... values) {
        if (values == null || values.length == 0) {
            mPrefEditor.remove(key).apply();
        } else {
            Set<String> existing = getPrefs().getStringSet(key, new HashSet<String>(values.length));
            existing.addAll(Arrays.asList(values));
            mPrefEditor.putStringSet(key, existing).apply();
        }
        return mPrefEditor;
    }

    public static SharedPreferences.Editor addToSet(String key, Set<String> values) {
        if (values == null || values.size() == 0) {
            mPrefEditor.remove(key).apply();
        } else {
            Set<String> existing = getPrefs().getStringSet(key, new HashSet<String>(values.size()));
            existing.addAll(values);
            mPrefEditor.putStringSet(key, existing).apply();
        }
        return mPrefEditor;
    }

    public static SharedPreferences.Editor removeFromSet(String key, String value) {
        Set<String> existing = getPrefs().getStringSet(key, null);
        if (existing != null) {
            existing.remove(value);
            if (existing.size() == 0) {
                mPrefEditor.remove(key).apply();
            } else {
                mPrefEditor.putStringSet(key, existing).apply();
            }
        }
        return mPrefEditor;
    }

    public static void clearAll() {
        mPrefEditor.clear().commit();
    }

    public static String getString(String key, String defaultValue) {
        return getPrefs().getString(key, defaultValue);
    }

    public static float getFloat(String key, Float defaultValue) {
        return getPrefs().getFloat(key, defaultValue);
    }

    public static Long getLong(String key, Long defaultValue) {
        return getPrefs().getLong(key, defaultValue);
    }

    public static Boolean getBoolean(String key, Boolean defaultValue) {
        return getPrefs().getBoolean(key, defaultValue);
    }
}

