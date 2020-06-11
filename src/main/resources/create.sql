CREATE OR REPLACE VIEW V_ODS_DEVICE_CENTER_INFO AS
SELECT T.SERIAL_NUM, --设备序列号
       T.DEVICE_MAC, --设备mac地址
       T.PRODUCT_CODE, --设备类型
       DECODE(T.WHETHER_REGISTER,
              '1',
              TO_DATE(REPLACE(T.CREATE_TIME, 'T', ' '),
                      'yyyy-mm-dd hh24:mi:ss')) AS REGIST_TIME, --注册时间
       T.DEVICE_NAME, --设备名称
       T2.USER_CELLPHONE_NO, --电话号码
       T2.USER_ADDRESS, --用户地址
       T3.SBLX_MLMC AS PRODUCT_CODE_NAME--设备类型名字
  FROM DEV_SBGL T
  JOIN DEV_BCCA T1
    ON T.ACCOUNT = T1.BCCA_ACCOUNT
   AND T.WHETHER_REGISTER = '1'
  JOIN USER_USER T2
    ON T1.CREATOR_ID = T2.USER_ID
  JOIN DEV_SBLX T3
    ON T.PRODUCT_CODE = T3.SBLX_MLDM;



CREATE OR REPLACE FORCE VIEW V_ODS_DEVICE_REPORT AS
SELECT '' AS MODEL_ID,--模型ID
       T.SERIAL_NUM, --设备序列号
       T1.DEVICE_MAC, --设备mac地址
       T.PRODUCT_CODE, --设备类型
       T.CREATED_TIME AS REPORT_TIME, --上报时间
       T1.DEVICE_NAME, --设备名称
       T.CMD_NAME --上报类型
  FROM DEV_SBMSG T
  JOIN DEV_SBGL T1
    ON T.SERIAL_NUM = T1.SERIAL_NUM;

CREATE OR REPLACE FORCE VIEW V_ODS_USER_CENTER_INFO AS
SELECT T.USER_ID, --用户id
       T.USER_NAME, --用户姓名
       T.USER_CARD_ID AS ID_CARD, --身份证
       CEIL(MONTHS_BETWEEN(SYSDATE,
                           TO_DATE(SUBSTR(T.USER_CARD_ID, 7, 8), 'yyyymmdd')) / 12) AS AGE, --年龄
       DECODE(T.USER_GENDER, 1, '0', 2, '1') AS GENDER, --性别
       T2.ORG_NAME AS DEPT, --组织
       T.USER_CELLPHONE_NO AS USER_CELLPHONE_NO, --电话号码
       T.USER_CREATED_TIME AS REGIST_TIME, --注册时间
       T.USER_ADDRESS--地址
  FROM USER_USER T
  LEFT JOIN USER_USER_ORG T1
    ON T.USER_ID = T1.USER_ID
  LEFT JOIN USER_ORGANIZE T2
    ON T1.ORG_ID = T2.ORG_ID;



create table ODS_COMPARE_MODEL_DATA
(
  id       VARCHAR2(36) not null,
  model_id VARCHAR2(36) not null,
  month    VARCHAR2(11),
  value    NUMBER(11),
  unit     VARCHAR2(50)
)
;
comment on column ODS_COMPARE_MODEL_DATA.id
  is 'id';
comment on column ODS_COMPARE_MODEL_DATA.model_id
  is '模型id';
comment on column ODS_COMPARE_MODEL_DATA.month
  is '月份（格式如：2019-12）';
comment on column ODS_COMPARE_MODEL_DATA.value
  is '数量';
comment on column ODS_COMPARE_MODEL_DATA.unit
  is '单位（数据单位，如：KW·h）';
alter table ODS_COMPARE_MODEL_DATA
  add primary key (ID);


create table ODS_LAYOUT_CONFIG
(
  id          VARCHAR2(36) not null,
  user_id     VARCHAR2(36) not null,
  layout      VARCHAR2(2000) not null,
  update_time DATE not null,
  create_time DATE not null,
  col_num     VARCHAR2(200) not null,
  status      NUMBER(11) not null,
  remark      VARCHAR2(255),
  style       VARCHAR2(2000)
)
;
comment on column ODS_LAYOUT_CONFIG.id
  is '主键id';
comment on column ODS_LAYOUT_CONFIG.user_id
  is '用户id';
comment on column ODS_LAYOUT_CONFIG.layout
  is '布局配置信息';
comment on column ODS_LAYOUT_CONFIG.update_time
  is '更新时间';
comment on column ODS_LAYOUT_CONFIG.create_time
  is '创建时间';
comment on column ODS_LAYOUT_CONFIG.col_num
  is '布局列数';
comment on column ODS_LAYOUT_CONFIG.status
  is '状态（0 草稿，1 发布）';
comment on column ODS_LAYOUT_CONFIG.remark
  is '备注';
comment on column ODS_LAYOUT_CONFIG.style
  is '布局风格';
alter table ODS_LAYOUT_CONFIG
  add primary key (ID);


create table ODS_LINK_MODEL_DATA
(
  id       VARCHAR2(36) not null,
  model_id VARCHAR2(36),
  name     VARCHAR2(255) not null,
  link_url VARCHAR2(255) not null
)
;
comment on column ODS_LINK_MODEL_DATA.id
  is '主键id';
comment on column ODS_LINK_MODEL_DATA.model_id
  is '模型id';
comment on column ODS_LINK_MODEL_DATA.name
  is '名称';
comment on column ODS_LINK_MODEL_DATA.link_url
  is '链接URL';
alter table ODS_LINK_MODEL_DATA
  add primary key (ID);


