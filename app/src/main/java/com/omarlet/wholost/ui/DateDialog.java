package com.omarlet.wholost.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.NonNull;

import com.omarlet.wholost.R;

public class DateDialog extends Dialog {

    public DateDialog(@NonNull Context context) {
        super(context);
    }

    private DatePicker datePicker;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_dialog_layout);

        date = "";
        Button choose = findViewById(R.id.choose);
        datePicker = findViewById(R.id.calender);

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date += datePicker.getDayOfMonth() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getYear();
                dismiss();
            }
        });
    }

    public String getDate(){
        return date;
    }
}
