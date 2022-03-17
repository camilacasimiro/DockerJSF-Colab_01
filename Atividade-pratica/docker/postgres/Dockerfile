FROM postgres
ENV POSTGRES_DB pratica01
ENV POSTGRES_USER jheycf
ENV POSTGRES_PASSWORD 333

COPY create.sql /docker-entrypoint-initdb.d/
COPY insert.sql /docker-entrypoint-initdb.d/