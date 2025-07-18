package com.library.rest;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * JAX-RS Application configuration
 * This class configures the REST API base path
 */
@ApplicationPath("/api")
public class RestApplication extends Application {
    // No additional configuration needed
    // JAX-RS will automatically discover and register all REST endpoints
}
