package com.example.touch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.fonts.Font;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    float x, y;//touch에 x, y 좌표 저장을 하기 위한 field
    String str = ""; //어떤 액션이 나오는지 출력하기 위한 저장용 field

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyView myView = new MyView(this);//MyView 객체 생성
        myView.setOnTouchListener(this);//myView에 TouchListener장착
        setContentView(myView);//화면 세팅
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //motionEvent에 getX, getY method가 있음, 좌표 따는 용
        x = motionEvent.getX();
        y = motionEvent.getY();
        //Action이라는 Method도 있음, 무슨 액션인지 확인하는 용
        int action = motionEvent.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                str = "ACTION_Down";//Action down이면 str에 이 문자 담아
                break;
            case MotionEvent.ACTION_UP:
                str = "ACTION_Up";//Action up이면 str에 이 문자 담아
                break;
            case MotionEvent.ACTION_MOVE:
                str = "ACTION_Move";//Action move면 str에 이 문자 담아
                break;
        }

        //정보가 변경되면 view를 다시 그려줘야 해 그러기 위한 method
        view.invalidate();//view를 무효화 시켜주세요

        return true;//false에서 true로 변경, true가 return되어야 하니까
    }

    class MyView extends View {//OnDraw가 필요함, 화면에서 그려줘야 하니까

        //그래서 우클릭 후 Generate에서 넣어줌
        public MyView(Context context) {//Constructor 필요해서 만들어줌
            super(context);
            //view의 constructor에서는 할 일
            setBackgroundColor(Color.CYAN);//그냥 배경 색 바꿔주자
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            //onDraw에서 필요한 거! 그래픽 작업, Canvas와 Paint
            //Canvas애서 Drawing method 제공, Paint에서 굵기, 색상 등 제공
            Paint paint = new Paint();//paint 객체 생성
            paint.setColor(Color.MAGENTA);
            paint.setTextSize(48f);//자료형이 float
            canvas.drawText("액션의 종류 : "+ str , 100, 50, paint);//텍스트 그려
            canvas.drawRect(x, y, x+100, y+100,paint);//사각형 그려
            //터치가 눌러진 곳 x, y
            // 가로세로 100픽셀 정도로
            //우리가 사용하는 paint로
        }
    }
}