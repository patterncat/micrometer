/**
 * Copyright 2017 Pivotal Software, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micrometer.core.instrument;

import java.util.concurrent.TimeUnit;

public class MockClock implements Clock {
    private long time = 0;

    @Override
    public long monotonicTime() {
        return time;
    }

    public static MockClock clock(MeterRegistry collector) {
        return (MockClock) collector.getClock();
    }

    public long addAndGet(long amount, TimeUnit unit) {
        time += unit.toNanos(amount);
        return time;
    }

    public long addAndGetNanos(long amount) {
        time += amount;
        return time;
    }
}
