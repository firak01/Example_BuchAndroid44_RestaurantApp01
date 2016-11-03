package de.visionera.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.ListFragment;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.content.ClipData.Item;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ListView;

/**
 * @author Arno Becker, Evgeny Zinovyev. c2011 visionera
 *         GmbH
 */
public class CartFragment extends ListFragment {

  private static final String TAG =
      CartFragment.class.getSimpleName();

  private final List<CartItem> mCartItems = new ArrayList<CartItem>();

  private CartArrayAdapter mListAdapter;

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    Log.d(TAG, "onActivityCreated(): entered...");

    mListAdapter =
        new CartArrayAdapter((Context) getActivity(),
            mCartItems);
    setListAdapter(mListAdapter);
    
    populateCart();

    final ListView listView = getListView();
    listView.setOnDragListener(new View.OnDragListener() {
      @Override
      public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()) {
          case DragEvent.ACTION_DRAG_STARTED:
            Log.d(TAG, "onDrag(): Drag started...");
            return processDragStarted(event);
          case DragEvent.ACTION_DROP:
            Log.d(TAG, "onDrag(): Dropped...");
            return processDrop(event);
        }
        return false;
      }
      
      
    });
  }

  private boolean processDragStarted(DragEvent event) {
    ClipDescription clipDesc = event.getClipDescription();
    if (clipDesc != null) {
      return clipDesc
          .hasMimeType(ClipDescription.MIMETYPE_TEXT_INTENT);
    }
    return false;
  }

  boolean processDrop(DragEvent event) {
    final ClipData data = event.getClipData();
    Log.d(TAG, "processDrop(): Data: " + data);
    if (data != null) {
      Log.d(
          TAG,
          "processDrop(): Items to drop: "
              + data.getItemCount());
      if (data.getItemCount() > 0) {
        final Item item = data.getItemAt(0);
        final Intent intent = item.getIntent();
        if (intent != null) {
          final int categoryId = intent.getIntExtra(
              "category", -1);
          final int entryId = intent.getIntExtra(
              "position", -1);

          // Warenkorb aktualisieren:
          final DirectoryCategory cat = Directory
              .getCategory(categoryId);
          final DirectoryEntry entry = cat
              .getEntry(entryId);

          // bei gleicher Kategorie: ersetzen...
          for (int pos = 0; pos < mCartItems.size(); pos++) {
            if (mCartItems.get(pos).categoryId == categoryId) {
              mCartItems.remove(pos);
            }
          }

          // Artikel dem Warenkorb hinzufügen:
          CartItem cartItem = new CartItem();
          cartItem.categoryId = categoryId;
          cartItem.entryId = entryId;
          cartItem.itemName = entry.getName();
          cartItem.imageRessourceId = entry.getResourceId();
          mCartItems.add(cartItem);

          populateCart();

          return true;
        }
      }
    }
    return false;
  }

  @Override
  public void onListItemClick(ListView l, View v,
      int position, long id) {
    Log.d(TAG, "onListItemClick(): position = " + position);
    ArticleDetailsFragment articleDetailsFragment =
        (ArticleDetailsFragment) getFragmentManager()
            .findFragmentById(R.id.article_details);

    CartItem cartItem = mListAdapter.getItem(position);
    articleDetailsFragment.updateDetails(
        cartItem.categoryId, cartItem.entryId);
  }

  private void populateCart() {
    mListAdapter.notifyDataSetChanged();
  }

  public List<CartItem> getCartItems() {
    return mCartItems;
  }
}
