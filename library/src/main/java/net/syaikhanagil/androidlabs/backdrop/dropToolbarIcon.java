package net.syaikhanagil.androidlabs.backdrop;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageButton;
import android.view.View;
import android.view.animation.Interpolator;

public class dropToolbarIcon implements View.OnClickListener {

    private Context context;
    private View backlayer;
    private View frontlayer;
    private Drawable hambergerIcon;
    private Drawable dropCloseIcon;
    private int translate;
    private Interpolator interpolator;
    private int anim_duration;
    private boolean dropped=false;
    private AnimatorSet animSet=new AnimatorSet();
    private AppCompatImageButton toolbaricon;
	public boolean open = false;

    public dropToolbarIcon(Context context, View frontview, View backview, Drawable mMenuicon,
						   Drawable mCloseicon, int height, Interpolator interpolator, int duration) {

        this.context=context;
        this.frontlayer=frontview;
        this.backlayer=backview;
        this.hambergerIcon=mMenuicon;
        this.dropCloseIcon=mCloseicon;
        this.interpolator=interpolator;
        anim_duration=duration;
        this.translate=height;
    }

    public  void  open(){

        if (!dropped){
            onClick(toolbaricon);
			open = true;
        }
    }

    public void close(){
        if (dropped){
            onClick(toolbaricon);
			open = false;
        }
    }

    @Override
    public void onClick(View v) {

        if (toolbaricon==null){
            this.toolbaricon=(AppCompatImageButton) v;
        }
        dropped=!dropped;
        animSet.removeAllListeners();
        animSet.end();
        animSet.cancel();

        updateIcon(v);

        ObjectAnimator objectAnim=ObjectAnimator.ofFloat(frontlayer,"translationY",
														 dropped? translate:0);
        animSet.play(objectAnim);
        objectAnim.setDuration(anim_duration);
        objectAnim.setInterpolator(interpolator);
        objectAnim.start();
    }

    private void updateIcon(View v) {
        if (hambergerIcon!=null&&dropCloseIcon!=null){
            if (dropped){
                toolbaricon.setImageDrawable(dropCloseIcon);
            }else {
                toolbaricon.setImageDrawable(hambergerIcon);
            }
        }
    }


}

