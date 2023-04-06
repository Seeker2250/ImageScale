package com.example.multitouch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    final int SIZE = 60;//원 크기
    final int MAX_POINT = 5;//손가락 갯수
    float[] x = new float[MAX_POINT];//x좌표 담을 곳
    float[] y = new float[MAX_POINT];//y좌표 담을 곳
    boolean[] touching = new boolean[MAX_POINT];//touch유효성 판단용
    Paint mPaint;//paint object 저장해서 사용
    //자주 쓸테니 resource 아끼는 차원에서 지정

    String str = "id";//id 뭐 쓰는지 체크하는 용도

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MultiTouchView multiTouchView = new MultiTouchView(this);
        setContentView(multiTouchView);
    }

    public class MultiTouchView extends View {
        float[] x = new float[5];//손가락 5개 터치 허용
        float[] y = new float[5];//손가락 5개 터치 허용
        boolean[] touching = new boolean[5];//각 터치 상태 정보 저장
        //아이디는 그대로인데 index는 계속 바뀜


        public MultiTouchView(Context context) {
            super(context);
            mPaint = new Paint();
            mPaint.setColor(Color.CYAN);
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            mPaint.setStrokeWidth(10f);//터치되는 동그라미 크기 설정
            mPaint.setTextSize(50f);//글자 크기 설정
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            str = "ID : ";//str초기화
            for (int i = 0; i < MAX_POINT ; i++) {
                if (touching[i] == true){
                    canvas.drawCircle(x[i], y[i], SIZE, mPaint);
                    //x 중심좌표, y 중심좌표, 반지름, 붓 (다 지정해둠)
                    //터치 유효성 검증
                    str = str + i + ", ";//id모아줘
                }
            }
            canvas.drawText(str,0,50, mPaint);
            //0 , 50좌표에 mPaint써서 어떤 id인지 출력해줘
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {//터치 여기서 처리함
            //index와 id를 취득해야해
            int index = event.getActionIndex();//index 파악
            int id = event.getPointerId(index);//그 index에 해당되는 id 파악
            int action = event.getActionMasked();//액션 종류 파악
            switch (action){//액션 종류에 따라 다름
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN://눌러지면(새로운 터치가 발생하면)
                    x[id] = event.getX(index);//id기준으로 x좌표 설정, multi니까 index 포함해야함
                    y[id] = event.getY(index);//id기준으로 y좌표 설정, multi니까 index 포함해야함
                    touching[id] = true;//touching 유효성 검사 true
                    break;
                case MotionEvent.ACTION_MOVE://움직이면
                    break;//딱히 해야할 일 없음 걍 움직여
                case MotionEvent.ACTION_UP://터치 안하고 떼어내면
                case MotionEvent.ACTION_POINTER_UP://터치 안하고 떼어내면
                case MotionEvent.ACTION_CANCEL://터치 안하고 떼어내면
                    touching[id] = false;//터치 안돼
                    break;//터치가 끝났으니 view를 다시 그려줘야해
                //따라서 invalidate써야해(무효화 해라)
            }
            invalidate();
            return true;//super.onTouchEvent(event);
        }
    }
}