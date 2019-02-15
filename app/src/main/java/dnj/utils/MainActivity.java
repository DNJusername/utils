package dnj.utils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import popup.CenterDialog;
import popup.SystemDialog;

public class MainActivity extends Activity {

    private CenterDialog dialog;
    private SystemDialog systemDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        dialog = new CenterDialog(this);
        dialog.setMessage("修改了提示信息")
                .setParamLeft("左边参数")
                .setParamRight("右边参数")
                .addListener(new CenterDialog.DialogListener() {
                    @Override
                    public void onClickLeft(View v,String param) {
                        ToastUtils.showShort(getApplicationContext(),param);
                    }

                    @Override
                    public void onClickRight(View v,String param) {
                        ToastUtils.showShort(getApplicationContext(),param);
                    }
                });
        systemDialog = new SystemDialog(this);
        systemDialog.setTitle("提示")
                .setMessage("提示的信息")
                .setParamCancel("取消.")
                .setParamSure("确定.")
                .create(new SystemDialog.DialogListener() {
                    @Override
                    public void sureListener(String param) {
                        ToastUtils.showShort(getApplicationContext(),param);
                    }

                    @Override
                    public void cancelListener(String param) {
                        ToastUtils.showShort(getApplicationContext(),param);
                    }
                });
    }

    public void centerBtn(View view) {
        dialog.show();
    }

    public void systemBtn(View view) {
        systemDialog.show();
    }
}
