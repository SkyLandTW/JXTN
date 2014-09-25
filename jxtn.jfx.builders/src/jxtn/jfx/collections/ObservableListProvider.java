/*
 * Copyright © 2014 Skyland Inc.
 * Copyright © 2014 天基科技有限公司。
 */

package jxtn.jfx.collections;

import javafx.collections.ObservableList;

public interface ObservableListProvider<T>
{
    /**
     * 取得供UI使用的可觀察異動的清單
     * <p>
     * 每個集合最多只會有一份這樣的清單。
     * </p>
     *
     * @return 供UI使用的可觀察異動的清單
     */
    ObservableList<T> observable();
}
