package com.example.sprint.dto;

/**
 * A Projection for the {@link com.example.sprint.model.Customer} entity
 */
public interface CustomerInfo {
    String getName();

    String getEmail();

    String getAddress();

    String getPhoneNumber();
}