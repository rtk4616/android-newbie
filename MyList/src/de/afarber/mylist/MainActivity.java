package de.afarber.mylist;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import de.afarber.mylist.MyListFragment.ListListener;
import de.afarber.mylist.MyMainFragment.MainListener;

public class MainActivity extends Activity 
    implements MyConstants, MainListener, ListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if (savedInstanceState == null) {
            Fragment fragment = new MyMainFragment();
            getFragmentManager().beginTransaction()
            	.replace(R.id.root, fragment, MAIN)
                .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	@Override
	public void selectedButtonClicked() {
		Fragment fragment = new MyListFragment();
        getFragmentManager().beginTransaction()
            .addToBackStack(null)
            .replace(R.id.root, fragment, LIST)
            .commit();
	}
	
	@Override
	public void itemSelected(int index) {
		setIndex(index);
		// dismiss MyListFragment and show MyMainFragment again
		getFragmentManager().popBackStack();
	}
	
	@Override
	public int getIndex() {
		SharedPreferences prefs = getSharedPreferences(PREFS, 0);
		return prefs.getInt(INDEX, -1);
	}
	
	private void setIndex(int index) {
		SharedPreferences prefs = getSharedPreferences(PREFS, 0);
		Editor editor = prefs.edit();
		editor.putInt(INDEX, index);
		editor.commit();
	}
}
