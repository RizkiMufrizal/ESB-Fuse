## Install Dependency Osgi :

1. install -s mvn:org.postgresql/postgresql/42.2.2
2. feature:install jdbc pax-jdbc-pool-dbcp2 camel-sql

## Build Project

1. mvn clean install
2. osgi:install -s mvn:org.rizki.mufrizal.esb.fuse/catalog-gateway/0.0.1
3. osgi:install -s mvn:org.rizki.mufrizal.esb.fuse/catalog-repository/0.0.1