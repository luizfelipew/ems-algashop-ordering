package com.algaworks.algashop.ordering.domain.entity;


import com.algaworks.algashop.ordering.domain.utility.IdGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;

class CustomerTest {

    @Test
    void given_invalidEmail_whenTryCreateCustomer_ShouldGenerateException() {

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    new Customer(
                            IdGenerator.generateTimeBasedUUID(),
                            "Jhon Doe",
                            LocalDate.of(1991, 7, 5),
                            "invalid",
                            "478-256-2504",
                            "255-08-0578",
                            true,
                            OffsetDateTime.now()
                    );
                });
    }

    @Test
    void given_invalidEmail_whenTryUpdatedCustomerEmail_ShouldGenerateException() {
        var customer = new Customer(
                IdGenerator.generateTimeBasedUUID(),
                "Jhon Doe",
                LocalDate.of(1991, 7, 5),
                "john.doe@email.com",
                "478-256-2504",
                "255-08-0578",
                true,
                OffsetDateTime.now()
        );
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    customer.changeEmail("invalid");
                });
//        customer.changeEmail("invalid");
    }

    @Test
    void given_unarchivedCustomer_whenArchive_shouldAnonymize() {
        var customer = new Customer(
                IdGenerator.generateTimeBasedUUID(),
                "Jhon Doe",
                LocalDate.of(1991, 7, 5),
                "john.doe@email.com",
                "478-256-2504",
                "255-08-0578",
                true,
                OffsetDateTime.now()
        );

        customer.archive();

        Assertions.assertWith(customer,
                c -> Assertions.assertThat(c.fullName()).isEqualTo("Anonymous"),
                c -> Assertions.assertThat(c.email()).isNotEqualTo("john.doe@email.com"),
                c -> Assertions.assertThat(c.phone()).isEqualTo("000-000-0000"),
                c -> Assertions.assertThat(c.document()).isEqualTo("000-00-0000"),
                c -> Assertions.assertThat(c.birthDate()).isNull()
        );
    }
}