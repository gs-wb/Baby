package com.yikang.health.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 自定义RecyclingImageView(内存优化)
 * @author
 *
 */
public class RecyclingImageView extends ImageView {

	public RecyclingImageView(Context context) {
        super(context);
    }
	
	public RecyclingImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onDetachedFromWindow() {
		setImageDrawable(null);
		setBackgroundDrawable(null);
//		FileLoaderManager.getInstance().removeKeyCacheWhileOnDetachedFromWindow(this);
		super.onDetachedFromWindow();
	}
	
	@Override
	public void setImageDrawable(Drawable drawable) {
		
        final Drawable previousDrawable = getDrawable();

        super.setImageDrawable(drawable);
        
        notifyDrawable(drawable, true);

        notifyDrawable(previousDrawable, false);
	}
	
	private static void notifyDrawable(Drawable drawable,
			final boolean isDisplayed) {
		if (drawable instanceof RecyclingBitmapDrawable) {
			((RecyclingBitmapDrawable) drawable).setIsDisplayed(isDisplayed);
		} else if (drawable instanceof LayerDrawable) {
			// The drawable is a LayerDrawable, so recurse on each layer
			LayerDrawable layerDrawable = (LayerDrawable) drawable;
			for (int i = 0, z = layerDrawable.getNumberOfLayers(); i < z; i++) {
				notifyDrawable(layerDrawable.getDrawable(i), isDisplayed);
			}
		}
	}

}
