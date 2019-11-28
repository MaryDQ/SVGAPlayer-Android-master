package com.example.ponycui_home.svgaplayer;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGAClickAreaListener;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGADynamicEntity;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import org.jetbrains.annotations.NotNull;


/**
 * Created by miaojun on 2019/6/21.
 * mail:1290846731@qq.com
 */
public class AnimationFromClickActivity extends Activity {

    SVGAImageView animationView = null;
    SVGAParser parser = new SVGAParser(this);

    private View viewBook,viewShop;
    private Button btnReceive;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        animationView = new SVGAImageView(this);
        setContentView(R.layout.activity_from_click);
        viewBook=findViewById(R.id.viewBook);
        viewShop=findViewById(R.id.viewShop);
        btnReceive=findViewById(R.id.btnReceive);
        animationView = findViewById(R.id.svgImageView);
        animationView.setOnAnimKeyClickListener(new SVGAClickAreaListener() {
            @Override
            public void onClick(@NotNull String clickKey) {
                Toast.makeText(AnimationFromClickActivity.this,clickKey,Toast.LENGTH_SHORT).show();
            }
        });
        animationView.setBackgroundColor(Color.WHITE);
        loadAnimation();

        initClickBtnReceiver();

    }

    private void initClickBtnReceiver() {
        btnReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewBook.setVisibility(View.VISIBLE);
                final Path path=new Path();
                viewBook.post(new Runnable() {
                    @Override
                    public void run() {
                        path.moveTo(viewBook.getLeft(),viewBook.getTop());
                        path.quadTo(viewShop.getLeft(),viewBook.getTop(),((viewShop.getLeft()+viewShop.getRight())/2-viewBook.getWidth()/2),((viewShop.getTop()+viewShop.getBottom())/2-viewBook.getHeight()/2));
                        ObjectAnimator animTranslation=ObjectAnimator.ofFloat(viewBook, View.X, View.Y, path);
                        ObjectAnimator animScaleY = ObjectAnimator.ofFloat(viewBook, "scaleY", 1f, 0.1f);
                        ObjectAnimator animScaleX = ObjectAnimator.ofFloat(viewBook, "scaleX", 1f, 0.1f);
                        AnimatorSet animatorSet=new AnimatorSet();
                        animatorSet.setDuration(792L);
                        animatorSet.play(animScaleY).with(animTranslation).with(animScaleX);
                        animatorSet.start();
                        animatorSet.addListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                viewBook.setVisibility(View.GONE);
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        });
                    }
                });

        }
    });
    }

    private void loadAnimation() {
        parser.decodeFromAssets("anim_receive_book.svga",new SVGAParser.ParseCompletion() {
            @Override
            public void onComplete(@NotNull SVGAVideoEntity videoItem) {
//                SVGADynamicEntity dynamicEntity = new SVGADynamicEntity();
//                dynamicEntity.setClickArea("img_10");
//                SVGADrawable drawable = new SVGADrawable(videoItem, dynamicEntity);
                SVGADynamicEntity dynamicEntity = new SVGADynamicEntity();
                dynamicEntity.setDynamicImage("https://github.com/PonyCui/resources/blob/master/svga_replace_avatar.png?raw=true", "course"); // Here is the KEY implementation.
                SVGADrawable drawable = new SVGADrawable(videoItem, dynamicEntity);

                animationView.setImageDrawable(drawable);
                animationView.startAnimation();
                animationView.setCallback(new SVGACallback() {
                    @Override
                    public void onPause() {

                    }

                    @Override
                    public void onFinished() {

                    }

                    @Override
                    public void onRepeat() {

                    }

                    @Override
                    public void onStep(int frame, double percentage) {
                        if (frame==95) {
                            animationView.stepToFrame(53,true);
                        }
                    }
                });

//
//                SVGADynamicEntity dynamicEntity = new SVGADynamicEntity();
//                dynamicEntity.setDynamicImage("https://github.com/PonyCui/resources/blob/master/svga_replace_avatar.png?raw=true", "99"); // Here is the KEY implementation.
//                SVGADrawable drawable = new SVGADrawable(videoItem, dynamicEntity);
//                testView.setImageDrawable(drawable);
//                testView.startAnimation();
            }
            @Override
            public void onError() {

            }
        });
    }

}

