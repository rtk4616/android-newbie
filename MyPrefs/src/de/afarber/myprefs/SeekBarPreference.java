package de.afarber.myprefs;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SeekBarPreference extends Preference implements OnSeekBarChangeListener {
	private SeekBar mSeekBar;
	private int mProgress;

	public SeekBarPreference(Context context) {
		this(context, null, 0);
	}

	public SeekBarPreference(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SeekBarPreference(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setLayoutResource(R.layout.preference_seekbar);
	}

	@Override
    protected void onBindView(View view) {
		super.onBindView(view);
		mSeekBar = (SeekBar) view.findViewById(R.id.seekbar);
		mSeekBar.setProgress(mProgress);
		mSeekBar.setOnSeekBarChangeListener(this);
	}
	
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		if (!fromUser)
			return;
		
		setValue(progress);
	}
	
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// not used
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// not used
	}
	
	@Override
	protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
		setValue(restoreValue ? getPersistedInt(mProgress) : (Integer) defaultValue);
	}

	public void setValue(int value) {
		if (shouldPersist()) {
			persistInt(value);
		}
		
		if (value != mProgress) {
			mProgress = value;
			notifyChanged();
		}
	}
	
	@Override
	protected Object onGetDefaultValue(TypedArray a, int index) {
	    return a.getInt(index, 0);
	}
}

