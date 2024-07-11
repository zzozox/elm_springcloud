FROM eclipse-temurin:21-jre-alpine
LABEL authors="DYQC"

# 设置时区（可选）
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 设置语言环境
ENV LANG=zh_CN.UTF-8
ENV LANGUAGE=zh_CN:zh
ENV LC_ALL=zh_CN.UTF-8

ADD build/libs/cart_consumer_13010-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 80

ENTRYPOINT ["java", "-jar", "app.jar"]