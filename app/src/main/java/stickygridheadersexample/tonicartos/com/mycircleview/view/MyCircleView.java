package stickygridheadersexample.tonicartos.com.mycircleview.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

/**
 * @author by :wangyanwei
 * @package name ：stickygridheadersexample.tonicartos.com.mycircleview.view
 * @describe :
 * @Date :2017/7/17 14:46
 */
public class MyCircleView extends View {
    private Paint paint = new Paint();
    private Paint ciclerPaint = new Paint();
    private Paint textPaint = new Paint();
    private Paint textProgressPaint = new Paint();
    private float circleR;
    private int viewWidth;//控件的高度和宽度
    private float jiaoDuProcess = 0;//初始进度，默认为0
    private int sleeptime = 10;
    private String text = "完成";
    private String text_progress = "";
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                MyCircleView.this.invalidate();
            }
        }
    };

    public MyCircleView(Context context) {
        super(context);
        initView(context);
    }

    public MyCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        circleR = dip2px(context, 15);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(circleR / 2);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GRAY);
        viewWidth = getScreenWidth((Activity) context) / 3;

        ciclerPaint.setAntiAlias(true);
        ciclerPaint.setStrokeWidth(circleR / 2);
        ciclerPaint.setStrokeCap(Paint.Cap.ROUND);
        ciclerPaint.setStyle(Paint.Style.STROKE);
        ciclerPaint.setColor(Color.RED);

        textPaint.setAntiAlias(true);
        textPaint.setStrokeWidth(1);
        //  textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setTextSize(dip2px(context, 10));
        textPaint.setColor(Color.BLUE);

        textProgressPaint.setAntiAlias(true);
        textProgressPaint.setStrokeWidth(1);
        //  textPaint.setStyle(Paint.Style.STROKE);
        textProgressPaint.setTextSize(dip2px(context, 10));
        textProgressPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.TRANSPARENT);
        //    RectF rect = new RectF(circleR, circleR, viewWidth - circleR / 2, viewWidth - circleR / 2);
        RectF rect = new RectF(paint.getStrokeWidth() / 2, paint.getStrokeWidth() / 2, getMeasuredWidth() - paint.getStrokeWidth() / 2, getMeasuredHeight() - paint.getStrokeWidth() / 2);
        canvas.drawArc(rect, 135, 270, false, paint);
        canvas.drawArc(rect, 135, jiaoDuProcess, false, ciclerPaint);
        canvas.drawText(text, getMeasuredWidth() / 2 - textPaint.measureText(text) / 2, getMeasuredHeight() / 2, textPaint);
        canvas.drawText(text_progress, getMeasuredWidth() / 2 - textPaint.measureText(text) / 2, getMeasuredHeight() / 2 + textProgressPaint.measureText(text_progress) / 2l, textProgressPaint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getLayoutParams();
//        params.addRule(RelativeLayout.CENTER_IN_PARENT);
//        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //    setMeasuredDimension(viewWidth, viewWidth);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public int getScreenWidth(Activity context) {
        WindowManager wm = context.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();//屏幕的宽度
        return width;
    }

    public void setProgress(float progress) {
        text_progress = (int) (progress * 100) + "%";
        if (progress > 1) {
            progress = 1;
        }
        final float maxProgress = 270 * progress;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (jiaoDuProcess < maxProgress) {
                        Thread.sleep(sleeptime);
                        jiaoDuProcess = jiaoDuProcess + 1;
                        post(new Runnable() {
                            @Override
                            public void run() {
                                MyCircleView.this.invalidate();
                            }
                        });
                        //   handler.sendEmptyMessage(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public boolean postDelayed(Runnable action, long delayMillis) {
        return super.postDelayed(action, delayMillis);
    }
}
