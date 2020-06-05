package com.ruoyi.framework.config;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class UserInfo {

    private String pro;//省份
    private String are;//地市
    private String pre;//县

    public static void main(String[] args) {
        List<UserInfo> infoVOS = Lists.newArrayList();
        UserInfo sd = new UserInfo("山东", "济南", "县城1");
        UserInfo sd1 = new UserInfo("山东", "济南", "县城2");
        UserInfo yt = new UserInfo("山东", "烟台", "县城3");
        UserInfo cs = new UserInfo("湖南", "长沙", "县城4");
        UserInfo xt = new UserInfo("湖南", "湘潭", "县城5");
        UserInfo xt2 = new UserInfo("湖南", "湘潭", "县城6");
        infoVOS.add(sd);
        infoVOS.add(sd1);
        infoVOS.add(yt);
        infoVOS.add(cs);
        infoVOS.add(xt);
        infoVOS.add(xt2);
    }

}
