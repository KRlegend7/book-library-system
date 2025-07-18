# Book Library Management System

A complete full-stack web application for managing a book library, built with Java EE (EJB) backend and React TypeScript frontend.

## 🏗️ Architecture Overview

- **Backend**: Java EE with Enterprise JavaBeans (EJB), JAX-RS, JPA/Hibernate
- **Frontend**: React TypeScript with Material-UI components
- **Database**: PostgreSQL with comprehensive schema
- **Container**: Docker & Docker Compose for easy deployment
- **Server**: WildFly application server

## 🚀 Complete Feature Set

### Backend Features
- ✅ RESTful API with JAX-RS
- ✅ Enterprise JavaBeans (EJB) for business logic
- ✅ JPA/Hibernate for data persistence
- ✅ Bean Validation for input validation
- ✅ Comprehensive exception handling
- ✅ CORS support for frontend integration
- ✅ Docker containerization with WildFly
- ✅ PostgreSQL database with sample data
- ✅ Logging and monitoring capabilities

### Frontend Features
- ✅ Modern React with TypeScript
- ✅ Material-UI for beautiful, responsive components
- ✅ Full CRUD operations (Create, Read, Update, Delete)
- ✅ Advanced search functionality (title, author, ISBN)
- ✅ Form validation with real-time error messages
- ✅ Confirmation dialogs for destructive actions
- ✅ Loading states and comprehensive error handling
- ✅ Professional UI/UX design
- ✅ Mobile-responsive layout

## 📋 Complete API Documentation

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| GET | `/api/books` | Get all books | None |
| GET | `/api/books/{id}` | Get book by ID | None |
| POST | `/api/books` | Create new book | BookDTO |
| PUT | `/api/books/{id}` | Update book | BookDTO |
| DELETE | `/api/books/{id}` | Delete book | None |
| GET | `/api/books/search/author?q={author}` | Search by author | None |

### API Response Format
```json
{
  "success": true,
  "message": "Operation completed successfully", 
  "data": { ... },
  "error": null
}
```

### BookDTO Schema
```json
{
  "title": "string (required, max 255 chars)",
  "author": "string (required, max 255 chars)", 
  "publicationYear": "integer (required, 1000-2030)",
  "isbn": "string (required, valid ISBN format)"
}
```

## 🛠️ Prerequisites

### Option 1: Docker (Recommended)
- **Docker** and **Docker Compose**

### Option 2: Manual Setup
- **Java 17+**
- **Maven 3.8+**
- **Node.js 18+**
- **PostgreSQL 12+**
- **WildFly 29+**
- **Maven 3.8+** (for local development)
- **Node.js 18+** and **npm** (for local development)

## 🚀 Quick Start with Docker

### 1. Clone the Repository
```bash
git clone <repository-url>
cd book-library-system
```

### 2. Start All Services
```bash
docker-compose up -d
```

This will start:
- PostgreSQL database on port `5432`
- Backend API on port `8080`
- Frontend application on port `3000`
- pgAdmin (optional) on port `5050`

### 3. Access the Application
- **Frontend**: http://localhost:3000
- **Backend API**: http://localhost:8080/book-library-backend/api/books
- **pgAdmin**: http://localhost:5050 (admin@booklibrary.com / admin123)

### 4. Initialize Sample Data
The database is automatically initialized with sample books on first startup.

## 💻 Local Development

### Backend Development

1. **Navigate to backend directory**:
   ```bash
   cd backend
   ```

2. **Start PostgreSQL** (using Docker):
   ```bash
   docker run -d --name postgres-dev \
     -e POSTGRES_DB=book_library \
     -e POSTGRES_USER=book_user \
     -e POSTGRES_PASSWORD=book_password \
     -p 5432:5432 postgres:16-alpine
   ```

3. **Run the database initialization script**:
   ```bash
   docker exec -i postgres-dev psql -U book_user -d book_library < src/main/resources/db/init.sql
   ```

4. **Build and run the backend**:
   ```bash
   mvn clean package
   mvn wildfly:run
   ```

   Or using Maven WildFly plugin:
   ```bash
   mvn wildfly:dev
   ```

   The backend will be available at: http://localhost:8080/book-library-backend/api

### Frontend Development

