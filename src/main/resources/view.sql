-- ----------------------------
-- Table structure for V_ODS_DEVICE_CENTER_INFO 设备
-- ----------------------------
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


-- ----------------------------
-- Table structure for V_ODS_DEVICE_REPORT 设备上报
-- ----------------------------
CREATE OR REPLACE FORCE VIEW V_ODS_DEVICE_REPORT AS
SELECT 'DEVICE_REPORT' AS MODEL_ID, --模型ID
       T.SERIAL_NUM, --设备序列号
       T.ACCOUNT,
       T.CMD_NAME, --上报类型
       T.CREATED_TIME AS REPORT_TIME, --上报时间
       T1.DEVICE_NAME, --设备名称
       T1.DEVICE_MAC, --设备mac地址
       T2.SBLX_MLMC AS PRODUCT_CODE_NAME,
       T.PRODUCT_CODE, --设备类型
       T4.AREA_NAME AS POSITION, --设备位置
       CASE
         WHEN T3.DEVICE_STATE = '0' THEN
          '正常'
         WHEN T3.DEVICE_STATE = '1' THEN
          '故障'
         WHEN T3.DEVICE_STATE = '2' THEN
          '报警'
         WHEN T3.DEVICE_STATE = '3' THEN
          '异常'
       END AS STATE, --设备状态
       T3.DEVICE_NAME AS SPARE --设备名称
  FROM DEV_SBMSG T
  LEFT JOIN DEV_SBGL T1 ON T.SERIAL_NUM = T1.SERIAL_NUM
  LEFT JOIN DEV_SBLX T2 ON T.PRODUCT_CODE = T2.SBLX_MLDM
  LEFT JOIN HKSFIREDEV_DEVICE_INFO T3 ON T.SERIAL_NUM = T3.PARENT_DEVICE_ID
  LEFT JOIN ORG_AREA T4 ON T3.AREA_ID = T4.AREA_ID


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
  LEFT JOIN USER_USER_ORG T1
    ON T.USER_ID = T1.USER_ID
  LEFT JOIN USER_ORGANIZE T2
    ON T1.ORG_ID = T2.ORG_ID;
