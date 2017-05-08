package com.fionera.ctracker.util;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.fionera.ctracker.CrashFallbackActivity;

import java.lang.Thread.UncaughtExceptionHandler;

@SuppressWarnings("InfiniteLoopStatement")
public class DefaultCrashHandler
        implements UncaughtExceptionHandler {
    private Context mContext;
    private UncaughtExceptionHandler mDefaultHandler;

    private DefaultCrashHandler() {
    }

    public static DefaultCrashHandler getInstance() {
        return new DefaultCrashHandler();
    }

    public void init(Context context) {
        mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                /*
                Here is a main loop for main thread message queue.
                If the main thread is dead, it will loop it again to maintain message queue.
                 */
                while (true) {
                    try {
                        Looper.loop();
                    } catch (Throwable e) {
                        uncaughtException(Looper.getMainLooper().getThread(), e);
                    }
                }
            }
        });
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            if (mContext != null) {
                String crashInfo = ex.getMessage();
                if (TextUtils.isEmpty(crashInfo)) {
                    crashInfo = ex.getCause().getMessage();
                }
                mContext.startActivity(new Intent(mContext, CrashFallbackActivity.class)
                        .putExtra("crash_info", crashInfo));
            }
        }
    }

    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        ex.printStackTrace();
        return true;
    }
}