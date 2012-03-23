@echo off
setlocal enabledelayedexpansion

for %%? in ("%~dp0..") do set DSS_HOME=%%~f?
rem echo Resolved DSS_HOME: "%DSS_HOME%"

java -Dflash.message.disabled=false -Djline.nobell=true -Droo.console.ansi=true -jar "%DSS_HOME%"\bootstrap.jar

:end