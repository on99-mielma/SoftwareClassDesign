package com.tothecloud.offices.services;


import com.alibaba.fastjson2.JSONObject;
import com.tothecloud.offices.domain.Office;
import com.tothecloud.offices.domain.canal.CanalBean;
import com.tothecloud.offices.domain.canal.TbCommodityInfo;
import com.tothecloud.offices.utils.ConstantUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class KafkaService {
    String keylite = ConstantUtils.PROJECT_PREFIX + ConstantUtils.OFFLITE_PREFIX;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @KafkaListener(id = "OfficesConsumer",groupId = ConstantUtils.OFFLITE_PREFIX,topicPartitions = {
            @TopicPartition(topic = "canalhospital",partitions = {"0"})
    })
    public void dealKafkaFromCanal(ConsumerRecord<?,?> consumer){
        String value = (String) consumer.value();
        log.info("topic名称:{},key:{},分区位置:{},下标:{},value:{}", consumer.topic(), consumer.key(),
                consumer.partition(), consumer.offset(), value);
        /**
         * 2023-07-19T18:01:47.435+08:00  INFO 25732 --- [sConsumer-0-C-1] c.t.offices.services.KafkaService        :
         * topic名称:canalhospital,
         * key:null,
         * 分区位置:0,
         * 下标:6,
         * value:{
         * "data":[{"id":"7017225572870918145","officeName":"什么科","officeInfo":"bzddddddd"}],
         * "database":"tothecloud",
         * "es":1689760907000,
         * "gtid":"",
         * "id":6,
         * "isDdl":false,
         * "mysqlType":{"id":"bigint","officeName":"varchar(100)","officeInfo":"varchar(4000)"},
         * "old":[{"officeInfo":"bzd"}],
         * "pkNames":["id"],
         * "sql":"",
         * "sqlType":{"id":-5,"officeName":12,"officeInfo":12},
         * "table":"offices",
         * "ts":1689760907072,
         * "type":"UPDATE"
         * }
         */
        CanalBean canalBean = JSONObject.parseObject(value, CanalBean.class);
        log.info(canalBean.getData().toString());
        boolean isDdl = canalBean.isDdl();
        if (canalBean.getDatabase().equals(ConstantUtils.MYSQL_DATABASE_NAME)&&canalBean.getTable().equals(ConstantUtils.MYSQL_TABLE_NAME)&&!isDdl){
            List<Office> officeList = canalBean.getData();
            if (officeList.size() <= 0){
                return;
            }
            Office t = officeList.get(0);
            String type = canalBean.getType();
            if ("INSERT".equals(type)){
                redisTemplate.opsForSet().add(keylite,t.getOfficeName());
            }else if ("UPDATE".equals(type)){
                List<Office> oldlist = canalBean.getOld();
                if (oldlist.size()<=0){
                    return;
                }
                Office old = oldlist.get(0);
                if (null == old.getOfficeName()){
                    return;
                }
                redisTemplate.opsForSet().remove(keylite,old.getOfficeName());
                redisTemplate.opsForSet().add(keylite,t.getOfficeName());
            } else if ("DELETE".equals(type)) {
                redisTemplate.opsForSet().remove(keylite,t.getOfficeName());
            }
        }
    }

}
