/*
 * Copyright (c) 2015. Xingyan, Ltd - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 *
 * This file is originally created by San.
 */

package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by San on 10/9/15.
 */
public class WorkOrders {
    @Json(name = "mcm") List<WorkOrder> workOrders;

    public List<WorkOrder> getWorkOrders() {
        return workOrders;
    }

    public void setWorkOrders(List<WorkOrder> workOrders) {
        this.workOrders = workOrders;
    }
}
