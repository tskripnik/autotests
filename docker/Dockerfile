# Use official Gradle image with JDK 17 for stability and performance
FROM gradle:8.7-jdk17-alpine

LABEL maintainer="SRE Team <devops@example.com>"
LABEL description="Docker image for building and testing the SRE project with Gradle"

# Set working directory
WORKDIR /home/gradle/project

# Copy Gradle wrapper and build files first for caching
COPY gradle gradle
COPY gradlew .
COPY settings.gradle .
COPY build.gradle .

# Pre-fetch dependencies to leverage Docker layer caching
RUN gradle dependencies --no-daemon

# Copy the rest of the source code
COPY . .

# Set environment variables for consistent Gradle behavior
ENV GRADLE_OPTS="-Dorg.gradle.daemon=false -Dorg.gradle.jvmargs=-Xmx1024m"
ENV TZ=UTC

# Default entrypoint and command
ENTRYPOINT ["gradle"]
CMD ["clean", "build"]
