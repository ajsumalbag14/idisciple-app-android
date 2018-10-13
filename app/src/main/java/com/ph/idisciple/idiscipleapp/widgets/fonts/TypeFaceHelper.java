package com.ph.idisciple.idiscipleapp.widgets.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Hashtable;

/**
 * Created by Jeven Soquita on 19/12/2017.
 */

public class TypeFaceHelper {

    private static final String TAG = "TypefaceHelper";

    private static final Hashtable<String, Typeface> cache = new Hashtable<>();

    @Nullable
    public static Typeface get(Context c, String assetPath) {
        synchronized (cache) {
            if (!cache.containsKey(assetPath)) {
                try {
                    Typeface t = Typeface.createFromAsset(c.getAssets(),
                            assetPath);
                    cache.put(assetPath, t);
                } catch (Exception e) {
                    Log.e(TAG, "Could not get typeface '" + assetPath
                            + "' because " + e.getMessage());
                    return null;
                }
            }
            return cache.get(assetPath);
        }
    }
}
