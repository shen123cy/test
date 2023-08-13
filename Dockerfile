FROM java:8
COPY *.jar /app.jar
CMD ["--sever.port=9090"]
EXPOSE 9090
ENTRYPOINT ["java","-jar","/app.jar"]