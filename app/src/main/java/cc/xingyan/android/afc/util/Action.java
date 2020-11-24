/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc.util;

public interface Action {
    /**
     * Name of this action
     */
    String name();

    /**
     * Proceeds to do this action.
     */
    void proceed();

    /**
     * Intercepts an action and decide whether it should proceed.
     */
    public interface Interceptor {
        /**
         * @param action
         * @return {@code true} to stop the {@code action}.
         */
        boolean onInterceptAction(Action action);
    }
}