1. **Navigate to frontend directory**:
   ```bash
   cd frontend
   ```

2. **Install dependencies**:
   ```bash
   npm install
   ```

3. **Start development server**:
   ```bash
   npm start
   ```

   The frontend will be available at: http://localhost:3000

4. **Build for production**:
   ```bash
   npm run build
   ```

## 🧪 Testing the Application

### Backend API Testing
```bash
# Test all books endpoint
curl http://localhost:8080/book-library-backend/api/books

# Create a new book
curl -X POST http://localhost:8080/book-library-backend/api/books \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Test Book",
    "author": "Test Author", 
    "publicationYear": 2024,
    "isbn": "978-0-123456-78-9"
  }'

# Get book by ID
curl http://localhost:8080/book-library-backend/api/books/1

# Update book
curl -X PUT http://localhost:8080/book-library-backend/api/books/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Updated Book",
    "author": "Updated Author",
    "publicationYear": 2024, 
    "isbn": "978-0-123456-78-9"
  }'

# Delete book
curl -X DELETE http://localhost:8080/book-library-backend/api/books/1

# Search by author
curl "http://localhost:8080/book-library-backend/api/books/search/author?q=Orwell"
```

### Frontend UI Testing
1. Open http://localhost:3000
2. ✅ View list of books with sample data
3. ✅ Click "Add Book" and create a new book 
4. ✅ Use search to filter books by title, author, or ISBN
5. ✅ Click "View" icon to see book details
6. ✅ Click "Edit" icon to modify a book
7. ✅ Click "Delete" icon and confirm deletion
8. ✅ Test form validation with invalid data

## 📁 Project Structure

```
book-library-system/
├── backend/                           # Java EE Backend
│   ├── src/main/java/com/library/
│   │   ├── entity/Book.java           # JPA Entity
│   │   ├── dto/                       # Data Transfer Objects
│   │   │   ├── BookDTO.java
│   │   │   └── ApiResponse.java
│   │   ├── service/BookService.java   # EJB Business Logic
│   │   ├── rest/                      # JAX-RS Controllers
│   │   │   ├── BookController.java
│   │   │   └── RestApplication.java
│   │   ├── filter/CorsFilter.java     # CORS Filter
│   │   ├── exception/                 # Custom Exceptions
│   │   └── util/BookMapper.java       # Entity/DTO Mapping
│   ├── src/main/resources/
│   │   ├── META-INF/
│   │   │   ├── persistence.xml        # JPA Configuration
│   │   │   └── beans.xml              # CDI Configuration  
│   │   └── db/init.sql                # Database Schema
│   ├── src/main/webapp/WEB-INF/web.xml
│   ├── Dockerfile                     # Backend Container
│   └── pom.xml                        # Maven Dependencies
├── frontend/                          # React Frontend
│   ├── src/
│   │   ├── components/                # React Components
│   │   │   ├── BookList.tsx
│   │   │   ├── BookForm.tsx  
│   │   │   ├── BookDetail.tsx
│   │   │   ├── ConfirmDialog.tsx
│   │   │   ├── Alert.tsx
│   │   │   └── Loading.tsx
│   │   ├── services/BookService.ts    # API Service Layer
│   │   ├── types/Book.ts              # TypeScript Interfaces
│   │   └── App.tsx                    # Main App Component
│   ├── Dockerfile                     # Frontend Container
│   ├── nginx.conf                     # Production Nginx Config
│   └── package.json                   # Dependencies
├── docker-compose.yml                 # Multi-container Setup
└── README.md                          # Documentation
```

## 🗄️ Database Schema

```sql
CREATE TABLE books (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL, 
    publication_year INTEGER NOT NULL CHECK (publication_year >= 1000 AND publication_year <= 2030),
    isbn VARCHAR(17) NOT NULL UNIQUE,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Indexes for performance
CREATE INDEX idx_books_title ON books(title);
CREATE INDEX idx_books_author ON books(author);
CREATE INDEX idx_books_isbn ON books(isbn);
```

## 🔧 Configuration

### Environment Variables

#### Backend (.env or docker-compose.yml)
```bash
DB_HOST=postgres
DB_PORT=5432
DB_NAME=book_library
DB_USER=book_user  
DB_PASSWORD=book_password
```

