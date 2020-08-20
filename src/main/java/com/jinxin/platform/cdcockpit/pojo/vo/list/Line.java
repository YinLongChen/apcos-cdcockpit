package com.jinxin.platform.cdcockpit.pojo.vo.list;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Line {
    private List<String> legend;
    private List<String> xAxis;
    private List<seriesData> series;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class seriesData {
        private String name;
        private List<String> data;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            seriesData that = (seriesData) o;
            return name.equals(that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
