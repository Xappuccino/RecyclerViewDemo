package com.eebbk.recyclerviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.util.Log;
import android.view.View;

public class MyDividerItemDecoration extends RecyclerView.ItemDecoration{
    private static String TAG = "ItemDecoration";
    private static int headerHeight = 60;
    private static final int[] ATTRS  = new int[]{
            android.R.attr.listDivider
    };
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private Drawable mDivider;
    private int mOrientation;
    public MyDividerItemDecoration(Context context, int orientation){
        final TypedArray typedArray = context.obtainStyledAttributes(ATTRS);
        mDivider = typedArray.getDrawable(0);
        typedArray.recycle();
        setOrientation(orientation);
    }
    private void setOrientation(int orientation){
        if(orientation!=HORIZONTAL_LIST && orientation!=VERTICAL_LIST){
            throw new IllegalArgumentException("illegal argument");
        }
        mOrientation = orientation;
        Log.d(TAG,"setOrientation");
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
        if(mOrientation==VERTICAL_LIST){
            outRect.set(0,0,0,mDivider.getIntrinsicHeight());
        }else {
            outRect.set(0,0,mDivider.getIntrinsicWidth(),0);
        }
        Log.d(TAG,"getItemOffsets");
    }
    @Override
    public void onDraw(Canvas c,RecyclerView parent,RecyclerView.State state){
        if(mOrientation==VERTICAL_LIST){
            drawVertical(c,parent);
        }else{
            drawHorizontal(c,parent);
        }
        Log.d(TAG,"onDraw");
    }
    private void drawVertical(Canvas c, RecyclerView parent){
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for(int i=0;i<childCount;i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin + Math.round(ViewCompat.getTranslationY(child));
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left,top,right,bottom);
//            mDivider.draw(c);
            Paint mPaint = new Paint();
            mPaint.setColor(Color.parseColor("#ff0000"));
//            c.drawRect(left,top,right,bottom,mPaint);
        }
    }
    private void drawHorizontal(Canvas c,RecyclerView parent){
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for(int i=0;i<childCount;i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin + Math.round(ViewCompat.getTranslationX(child));
            final int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }
}

