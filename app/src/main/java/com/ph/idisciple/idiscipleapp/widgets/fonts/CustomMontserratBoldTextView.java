package com.ph.idisciple.idiscipleapp.widgets.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.ph.idisciple.idiscipleapp.R;


/**
 * Created by Jeven Soquita on 19/12/2017.
 */

public class CustomMontserratBoldTextView extends AppCompatTextView {
    public CustomMontserratBoldTextView(Context context) {
        super(context);
    }

    public CustomMontserratBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyFont(context);
    }

    public CustomMontserratBoldTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyFont(context);
    }

    private void applyFont(Context context) {
        if (!isInEditMode()) {
            Typeface type = TypeFaceHelper.get(context, context.getString(R.string.font_montserrat_bold));
            setTypeface(type);
        }
    }
}
