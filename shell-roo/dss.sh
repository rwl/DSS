#!/bin/sh

export MAVEN_OPTS=-Xmx256m

mvn exec:java -Dexec.mainClass="com.ncond.dss.shell.roo.DSSShell" -Droo.console.ansi=true
