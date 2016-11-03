package de.visionera.fragments;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * @author Arno Becker, Evgeny Zinovyev. c2011 visionera
 *         GmbH
 */
public class CartArrayAdapter extends
    ArrayAdapter<CartItem> {

  private List<CartItem> mItems;

  public CartArrayAdapter(Context context,
      List<CartItem> items) {
    super(context, R.layout.cart_list_item, items);

    mItems = items;
  }

  private CartArrayAdapter(Context context,
      int textViewResourceId) {
    super(context, textViewResourceId);
  }

  @Override
  public View getView(int position, View convertView,
      ViewGroup parent) {
    View v = convertView;

    if (v == null) {
      LayoutInflater vi = (LayoutInflater) getContext()
          .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      v = vi.inflate(R.layout.cart_list_item, null);
    }
    CartItem cartItem = mItems.get(position);
    if (cartItem != null) {
      TextView cartItemText = (TextView) v
          .findViewById(R.id.txt_cart_item);
      cartItemText.setText(cartItem.itemName);
    }
    return v;
  }

}
