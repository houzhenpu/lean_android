package com.example.my_lean_android.screen.notchDisplay;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.my_lean_android.R;

import java.util.List;

public class NotchDisplayActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1设置全屏显示
        Window window = getWindow();
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //对手机厂商进行判断
        /**
         *  if(notch){
         *  让内容延伸进入刘海区域
         *  }
         *  1、设置
         *  2、判断手机厂商
         *  3、判断是否有刘海屏
         *  4、获取刘海屏高度
         *  5、控件避开刘海屏区域
         */

//2让内容延伸进入刘海区域
        /**
         * * @see #LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT 全屏模式，内容下移 非全屏模式下不受影响
         * * @see #LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES 允许内容进入刘海区域
         * * @see #LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER 不允许内容进入刘海区域
         */
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        //状态栏设置透明
        window.setStatusBarColor(0);
        //3设置沉浸式
        int flags = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        int visibility = window.getDecorView().getSystemUiVisibility();
        visibility |= flags;
        window.getDecorView().setSystemUiVisibility(visibility);

        setContentView(R.layout.activity_notch_displaymain);
        //4下沉
//        Button button = findViewById(R.id.button);
//        ConstraintLayout.LayoutParams layoutParamsB = (ConstraintLayout.LayoutParams) button.getLayoutParams();
//        layoutParamsB.topMargin = heightForDisplayCutout();//刘海高度
//        button.setLayoutParams(layoutParamsB);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        //判断有没有刘海屏(获取需要在View绑定到Window之后，否在拿不到)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            DisplayCutout displayCutout = getWindow().getDecorView().getRootWindowInsets().getDisplayCutout();
            if (displayCutout != null) {
                //有刘海
                Log.e("DisplayCutout", "Rect  " + displayCutout.getBoundingRects());
                Log.e("DisplayCutout", "Left  " + displayCutout.getSafeInsetLeft());
                Log.e("DisplayCutout", "Top  " + displayCutout.getSafeInsetTop());
                Log.e("DisplayCutout", "Right  " + displayCutout.getSafeInsetRight());
                Log.e("DisplayCutout", "Bottom  " + displayCutout.getSafeInsetBottom());
                List<Rect> rects = displayCutout.getBoundingRects();
                for (Rect rect : rects) {
                    Log.e("DisplayCutout", "width  height" + rect.width() + "  " + rect.height());
                }
                //下沉
                ConstraintLayout layout = findViewById(R.id.layout);
                layout.setPadding(displayCutout.getSafeInsetLeft(), displayCutout.getSafeInsetTop()
                        , displayCutout.getSafeInsetRight(), displayCutout.getSafeInsetBottom());

            }
        }
    }

    private int heightForDisplayCutout() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 96;
    }


}
