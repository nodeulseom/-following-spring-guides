package com.example.managingtransactions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class AppRunner implements CommandLineRunner {

    private final static Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final BookingService bookingService;

    public AppRunner(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public void run(String... args) throws Exception {
        bookingService.book("Alice", "Bob", "Carol");
        Assert.isTrue(bookingService.findAllBookings().size() == 3,
        "First booking should work with no problem");
        logger.info("Alice, Bob and Carol have been booked");
        try {
            bookingService.book("Chris", "Samuel");
        } catch (RuntimeException e) {
            logger.info("v--- the following exception is expect because 'Sameul' is too " +
                    "big for the DB ---v");
            logger.error(e.getMessage());
        }
        for (String person : bookingService.findAllBookings()) {
            logger.info("So far, " + person + " is booked.");
        }
        logger.info("You shouldn't se Buddy or null. null violated DB constraints, and " +
                "Buddy was rolled back in the same TX");
        Assert.isTrue(bookingService.findAllBookings().size() == 3,
                "'null' should have triggered a rollback");
    }
}
