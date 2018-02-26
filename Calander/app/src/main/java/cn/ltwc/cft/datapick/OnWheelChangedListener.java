/*
 *  Copyright 2010 Yuri Kanivets
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package cn.ltwc.cft.datapick;

/**
 * TODO:Wheel changed listener interface.
 * <p>
 * The currentItemChanged() method is called whenever current wheel positions is
 * changed:
 * <li>New Wheel position is set
 * <li>Wheel view is scrolled
 *
 * @author huangshang 2015-11-21 上午1:21:12
 * @Modified_By:
 */
public interface OnWheelChangedListener {
    /**
     * Callback method to be invoked when current item_show_img changed
     *
     * @param wheel    the wheel view whose state has changed
     * @param oldValue the old value of current item_show_img
     * @param newValue the new value of current item_show_img
     */
    void onChanged(WheelView wheel, int oldValue, int newValue);
}
