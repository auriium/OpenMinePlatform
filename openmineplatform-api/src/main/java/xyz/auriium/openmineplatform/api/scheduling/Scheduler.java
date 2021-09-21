/*
 * OpenMinePlatform
 * Copyright © 2021 Auriium
 *
 * OpenMinePlatform is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * OpenMinePlatform is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with OpenMinePlatform. If not, see <https://www.gnu.org/licenses/>
 * and navigate to version 3 of the GNU Affero General Public License.
 */

package xyz.auriium.openmineplatform.api.scheduling;

import xyz.auriium.openmineplatform.api.PlatformCloseable;

import java.util.concurrent.Executor;

public interface Scheduler extends PlatformCloseable {

    Executor sync();
    DelayedPurveyor async();

    SchedulerTask run(Runnable runnable);
    SchedulerTask runLater(Runnable runnable, long delay);
    SchedulerTask runRepeated(Runnable runnable, long period);

    SchedulerTask runAsync(Runnable runnable);
    SchedulerTask runLaterAsync(Runnable runnable, long delay);
    SchedulerTask runRepeatedAsync(Runnable runnable, long period);

    SchedulerTask run(Runnable runnable, boolean persistent);
    SchedulerTask runLater(Runnable runnable, long delay, boolean persistent);
    SchedulerTask runRepeated(Runnable runnable, long period, boolean persistent);

    SchedulerTask runAsync(Runnable runnable, boolean persistent);
    SchedulerTask runLaterAsync(Runnable runnable, long delay, boolean persistent);
    SchedulerTask runRepeatedAsync(Runnable runnable, long period, boolean persistent);



}
