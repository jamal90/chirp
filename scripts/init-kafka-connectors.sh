# create kafka connectors

# source connector for all tables
curl --location 'http://localhost:8083/connectors' \
--header 'Content-Type: application/json' \
--data '{
    "name": "tweets-source",
    "config": {
        "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
        "database.hostname": "host.docker.internal",
        "database.port": "5432",
        "database.user": "sa",
        "database.password": "password",
        "database.dbname": "tweets",
        "table.whitelist": "tweets",
        "topic.prefix": "chirp",
        "plugin.name": "pgoutput"
    }
}'

# sink connector for following table
curl --location 'http://localhost:8083/connectors' \
--header 'Content-Type: application/json' \
--data '{
      "name": "following_sink",
      "config": {
          "connector.class": "io.confluent.connect.jdbc.JdbcSinkConnector",
          "value.converter.schema.registry.url": "http://localhost:8081",
          "key.converter.schema.registry.url": "http://localhost:8081",
          "tasks.max": "1",
          "topics": "chirp.public.following",
          "auto.create": "true",
          "connection.url": "jdbc:postgresql://host.docker.internal:5433/feeds?user=sa&password=password",
          "value.converter": "io.confluent.connect.avro.AvroConverter",
          "key.converter": "io.confluent.connect.avro.AvroConverter",
          "table.name.format": "public.following",
          "transforms": "ExtractField,ConvertTimestamp",
          "transforms.ExtractField.type": "org.apache.kafka.connect.transforms.ExtractField$Value",
          "transforms.ExtractField.field": "after",
          "transforms.ConvertTimestamp.type": "org.apache.kafka.connect.transforms.TimestampConverter$Value",
          "transforms.ConvertTimestamp.field": "created_at",
          "transforms.ConvertTimestamp.unix.precision": "microseconds",
          "transforms.ConvertTimestamp.format": "yyyy-MM-dd hh:mm:ss",
          "transforms.ConvertTimestamp.target.type": "Timestamp"
      }
}'

# users info sink
curl --location 'http://localhost:8083/connectors' \
--header 'Content-Type: application/json' \
--data '{
    "name": "users_sink",
    "config": {
        "connector.class": "io.confluent.connect.jdbc.JdbcSinkConnector",
        "value.converter.schema.registry.url": "http://localhost:8081",
        "key.converter.schema.registry.url": "http://localhost:8081",
        "tasks.max": "1",
        "topics": "chirp.public.users",
        "auto.create": "true",
        "connection.url": "jdbc:postgresql://host.docker.internal:5433/feeds?user=sa&password=password",
        "value.converter": "io.confluent.connect.avro.AvroConverter",
        "key.converter": "io.confluent.connect.avro.AvroConverter",
        "table.name.format": "public.users",
        "transforms": "ExtractField,ConvertTimestamp",
        "transforms.ExtractField.type": "org.apache.kafka.connect.transforms.ExtractField$Value",
        "transforms.ExtractField.field": "after",
        "transforms.ConvertTimestamp.type": "org.apache.kafka.connect.transforms.TimestampConverter$Value",
        "transforms.ConvertTimestamp.field": "created_at",
        "transforms.ConvertTimestamp.unix.precision": "microseconds",
        "transforms.ConvertTimestamp.format": "yyyy-MM-dd hh:mm:ss",
        "transforms.ConvertTimestamp.target.type": "Timestamp"
    }
}'


# tweets sink
curl --location 'http://localhost:8083/connectors' \
--header 'Content-Type: application/json' \
--data '{
    "name": "tweets_sink",
    "config": {
        "connector.class": "io.confluent.connect.jdbc.JdbcSinkConnector",
        "value.converter.schema.registry.url": "http://localhost:8081",
        "key.converter.schema.registry.url": "http://localhost:8081",
        "tasks.max": "1",
        "topics": "chirp.public.tweet",
        "auto.create": "true",
        "connection.url": "jdbc:postgresql://host.docker.internal:5433/feeds?user=sa&password=password",
        "value.converter": "io.confluent.connect.avro.AvroConverter",
        "key.converter": "io.confluent.connect.avro.AvroConverter",
        "table.name.format": "public.tweet",
        "transforms": "ExtractField,ConvertTimestamp",
        "transforms.ExtractField.type": "org.apache.kafka.connect.transforms.ExtractField$Value",
        "transforms.ExtractField.field": "after",
        "transforms.ConvertTimestamp.type": "org.apache.kafka.connect.transforms.TimestampConverter$Value",
        "transforms.ConvertTimestamp.field": "created_at",
        "transforms.ConvertTimestamp.unix.precision": "microseconds",
        "transforms.ConvertTimestamp.format": "yyyy-MM-dd hh:mm:ss",
        "transforms.ConvertTimestamp.target.type": "Timestamp"
    }
}'