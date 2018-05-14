# Activity

Using Kafka as a messaging pattern we can track inter-related events between application.
Create a topic in Kafka as events (*or name as per your convience).
Send events in below format.
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
