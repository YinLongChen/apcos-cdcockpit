package com.jinxin.platform.cdcockpit.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jinxin.platform.cdcockpit.mapper.UserMapper;
import com.jinxin.platform.cdcockpit.pojo.domain.User;
import com.jinxin.platform.cdcockpit.pojo.enumeration.GenderType;
import com.jinxin.platform.cdcockpit.pojo.vo.config.CountResult;
import com.jinxin.platform.cdcockpit.pojo.vo.result.Paging;
import com.jinxin.platform.cdcockpit.pojo.vo.user.UserCriteria;
import com.jinxin.platform.cdcockpit.pojo.vo.user.UserForm;
import com.jinxin.platform.cdcockpit.service.CountUserService;
import com.jinxin.platform.cdcockpit.utils.Phone;
import com.jinxin.platform.cdcockpit.utils.PhoneUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Huang LingSong
 * 2020-04-16 15:21
 */
@Slf4j
@Service
public class CountUserServiceImpl implements CountUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Paging<User> findUsersPage(UserForm form) {
        Page page = PageHelper.startPage(form.getCurrent(), form.getSize());

        int[] ages = getAge(form.getAgeGroup());

        List<User> users = userMapper.selectUser(UserCriteria
                .builder()
                .gender(form.getGender())
                .startTime(form.getStartTime())
                .ageStart(ages == null ? null : ages[0])
                .ageEnd(ages == null ? null : ages[1])
                .build());

        return new Paging<>(form.getCurrent(), form.getSize(), page.getTotal(), page.getPages(), users);
    }

    @Override
    public List<CountResult> userCountByGender() {
        Map<String, List<CountResult>> CountMap = userMapper.userCountByGender().stream().map(u -> {
            u.setRemark(u.getName());
            String gender = GenderType.MAN.getDesc().equals(u.getName()) || GenderType.WOMAN.getDesc().equals(u.getName()) ? u.getName() : GenderType.getByVal(u.getName()).getDesc();
            u.setName(gender);
            return u;
        }).collect(Collectors.groupingBy(CountResult::getName));

        List<CountResult> countResults = new ArrayList<>();
        for (Map.Entry<String, List<CountResult>> entry : CountMap.entrySet()) {
            Integer sum = entry.getValue().stream().mapToInt(CountResult::getValue).sum();
            countResults.add(CountResult.builder().name(entry.getKey()).value(sum).remark(GenderType.getByDesc(entry.getKey()).getValue()).build());
        }

        return countResults;

    }

    @Override
    public List<CountResult> userCountByAgeGroup() {

        Map<String, List<User>> userMap = userMapper.selectUser(null)
                .stream().map(u -> {
                    u.setAgeGroup(getAgeGroup(u.getAge()));
                    return u;
                }).collect(Collectors.groupingBy(User::getAgeGroup));

        List<CountResult> countResults = new ArrayList<>();
        for (Map.Entry<String, List<User>> entry : userMap.entrySet()) {
            countResults.add(CountResult.builder().name(entry.getKey()).value(entry.getValue().size()).build());
        }

        return countResults;
    }

    @Override
    public List<CountResult> userCountByTime(int field) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat;
        switch (field) {
            case Calendar.DATE:
                c.add(Calendar.DATE, -1);
                dateFormat = new SimpleDateFormat("dd日HH点");
                break;
            case Calendar.WEEK_OF_YEAR:
                c.add(Calendar.DATE, -7);
                dateFormat = new SimpleDateFormat("MM-dd");
                break;
            case Calendar.MONTH:
                c.add(Calendar.MONTH, -1);
                dateFormat = new SimpleDateFormat("MM-dd");
                break;
            case Calendar.YEAR:
                c.add(Calendar.YEAR, -1);
                dateFormat = new SimpleDateFormat("yyyy-MM");
                break;
            default:
                return null;

        }
        //获取数据
        List<User> users = userMapper.selectUser(UserCriteria.builder()
                .startTime(c.getTime())
                .build());

        Map<String, List<User>> userMap = users.stream()
                .filter(u -> u.getRegistTime() != null)
                .map(u -> {
                    u.setRegistStrTime(dateFormat.format(u.getRegistTime()));
                    return u;
                })
                .collect(Collectors.groupingBy(User::getRegistStrTime));


        List<CountResult> countResults = new ArrayList<>();
        Map<String, Integer> dataSet = new HashMap();
        for (Map.Entry<String, List<User>> entry : userMap.entrySet()) {
            countResults.add(CountResult.builder().name(entry.getKey()).value(entry.getValue().size()).build());
            dataSet.put(entry.getKey(), entry.getValue().size());
        }


        //获取注册人数为零的日期 填充
        List<CountResult> fill = new ArrayList<>();
        while (c.getTime().before(new Date())) {
            if (null == dataSet.get(dateFormat.format(c.getTime()))) {
                fill.add(CountResult.builder().name(dateFormat.format(c.getTime())).value(0).build());
            }
            switch (field) {
                case Calendar.DATE:
                    c.add(Calendar.HOUR, 1);
                    break;
                case Calendar.MONTH:
                case Calendar.WEEK_OF_YEAR:
                    c.add(Calendar.DATE, 1);
                    break;
                case Calendar.YEAR:
                    c.add(Calendar.MONTH, 1);
                    break;
                default:
                    return null;

            }
        }

        countResults.addAll(fill);
        countResults.sort(Comparator.comparing(CountResult::getName));

        return countResults;
    }

    @Override
    public List<CountResult> userCountByRegion() {
        List<User> users =  userMapper.selectUser(UserCriteria.builder().build());

        Map<String, List<User>> map = users.stream().map(u -> {

            if (StringUtils.isEmpty(u.getAddress())) {
                Phone region = PhoneUtil.getPhoneNumberInfo(u.getPhone());
                u.setProv(region == null ? "未知" : region.getProvince());
            } else {
                u.setProv(PhoneUtil.transform(u.getAddress()).getProvince());
            }

            return u;
        }).collect(Collectors.groupingBy(User::getProv));


        List<CountResult> countResults = new ArrayList<>();
        for (Map.Entry<String, List<User>> entry : map.entrySet()) {
            countResults.add(CountResult.builder().name(entry.getKey()).value(entry.getValue().size()).build());

        }

        return countResults;
    }


    private String getAgeGroup(Integer age) {
        if (age == null) {
            return "其他";
        }
        return (age - (age % 10)) + "-" + (age - (age % 10) + 10);
    }

    private int[] getAge(String ageGroup) {
        if (StringUtils.isEmpty(ageGroup)) {
            return null;
        }
        String[] ageStr = ageGroup.split("-");
        if (ageStr.length == 2) {
            int[] ages = new int[2];
            for (int i = 0; i < ageStr.length; i++) {
                ages[i] = Integer.valueOf(ageStr[i]);
            }
            if (ages[0] > ages[1]) {
                throw new RuntimeException("参数错误 ageGroup：" + ageGroup);
            }
            return ages;
        }
        throw new RuntimeException("参数错误 ageGroup：" + ageGroup);
    }

}
