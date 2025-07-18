#!/bin/bash
# Development start script

echo "🚀 Starting Book Library Management System..."

# Check if Docker is running
if ! docker info > /dev/null 2>&1; then
    echo "❌ Docker is not running. Please start Docker first."
    exit 1
fi

# Check if docker-compose is available
if ! command -v docker-compose &> /dev/null; then
    echo "❌ docker-compose is not installed. Please install Docker Compose."
    exit 1
fi

echo "📦 Starting services with Docker Compose..."
docker-compose up -d

echo "⏳ Waiting for services to start..."
sleep 10

# Check service health
echo "🔍 Checking service health..."

# Check database
if docker-compose ps postgres | grep -q "Up"; then
    echo "✅ Database is running"
else
    echo "❌ Database failed to start"
fi

# Check backend
if docker-compose ps backend | grep -q "Up"; then
    echo "✅ Backend is running"
else
    echo "❌ Backend failed to start"
fi

# Check frontend
if docker-compose ps frontend | grep -q "Up"; then
    echo "✅ Frontend is running"
else
    echo "❌ Frontend failed to start"
fi

echo ""
echo "🎉 Application is starting up!"
echo ""
echo "📱 Frontend: http://localhost:3000"
echo "🔧 Backend API: http://localhost:8080/book-library-backend/api/books"
echo "🗄️  pgAdmin: http://localhost:5050 (admin@booklibrary.com / admin123)"
echo ""
echo "📊 View logs: docker-compose logs -f"
echo "🛑 Stop services: docker-compose down"
echo ""
