FROM openjdk:8-jdk-alpine
RUN addgroup -S payment && adduser -S payment -G payment
USER payment:payment
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.example.payment.PaymentApplication"]