#### Frontend (.env.development / .env.production)
```bash
REACT_APP_API_BASE_URL=http://localhost:8080/book-library-backend/api
PORT=3000
```

## 🐛 Troubleshooting

### Common Issues

1. **Backend fails to start**
   - ✅ Check PostgreSQL is running: `docker ps | grep postgres`
   - ✅ Verify database connection in logs
   - ✅ Ensure WildFly has proper datasource configuration

2. **Frontend can't connect to backend**
   - ✅ Check CORS headers in browser network tab
   - ✅ Verify `REACT_APP_API_BASE_URL` environment variable
   - ✅ Test backend API directly: `curl http://localhost:8080/book-library-backend/api/books`

3. **Database connection issues**
   - ✅ Verify PostgreSQL credentials
   - ✅ Check if database `book_library` exists
   - ✅ Run database initialization script manually

4. **Docker issues**
   - ✅ Check all services are up: `docker-compose ps`
   - ✅ View logs: `docker-compose logs [service-name]`
   - ✅ Restart services: `docker-compose restart`

### Useful Commands

```bash
# View all container logs
docker-compose logs -f

# View specific service logs
docker-compose logs backend
docker-compose logs frontend
docker-compose logs postgres

# Restart specific service
docker-compose restart backend

# Stop and remove all containers
docker-compose down

# Stop and remove all containers + volumes
docker-compose down -v

# Rebuild containers
docker-compose up --build
```

## 🏆 Technical Implementation Highlights

### Backend Architecture
- **Enterprise JavaBeans (EJB)**: Stateless session beans for business logic
- **JAX-RS**: RESTful web services with proper HTTP status codes
- **JPA/Hibernate**: Object-relational mapping with named queries
- **Bean Validation**: Input validation with custom error messages
- **Exception Handling**: Global exception handling with proper HTTP responses
- **CORS Filter**: Cross-origin support for frontend integration

### Frontend Architecture  
- **React TypeScript**: Type-safe component development
- **Material-UI**: Professional, accessible UI components
- **Axios**: HTTP client with interceptors for logging and error handling
- **State Management**: React hooks for efficient state handling
- **Form Validation**: Real-time validation with user-friendly error messages
- **Responsive Design**: Mobile-first design approach

### DevOps & Deployment
- **Multi-stage Docker builds**: Optimized container images
- **Docker Compose**: Multi-container orchestration
- **Health checks**: Container health monitoring
- **Nginx**: Production-ready frontend serving with gzip compression
- **Environment configuration**: Flexible deployment configuration

## 📊 Sample Data

The system comes pre-loaded with classic books including:
- The Great Gatsby by F. Scott Fitzgerald (1925)
- To Kill a Mockingbird by Harper Lee (1960)  
- 1984 by George Orwell (1949)
- Pride and Prejudice by Jane Austen (1813)
- And more...

## 🎯 Production Deployment

For production deployment:

