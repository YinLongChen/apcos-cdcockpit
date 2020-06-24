package com.jinxin.platform.cdcockpit.pojo.vo.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author Huang LingSong
 * 2020-06-18 14:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Series {
    private String name;
    private String[] data;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Series series = (Series) o;
        return name.equals(series.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
