FROM adoptopenjdk/openjdk11:alpine-jre
LABEL maintainer="Rajesh Iyer <iyerajesh@gmail.com>"
EXPOSE 8080
RUN set -ex \
  && apk update \
  && apk upgrade
RUN mkdir /payments
COPY /build/libs/*.jar /payments/amortization-service.jar
WORKDIR /payments
CMD ["java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "amortization-service.jar"]
