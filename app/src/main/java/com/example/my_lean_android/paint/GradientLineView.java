package com.example.my_lean_android.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.SumPathEffect;
import android.graphics.SweepGradient;
import android.view.View;

import com.example.my_lean_android.R;

public class GradientLineView extends View {
    private Paint mPaint;
    private Shader mShader;
    private Bitmap mBitmap;

    float phase;
    PathEffect[] effects = new PathEffect[7];
    int[] colors;
    Path path;

    public GradientLineView(Context context) {
        super(context);
        init();
    }

    private void init() {

        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.demo);

        mPaint = new Paint(); //初始化
        mPaint.setColor(Color.RED);// 设置颜色
        mPaint.setARGB(255, 255, 255, 0); // 设置 Paint对象颜色,范围为0~255
        mPaint.setAlpha(200); // 设置alpha不透明度,范围为0~255
        mPaint.setAntiAlias(true); // 抗锯齿
        mPaint.setFilterBitmap(true); //设置双线性过滤(滤波，也可起到抗锯齿的效果)
        mPaint.setStyle(Paint.Style.STROKE); //描边效果
        mPaint.setStrokeWidth(4);//描边宽度
        mPaint.setStrokeCap(Paint.Cap.ROUND); //圆角效果
        mPaint.setStrokeJoin(Paint.Join.MITER);//拐角风格
        mPaint.setShader(new SweepGradient(200, 200, Color.BLUE, Color.RED)); //设置环形渲染器
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DARKEN)); //设置图层混合模式
        mPaint.setColorFilter(new LightingColorFilter(0x00ffff, 0x000000)); //设置颜色过滤器
        mPaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));//设置画笔遮罩滤镜 ,传入度数和样式
        mPaint.setTextScaleX(2);// 设置文本缩放倍数
        mPaint.setTextSize(38);// 设置字体大小
        mPaint.setTextAlign(Paint.Align.LEFT);//对其方式
        mPaint.setUnderlineText(true);// 设置下划线

        String str = "Android高级工程师";
        Rect rect = new Rect();
        mPaint.getTextBounds(str, 0, str.length(), rect); //测量文本大小，将文本大小信息存放在rect中
        mPaint.measureText(str); //获取文本的宽
        mPaint.getFontMetrics(); //获取字体度量对象
        path = new Path();
        path.moveTo(0, 0);
        for (int i = 1; i <= 40; i++) {
            path.lineTo(i * 20, (float) Math.random() * 80);
        }
        path.close();
//
        colors = new int[]{Color.BLACK, Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.RED, Color.MAGENTA};
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawCircle(100,100,10,mPaint);
//        canvas.drawCircle(200,100,10,mPaint);
//        canvas.drawCircle(300,200,10,mPaint);
//        canvas.drawCircle(200,400,10,mPaint);
//        canvas.drawCircle(400,400,10,mPaint);

//        path = new Path();
//        path.moveTo(100, 100);
//        path.quadTo(200, 100, 300, 200);
//        path.quadTo(200, 400, 400, 400);
        //canvas.drawPath(path,mPaint);


        canvas.drawColor(Color.WHITE);
        effects[0] = null;
        effects[1] = new CornerPathEffect(10);
        effects[2] = new DiscretePathEffect(3.0f, 5.0f);
        effects[3] = new DashPathEffect(new float[]{20, 10, 5, 10}, phase);

        Path p = new Path();
        p.addRect(0, 0, 3, 3, Path.Direction.CCW);

        effects[4] = new PathDashPathEffect(p, 12, phase, PathDashPathEffect.Style.MORPH);
        effects[5] = new ComposePathEffect(effects[2], effects[4]);
        effects[6] = new SumPathEffect(effects[2], effects[4]);

        //将画布移动至（8，8）处开始绘制；
        canvas.translate(80, 8);
        for (int i = 0; i < effects.length; i++) {
            mPaint.setPathEffect(effects[i]);
            mPaint.setColor(colors[i]);
            canvas.drawPath(path, mPaint);
            canvas.translate(0, 130);
        }

        phase += 1;//改变phase的值，形成动画效果
        //invalidate();


    }
}

