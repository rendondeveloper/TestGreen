package com.example.o_lrendon.testgrin.View;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v4.app.Fragment;

public class BaseView extends Fragment
{
    private ProgressDialog dialog = null;

    protected void ShowModal(final Activity activity, final String message, final boolean indeterminate, final boolean cancelable) {
        this.dialog = new ProgressDialog(activity);
        this.dialog.setMessage(message);
        this.dialog.setIndeterminate(indeterminate);
        this.dialog.setCancelable(cancelable);
        this.dialog.show();
    }

    protected void ClosedModal() {
        if (this.dialog != null && this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
    }
}
