package io.portfolio.ecommerce.exception;


    public class ResourceNotFoundException extends RuntimeException {

        private static final long serialVersionUID = 5861310537366287163L;

        // Serial version UID for serialization
        public ResourceNotFoundException() {
            super();
        }

        // Default constructor for ResourceNotFoundException
        public ResourceNotFoundException(final String message, final Throwable cause) {
            super(message, cause); // Call the superclass constructor with message and cause
        }

        // Constructor with message and cause parameters
        public ResourceNotFoundException(final String message) {
            super(message);// Call the superclass constructor with message and cause
        }

        // Constructor with message parameter
        public ResourceNotFoundException(final Throwable cause) {
            super(cause);// Call the superclass constructor with cause
        }
    }
