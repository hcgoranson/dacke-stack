package one.goranson.bokker.metric;

import java.util.List;

import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.config.MeterFilter;

@Configuration
public class MetricRegistryConfig {
    private static String METRIC_HTTP_SERVER_REQUESTS = "http.server.requests";

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsRegistryConfig() {
        return registry -> registry.config()
                .meterFilter(MeterFilter.deny(id -> false))
                .meterFilter(addStatusRangeTag())
                .meterFilter(addServiceNameTag());
    }

    private static MeterFilter addServiceNameTag() {
        return new MeterFilter() {
            @Override
            public Meter.Id map(Meter.Id id) {
                return id.withTags(List.of(
                        Tag.of("service", "bokker"),
                        Tag.of("instance", "one"),
                        Tag.of("application", "bokker")
                        ));
            }
        };
    }

    private static MeterFilter addStatusRangeTag() {
        return new MeterFilter() {
            @Override
            public Meter.Id map(Meter.Id id) {
                // Generalize the status code into 1xx, 2xx, etc
                if (id.getName().startsWith(METRIC_HTTP_SERVER_REQUESTS)) {
                    final String statusRange = getHttpStatusCodeAsRange(id.getTag("status"));
                    if (statusRange != null) {
                        return id.withTag(Tag.of("status-range", statusRange));
                    }
                }
                return id;
            }
        };
    }

    public static String getHttpStatusCodeAsRange(String status) {
        if (status == null) {
            return null;
        }

        if (status.startsWith("1")) {
            return "1xx";
        } else if (status.startsWith("2")) {
            return "2xx";
        } else if (status.startsWith("3")) {
            return "3xx";
        } else if (status.startsWith("4")) {
            return "4xx";
        } else if (status.startsWith("5")) {
            return "5xx";
        }

        return null;
    }
}