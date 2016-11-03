package de.visionera.fragments;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Arno Becker, Evgeny Zinovyev. c2011 visionera
 *         GmbH
 */
public class CartItem implements Parcelable {

  public int categoryId;
  public int entryId;
  public int imageRessourceId;
  public String itemName;

  public static final Parcelable.Creator<CartItem> CREATOR = new Parcelable.Creator<CartItem>() {
    public CartItem createFromParcel(Parcel in) {
      return new CartItem(in);
    }

    public CartItem[] newArray(int size) {
      return new CartItem[size];
    }
  };

  public CartItem() {

  }

  private CartItem(Parcel in) {
    readFromParcel(in);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(categoryId);
    dest.writeInt(entryId);
    dest.writeInt(imageRessourceId);
    dest.writeString(itemName);
  }

  public void readFromParcel(Parcel in) {
    categoryId = in.readInt();
    entryId = in.readInt();
    imageRessourceId = in.readInt();
    itemName = in.readString();
  }
}
