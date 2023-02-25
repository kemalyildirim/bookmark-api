#!/bin/zsh

declare dc_infra=docker-compose.yml
declare dc_app=docker-compose-app.yml

build_app() {
    echo "Building the app"
    cd bookmarker-api && ./gradlew clean build && cd ..
}
start_infra() {
    echo "Start infra"
    docker compose -f $dc_infra up -d
}

stop_infra() {
    echo "Stop infra"
    docker compose -f $dc_infra stop
}

start_app() {
    echo "Start app"
    docker compose -f $dc_app up -d
}

stop_app() {
    echo "Stop app"
    docker compose -f $dc_app stop
}

start() {
    build_app
    echo "Starting"
    docker compose -f $dc_app -f $dc_infra up -d
}
stop() {
    echo "Stopping"
    docker compose -f $dc_infra -f $dc_app stop
}

main() {
  case $1 in
    "--start-all")
    start ;;
    "--stop-all")
    stop ;;
    "--start-infra")
    start_infra ;;
    "--stop-infra")
    stop_infra ;;
    "--start-app")
    start_app ;;
    "--stop-app")
    stop_app ;;
    "--help")
    printf "bookmarker-app helper CLI tool\n\n"
    printf "--start\n\t start app with all components\n"
    printf "--stop\n\t stop the app\n"
    printf "--start-app\n\t start the Java application only\n"
    printf "--stop-app\n\t stop the Java application\n"
    printf "--start-infra\n\t start Infra services like database\n"
    printf "--stop-infra\n\t stop infra services\n"
    printf "--build-app\n\t build the Java application\n"
    ;;
    "--build-app")
    build_app ;;
  esac
}

main "$1"
