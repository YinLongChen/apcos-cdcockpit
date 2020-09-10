-- ----------------------------
-- Table structure for V_ODS_DEVICE_CENTER_INFO 设备
-- ----------------------------
CREATE OR REPLACE VIEW V_ODS_DEVICE_CENTER_INFO AS
SELECT '3' AS MODEL_ID, --模型ID
       T.SERIAL_NUM, --设备序列号
       T.DEVICE_MAC, --设备mac地址
       T.PRODUCT_CODE, --设备类型
       DECODE(T.WHETHER_REGISTER,
              '1',T.CREATE_DATE) AS REGIST_TIME, --注册时间
       T.DEVICE_NAME, --设备名称
       T2.USER_CELLPHONE_NO, --电话号码
       T2.USER_ADDRESS, --用户地址
       T3.TYPE_NAME AS PRODUCT_CODE_NAME --设备类型名字

  FROM PRO_PRODUCT T
  JOIN PRO_ACCOUNT T1 ON T.ACCOUNT = T1.BCCA_ACCOUNT  AND T.WHETHER_REGISTER = '1'
  JOIN USER_USER T2 ON T1.CREATE_ID = T2.USER_ID
  JOIN PRO_PRODUCT_TYPE T3 ON T.PRODUCT_CODE = T3.PRODUCT_CODE


-- ----------------------------
-- Table structure for V_ODS_DEVICE_REPORT 设备上报
-- ----------------------------
CREATE OR REPLACE FORCE VIEW V_ODS_DEVICE_REPORT AS
SELECT 'DEVICE_REPORT' AS MODEL_ID, --模型ID
       T.SERIAL_NUM, --设备序列号
       T.ACCOUNT,
       T.CMD_NAME, --上报类型
       T.CREATE_DATE AS REPORT_TIME, --上报时间
       T1.DEVICE_NAME, --设备名称
       T1.DEVICE_MAC, --设备mac地址
       T2.TYPE_NAME AS PRODUCT_CODE_NAME,
       T.PRODUCT_CODE, --设备类型
       'POSITION' AS POSITION, --设备位置
       'STATE' AS STATE, --设备状态
       'SPARE' AS SPARE --设备名称
  FROM PRO_REPORT T
  LEFT JOIN PRO_PRODUCT T1  ON T.SERIAL_NUM = T1.SERIAL_NUM
  LEFT JOIN PRO_PRODUCT_TYPE T2 ON T.PRODUCT_CODE = T2.PRODUCT_CODE


-- ----------------------------
-- Table structure for V_ODS_USER_CENTER_INFO 用户
-- ----------------------------
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
  LEFT JOIN USER_USER_ORG T1 ON T.USER_ID = T1.USER_ID
  LEFT JOIN USER_ORGANIZE T2  ON T1.ORG_ID = T2.ORG_ID


-- ----------------------------
-- Table structure for V_ODS_PRO_MODE 模式
-- ----------------------------
CREATE OR REPLACE VIEW V_ODS_PRO_MODE AS
SELECT ID,
       YMSID,
       MODE_NAME,
       ACCOUNT,
       ENABLE_MODE,
       AREA_CODE1,
       AREA_CODE2,
       AREA_CODE3,
       DETAIL_ADDRESS,
       MS_ORDER,
       CREATE_ID,
       CREATE_DATE,
       EDIT_ID,
       EDIT_DATE,
       MODE_README,
	   '/product_mode/msControl' as MS_CONTROL
  FROM PRO_MODE T

-- ----------------------------
-- Table structure for V_ODS_ELECTRIC_CONSUMPTION 用电表
-- ----------------------------
CREATE OR REPLACE VIEW V_ODS_ELECTRIC_CONSUMPTION AS
SELECT
	to_date( CONCAT( BUSI_MONTH, '01 00:00:01' ), 'yyyy-MM-dd HH24:mi:ss' ) AS TIME,
	ENERGY_USAGE_TYPE as NAME,
	ENERGY_USAGE as VALUE,
	ENERGY_UNIT as UNIT,
	ENERGY_AREA_ID as ORG
