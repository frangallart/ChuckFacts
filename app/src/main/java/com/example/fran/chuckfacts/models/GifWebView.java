package com.example.fran.chuckfacts.models;

import android.content.Context;
import android.webkit.WebView;

/**
 * Created by Fran on 06/02/2015.
 */
public class GifWebView extends WebView {

    public GifWebView(Context context, String path){
        super(context);
        loadUrl(path);
    }
}
