package com.intechdev.tcommerce.Classes;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.intechdev.tcommerce.R;

public class Alert_Dialog {

    private Context context;
    private Dialog dialog;
    private TextView txtTitle,txtMessage;

    public Alert_Dialog(Context context) {
        this.context = context;
        dialog = new Dialog(context);
    }

    public void showAlert(String title,String message){
        dialog.setContentView(R.layout.dialog_alert);
        txtTitle = dialog.findViewById(R.id.txt_alertDialog_title);
        txtMessage = dialog.findViewById(R.id.txt_alertDialog_message);
        txtTitle.setText(title);
        txtMessage.setText(message);
        dialog.getWindow().setLayout(1000,500);
        dialog.show();
    }

    public void dismiss(){

            dialog.dismiss();


    }
}
