#!/bin/bash

date1="`date -d "$1days ago" +%Y%m%d`"

echo ${date1}
echo "------查缴办数据拉取开始,开始时间："`date +'%Y-%m-%d %H:%M:%S'`"------"

#hadoop distcp hdfs://132.46.115.89/user/sinova/hive/warehouse/t_ods_3hall_intf/dt=$date1 hdfs://10.169.9.112/user/hive/warehouse/unicom_2i_tmp_schema.db/t_ods_3hall_intf/

#hive -e "set hive.msck.path.validation=ignore;msck repair table raw_layer.t_ods_3hall_intf";

if  [[ $? -eq 0  ]];then
    echo "------SUCCESS 查缴办数据拉取结束,结束时间："`date +'%Y-%m-%d %H:%M:%S'`"------"
else
    echo "------ERROR 查缴办数据拉取失败,时间："`date +'%Y-%m-%d %H:%M:%S'`"------"
fi