FROM
	ODS_ENERGY_USETYPE_DATA
WHERE
	ENERGY_TYPE = '电'

-- ----------------------------
-- Table structure for V_ODS_WATER_CONSUMPTION 用水表
-- ----------------------------
CREATE OR REPLACE VIEW V_ODS_WATER_CONSUMPTION AS
SELECT
	to_date( CONCAT( BUSI_MONTH, '01 00:00:01' ), 'yyyy-MM-dd HH24:mi:ss' ) AS TIME,
	ENERGY_USAGE_TYPE as NAME,
	ENERGY_USAGE as VALUE,
	ENERGY_UNIT as UNIT,
	ENERGY_AREA_ID as ORG
FROM
	ODS_ENERGY_USETYPE_DATA
WHERE
	ENERGY_TYPE = '水'

-- ----------------------------
-- Table structure for V_ODS_ENERGY_COST 能源费用表
-- ----------------------------
CREATE OR REPLACE VIEW V_ODS_ENERGY_COST AS
SELECT
	nvl( T.WATER_AMOUNT, 0 ) AS WATER_AMOUNT, nvl( T.ELECTRIC_AMOUNT, 0 ) ELECTRIC_AMOUNT,
	nvl( T.WATER_AMOUNT, 0 ) + nvl( T.ELECTRIC_AMOUNT, 0 ) AS total, nvl( T.ETIME, T.WTIME ) AS TIME,nvl( T.EORG, T.WORG ) AS ORG
FROM
	(
		SELECT w.WATER_AMOUNT, e.ELECTRIC_AMOUNT, w.wtime, e.etime,w.WORG,e.EORG
		FROM
				(SELECT ENERGY_AMOUNT AS ELECTRIC_AMOUNT, BUSI_MONTH, to_date( CONCAT( BUSI_MONTH, '01' ), 'yyyy-MM-dd HH24:mi:ss' ) AS etime,ENERGY_AREA_ID as EORG FROM ODS_ENERGY_PAY_DATA WHERE ENERGY_TYPE = '电' ) e
		FULL OUTER JOIN
				(SELECT ENERGY_AMOUNT AS WATER_AMOUNT, BUSI_MONTH, to_date( CONCAT( BUSI_MONTH, '01' ), 'yyyy-MM-dd HH24:mi:ss' ) AS wtime,ENERGY_AREA_ID as WORG FROM ODS_ENERGY_PAY_DATA WHERE ENERGY_TYPE = '水' ) w
		ON e.BUSI_MONTH = w.BUSI_MONTH AND e.EORG=w.WORG
) T

-- ----------------------------
-- Table structure for V_ODS_ENERGY_ELECTRIC 用电分析
-- ----------------------------
CREATE OR REPLACE VIEW V_ODS_ENERGY_ELECTRIC AS
SELECT
  ENERGY_USAGE AS TOTAL,
  HUNMAN_NUM,
  AREAS,
  to_date(BUSI_DATE, 'yyyy-MM-dd HH24:mi:ss' ) AS time,
	ENERGY_AREA_ID as ORG
FROM
  ODS_ENERGY_USAGE_DATA
WHERE
  ENERGY_TYPE = '电' ORDER BY time

-- ----------------------------
-- Table structure for V_ODS_ENERGY_WATER 用水分析
-- ----------------------------
CREATE OR REPLACE VIEW V_ODS_ENERGY_WATER AS
SELECT
	ENERGY_USAGE AS TOTAL,
	HUNMAN_NUM,
	AREAS,
	to_date(BUSI_DATE, 'yyyy-MM-dd HH24:mi:ss' ) AS time,
	ENERGY_AREA_ID as ORG
FROM
	ODS_ENERGY_USAGE_DATA
WHERE
	ENERGY_TYPE = '水' ORDER BY time


