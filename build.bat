@echo off
rem Clean previous build
if exist bin rmdir /S /Q bin
mkdir bin

rem Compile each Java source file recursively
for /R src %%f in (*.java) do (
    javac -d bin -sourcepath src "%%f"
)

if %errorlevel% neq 0 (
    echo Build failed
    exit /b %errorlevel%
)

echo Build succeeded

rem Create bin directory if it doesn't exist
if not exist bin mkdir bin

rem Compile all Java source files under src\
rem -d bin : output classes to bin
rem -sourcepath src : source root
rem Use ** to include subfolders (requires Java 11+)

for /R src %%f in (*.java) do (
    javac -d bin -sourcepath src "%%f"
)

if %errorlevel% neq 0 (
    echo Build failed
    exit /b %errorlevel%
)

echo Build succeeded
