package de.visionera.fragments;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * 
 * @author Arno Becker, Evgeny Zinovyev. c2011 visionera GmbH
 *
 */
public class ArticleDetailsFragment extends Fragment {

  private static final String TAG =
      ArticleDetailsFragment.class.getSimpleName();

  @Override
  public View onCreateView(LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {
    Log.d(TAG, "onCreateView(): entered...");
    final View view = inflater.inflate(
        R.layout.details_layout, null);

    view.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        final ActionBar bar = getActivity()
            .getActionBar();
        if (bar != null) {
          if (bar.isShowing()) {
            bar.hide();
          } else {
            bar.show();
          }
        }
      }
    });
    return view;
  }

  public void updateDetails(final int category,
      final int position) {
    Log.d(TAG, "updateDetails(): Category: " + category);
    Log.d(TAG, "updateDetails(): Position: " + position);

    final ImageView imageView = (ImageView) getView()
        .findViewById(R.id.image);
    imageView.setImageDrawable(Directory
        .getCategory(category).getEntry(position)
        .getDrawable(getResources()));
  }
}
