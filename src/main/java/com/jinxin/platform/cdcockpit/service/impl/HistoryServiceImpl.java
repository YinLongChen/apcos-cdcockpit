package com.jinxin.platform.cdcockpit.service.impl;

import com.jinxin.platform.cdcockpit.exception.CockpitException;
import com.jinxin.platform.cdcockpit.mapper.HistoryMapper;
import com.jinxin.platform.cdcockpit.mapper.ListMapper;
import com.jinxin.platform.cdcockpit.pojo.domain.ListMap;
import com.jinxin.platform.cdcockpit.pojo.vo.list.Line;
import com.jinxin.platform.cdcockpit.pojo.vo.list.ListForm;
import com.jinxin.platform.cdcockpit.pojo.vo.list.ListMapCriteria;
import com.jinxin.platform.cdcockpit.service.HistoryService;
import com.jinxin.platform.cdcockpit.service.ListService;
import com.jinxin.platform.cdcockpit.utils.ResultUtil;
import com.jinxin.platform.cdcockpit.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private ListMapper listMapper;

    @Autowired
    private ListService listService;
    @Autowired
    private HistoryMapper historyMapper;

    @Override
    @Deprecated
    public Line selectHistoryData(String modelId, Integer field) {

        if (StringUtils.isEmpty(modelId)) {
            throw new CockpitException("model_id不能为空");
        }

        String view = listMapper.selectViewByModelId(modelId);
        if (StringUtils.isEmpty(view)) {
            throw new CockpitException("没有找到view");
        }

        Map<String, String> listMap = listMapper.selectMaps(ListMapCriteria.builder().modelId(modelId).show(0).build()).stream()
                .collect(Collectors.toMap(ListMap::getColumnOrl, ListMap::getColumnCn));
        List<String> legend = new ArrayList<>();
        List<Line.seriesData> series = new ArrayList<>();
        for (Map.Entry<String, String> entry : listMap.entrySet()) {
            series.add(Line.seriesData.builder().name(entry.getValue()).data(new ArrayList<>()).build());
            legend.add(entry.getValue());
        }

        Calendar c = ResultUtil.handleTime(field);


        SimpleDateFormat formater = new SimpleDateFormat("MM-dd");
        if (Calendar.YEAR == field) {
            formater = new SimpleDateFormat("YY-MM-dd");
        }
        List<Map<String, Object>> list = historyMapper.selectHistoryData(view, c.getTime(), new Date());
        for (Map<String, Object> m : list) {
            m.put("TIME_STR", formater.format((Date) m.get("TIME")));
        }


        StringUtil.distinct(list, "TIME_STR");

        List<String> xAxis = new ArrayList<>();

        List<Map<String, Object>> fill = ResultUtil.fillInData2(list, field);
        if (Calendar.YEAR == field) {
            SimpleDateFormat year = new SimpleDateFormat("yyyy-MM");
            Map<String, List<Map<String, Object>>> group = fill.stream()
                    .map(m -> {
                        m.put("TIME_STR", year.format((Date) m.get("TIME")));
                        return m;
                    })
                    .collect(Collectors.groupingBy(e -> e.get("TIME_STR").toString()));

            LinkedHashMap<String, List<Map<String, Object>>> collect = group
                    .entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
            fill = new ArrayList<>();
            for (Map.Entry<String, List<Map<String, Object>>> e : collect.entrySet()) {
                Map map = new HashMap();
                map.put("TIME_STR", e.getKey());
                e.getValue().stream().forEach(m -> {
                    for (Map.Entry<String, String> entry : listMap.entrySet()) {
                        Float value = m.get(entry.getKey()) == null ? 0 : Float.parseFloat(m.get(entry.getKey()).toString());
                        if (map.get(entry.getKey()) == null) {
                            map.put(entry.getKey(), 0);
                        }
                        map.put(entry.getKey(), Float.parseFloat(map.get(entry.getKey()).toString()) + value);
                    }
                });
                fill.add(map);
            }

        }
        fill.forEach(f -> {
            xAxis.add(f.get("TIME_STR").toString());
            for (Map.Entry<String, String> entry : listMap.entrySet()) {
                int index = series.indexOf(Line.seriesData.builder().name(entry.getValue()).build());
                String value = f.get(entry.getKey()) == null ? "0" : f.get(entry.getKey()).toString();
                series.get(index).getData().add(value);
            }
        });


        return Line.builder().legend(legend).xAxis(xAxis).series(series).build();
    }

    public Line selectHistoryData(ListForm form) {

        if (StringUtils.isEmpty(form.getModelId())) {
            throw new CockpitException("model_id不能为空");
        }

        String view = listMapper.selectViewByModelId(form.getModelId());
        if (StringUtils.isEmpty(view)) {
            throw new CockpitException("没有找到view");
        }

        Map<String, String> listMap = listMapper.selectMaps(ListMapCriteria.builder().modelId(form.getModelId()).show(0).build()).stream()
                .collect(Collectors.toMap(ListMap::getColumnOrl, ListMap::getColumnCn));
        List<String> legend = new ArrayList<>();
        List<Line.seriesData> series = new ArrayList<>();
        for (Map.Entry<String, String> entry : listMap.entrySet()) {
            series.add(Line.seriesData.builder().name(entry.getValue()).data(new ArrayList<>()).build());
            legend.add(entry.getValue());
        }

        Calendar c = ResultUtil.handleTime(form.getField());


        SimpleDateFormat formater = new SimpleDateFormat("MM-dd");
        if (Calendar.YEAR == form.getField()) {
            formater = new SimpleDateFormat("YY-MM-dd");
        }

        String where = listService.buildWhere(form.getWheres(), view);

        List<Map<String, Object>> list = historyMapper.selectHistory(view, where ,c.getTime(), new Date());
        for (Map<String, Object> m : list) {
            m.put("TIME_STR", formater.format((Date) m.get("TIME")));
        }


        StringUtil.distinct(list, "TIME_STR");

        List<String> xAxis = new ArrayList<>();

        List<Map<String, Object>> fill = ResultUtil.fillInData2(list, form.getField());
        if (Calendar.YEAR == form.getField()) {
            SimpleDateFormat year = new SimpleDateFormat("yyyy-MM");
            Map<String, List<Map<String, Object>>> group = fill.stream()
                    .map(m -> {
                        m.put("TIME_STR", year.format((Date) m.get("TIME")));
                        return m;
                    })
                    .collect(Collectors.groupingBy(e -> e.get("TIME_STR").toString()));

            LinkedHashMap<String, List<Map<String, Object>>> collect = group
                    .entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
            fill = new ArrayList<>();
            for (Map.Entry<String, List<Map<String, Object>>> e : collect.entrySet()) {
                Map map = new HashMap();
                map.put("TIME_STR", e.getKey());
                e.getValue().stream().forEach(m -> {
                    for (Map.Entry<String, String> entry : listMap.entrySet()) {
                        Float value = m.get(entry.getKey()) == null ? 0 : Float.parseFloat(m.get(entry.getKey()).toString());
                        if (map.get(entry.getKey()) == null) {
                            map.put(entry.getKey(), 0);
                        }
                        map.put(entry.getKey(), Float.parseFloat(map.get(entry.getKey()).toString()) + value);
                    }
                });
                fill.add(map);
            }

        }
        fill.forEach(f -> {
            xAxis.add(f.get("TIME_STR").toString());
            for (Map.Entry<String, String> entry : listMap.entrySet()) {
                int index = series.indexOf(Line.seriesData.builder().name(entry.getValue()).build());
                String value = f.get(entry.getKey()) == null ? "0" : f.get(entry.getKey()).toString();
                series.get(index).getData().add(value);
            }
        });


        return Line.builder().legend(legend).xAxis(xAxis).series(series).build();
    }

}
