@echo off
REM Development start script for Windows

echo 🚀 Starting Book Library Management System...

REM Check if Docker is running
docker info >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Docker is not running. Please start Docker first.
    pause
    exit /b 1
)

REM Check if docker-compose is available
docker-compose --version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ docker-compose is not installed. Please install Docker Compose.
    pause
    exit /b 1
)

echo 📦 Starting services with Docker Compose...
docker-compose up -d

echo ⏳ Waiting for services to start...
timeout /t 10 /nobreak >nul

echo 🔍 Checking service health...

REM Check database
docker-compose ps postgres | findstr "Up" >nul
if %errorlevel% equ 0 (
    echo ✅ Database is running
) else (
    echo ❌ Database failed to start
)

REM Check backend
docker-compose ps backend | findstr "Up" >nul
if %errorlevel% equ 0 (
    echo ✅ Backend is running
) else (
    echo ❌ Backend failed to start
)

REM Check frontend
docker-compose ps frontend | findstr "Up" >nul
if %errorlevel% equ 0 (
    echo ✅ Frontend is running
) else (
    echo ❌ Frontend failed to start
)

echo.
echo 🎉 Application is starting up!
echo.
echo 📱 Frontend: http://localhost:3000
echo 🔧 Backend API: http://localhost:8080/book-library-backend/api/books
echo 🗄️  pgAdmin: http://localhost:5050 (admin@booklibrary.com / admin123)
echo.
echo 📊 View logs: docker-compose logs -f
echo 🛑 Stop services: docker-compose down
echo.
pause
