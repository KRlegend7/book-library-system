# PowerShell Project verification script for Windows

Write-Host "=== Book Library Management System - Project Verification ===" -ForegroundColor Cyan
Write-Host ""

# Check if we're in the right directory
if (-not (Test-Path "docker-compose.yml")) {
    Write-Host "❌ Please run this script from the project root directory" -ForegroundColor Red
    exit 1
}

Write-Host "📁 Checking project structure..." -ForegroundColor Yellow

# Backend files
Write-Host "Backend files:" -ForegroundColor Green
if (Test-Path "backend/pom.xml") { Write-Host "✅ Maven configuration (pom.xml)" -ForegroundColor Green } else { Write-Host "❌ Maven configuration missing" -ForegroundColor Red }
if (Test-Path "backend/Dockerfile") { Write-Host "✅ Backend Dockerfile" -ForegroundColor Green } else { Write-Host "❌ Backend Dockerfile missing" -ForegroundColor Red }
if (Test-Path "backend/src/main/java/com/library/entity/Book.java") { Write-Host "✅ Book entity" -ForegroundColor Green } else { Write-Host "❌ Book entity missing" -ForegroundColor Red }
if (Test-Path "backend/src/main/java/com/library/service/BookService.java") { Write-Host "✅ Book service (EJB)" -ForegroundColor Green } else { Write-Host "❌ Book service missing" -ForegroundColor Red }
if (Test-Path "backend/src/main/java/com/library/rest/BookController.java") { Write-Host "✅ REST controller" -ForegroundColor Green } else { Write-Host "❌ REST controller missing" -ForegroundColor Red }
if (Test-Path "backend/src/main/resources/META-INF/persistence.xml") { Write-Host "✅ JPA configuration" -ForegroundColor Green } else { Write-Host "❌ JPA configuration missing" -ForegroundColor Red }
if (Test-Path "backend/src/main/resources/db/init.sql") { Write-Host "✅ Database initialization script" -ForegroundColor Green } else { Write-Host "❌ Database script missing" -ForegroundColor Red }

Write-Host ""

# Frontend files  
Write-Host "Frontend files:" -ForegroundColor Green
if (Test-Path "frontend/package.json") { Write-Host "✅ Frontend package.json" -ForegroundColor Green } else { Write-Host "❌ Frontend package.json missing" -ForegroundColor Red }
if (Test-Path "frontend/Dockerfile") { Write-Host "✅ Frontend Dockerfile" -ForegroundColor Green } else { Write-Host "❌ Frontend Dockerfile missing" -ForegroundColor Red }
if (Test-Path "frontend/src/App.tsx") { Write-Host "✅ Main App component" -ForegroundColor Green } else { Write-Host "❌ Main App component missing" -ForegroundColor Red }
if (Test-Path "frontend/src/services/BookService.ts") { Write-Host "✅ API service" -ForegroundColor Green } else { Write-Host "❌ API service missing" -ForegroundColor Red }
if (Test-Path "frontend/src/types/Book.ts") { Write-Host "✅ TypeScript types" -ForegroundColor Green } else { Write-Host "❌ TypeScript types missing" -ForegroundColor Red }
if (Test-Path "frontend/src/components") { Write-Host "✅ React components directory" -ForegroundColor Green } else { Write-Host "❌ React components missing" -ForegroundColor Red }

Write-Host ""

# Docker files
Write-Host "Docker configuration:" -ForegroundColor Green
if (Test-Path "docker-compose.yml") { Write-Host "✅ Docker Compose configuration" -ForegroundColor Green } else { Write-Host "❌ Docker Compose missing" -ForegroundColor Red }

Write-Host ""

# Count files
Write-Host "📊 Project statistics:" -ForegroundColor Yellow
$javaFiles = (Get-ChildItem -Path "backend/src" -Recurse -Filter "*.java" -ErrorAction SilentlyContinue).Count
$tsFiles = (Get-ChildItem -Path "frontend/src" -Recurse -Include "*.tsx", "*.ts" -ErrorAction SilentlyContinue).Count
Write-Host "📝 Java files: $javaFiles" -ForegroundColor Cyan
Write-Host "📝 TypeScript/React files: $tsFiles" -ForegroundColor Cyan

Write-Host ""
Write-Host "🎉 Project verification complete!" -ForegroundColor Green
Write-Host ""
Write-Host "🚀 To start the application:" -ForegroundColor Yellow
Write-Host "   docker-compose up --build" -ForegroundColor White
Write-Host ""
Write-Host "🌐 Access points:" -ForegroundColor Yellow
Write-Host "   Frontend: http://localhost:3000" -ForegroundColor White
Write-Host "   Backend API: http://localhost:8080/book-library-backend/api/books" -ForegroundColor White
Write-Host "   Database Admin: http://localhost:5050" -ForegroundColor White
