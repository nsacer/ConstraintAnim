package menu.zpf.myapplication;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private ConstraintSet csApply = new ConstraintSet();
    private ConstraintSet csReset = new ConstraintSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        constraintLayout = (ConstraintLayout) findViewById(R.id.cs_main);

        csApply.clone(constraintLayout);
        csReset.clone(constraintLayout);

    }

    /**
     * 应用动画点击按钮
     */
    public void onApplyClick(View view) {

//        animFirstBtnToLeft();

//        animAllBtnCenterHorizontal();

//        animThreeBtnCenter();

//        animTwoBtnWidthHeight();

//        animTwoThreeGoneOneCrop();

        animChainStyleButtons();
    }

    /**
     * 重置布局按钮点击事件
     */
    public void onResetClick(View view) {

        TransitionManager.beginDelayedTransition(constraintLayout);
        csReset.applyTo(constraintLayout);
    }

    /**
     * 第一个按钮移动到左边
     */
    private void animFirstBtnToLeft() {

        TransitionManager.beginDelayedTransition(constraintLayout);
        csApply.setMargin(R.id.btn_one, ConstraintSet.START, 0);
        csApply.applyTo(constraintLayout);
    }

    /**
     * 所有按钮移动到水平居中位置
     */
    private void animAllBtnCenterHorizontal() {

        TransitionManager.beginDelayedTransition(constraintLayout);

        csApply.setMargin(R.id.btn_one, ConstraintSet.START, 0);
        csApply.setMargin(R.id.btn_one, ConstraintSet.END, 0);

        csApply.setMargin(R.id.btn_two, ConstraintSet.START, 0);
        csApply.setMargin(R.id.btn_two, ConstraintSet.END, 0);

        csApply.setMargin(R.id.btn_three, ConstraintSet.START, 0);
        csApply.setMargin(R.id.btn_three, ConstraintSet.END, 0);

        csApply.centerHorizontally(R.id.btn_one, R.id.cs_main);
        csApply.centerHorizontally(R.id.btn_two, R.id.cs_main);
        csApply.centerHorizontally(R.id.btn_three, R.id.cs_main);

        csApply.applyTo(constraintLayout);
    }

    /**
     * 第三个按钮移动到中心位置
     */
    private void animThreeBtnCenter() {

        TransitionManager.beginDelayedTransition(constraintLayout);

        csApply.setMargin(R.id.btn_three, ConstraintSet.START, 0);
        csApply.setMargin(R.id.btn_three, ConstraintSet.TOP, 0);
        csApply.setMargin(R.id.btn_three, ConstraintSet.END, 0);
        csApply.setMargin(R.id.btn_three, ConstraintSet.BOTTOM, 0);
        csApply.centerHorizontally(R.id.btn_three, R.id.cs_main);
        csApply.centerVertically(R.id.btn_three, R.id.cs_main);

        csApply.applyTo(constraintLayout);
    }

    /**
     * 更改第二个按钮的宽度、高度
     */
    private void animTwoBtnWidthHeight() {

        TransitionManager.beginDelayedTransition(constraintLayout);

        csApply.constrainWidth(R.id.btn_two, 9600);
        csApply.constrainHeight(R.id.btn_two, 200);

        csApply.applyTo(constraintLayout);
    }

    /**
     * 隐藏第二、第三按钮，第一个按钮扩展整个屏幕
     */
    private void animTwoThreeGoneOneCrop() {

        TransitionManager.beginDelayedTransition(constraintLayout);

        csApply.setVisibility(R.id.btn_two, ConstraintSet.GONE);
        csApply.setVisibility(R.id.btn_three, ConstraintSet.GONE);

        csApply.clear(R.id.btn_one);
        csApply.connect(R.id.btn_one, ConstraintSet.LEFT, R.id.cs_main, ConstraintSet.LEFT, 0);
        csApply.connect(R.id.btn_one, ConstraintSet.TOP, R.id.cs_main, ConstraintSet.TOP, 0);
        csApply.connect(R.id.btn_one, ConstraintSet.RIGHT, R.id.cs_main, ConstraintSet.RIGHT, 0);
        csApply.connect(R.id.btn_one, ConstraintSet.BOTTOM, R.id.cs_main, ConstraintSet.BOTTOM, 0);

        csApply.applyTo(constraintLayout);
    }

    /**
     * 三个按钮链式布局
     */
    private void animChainStyleButtons() {

        TransitionManager.beginDelayedTransition(constraintLayout);

        csApply.clear(R.id.btn_one);
        csApply.clear(R.id.btn_two);
        csApply.clear(R.id.btn_three);

        csApply.connect(R.id.btn_one, ConstraintSet.LEFT, R.id.cs_main, ConstraintSet.LEFT, 0);
        csApply.connect(R.id.btn_one, ConstraintSet.RIGHT, R.id.btn_two, ConstraintSet.LEFT, 0);

        csApply.connect(R.id.btn_two, ConstraintSet.LEFT, R.id.btn_one, ConstraintSet.RIGHT, 0);
        csApply.connect(R.id.btn_two, ConstraintSet.RIGHT, R.id.btn_three, ConstraintSet.LEFT, 0);

        csApply.connect(R.id.btn_three, ConstraintSet.LEFT, R.id.btn_two, ConstraintSet.RIGHT, 0);
        csApply.connect(R.id.btn_three, ConstraintSet.RIGHT, R.id.cs_main, ConstraintSet.RIGHT, 0);

        csApply.createHorizontalChain(R.id.cs_main, ConstraintSet.LEFT, R.id.cs_main, ConstraintSet.RIGHT,
                new int[]{R.id.btn_one, R.id.btn_two, R.id.btn_three}, null, ConstraintSet.MATCH_CONSTRAINT);

        csApply.constrainWidth(R.id.btn_one, ConstraintSet.WRAP_CONTENT);
        csApply.constrainHeight(R.id.btn_one, ConstraintSet.WRAP_CONTENT);
        csApply.constrainWidth(R.id.btn_two, ConstraintSet.WRAP_CONTENT);
        csApply.constrainHeight(R.id.btn_two, ConstraintSet.WRAP_CONTENT);
        csApply.constrainWidth(R.id.btn_three, ConstraintSet.WRAP_CONTENT);
        csApply.constrainHeight(R.id.btn_three, ConstraintSet.WRAP_CONTENT);

        csApply.applyTo(constraintLayout);
    }
}
