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
package io.micrometer.core.instrument.prometheus;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Measurement;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.util.MeterId;

import java.util.Collections;

public class PrometheusGauge implements Gauge {
    private final MeterId id;
    private io.prometheus.client.Gauge.Child gauge;

    PrometheusGauge(MeterId id, io.prometheus.client.Gauge.Child gauge) {
        this.id = id;
        this.gauge = gauge;
    }

    @Override
    public double value() {
        return gauge.get();
    }

    @Override
    public String getName() {
        return id.getName();
    }

    @Override
    public Iterable<Tag> getTags() {
        return id.getTags();
    }

    @Override
    public Iterable<Measurement> measure() {
        return Collections.singletonList(id.measurement(gauge.get()));
    }
}
