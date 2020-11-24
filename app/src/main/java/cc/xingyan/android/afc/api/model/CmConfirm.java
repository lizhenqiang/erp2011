package cc.xingyan.android.afc.api.model;

import com.squareup.moshi.Json;

/**
 * Created by 1 on 2015/12/8.
 */
public class CmConfirm {
        @Json(name = "OrderId") String OrderId;
        @Json(name = "confirm") String confirm;
        public String getOrderId() {
                return OrderId;
        }

        public void setOrderId(String OrderId) {
                this.OrderId = OrderId;
        }

        public String getconfirm() {
                return confirm;
        }

        public void setconfirm(String confirm) {
                this.confirm = confirm;
        }
}
