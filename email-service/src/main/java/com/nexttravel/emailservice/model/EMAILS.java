package com.nexttravel.emailservice.model;

import org.springframework.context.annotation.Bean;

public enum EMAILS {
    WELCOME("Welcome to Next Travel Pvt Ltd.! We are thrilled to have you join our community of passionate travelers. Your adventure with us begins now.\n" + "\n" + "At Next Travel, we are committed to providing you with memorable travel experiences, and we're excited to be a part of your journey. Whether you're seeking a relaxing getaway, an adventurous expedition, or an exploration of new cultures, we have the perfect travel options for you.\n" + "\n" + "Here's what you can expect from your Next Travel experience:\n" + "\n" + "Diverse Travel Packages: Explore our wide range of travel packages tailored to suit various preferences. From luxurious escapes to budget-friendly adventures, we have something for everyone.\n" + "\n" + "Exclusive Discounts: As a valued member, you'll enjoy exclusive discounts on your bookings, making your travel dreams even more affordable.\n" + "\n" + "Expert Guidance: Our team of experienced travel experts is here to assist you at every step of your journey. We'll help you plan, book, and make the most of your travels.\n" + "\n" + "Additional Services: Enhance your travel with options like professional guides, video tutorials, and comprehensive insurance coverage for added peace of mind.\n" + "\n" + "To get started, simply log in to your account and start exploring our travel packages. Once you've found your dream destination, booking your next adventure is just a few clicks away.\n" + "\n" + "As a Next Travel customer, you're part of a community that shares a passion for exploration and discovery. We're here to make your travel dreams a reality and ensure your experiences are unforgettable.\n" + "\n" + "If you have any questions or need assistance with your bookings, our customer support team is available to help you 24/7. Feel free to reach out anytime.\n" + "\n" + "Thank you for choosing Next Travel Pvt Ltd. for your travel needs. We can't wait to be a part of your incredible travel experiences. Your adventure begins here!\n" + "\n" + "Warm regards,\n" + "\n" + "Next Travel Pvt Ltd.\n" + "nexttravel.com"),
    BOOKING("Booking Confirmation"),
    CANCEL("Booking Cancellation"),
    UPDATE("Booking Update"),
    PAYMENT("Payment Confirmation"),
    FEEDBACK("Feedback");

    private final String subject;

    EMAILS(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }
}
