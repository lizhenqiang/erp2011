/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc.util;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by San on 10/10/15.
 */
public class QueueRunner {
    private Queue<Runnable> mTaskQueue = new ArrayDeque<>();

    public QueueRunner add(Runnable task) {
        mTaskQueue.add(task);
        return this;
    }

    public void next() {
        if (!mTaskQueue.isEmpty()) {
            mTaskQueue.remove().run();
        }
    }
}
