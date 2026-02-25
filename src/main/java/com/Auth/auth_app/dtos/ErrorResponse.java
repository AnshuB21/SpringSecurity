package com.Auth.auth_app.dtos;

import org.springframework.http.HttpStatus;

public record ErrorResponse(
        String message,
        HttpStatus status
) {

    //Records are immutable because all fields are private final by default

    /*
     * Java Record (introduced in Java 16)
     *
     * ✅ Allowed:
     * - Immutable data fields (implicitly private and final)
     * - Canonical constructor and compact constructor
     * - Custom methods
     * - Static fields and static methods
     * - Implementing interfaces
     * - Validation inside compact constructor
     *
     * ❌ Not Allowed:
     * - Extending another class (records are implicitly final)
     * - Mutable instance fields
     * - Explicit instance fields outside the record components
     * - Instance initializer blocks
     * - No-args constructor (must initialize all components)
     *
     * Note:
     * - Automatically generates constructor, getters (fieldName()),
     *   equals(), hashCode(), and toString().
     * - Records extend java.lang.Record implicitly.
     */
}
