package net.neonlotus.chungusadmin.ui.views;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewDivider extends RecyclerView.ItemDecoration {
    private final int pixelHeight;
    private final int pixelWidth;

    public RecyclerViewDivider(int pxW, int pxH) {
        //mDivider = ContextCompat.getDrawable(context, R.drawable.line_divider);
        pixelWidth = pxW;
        pixelHeight = pxH;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (pixelWidth == -1) {
            //Old/existing way
            outRect.bottom = pixelHeight;
            outRect.top = 0;
            outRect.left = pixelHeight;
            outRect.right = pixelHeight;
        } else {
            //New way to control both offsets
            outRect.bottom = pixelHeight;
            outRect.top = pixelHeight;
            outRect.left = pixelWidth;
            outRect.right = pixelWidth;
        }
    }
}