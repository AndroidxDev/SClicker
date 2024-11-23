package com.devstudios.sclicker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {

  private View view;
  public Button bclick;
  public TextView clickst;
  public int clicks;
  public int level;

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.home_fragment, container, false);
    bclick = view.findViewById(R.id.bclick);
    clickst = view.findViewById(R.id.clicks);

    SharedPreferences preference = getActivity().getSharedPreferences("Prefs",Context.MODE_PRIVATE);
    clicks = preference.getInt("clicks", 1);
	level = preference.getInt("newlevel", 1);
    clickst.setText("Текущие клики: " + clicks);

    bclick.setOnClickListener(
        v -> {
          clicks = clicks + level;
          clickst.setText("Текущие клики: " + clicks);

          SharedPreferences.Editor editor = preference.edit();
          editor.putInt("clicks", clicks);
          editor.apply();
        });

    return view;
  }
  
    @Override
  public void onResume() {
    super.onResume();
	
	SharedPreferences preference = getActivity().getSharedPreferences("Prefs",Context.MODE_PRIVATE);
    clicks = preference.getInt("clicks", 1);
	level = preference.getInt("newlevel", 1);
  }
}
