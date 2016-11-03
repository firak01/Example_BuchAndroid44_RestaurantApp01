package de.visionera.fragments;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * @author Arno Becker, Evgeny Zinovyev. c2011 visionera
 *         GmbH
 */
public class ShowActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.show_layout);

    final LinearLayout linearLayout =
        (LinearLayout) findViewById(R.id.show_layout_id);

    final Animator[] animators = createAnimations();

    final LayoutTransition transition = new LayoutTransition();
    transition.setDuration(2000);
    linearLayout.setLayoutTransition(transition);

    final Parcelable[] items = getIntent()
        .getParcelableArrayExtra("cartitems");
    int i = 0;
    for (Parcelable cartItem : items) {
      if (cartItem != null && cartItem instanceof CartItem) {
        transition.setAnimator(LayoutTransition.APPEARING,
            animators[i++]);

        final ImageView imageView = new ImageView(this);
        imageView
            .setImageResource(((CartItem) cartItem).imageRessourceId);
        imageView.setAdjustViewBounds(true);
        imageView.setMaxWidth(420);
        linearLayout.addView(imageView);
      }
    }
  }

  private Animator[] createAnimations() {
    final PropertyValuesHolder propValuesOne =
        PropertyValuesHolder.ofFloat("y", -420f, 400f);
    final PropertyValuesHolder propValuesTwo =
        PropertyValuesHolder.ofFloat("y", 800f, 0f);

    final Animator firstImageAnimation =
        ObjectAnimator.ofPropertyValuesHolder(this,
            propValuesOne);
    final Animator secondImageAnimation =
        ObjectAnimator.ofPropertyValuesHolder(this,
            propValuesTwo);
    final Animator thirdImageAnimation =
        ObjectAnimator.ofPropertyValuesHolder(this,
            propValuesOne);

    return new Animator[] { firstImageAnimation,
        secondImageAnimation, thirdImageAnimation };
  }
}
