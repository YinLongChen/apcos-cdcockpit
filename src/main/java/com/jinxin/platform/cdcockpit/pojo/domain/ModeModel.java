package com.jinxin.platform.cdcockpit.pojo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

/**
 * @author Huang LingSong
 * 2020-07-10 11:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModeModel {
    private String id;
    private String modeName;
    private String account;
    private String enableMode;
    private String msOrder;
    private String modeReadme;
    private String msControl;
}
