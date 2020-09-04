INSERT INTO "ODS_MODEL_TYPE_CONFIG"("ID", "TYPE_NAME") VALUES ('1', '列表类模型');
INSERT INTO "ODS_MODEL_TYPE_CONFIG"("ID", "TYPE_NAME") VALUES ('2', '排行类模型');
INSERT INTO "ODS_MODEL_TYPE_CONFIG"("ID", "TYPE_NAME") VALUES ('3', '趋势比较模型');
INSERT INTO "ODS_MODEL_TYPE_CONFIG"("ID", "TYPE_NAME") VALUES ('4', 'KV模型');
INSERT INTO "ODS_MODEL_TYPE_CONFIG"("ID", "TYPE_NAME") VALUES ('5', '历史数据模型');

INSERT INTO "ODS_MODEL_CONFIG"("ID", "MODEL_NAME", "TYPE_ID") VALUES ('ENERGY_ELECTRIC', '用电统计分析', '5');
INSERT INTO "ODS_MODEL_CONFIG"("ID", "MODEL_NAME", "TYPE_ID") VALUES ('ENERGY_WATER', '用水统计分析', '5');
INSERT INTO "ODS_MODEL_CONFIG"("ID", "MODEL_NAME", "TYPE_ID") VALUES ('ENERGY_COST', '能耗费用统计分析', '5');
INSERT INTO "ODS_MODEL_CONFIG"("ID", "MODEL_NAME", "TYPE_ID") VALUES ('ELECTRIC_CONSUMPTION', '用电量', '1');
INSERT INTO "ODS_MODEL_CONFIG"("ID", "MODEL_NAME", "TYPE_ID") VALUES ('BASE_DATA', '基础数据', '4');
INSERT INTO "ODS_MODEL_CONFIG"("ID", "MODEL_NAME", "TYPE_ID") VALUES ('ENERGY_CONSUMPTION', '累计能耗', '4');
INSERT INTO "ODS_MODEL_CONFIG"("ID", "MODEL_NAME", "TYPE_ID") VALUES ('WATER_CONSUMPTION', '用水量', '1');


INSERT INTO "ODS_LIST_MODEL_VIEW"("MODEL_ID", "VIEW_NAME", "REMARK", "TYPE") VALUES ('ELECTRIC_CONSUMPTION', 'V_ODS_ELECTRIC_CONSUMPTION', '用电量', '业务表');
INSERT INTO "ODS_LIST_MODEL_VIEW"("MODEL_ID", "VIEW_NAME", "REMARK", "TYPE") VALUES ('WATER_CONSUMPTION', 'V_ODS_WATER_CONSUMPTION', '用水量', '业务表');
INSERT INTO "ODS_LIST_MODEL_VIEW"("MODEL_ID", "VIEW_NAME", "REMARK", "TYPE") VALUES ('ENERGY_ELECTRIC', 'V_ODS_ENERGY_ELECTRIC', '历史用电量', '业务表');
INSERT INTO "ODS_LIST_MODEL_VIEW"("MODEL_ID", "VIEW_NAME", "REMARK", "TYPE") VALUES ('ENERGY_COST', 'V_ODS_ENERGY_COST', '历史能源费用', '业务表');
INSERT INTO "ODS_LIST_MODEL_VIEW"("MODEL_ID", "VIEW_NAME", "REMARK", "TYPE") VALUES ('ENERGY_WATER', 'V_ODS_ENERGY_WATER', '历史用水量', '业务表');