1. **Update environment variables** in `docker-compose.yml`
2. **Configure SSL/TLS** for HTTPS
3. **Set up reverse proxy** (nginx/Apache) for load balancing
4. **Configure database backup** strategy
5. **Set up monitoring** and logging
6. **Configure firewall** rules

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/amazing-feature`
3. Commit changes: `git commit -m 'Add amazing feature'`
4. Push to branch: `git push origin feature/amazing-feature`
5. Open a Pull Request

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙋‍♂️ Support & Contact

For questions, issues, or contributions:
- 📧 Open an issue in this repository
- 📚 Check the troubleshooting section above
- 🔍 Review the API documentation
- 📋 Check Docker logs: `docker-compose logs`

---

**Built with ❤️ using Java EE, React TypeScript, and modern DevOps practices**

2. **Install dependencies**:
   ```bash
   npm install
   ```

3. **Start development server**:
   ```bash
   npm start
   ```

   The frontend will be available at: http://localhost:3000

## 🐳 Docker Services

### Backend Service
- **Image**: Custom WildFly-based image
- **Port**: 8080 (HTTP), 9990 (Management)
- **Health Check**: Checks API endpoint availability

### Frontend Service
- **Image**: Custom Nginx-based image
- **Port**: 3000
- **Health Check**: Checks HTTP response

### Database Service
- **Image**: postgres:16-alpine
- **Port**: 5432
- **Volumes**: Persistent data storage
- **Init Script**: Automatically creates tables and sample data

## 🗄️ Database Schema

```sql
CREATE TABLE books (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    publication_year INTEGER NOT NULL CHECK (publication_year >= 1000 AND publication_year <= 2030),
    isbn VARCHAR(17) NOT NULL UNIQUE,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
```

## 🧪 Testing

### Backend Testing
```bash
cd backend
mvn test
```

### Frontend Testing
```bash
cd frontend
npm test
```

### Integration Testing
```bash
# Start all services
docker-compose up -d

# Wait for services to be healthy
docker-compose ps

# Test API endpoints
curl http://localhost:8080/book-library-backend/api/books
```

## 📊 Monitoring and Management

### Application Health Checks
- **Backend Health**: http://localhost:8080/book-library-backend/api/books
- **Frontend Health**: http://localhost:3000/health
- **Database Health**: Automated via Docker health checks

### Management Interfaces
- **WildFly Management**: http://localhost:9990 (admin/admin123)
- **pgAdmin**: http://localhost:5050 (admin@booklibrary.com/admin123)

## 🔧 Configuration

### Environment Variables

#### Backend
- `DB_HOST`: Database host (default: postgres)
- `DB_PORT`: Database port (default: 5432)
- `DB_NAME`: Database name (default: book_library)
- `DB_USER`: Database user (default: book_user)
- `DB_PASSWORD`: Database password (default: book_password)

#### Frontend
- `REACT_APP_API_BASE_URL`: Backend API URL (default: http://localhost:8080/book-library-backend/api)

### Database Configuration
Update `backend/src/main/resources/META-INF/persistence.xml` for database settings.

### CORS Configuration
CORS is configured in `backend/src/main/java/com/library/filter/CorsFilter.java`.

## 🚨 Troubleshooting

### Common Issues

1. **Port conflicts**: Ensure ports 3000, 5432, and 8080 are available
2. **Database connection**: Check if PostgreSQL is running and accessible
3. **Docker permissions**: Ensure Docker daemon is running with proper permissions

### Logs
```bash
# View all service logs
docker-compose logs

# View specific service logs
docker-compose logs backend
docker-compose logs frontend
docker-compose logs postgres
```

### Reset Environment
```bash
# Stop and remove all containers and volumes
docker-compose down -v

# Rebuild and restart
docker-compose up --build -d
```

## 🏷️ API Examples

### Create a Book
```bash
curl -X POST http://localhost:8080/book-library-backend/api/books \
  -H "Content-Type: application/json" \
  -d '{
    "title": "The Great Gatsby",
    "author": "F. Scott Fitzgerald",
    "publicationYear": 1925,
    "isbn": "978-0-7432-7356-5"
  }'
```

### Get All Books
```bash
curl http://localhost:8080/book-library-backend/api/books
```

### Update a Book
```bash
curl -X PUT http://localhost:8080/book-library-backend/api/books/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "The Great Gatsby (Updated)",
    "author": "F. Scott Fitzgerald",
    "publicationYear": 1925,
    "isbn": "978-0-7432-7356-5"
  }'
```

### Delete a Book
```bash
curl -X DELETE http://localhost:8080/book-library-backend/api/books/1
```

## 🎯 Performance Considerations

- Database indexes on frequently queried columns
- Connection pooling for database connections
- Gzip compression for frontend assets
- Caching headers for static resources
- Proper error handling and logging

## 🔒 Security Features

- Input validation on both frontend and backend
- SQL injection prevention via JPA
- XSS protection headers
- CORS configuration
- Secure cookie settings

## 📈 Scalability

The application is designed to be easily scalable:
- Stateless backend services
- Database connection pooling
- Containerized deployment
- Load balancer ready
- Microservice architecture

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License.

## 👥 Team

- Backend Development: Java EJB, JAX-RS, JPA/Hibernate
- Frontend Development: React, TypeScript, Material-UI
- DevOps: Docker, Docker Compose, PostgreSQL
- Database Design: PostgreSQL schema and optimization

---

**Built with ❤️ for efficient book library management**
