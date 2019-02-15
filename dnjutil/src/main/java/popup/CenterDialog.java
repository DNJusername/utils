package popup;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import dnj.util.R;

/**
 * 居中显示
 * Created by DNJ on 2019/2/13.
 */

public class CenterDialog {

    public interface DialogListener {
        void onClickLeft(View v,String param);
        void onClickRight(View v,String param);
    }

    private Context context;
    private Dialog dialog;
    private TextView rightText, leftText, midMsg;
    private DialogListener listener;

    public CenterDialog(Context context) {
        this.context = context;
        dialog = new Dialog(context);
        init();
    }

    private void init() {
        dialog = new Dialog(context, R.style.StyleDialog);
        dialog.setContentView(R.layout.dialog_center);
        rightText = dialog.findViewById(R.id.dialog_right_text);
        leftText = dialog.findViewById(R.id.dialog_left_text);
        midMsg = dialog.findViewById(R.id.dialog_message);
        parameter();
        leftText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener !=null){
                    listener.onClickLeft(v,leftText.getText().toString());
                    cancel();
                }
            }
        });

        rightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener !=null){
                    listener.onClickRight(v,rightText.getText().toString());
                    cancel();
                }
            }
        });
    }

    //dialog参数设置
    private void parameter() {
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.6f; //背景阴影
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        dialog.getWindow().getDecorView().setPadding(0, 0, 0, 0);
        lp.width = (int) (dm.widthPixels * 0.65);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setGravity(Gravity.CENTER);
    }

    //提示信息
    public <T> CenterDialog setMessage(T s){
        if (s instanceof String){
            midMsg.setText((String)s);
        }else if (s instanceof Integer){
            midMsg.setText((Integer)s);
        }else {
            midMsg.setText("提示信息");
        }
        return this;
    }

    //右边按钮
    public <T> CenterDialog setParamRight(T s){
        if (s instanceof String){
            rightText.setText((String)s);
        }else if (s instanceof Integer){
            rightText.setText((Integer)s);
        }else {
            rightText.setText("确定");
        }
        return this;
    }

    //左边按钮
    public <T> CenterDialog setParamLeft(T s){
        if (s instanceof String){
            leftText.setText((String)s);
        }else if (s instanceof Integer){
            leftText.setText((Integer)s);
        }else {
            leftText.setText("取消");
        }
        return this;
    }

    public void addListener(DialogListener listener){
        this.listener = listener;
    }

    public void show() {
        if (dialog != null)
            dialog.show();
    }

    public void cancel() {
        if (dialog != null && dialog.isShowing())
            dialog.cancel();
    }

}
