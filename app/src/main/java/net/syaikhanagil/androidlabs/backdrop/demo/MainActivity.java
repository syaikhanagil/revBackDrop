package net.syaikhanagil.androidlabs.backdrop.demo;

import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import net.syaikhanagil.androidlabs.backdrop.dropContainer;
import android.view.animation.*;

public class MainActivity extends AppCompatActivity 
{
	Toolbar mToolbar;
	dropContainer dropContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		mToolbar = (Toolbar) findViewById(R.id.ToolbarID);
		dropContainer = (dropContainer) findViewById(R.id.DropContainerID);
		int droppedHeight = this.getResources().getDimensionPixelSize(R.dimen.drop_height);
		dropContainer.attachToolbar(mToolbar).dropInterpolator(new LinearInterpolator()).dropHeight(droppedHeight).createDrop();
    }
}
