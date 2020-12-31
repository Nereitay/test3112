FROM java:8

WORKDIR /home/test/

COPY test.jar .

EXPOSE 8031
ENTRYPOINT ["java", "-jar"]

CMD ["test.jar"]
