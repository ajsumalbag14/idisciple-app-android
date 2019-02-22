package com.ph.idisciple.idiscipleapp.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ph.idisciple.idiscipleapp.R;

public class ShowMessageUtil {

    private Context mContext;
    private AlertDialog mDialog;

    public ShowMessageUtil(Context context) {
        this.mContext = context;
    }

    public void showToastMessage(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    public AlertDialog showLoadingDialog() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_loading, null);

        mDialog = new AlertDialog.Builder(mContext)
                .setView(view)
                .setCancelable(false)
                .create();

        mDialog.show();
        return mDialog;
    }

    public void showOkMessage(String title, String message) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_main, null);

        mDialog = new AlertDialog.Builder(mContext)
                .setView(view)
                .setCancelable(false)
                .create();

        TextView tvDialogHeader = (TextView) view.findViewById(R.id.tvDialogHeader);
        TextView tvDialogMessage = (TextView) view.findViewById(R.id.tvDialogMessage);
        Button bConfirm = (Button) view.findViewById(R.id.bConfirm);

        tvDialogHeader.setText(title);
        tvDialogMessage.setText(message);
        bConfirm.setText(mContext.getString(android.R.string.ok));

        bConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        mDialog.show();
    }

    public AlertDialog showOkMessage(String title, String message, View.OnClickListener onConfirmClickListener) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_main, null);

        mDialog = new AlertDialog.Builder(mContext)
                .setView(view)
                .setCancelable(false)
                .create();

        TextView tvDialogHeader = (TextView) view.findViewById(R.id.tvDialogHeader);
        TextView tvDialogMessage = (TextView) view.findViewById(R.id.tvDialogMessage);
        Button bConfirm = (Button) view.findViewById(R.id.bConfirm);

        tvDialogHeader.setText(title);
        tvDialogMessage.setText(message);
        bConfirm.setText(mContext.getString(android.R.string.ok));

        bConfirm.setOnClickListener(onConfirmClickListener);

        mDialog.show();
        return mDialog;
    }

    public AlertDialog showOkMessage(String title, String message, String buttonText, View.OnClickListener onConfirmClickListener) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_main, null);

        mDialog = new AlertDialog.Builder(mContext)
                .setView(view)
                .setCancelable(false)
                .create();

        TextView tvDialogHeader = (TextView) view.findViewById(R.id.tvDialogHeader);
        TextView tvDialogMessage = (TextView) view.findViewById(R.id.tvDialogMessage);
        Button bConfirm = (Button) view.findViewById(R.id.bConfirm);

        tvDialogHeader.setText(title);
        tvDialogMessage.setText(message);
        bConfirm.setText(buttonText);

        bConfirm.setOnClickListener(onConfirmClickListener);

        mDialog.show();
        return mDialog;
    }

    public AlertDialog showConfirmMessage(String title, String message,
                                           View.OnClickListener onConfirmClickListener) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_decision, null);

        mDialog = new AlertDialog.Builder(mContext)
                .setView(view)
                .setCancelable(false)
                .create();

        TextView tvDialogHeader = (TextView) view.findViewById(R.id.tvDialogHeader);
        TextView tvDialogMessage = (TextView) view.findViewById(R.id.tvDialogMessage);
        Button bConfirm = (Button) view.findViewById(R.id.bConfirm);
        Button bDecline = (Button) view.findViewById(R.id.bDecline);

        tvDialogHeader.setText(title);
        tvDialogMessage.setText(message);

        bConfirm.setOnClickListener(onConfirmClickListener);
        bDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        mDialog.show();
        return mDialog;
    }

    public AlertDialog showDecisionMessage(String title, String message,
                                           View.OnClickListener onConfirmClickListener,
                                           View.OnClickListener onDeclineClickListener) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_decision, null);

        mDialog = new AlertDialog.Builder(mContext)
                .setView(view)
                .setCancelable(false)
                .create();

        TextView tvDialogHeader = (TextView) view.findViewById(R.id.tvDialogHeader);
        TextView tvDialogMessage = (TextView) view.findViewById(R.id.tvDialogMessage);
        Button bConfirm = (Button) view.findViewById(R.id.bConfirm);
        Button bDecline = (Button) view.findViewById(R.id.bDecline);

        tvDialogHeader.setText(title);
        tvDialogMessage.setText(message);

        bConfirm.setOnClickListener(onConfirmClickListener);
        bDecline.setOnClickListener(onDeclineClickListener);

        mDialog.show();
        return mDialog;
    }
}
