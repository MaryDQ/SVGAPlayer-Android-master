package com.example.ponycui_home.svgaplayer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGADynamicEntity;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;

public class AnimationWithDynamicImageActivity extends Activity {

    SVGAImageView animationView = null;
    SVGAParser parser = new SVGAParser(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animationView = new SVGAImageView(this);
        animationView.setBackgroundColor(Color.GRAY);
        loadAnimation();
        setContentView(animationView);
    }

    private void loadAnimation() {
        try { // new URL needs try catch.
            parser.decodeFromAssets("songchong3.svga", new SVGAParser.ParseCompletion() {
                @Override
                public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                    SVGADynamicEntity dynamicEntity = new SVGADynamicEntity();
                    dynamicEntity.setDynamicImage("https://oss.pdabc.com/20191126/f351571b-7d18-4a8a-96fa-e8b49c4c49de.png", "course"); // Here is the KEY implementation.
                    SVGADrawable drawable = new SVGADrawable(videoItem, dynamicEntity);
                    animationView.setImageDrawable(drawable);
                    animationView.startAnimation();
                }
                @Override
                public void onError() {

                }
            });
//            parser.decodeFromURL(new URL("https://github.com/yyued/SVGA-Samples/blob/master/kingset.svga?raw=true"), new SVGAParser.ParseCompletion() {
//                @Override
//                public void onComplete(@NotNull SVGAVideoEntity videoItem) {
//                    SVGADynamicEntity dynamicEntity = new SVGADynamicEntity();
//                    dynamicEntity.setDynamicImage("https://oss.pdabc.com/20191126/23697888-dfe4-4fea-9318-c6b5d858b677.png", "99"); // Here is the KEY implementation.
//                    SVGADrawable drawable = new SVGADrawable(videoItem, dynamicEntity);
//                    animationView.setImageDrawable(drawable);
//                    animationView.startAnimation();
//                }
//                @Override
//                public void onError() {
//
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