INSERT INTO "ODS_LIST_MODEL_DATA_MAP"("ID", "MODEL_ID", "COLUMN_ORI", "COLUMN_EN", "COLUMN_CN", "DATA_TYPE", "SHOW_TYPE") VALUES ('1', 'ENERGY_WATER', 'TOTAL', 'TOTAL', '用水总量', '1', '0');
INSERT INTO "ODS_LIST_MODEL_DATA_MAP"("ID", "MODEL_ID", "COLUMN_ORI", "COLUMN_EN", "COLUMN_CN", "DATA_TYPE", "SHOW_TYPE") VALUES ('10', 'ELECTRIC_CONSUMPTION', 'TIME', 'TIME', '时间', '0', '1');
INSERT INTO "ODS_LIST_MODEL_DATA_MAP"("ID", "MODEL_ID", "COLUMN_ORI", "COLUMN_EN", "COLUMN_CN", "DATA_TYPE", "SHOW_TYPE") VALUES ('11', 'ELECTRIC_CONSUMPTION', 'VALUE', 'VALUE', '用电量', '1', '0');
INSERT INTO "ODS_LIST_MODEL_DATA_MAP"("ID", "MODEL_ID", "COLUMN_ORI", "COLUMN_EN", "COLUMN_CN", "DATA_TYPE", "SHOW_TYPE") VALUES ('12', 'ELECTRIC_CONSUMPTION', 'NAME', 'NAME', '位置', '1', '0');
INSERT INTO "ODS_LIST_MODEL_DATA_MAP"("ID", "MODEL_ID", "COLUMN_ORI", "COLUMN_EN", "COLUMN_CN", "DATA_TYPE", "SHOW_TYPE") VALUES ('13', 'ELECTRIC_CONSUMPTION', 'UNIT', 'UNIT', '单位', '1', '0');
INSERT INTO "ODS_LIST_MODEL_DATA_MAP"("ID", "MODEL_ID", "COLUMN_ORI", "COLUMN_EN", "COLUMN_CN", "DATA_TYPE", "SHOW_TYPE") VALUES ('14', 'WATER_CONSUMPTION', 'TIME', 'TIME', '时间', '0', '1');
INSERT INTO "ODS_LIST_MODEL_DATA_MAP"("ID", "MODEL_ID", "COLUMN_ORI", "COLUMN_EN", "COLUMN_CN", "DATA_TYPE", "SHOW_TYPE") VALUES ('15', 'WATER_CONSUMPTION', 'NAME', 'NAME', '位置', '1', '0');
INSERT INTO "ODS_LIST_MODEL_DATA_MAP"("ID", "MODEL_ID", "COLUMN_ORI", "COLUMN_EN", "COLUMN_CN", "DATA_TYPE", "SHOW_TYPE") VALUES ('16', 'WATER_CONSUMPTION', 'VALUE', 'VALUE', '用水量', '1', '0');
INSERT INTO "ODS_LIST_MODEL_DATA_MAP"("ID", "MODEL_ID", "COLUMN_ORI", "COLUMN_EN", "COLUMN_CN", "DATA_TYPE", "SHOW_TYPE") VALUES ('17', 'WATER_CONSUMPTION', 'UNIT', 'UNIT', '单位', '1', '0');
INSERT INTO "ODS_LIST_MODEL_DATA_MAP"("ID", "MODEL_ID", "COLUMN_ORI", "COLUMN_EN", "COLUMN_CN", "DATA_TYPE", "SHOW_TYPE") VALUES ('2', 'ENERGY_WATER', 'HUNMAN_NUM', 'HUNMAN_NUM', '人均用水', '1', '0');
INSERT INTO "ODS_LIST_MODEL_DATA_MAP"("ID", "MODEL_ID", "COLUMN_ORI", "COLUMN_EN", "COLUMN_CN", "DATA_TYPE", "SHOW_TYPE") VALUES ('3', 'ENERGY_WATER', 'AREAS', 'AREAS', '面积均用水', '1', '0');
INSERT INTO "ODS_LIST_MODEL_DATA_MAP"("ID", "MODEL_ID", "COLUMN_ORI", "COLUMN_EN", "COLUMN_CN", "DATA_TYPE", "SHOW_TYPE") VALUES ('4', 'ENERGY_ELECTRIC', 'TOTAL', 'TOTAL', '用电总量', '1', '0');
INSERT INTO "ODS_LIST_MODEL_DATA_MAP"("ID", "MODEL_ID", "COLUMN_ORI", "COLUMN_EN", "COLUMN_CN", "DATA_TYPE", "SHOW_TYPE") VALUES ('5', 'ENERGY_ELECTRIC', 'HUNMAN_NUM', 'HUNMAN_NUM', '人均用电', '1', '0');
INSERT INTO "ODS_LIST_MODEL_DATA_MAP"("ID", "MODEL_ID", "COLUMN_ORI", "COLUMN_EN", "COLUMN_CN", "DATA_TYPE", "SHOW_TYPE") VALUES ('6', 'ENERGY_ELECTRIC', 'AREAS', 'AREAS', '面积均用电', '1', '0');
INSERT INTO "ODS_LIST_MODEL_DATA_MAP"("ID", "MODEL_ID", "COLUMN_ORI", "COLUMN_EN", "COLUMN_CN", "DATA_TYPE", "SHOW_TYPE") VALUES ('7', 'ENERGY_COST', 'WATER_AMOUNT', 'WATER_AMOUNT', '用水费用', '1', '0');
INSERT INTO "ODS_LIST_MODEL_DATA_MAP"("ID", "MODEL_ID", "COLUMN_ORI", "COLUMN_EN", "COLUMN_CN", "DATA_TYPE", "SHOW_TYPE") VALUES ('8', 'ENERGY_COST', 'ELECTRIC_AMOUNT', 'ELECTRIC_AMOUNT', '用电费用', '1', '0');
INSERT INTO "ODS_LIST_MODEL_DATA_MAP"("ID", "MODEL_ID", "COLUMN_ORI", "COLUMN_EN", "COLUMN_CN", "DATA_TYPE", "SHOW_TYPE") VALUES ('9', 'ENERGY_COST', 'TOTAL', 'TOTAL', '费用总计', '1', '0');