create table ODS_LIST_MODEL_DATA
(
  model_id    VARCHAR2(32) not null,
  column_1    VARCHAR2(64),
  column_2    VARCHAR2(64),
  column_3    VARCHAR2(64),
  column_4    VARCHAR2(64),
  column_5    VARCHAR2(64),
  column_6    VARCHAR2(64),
  column_7    VARCHAR2(64),
  column_8    VARCHAR2(64),
  column_9    VARCHAR2(64),
  column_10   VARCHAR2(64),
  column_11   NUMBER,
  column_12   NUMBER,
  column_13   NUMBER,
  column_14   NUMBER,
  column_15   DATE,
  column_16   DATE,
  insert_time DATE not null
)
;
comment on table ODS_LIST_MODEL_DATA
  is '报表模型数据表';
comment on column ODS_LIST_MODEL_DATA.model_id
  is '报表模型id';
comment on column ODS_LIST_MODEL_DATA.column_1
  is '字符字段1';
comment on column ODS_LIST_MODEL_DATA.column_2
  is '字符字段2';
comment on column ODS_LIST_MODEL_DATA.column_3
  is '字符字段3';
comment on column ODS_LIST_MODEL_DATA.column_4
  is '字符字段4';
comment on column ODS_LIST_MODEL_DATA.column_5
  is '字符字段5';
comment on column ODS_LIST_MODEL_DATA.column_6
  is '字符字段6';
comment on column ODS_LIST_MODEL_DATA.column_7
  is '字符字段7';
comment on column ODS_LIST_MODEL_DATA.column_8
  is '字符字段8';
comment on column ODS_LIST_MODEL_DATA.column_9
  is '字符字段9';
comment on column ODS_LIST_MODEL_DATA.column_10
  is '字符字段10';
comment on column ODS_LIST_MODEL_DATA.column_11
  is '数值字段11';
comment on column ODS_LIST_MODEL_DATA.column_12
  is '数值字段12';
comment on column ODS_LIST_MODEL_DATA.column_13
  is '数值字段13';
comment on column ODS_LIST_MODEL_DATA.column_14
  is '数值字段14';
comment on column ODS_LIST_MODEL_DATA.column_15
  is '日期字段15';
comment on column ODS_LIST_MODEL_DATA.column_16
  is '日期字段16';
comment on column ODS_LIST_MODEL_DATA.insert_time
  is '数据插入时间';


create table ODS_LIST_MODEL_DATA_MAP
(
  id         VARCHAR2(36) not null,
  model_id   VARCHAR2(255) not null,
  column_ori VARCHAR2(255) not null,
  column_en  VARCHAR2(255) not null,
  column_cn  VARCHAR2(255)
)
;
comment on column ODS_LIST_MODEL_DATA_MAP.id
  is '主键id';
comment on column ODS_LIST_MODEL_DATA_MAP.model_id
  is '模型id';
comment on column ODS_LIST_MODEL_DATA_MAP.column_ori
  is '原始列名';
comment on column ODS_LIST_MODEL_DATA_MAP.column_en
  is '英文名';
comment on column ODS_LIST_MODEL_DATA_MAP.column_cn
  is '中文名';
alter table ODS_LIST_MODEL_DATA_MAP
  add primary key (ID);


create table ODS_MODEL_CONFIG
(
  id         VARCHAR2(36) not null,
  model_name VARCHAR2(255) not null,
  type_id    VARCHAR2(36) not null
)
;
comment on column ODS_MODEL_CONFIG.id
  is '主键id';
comment on column ODS_MODEL_CONFIG.model_name
  is '模型名称';
comment on column ODS_MODEL_CONFIG.type_id
  is '模型类型id';
alter table ODS_MODEL_CONFIG
  add primary key (ID);


create table ODS_MODEL_TYPE_CONFIG
(
  id        VARCHAR2(36) not null,
  type_name VARCHAR2(200) not null
)
;
comment on column ODS_MODEL_TYPE_CONFIG.id
  is '主键id';
comment on column ODS_MODEL_TYPE_CONFIG.type_name
  is '类型名称';
alter table ODS_MODEL_TYPE_CONFIG
  add primary key (ID);


create table ODS_MONITOR_MODEL_DATA
(
  id          VARCHAR2(36) not null,
  model_id    VARCHAR2(36),
  device_name VARCHAR2(200) not null,
  position    VARCHAR2(200),
  status      VARCHAR2(200),
  rtmp_url    VARCHAR2(200) not null,
  device_id   VARCHAR2(36) not null
)
;
comment on column ODS_MONITOR_MODEL_DATA.id
  is 'id';
comment on column ODS_MONITOR_MODEL_DATA.model_id
  is '模型id';
comment on column ODS_MONITOR_MODEL_DATA.device_name
  is '设备名称';
comment on column ODS_MONITOR_MODEL_DATA.position
  is '位置';
comment on column ODS_MONITOR_MODEL_DATA.status
  is '1在线 0离线';
comment on column ODS_MONITOR_MODEL_DATA.rtmp_url
  is '转换流地址';
comment on column ODS_MONITOR_MODEL_DATA.device_id
  is '设备id';


create table ODS_RANKING_MODEL_DATA
(
  id       VARCHAR2(36) not null,
  name     VARCHAR2(200) not null,
  value    NUMBER(11) not null,
  model_id VARCHAR2(36) not null
)
;
comment on column ODS_RANKING_MODEL_DATA.id
  is '主键id';
comment on column ODS_RANKING_MODEL_DATA.name
  is '名称';
comment on column ODS_RANKING_MODEL_DATA.value
  is '数量';
comment on column ODS_RANKING_MODEL_DATA.model_id
  is '模型id';
alter table ODS_RANKING_MODEL_DATA
  add primary key (ID);
