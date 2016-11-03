package de.visionera.fragments;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * 
 * @author Arno Becker, Evgeny Zinovyev. c2011 visionera GmbH
 *
 */
public class MainActivity extends Activity implements
    ActionBar.TabListener {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    Directory.initializeDirectory(this);

    ActionBar bar = getActionBar();

    for (int i = 0; i < Directory.getCategoryCount(); i++) {
      bar.addTab(bar.newTab()
          .setText(Directory.getCategory(i).getName())
          .setTabListener(this));
    }

    bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
        | ActionBar.DISPLAY_USE_LOGO);
    bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    bar.setDisplayShowHomeEnabled(true);
  }

  @Override
  public void onTabSelected(Tab tab, FragmentTransaction ft) {
    final ArticleListFragment articleListFragment =
        (ArticleListFragment) getFragmentManager()
            .findFragmentById(R.id.article_list);

    articleListFragment.populateArticleList(tab
        .getPosition());
    articleListFragment.selectPosition(0);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.item_shoppingcart:

        final Fragment shoppingCart =
            getFragmentManager().findFragmentById(
                R.id.cart_content);
        
        FragmentTransaction ft = getFragmentManager()
            .beginTransaction();
        ft.setCustomAnimations(android.R.animator.fade_in,
            android.R.animator.fade_out);
        TextView tv = (TextView) findViewById(R.id.txt_drag_drop);
        
        if (shoppingCart.isHidden()) {
          ft.show(shoppingCart);
          tv.setVisibility(TextView.VISIBLE);
        } else {
          ft.hide(shoppingCart);
          tv.setVisibility(TextView.GONE);
        }
        ft.commit();
        break;
      case R.id.item_help:
        // TODO - FragmentDialog
        break;
      case R.id.item_show:
        final CartFragment cartFragment =
            (CartFragment) getFragmentManager()
                .findFragmentById(R.id.cart_content);

        final Intent intent = new Intent(this,
            ShowActivity.class);
        intent.putExtra("cartitems", cartFragment
            .getCartItems().toArray(new CartItem[3]));
        startActivity(intent);
        break;
      default:
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onTabReselected(Tab tab,
      FragmentTransaction ft) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onTabUnselected(Tab tab,
      FragmentTransaction ft) {
    // TODO Auto-generated method stub

  }
}