Change to postgresql

activiti-app(activiti.properties)

datasource.driver=org.postgresql.Driver

datasource.url=jdbc:postgresql://127.0.0.1:5432/activiti6ui

datasource.username=postgres

datasource.password=postgres

hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

activiti-rest(db.properties)

db=postgresql

jdbc.driver=org.postgresql.Driver

jdbc.url=jdbc:postgresql://127.0.0.1:5432/activiti6ui

jdbc.username=postgres

jdbc.password=postgres