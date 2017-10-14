package com.example.chori.bookmaker;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.FrameLayout;

public class SlideTopAnimation extends Animation {
    public enum Direction {
        UP,
        DOWN
    }

    private FrameLayout.LayoutParams layoutParams;
    private Direction direction;
    private View view;

    public SlideTopAnimation(View view, Direction direction) {
        this.view = view;
        this.direction = direction;
        this.layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        setDuration(200);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final int topMargin = (int) (-1 * view.getHeight()
                * (direction == Direction.UP ? 1 - interpolatedTime : interpolatedTime));
        layoutParams.topMargin = topMargin;
        view.setLayoutParams(layoutParams);
    }
}
