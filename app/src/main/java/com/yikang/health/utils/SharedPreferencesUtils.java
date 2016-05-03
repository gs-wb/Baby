package com.yikang.health.utils;

import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

public class SharedPreferencesUtils {

	private SharedPreferences preferences;

	public SharedPreferencesUtils(Context context) {
		preferences = context.getSharedPreferences("config",
				Context.MODE_APPEND);
	}

	public void save(String key, String value) {
		Editor editor = preferences.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	public void saveBalance(String key, String value) {
		if(TextUtils.isEmpty(value))return;
		Editor editor = preferences.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	public void saveBoolean(String key, boolean value) {
		Editor editor = preferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public void saveInt(String key, int value) {
		Editor editor = preferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public int getIntParam(String key) {
		return preferences.getInt(key, 0);
	}

	public String getParam(String key) {
		return preferences.getString(key, null);
	}
	
	public boolean getBoolean(String key) {
		return preferences.getBoolean(key, false);
	}

	public void saveMany(Map<String, String> map) {
		Editor editor = preferences.edit();
		for (String key : map.keySet()) {
			String value = map.get(key).toString();
			editor.putString(key, value);
		}
		editor.commit();
	}

	public void clear() {
		Editor editor = preferences.edit();
		editor.clear();
		editor.commit();
	}
	
	public void remove(String key) {
		Editor editor = preferences.edit();
		editor.remove(key);
		editor.commit();
	}
}
