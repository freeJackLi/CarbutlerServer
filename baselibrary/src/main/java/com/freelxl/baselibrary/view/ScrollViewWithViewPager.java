package com.freelxl.baselibrary.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 解决ViewPager在ScrollView中滑动经常失效、无法正常滑动问题
 * @author wujh
 */
public class ScrollViewWithViewPager extends ScrollView {
	private GestureDetector mGestureDetector;  
	  
    public ScrollViewWithViewPager(Context context, AttributeSet attrs) {  
        super(context, attrs);  
        mGestureDetector = new GestureDetector(context, new YScrollDetector());  
    }  
  
    @Override  
    public boolean onInterceptTouchEvent(MotionEvent ev) {  
        return super.onInterceptTouchEvent(ev)  
                && mGestureDetector.onTouchEvent(ev);  
    }  
  
    class YScrollDetector extends SimpleOnGestureListener {  
  
        @Override  
        public boolean onScroll(MotionEvent e1, MotionEvent e2,  
                float distanceX, float distanceY) {  
            /** 
             * 如果我们滚动更接近水平方向,返回false,让子视图来处理它 
             */  
            return (Math.abs(distanceY) > Math.abs(distanceX));  
        }  
    }  
}
