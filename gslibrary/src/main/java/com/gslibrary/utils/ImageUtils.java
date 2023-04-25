package com.gslibrary.utils;

import android.widget.ImageView;

import com.gslibrary.R;

import org.xutils.image.ImageOptions;
import org.xutils.x;

public class ImageUtils {

    /**
      * 根据url加载网络图片
      * Create by HC
      * time: 2017/4/25 16:14
      */
    public void setImageForUrl(ImageView imageView,String url) {
        x.image().bind(imageView, url, getDefaultOption());
    }

    /**
      * ImageOptions配置
      * Create by HC
      * time: 2017/4/25 16:12
      */
    public static ImageOptions getSquaer() {
        ImageOptions opSquaer = new ImageOptions.Builder()
                // 设置加载过程中的图片
                .setUseMemCache(true)
                .setImageScaleType(ImageView.ScaleType.FIT_XY)
                // 设置显示圆形图片
                .setFadeIn(true)
                .setFailureDrawableId(R.drawable.picgoodsb)
                .setLoadingDrawableId(R.drawable.picgoodsb)
                .build();
        return opSquaer;
    }

    /**
      * ImageOptions配置
      * Create by HC
      * time: 2017/4/25 16:12
      */
    public static ImageOptions getRound() {
        ImageOptions opRound = new ImageOptions.Builder()
                // 设置加载过程中的图片
                .setUseMemCache(true)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                // 设置显示圆形图片
                .setFadeIn(true)
                .setCircular(true)
                .setFailureDrawableId(R.drawable.picgoodsb)
                .setLoadingDrawableId(R.drawable.picgoodsb)
                .build();
        return opRound;
    }

    public static ImageOptions getDefaultOption(){
        ImageOptions option = new ImageOptions.Builder()
                // 设置加载过程中的图片
                .setUseMemCache(true)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                // 设置显示圆形图片
                .setFailureDrawableId(R.drawable.picgoodsb)
                .setLoadingDrawableId(R.drawable.picgoodsb)
                .build();
        return option;
    }

}
