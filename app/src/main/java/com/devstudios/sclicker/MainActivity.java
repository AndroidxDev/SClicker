package com.devstudios.sclicker;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;

public class MainActivity extends AppCompatActivity {

  private BottomNavigationView bottomNavigationView;
  private FrameLayout frameLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    bottomNavigationView = findViewById(R.id.bottomNavView);
    frameLayout = findViewById(R.id.frameLayout);
    bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_SELECTED);
    bottomNavigationView.setOnNavigationItemSelectedListener(
        new BottomNavigationView.OnNavigationItemSelectedListener() {

          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int itemId = item.getItemId();

            if (itemId == R.id.navHome) {
              lf(new MainFragment(), false);
            } else if (itemId == R.id.navUp) {
              lf(new UpdaterFragment(), false);
            }
            return true;
          }
        });
    lf(new MainFragment(), true);

    float cornerR = 999f;
    MaterialShapeDrawable background = (MaterialShapeDrawable) bottomNavigationView.getBackground();
    ShapeAppearanceModel shapeAppearanceModel =
        background.getShapeAppearanceModel().toBuilder()
            .setTopLeftCornerSize(cornerR)
            .setTopRightCornerSize(cornerR)
            .setBottomLeftCornerSize(cornerR)
            .setBottomRightCornerSize(cornerR)
            .build();
    background.setShapeAppearanceModel(shapeAppearanceModel);
  }

  private void lf(Fragment fragment, boolean isAppInitialized) {

    FragmentManager fragmentManager = getSupportFragmentManager();
    Fragment currentFragment = fragmentManager.findFragmentById(R.id.frameLayout);

    if (currentFragment != null && currentFragment.getClass().equals(fragment.getClass())) {
      return;
    }

    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.setCustomAnimations(
        R.anim.anim_in, R.anim.anim_out, R.anim.anim_out, R.anim.anim_in);
    if (isAppInitialized) {
      fragmentTransaction.add(R.id.frameLayout, fragment);
    } else {
      fragmentTransaction.replace(R.id.frameLayout, fragment);
    }
    fragmentTransaction.commit();
  }
}
