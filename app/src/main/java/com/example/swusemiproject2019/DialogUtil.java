package com.example.swusemiproject2019;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogUtil {
    public static void showDialog(Context context, String title, String msg,
                                  String okMsg, DialogInterface.OnClickListener okListener,
                                  String cancleMsg, DialogInterface.OnClickListener cancelListener)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(msg);

        if(okListener != null) {
            builder.setPositiveButton(okMsg, okListener);
        }

        if(cancelListener != null) {
            builder.setNegativeButton(cancleMsg, cancelListener);
        }
        builder.show();
    }
}
