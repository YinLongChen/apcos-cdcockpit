package com.jinxin.platform.cdcockpit.pojo.vo.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author Huang LingSong
 * 2020-05-14 9:47
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountResult {
    private String name;
    private Integer value;
    private String remark;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CountResult that = (CountResult) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
