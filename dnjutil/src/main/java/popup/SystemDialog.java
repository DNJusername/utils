package popup;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * 系统样式
 * Created by DNJ on 2019/2/14.
 */

public class SystemDialog {

    public interface DialogListener{
        void sureListener(String param);
        void cancelListener(String param);
    }

    private AlertDialog.Builder systemDialog;
    private String paramSure,paramCancel;
    private Context context;

    //默认样式
    public SystemDialog(Context context){
        this.context = context;
        systemDialog = new AlertDialog.Builder(context,android.R.style.Theme_Material_Light_Dialog_Alert);
        initParam();
    }

    //自定义AlertDialog样式
    public SystemDialog(Context context,int themeResId){
        this.context = context;
        systemDialog = new AlertDialog.Builder(context,themeResId);
        initParam();
    }

    private void initParam(){
        systemDialog.setTitle("标题");
        systemDialog.setMessage("提示信息");
        paramSure = "确定";
        paramCancel = "取消";
    }

    //设置title
    public <T> SystemDialog setTitle(T s){
        if (systemDialog != null){
            if (s instanceof String){
                String str = (String)s;
                systemDialog.setTitle(str);
            }else if (s instanceof Integer){
                Integer resource = (Integer)s;
                systemDialog.setTitle(resource);
            }else {
                systemDialog.setTitle("未知...");
            }
        }
        return this;
    }

    //设置message
    public <T> SystemDialog setMessage(T s){
        if (systemDialog != null){
            if (s instanceof String){
                String str = (String)s;
                systemDialog.setMessage(str);
            }else if (s instanceof Integer){
                Integer resource = (Integer)s;
                systemDialog.setTitle(resource);
            }else {
                systemDialog.setMessage("未知...");
            }
        }
        return this;
    }

    //确定按钮
    public <T> SystemDialog setParamSure(T s){
        if (s instanceof String){
            paramSure = (String)s;
        }else if (s instanceof Integer){
            Integer resource = (Integer)s;
            paramSure = context.getResources().getString(resource);
        }else {
            paramSure = "确定";
        }
        return this;
    }

    //取消按钮
    public <T> SystemDialog setParamCancel(T s){
        if (s instanceof String){
            paramCancel = (String)s;
        }else if (s instanceof Integer){
            Integer resource = (Integer)s;
            paramCancel = context.getResources().getString(resource);
        }else {
            paramCancel = "取消";
        }
        return this;
    }

    public SystemDialog setCancelable(boolean isCancel){
        if (systemDialog == null)
            return this;
        //点击返回键是否消失
        systemDialog.setCancelable(isCancel);
        return this;
    }

    public void show(){
        if (systemDialog == null)
            return;
        systemDialog.show();
    }

    public void create(final DialogListener listener){
        if (systemDialog == null){
            return;
        }
        //确定
        systemDialog.setPositiveButton(paramSure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.sureListener(paramSure);
            }
        });
        //取消
        systemDialog.setNegativeButton(paramCancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.cancelListener(paramCancel);
            }
        });
        systemDialog.create();
    }
}
