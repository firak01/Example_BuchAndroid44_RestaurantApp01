package de.visionera.fragments;

import android.app.ListFragment;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener;

/**
 * 
 * @author Arno Becker, Evgeny Zinovyev. c2011 visionera GmbH
 *
 */
public class ArticleListFragment extends ListFragment {

  private int mCategory = 0;
  private int mCurPosition = 0;

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    populateArticleList(0);

    selectPosition(mCurPosition);

    ListView lv = getListView();
    lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    lv.setCacheColorHint(Color.TRANSPARENT);
    
    lv.setOnItemLongClickListener(new OnItemLongClickListener() {
      public boolean onItemLongClick(AdapterView<?> av,
          View v, int pos, long id) {
        showDetails(pos);

        final String title = (String) ((TextView) v)
            .getText();

        final Intent intent = new Intent();
        intent.putExtra("category", mCategory);
        intent.putExtra("position", pos);
        final ClipData data = ClipData.newIntent(title,
            intent);
        v.startDrag(data, new MyDragShadowBuilder(v), null,
            0);

        return true;
      }
    });
  }

  @Override
  public void onListItemClick(ListView l, View v,
      int position, long id) {
    showDetails(position);
  }

  public void populateArticleList(int category) {
    DirectoryCategory cat = Directory.getCategory(category);
    String[] items = new String[cat.getEntryCount()];
    for (int i = 0; i < cat.getEntryCount(); i++) {
      items[i] = cat.getEntry(i).getName();
    }
    setListAdapter(new ArrayAdapter<String>(getActivity(),
        android.R.layout.simple_list_item_activated_1,
        items));
    mCategory = category;
  }

  public void selectPosition(int position) {
    ListView lv = getListView();
    lv.setItemChecked(position, true);
    showDetails(position);
  }

  void showDetails(int position) {
    mCurPosition = position;

    ArticleDetailsFragment articleDetailsFragment =
        (ArticleDetailsFragment) getFragmentManager()
            .findFragmentById(R.id.article_details);
    articleDetailsFragment.updateDetails(mCategory,
        position);
  }

  private class MyDragShadowBuilder extends
      View.DragShadowBuilder {
    private Drawable mShadow;

    public MyDragShadowBuilder(View v) {
      super(v);
      mShadow = new ColorDrawable(Color.LTGRAY);
      mShadow.setBounds(0, 0, v.getWidth(), v.getHeight());
    }

    @Override
    public void onDrawShadow(Canvas canvas) {
      super.onDrawShadow(canvas);
      mShadow.draw(canvas);
    }
  }
}
