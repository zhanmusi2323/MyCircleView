package stickygridheadersexample.tonicartos.com.mycircleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import stickygridheadersexample.tonicartos.com.mycircleview.view.MyCircleView;

public class MainActivity extends AppCompatActivity {
    private MyCircleView myCircleView1, myCircleView2, myCircleView3, myCircleView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        myCircleView1.setProgress((float) 0.6);
        myCircleView2.setProgress((float) 0.2);
        myCircleView3.setProgress((float) 0.3);
        myCircleView4.setProgress((float) 0.7);
    }

    private void initView() {
        myCircleView1 = (MyCircleView) findViewById(R.id.cicler_view1);
        myCircleView2 = (MyCircleView) findViewById(R.id.cicler_view2);
        myCircleView3 = (MyCircleView) findViewById(R.id.cicler_view3);
        myCircleView4 = (MyCircleView) findViewById(R.id.cicler_view4);
    }
}