INSERT INTO "ODS_KV_MODEL_DATA"("ID", "MODEL_ID", "NAME", "KEY", "VALUE", "REMARK") VALUES ('1', 'BASE_DATA', '总人数', 'total_pople', '0', '人');
INSERT INTO "ODS_KV_MODEL_DATA"("ID", "MODEL_ID", "NAME", "KEY", "VALUE", "REMARK") VALUES ('2', 'BASE_DATA', '总面积', 'total_area', '0', '㎡');
INSERT INTO "ODS_KV_MODEL_DATA"("ID", "MODEL_ID", "NAME", "KEY", "VALUE", "REMARK") VALUES ('3', 'ENERGY_CONSUMPTION', '累计水能耗', 'total_water', '0', 'T');
INSERT INTO "ODS_KV_MODEL_DATA"("ID", "MODEL_ID", "NAME", "KEY", "VALUE", "REMARK") VALUES ('4', 'ENERGY_CONSUMPTION', '累计电能耗', 'total_electric', '0', 'KW·h');




INSERT INTO "ODS_LAYOUT_CONFIG"("ID", "USER_ID", "LAYOUT", "UPDATE_TIME", "CREATE_TIME", "COL_NUM", "STATUS", "REMARK", "STYLE", "NAME", "PROJECT", "ORDER") VALUES ('AD35B4EFA4AA696FE053960AA8C0435D', 'user_id', '[{"x":0,"y":0,"w":3,"h":8,"i":0,"id":"id0","col":1,"border":true,"comp":[{"name":"costCount","args":{"title":"能耗费用统计"},"top":true}],"moved":false},{"x":0,"y":17,"w":3,"h":8,"i":1,"id":"id2","col":1,"border":true,"comp":[{"name":"costCountLine","args":{"title":"用水统计分析","type":"ENERGY_WATER","time":2},"top":true}],"moved":false},{"x":0,"y":8,"w":3,"h":9,"i":2,"id":"id3","col":1,"border":true,"comp":[{"name":"costCountLine","args":{"title":"能耗费用分析","type":"ENERGY_COST","time":2},"top":true}],"moved":false},{"x":3,"y":0,"w":6,"h":17,"i":3,"id":"id4","col":1,"border":true,"comp":[{"name":"buildingMap","args":{"title":"建筑"},"top":false}],"moved":false},{"x":3,"y":17,"w":3,"h":8,"i":4,"id":"id5","col":1,"border":true,"comp":[{"name":"basicData","args":{"title":"基础数据"},"top":false}],"moved":false},{"x":6,"y":17,"w":3,"h":8,"i":5,"id":"id6","col":1,"border":true,"comp":[{"name":"basicData2","args":{"title":"累计能耗"},"top":false}],"moved":false},{"x":9,"y":0,"w":3,"h":9,"i":6,"id":"id7","col":1,"border":true,"comp":[{"name":"costCount2","args":{"title":"用电量"},"top":true}],"moved":false},{"x":9,"y":9,"w":3,"h":8,"i":7,"id":"id8","col":1,"border":true,"comp":[{"name":"costCount3","args":{"title":"用水量"},"top":true}],"moved":false},{"x":9,"y":17,"w":3,"h":8,"i":8,"id":"id9","col":1,"border":true,"comp":[{"name":"costCountLine","args":{"title":"用电统计分析","type":"ENERGY_ELECTRIC","time":2},"top":true}],"moved":false}]', TO_DATE('2020-08-27 14:49:17', 'SYYYY-MM-DD HH24:MI:SS'), TO_DATE('2020-08-19 13:59:29', 'SYYYY-MM-DD HH24:MI:SS'), '0', '1', '能源太空舱', '3', NULL, '国家税务总局北京市税务局能耗监管平台', '2');
