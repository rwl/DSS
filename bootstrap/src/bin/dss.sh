#!/bin/sh

DIR="$( cd "$( dirname "$0" )" && pwd )"
#echo Resolved DSS_HOME: $DIR

java -Droo.console.ansi=true -jar "$DIR"/bootstrap.jar
