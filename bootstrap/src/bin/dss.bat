@echo off
setlocal enabledelayedexpansion

set DSS_HOME=%~dp0
rem echo Resolved DSS_HOME: "%DSS_HOME%"

java -Dflash.message.disabled=false -Djline.nobell=true -Droo.console.ansi=true -jar "%DSS_HOME%\bootstrap.jar"

:end
