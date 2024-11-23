package com.devstudios.sclicker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class UpdaterFragment extends Fragment {

  private View view;
  public Button newlevel;
  public TextView levelt, pricet;
  public int level;
  public int price;
  public int clicks;

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.updater_fragment, container, false);
    newlevel = view.findViewById(R.id.newlev);
    levelt = view.findViewById(R.id.level);
    pricet = view.findViewById(R.id.price);
    SharedPreferences preference =
        getActivity().getSharedPreferences("Prefs", Context.MODE_PRIVATE);
    level = preference.getInt("newlevel", 1);
    price = preference.getInt("price", 100);
    clicks = preference.getInt("clicks", 1);
    levelt.setText("level: " + level);
    pricet.setText("price: " + price);
    newlevel.setOnClickListener(
        v -> {
          if (clicks >= price) {
            clicks = clicks - price;
            price = price * 2;
            level = level + 1;

            SharedPreferences.Editor editor = preference.edit();
            editor.putInt("clicks", clicks);
            editor.apply();

            editor.putInt("newlevel", level);
            editor.apply();

            editor.putInt("price", price);
            editor.apply();

            levelt.setText("level: " + level);
            pricet.setText("price: " + price);
          }
        });
    return view;
  }

  @Override
  public void onResume() {
    super.onResume();

    SharedPreferences preference =
        getActivity().getSharedPreferences("Prefs", Context.MODE_PRIVATE);
    clicks = preference.getInt("clicks", 1);
  }
}
