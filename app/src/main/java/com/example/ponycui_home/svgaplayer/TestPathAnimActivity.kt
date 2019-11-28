package com.example.ponycui_home.svgaplayer

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Path
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.PathInterpolator
import android.view.animation.ScaleAnimation
import kotlinx.android.synthetic.main.activity_test_path_anim.*
import android.support.v4.app.SupportActivity
import android.support.v4.app.SupportActivity.ExtraData
import android.support.v4.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class TestPathAnimActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_path_anim)

        btnStart.setOnClickListener {
            viewAnim.visibility=View.VISIBLE
            var path:Path?=null
            viewAnim.post {
                path = Path().apply {
                    Log.d("donghua","左边距:${viewAnim.width/2/10},右边距:${viewAnim.top}")
                    moveTo(viewAnim.left.toFloat(),viewAnim.top.toFloat())
                    quadTo(btnStart.left.toFloat(),viewAnim.top.toFloat(), ((btnStart.left+btnStart.right)/2-viewAnim.width/2).toFloat(),((btnStart.top+btnStart.bottom)/2-viewAnim.height/2).toFloat())
                }
                //平移动画
                val animTranslation=ObjectAnimator.ofFloat(viewAnim, View.X, View.Y, path)
                //缩放动画
                val animScaleY = ObjectAnimator.ofFloat(viewAnim, "scaleY", 1f, 0.1f)
                val animScaleX = ObjectAnimator.ofFloat(viewAnim, "scaleX", 1f, 0.1f)
                val animSet= AnimatorSet()
                animSet.duration=792L
                animSet.play(animScaleY).with(animTranslation).with(animScaleX)
                animSet.start()
                animSet.addListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        viewAnim.visibility=View.GONE
                    }

                    override fun onAnimationCancel(animation: Animator?) {
//                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onAnimationStart(animation: Animator?) {

                    }
                })
            }

        }

        viewAnim.setOnClickListener {
            println("你好你好你好")
        }
    }
}
