@echo off
rem Run the Algorithm Explorer application
rem Ensure the bin folder exists
if not exist bin (
    echo "bin directory not found. Please run build.bat first."
    exit /b 1
)

javaw -cp bin algexplorer.Main

if %errorlevel% neq 0 (
    echo Application exited with error code %errorlevel%
) else (
    echo Application closed.
)
