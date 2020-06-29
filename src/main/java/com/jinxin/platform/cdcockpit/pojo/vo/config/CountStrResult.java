package com.jinxin.platform.cdcockpit.pojo.vo.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author Huang LingSong
 * 2020-06-22 16:18
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountStrResult {
    private String name;
    private String value;
    private String remark;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CountStrResult that = (CountStrResult) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
