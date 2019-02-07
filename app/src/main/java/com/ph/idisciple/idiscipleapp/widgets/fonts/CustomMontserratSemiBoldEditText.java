package com.ph.idisciple.idiscipleapp.widgets.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.ph.idisciple.idiscipleapp.R;

import java.lang.reflect.Field;


/**
 * Created by Jeven Soquita on 19/12/2017.
 */

public class CustomMontserratSemiBoldEditText extends AppCompatEditText {
    public CustomMontserratSemiBoldEditText(Context context) {
        super(context);
    }

    public CustomMontserratSemiBoldEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        applyFont(context);
    }

    public CustomMontserratSemiBoldEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        applyFont(context);
    }

    private void applyFont(Context context) {
        if (!isInEditMode()) {
            Typeface type = TypeFaceHelper.get(context, context.getString(R.string.font_montserrat_semibold));
            setTypeface(type);
        }
    }

    /* Disable Copy Paste Function */

    /** This is a replacement method for the base TextView class' method of the same name. This
     * method is used in hidden class android.widget.Editor to determine whether the PASTE/REPLACE popup
     * appears when triggered from the text insertion handle. Returning false forces this window
     * to never appear.
     * @return false
     */
    boolean canPaste()
    {
        return false;
    }

    /** This is a replacement method for the base TextView class' method of the same name. This method
     * is used in hidden class android.widget.Editor to determine whether the PASTE/REPLACE popup
     * appears when triggered from the text insertion handle. Returning false forces this window
     * to never appear.
     * @return false
     */
    @Override
    public boolean isSuggestionsEnabled()
    {
        return false;
    }

    private void init()
    {
        this.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
        this.setLongClickable(false);
        this.setTextIsSelectable(false);
        this.setSelected(false);
        this.setCustomSelectionActionModeCallback(new ActionModeCallbackInterceptor());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // setInsertionDisabled when user touches the view
            this.setInsertionDisabled();
        }
        return super.onTouchEvent(event);
    }

    private void setInsertionDisabled() {
        try {
            Field editorField = TextView.class.getDeclaredField("mEditor");
            editorField.setAccessible(true);
            Object editorObject = editorField.get(this);

            Class editorClass = Class.forName("android.widget.Editor");
            Field mInsertionControllerEnabledField = editorClass.getDeclaredField("mInsertionControllerEnabled");
            mInsertionControllerEnabledField.setAccessible(true);
            mInsertionControllerEnabledField.set(editorObject, false);
        }
        catch (Exception ignored) {
            // ignore exception here
        }
    }

    /**
     * Prevents the action bar (top horizontal bar with cut, copy, paste, etc.) from appearing
     * by intercepting the callback that would cause it to be created, and returning false.
     */
    private class ActionModeCallbackInterceptor implements ActionMode.Callback
    {
        @Override
        public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
            try {
                menu.clear();
            } catch (Exception ex){ }
            return false;
        }

        @Override
        public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
            try {
                menu.clear();
            } catch (Exception ex){ }
            return false;
        }

        @Override
        public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
            return false;
        }

        @Override
        public void onDestroyActionMode(android.view.ActionMode mode) {

        }
    }

}
