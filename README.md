# Activity
Introduction
-------------
This project is a POC on how to implement Kafka for tracking dependent activity between application.

Kafka Setup
-------------
1. Download zip file from  https://www.apache.org/dyn/closer.cgi?path=/kafka/2.1.0/kafka_2.11-2.1.0.tgz
2. Extract zip & change directory location.<br />	
   i) tar -xzf kafka_2.11-2.1.0.tgz <br />	
  ii) cd kafka_2.11-2.1.0
3. Start zookeeper by cmd bin/zookeeper-server-start.sh config/zookeeper.properties.<br />
4. Start kafka server using cmd bin/kafka-server-start.sh config/server.properties.<br />
5. Create topic using cmd bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic <topic name> <br />
6. Send message to kafka using cmd bin/kafka-console-producer.sh --broker-list localhost:9092 --topic <topic name><br />
7. Consume message from Kafka using cmd bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic <topic name> --from-beginning. <br />
8. Above mentioned steps refer to installing and testing kafka using CLI.<br />
9. For windows users refer kafka documentation.<br />
 
 DB
 ---
 I have used mongodb for persistence.

For testing activity application you need to send message as a JSON format shown below:
------
{
            "eventList": [
            {
                "noun": "server-config"
                "verb": "update",
                "eventId": "serverconfig",
                "timestamp": 1438791098,
                "data": {
            "id": 1,
            "user": {
                "id": 2,
                "name": "Admin"
            },
            "comment": "this is an event"
         }
    },
    {
                "noun": "server-config",
                "verb": "update",
                "eventId": "serverconfig",
                "timestamp": 1438791098,
                "data": {
            "id": 1,
            "user": {
                "id": 2,
                "name": "Admin"
            },
            "comment": "this is an event"
         }
    }
                        ]
}
