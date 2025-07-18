#!/bin/bash
# Project verification script

echo "=== Book Library Management System - Project Verification ==="
echo ""

# Check if we're in the right directory
if [ ! -f "docker-compose.yml" ]; then
    echo "❌ Please run this script from the project root directory"
    exit 1
fi

echo "📁 Checking project structure..."

# Backend files
echo "Backend files:"
[ -f "backend/pom.xml" ] && echo "✅ Maven configuration (pom.xml)" || echo "❌ Maven configuration missing"
[ -f "backend/Dockerfile" ] && echo "✅ Backend Dockerfile" || echo "❌ Backend Dockerfile missing"
[ -f "backend/src/main/java/com/library/entity/Book.java" ] && echo "✅ Book entity" || echo "❌ Book entity missing"
[ -f "backend/src/main/java/com/library/service/BookService.java" ] && echo "✅ Book service (EJB)" || echo "❌ Book service missing"
[ -f "backend/src/main/java/com/library/rest/BookController.java" ] && echo "✅ REST controller" || echo "❌ REST controller missing"
[ -f "backend/src/main/resources/META-INF/persistence.xml" ] && echo "✅ JPA configuration" || echo "❌ JPA configuration missing"
[ -f "backend/src/main/resources/db/init.sql" ] && echo "✅ Database initialization script" || echo "❌ Database script missing"

echo ""

# Frontend files  
echo "Frontend files:"
[ -f "frontend/package.json" ] && echo "✅ Frontend package.json" || echo "❌ Frontend package.json missing"
[ -f "frontend/Dockerfile" ] && echo "✅ Frontend Dockerfile" || echo "❌ Frontend Dockerfile missing"
[ -f "frontend/src/App.tsx" ] && echo "✅ Main App component" || echo "❌ Main App component missing"
[ -f "frontend/src/services/BookService.ts" ] && echo "✅ API service" || echo "❌ API service missing"
[ -f "frontend/src/types/Book.ts" ] && echo "✅ TypeScript types" || echo "❌ TypeScript types missing"
[ -d "frontend/src/components" ] && echo "✅ React components directory" || echo "❌ React components missing"

echo ""

# Docker files
echo "Docker configuration:"
[ -f "docker-compose.yml" ] && echo "✅ Docker Compose configuration" || echo "❌ Docker Compose missing"

echo ""

# Count files
echo "📊 Project statistics:"
java_files=$(find backend/src -name "*.java" 2>/dev/null | wc -l)
tsx_files=$(find frontend/src -name "*.tsx" -o -name "*.ts" 2>/dev/null | wc -l)
echo "📝 Java files: $java_files"
echo "📝 TypeScript/React files: $tsx_files"

echo ""
echo "🎉 Project verification complete!"
echo ""
echo "🚀 To start the application:"
echo "   docker-compose up --build"
echo ""
echo "🌐 Access points:"
echo "   Frontend: http://localhost:3000"
echo "   Backend API: http://localhost:8080/book-library-backend/api/books"
echo "   Database Admin: http://localhost:5050"
