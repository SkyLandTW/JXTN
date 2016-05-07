mkdir "%JRE_HOME%\lib\endorsed"
cd "%JDK_HOME%\jre\lib" && mklink /D endorsed ..\..\..\jre8\lib\endorsed
