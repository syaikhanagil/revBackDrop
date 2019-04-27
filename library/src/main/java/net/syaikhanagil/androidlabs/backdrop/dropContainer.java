package net.syaikhanagil.androidlabs.backdrop;


import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.drawable.*;
import android.support.annotation.*;
import android.support.v7.widget.*;
import android.util.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;
import net.syaikhanagil.androidlabs.backdrop.*;

import android.support.v7.widget.Toolbar;

public class dropContainer extends FrameLayout implements dropActions {

    private Context context;
    private Toolbar toolbar;
    private dropToolbarIcon toolbarClick;
    private Drawable mMenuicon;
    private Drawable mCloseicon;
    private int height;
	public boolean openDrop;
    Interpolator interpolator;
    int duration;
	

    public dropContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.dropContainer,0,0);
        mMenuicon=typedArray.getDrawable(R.styleable.dropContainer_dropMenuIcon);
        mCloseicon=typedArray.getDrawable(R.styleable.dropContainer_dropCloseIcon);
        duration=typedArray.getInt(R.styleable.dropContainer_duration,1000);
        typedArray.recycle();
        DisplayMetrics metrics=new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        height=metrics.heightPixels;
		
    }

    public dropContainer attachToolbar(Toolbar toolbar){

        this.toolbar=toolbar;
        this.toolbar.setNavigationIcon(mMenuicon);
        return  this;
    }

    public dropContainer dropHeight(int peek){
        height=height-peek;
        return this;
    }

    public dropContainer dropInterpolator(Interpolator interpolator){
        this.interpolator=interpolator;
        return  this;
    }

    public void createDrop(){

        if (checkTotalview()){

            toolbarClick =new dropToolbarIcon(context,getChildAt(1),getBackview(),mMenuicon,
											  mCloseicon,height,interpolator,duration);
            toolbar.setNavigationOnClickListener(toolbarClick);
        }else {
            throw new ArrayIndexOutOfBoundsException("The container can only be used for two child layout");
        }
    }

    private int getFrontViewMargin() {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getFrontview().getLayoutParams();
        int frontViewMarginTop = layoutParams.topMargin;
        return frontViewMarginTop;
    }

    boolean checkTotalview(){
        if (getChildCount()>2){
            return false;
        }
        return true;
    }

    View getFrontview(){
		return getChildAt(1);
    }
    View getBackview(){
        return getChildAt(0);
    }

    private int dpToPx(int topmargin){
        Resources resources=getResources();
        float MarginTopPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,topmargin,resources.getDisplayMetrics());
        return (int) MarginTopPixels;
    }


	@Override
	public void showDrop() {
		toolbarClick.open();
		openDrop = true;
	}

	@Override
	public void closeDrop() {
        toolbarClick.close();
		openDrop = false;
	}
}

