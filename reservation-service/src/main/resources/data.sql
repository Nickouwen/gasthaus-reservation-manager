CREATE TABLE IF NOT EXISTS reservation (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    reservation_date_time TIMESTAMP NOT NULL,
    number_of_guests INT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO reservation (id, name, email, reservation_date_time, number_of_guests, created_at, updated_at)
SELECT 'a1b2c3d4-e5f6-4a5b-8c9d-0e1f2a3b4c5d', 'Emma Thompson', 'emma.thompson@email.com', '2025-12-05 19:00:00', 4, '2025-11-20 10:30:00', '2025-11-20 10:30:00'
WHERE NOT EXISTS (SELECT 1 FROM reservation WHERE id = 'a1b2c3d4-e5f6-4a5b-8c9d-0e1f2a3b4c5d');

INSERT INTO reservation (id, name, email, reservation_date_time, number_of_guests, created_at, updated_at)
SELECT 'b2c3d4e5-f6a7-4b5c-9d0e-1f2a3b4c5d6e', 'James Wilson', 'j.wilson@email.com', '2025-12-05 20:30:00', 2, '2025-11-21 14:15:00', '2025-11-21 14:15:00'
WHERE NOT EXISTS (SELECT 1 FROM reservation WHERE id = 'b2c3d4e5-f6a7-4b5c-9d0e-1f2a3b4c5d6e');

INSERT INTO reservation (id, name, email, reservation_date_time, number_of_guests, created_at, updated_at)
SELECT 'c3d4e5f6-a7b8-4c5d-0e1f-2a3b4c5d6e7f', 'Maria Garcia', 'maria.garcia@email.com', '2025-12-06 18:00:00', 6, '2025-11-22 09:45:00', '2025-11-22 09:45:00'
WHERE NOT EXISTS (SELECT 1 FROM reservation WHERE id = 'c3d4e5f6-a7b8-4c5d-0e1f-2a3b4c5d6e7f');

INSERT INTO reservation (id, name, email, reservation_date_time, number_of_guests, created_at, updated_at)
SELECT 'd4e5f6a7-b8c9-4d5e-1f2a-3b4c5d6e7f8a', 'Oliver Chen', 'oliver.chen@email.com', '2025-12-06 19:30:00', 3, '2025-11-22 16:20:00', '2025-11-22 16:20:00'
WHERE NOT EXISTS (SELECT 1 FROM reservation WHERE id = 'd4e5f6a7-b8c9-4d5e-1f2a-3b4c5d6e7f8a');

INSERT INTO reservation (id, name, email, reservation_date_time, number_of_guests, created_at, updated_at)
SELECT 'e5f6a7b8-c9d0-4e5f-2a3b-4c5d6e7f8a9b', 'Sophie Martin', 'sophie.martin@email.com', '2025-12-07 18:30:00', 2, '2025-11-23 11:00:00', '2025-11-23 11:00:00'
WHERE NOT EXISTS (SELECT 1 FROM reservation WHERE id = 'e5f6a7b8-c9d0-4e5f-2a3b-4c5d6e7f8a9b');

INSERT INTO reservation (id, name, email, reservation_date_time, number_of_guests, created_at, updated_at)
SELECT 'f6a7b8c9-d0e1-4f5a-3b4c-5d6e7f8a9b0c', 'David Anderson', 'david.anderson@email.com', '2025-12-07 20:00:00', 5, '2025-11-23 13:30:00', '2025-11-24 10:15:00'
WHERE NOT EXISTS (SELECT 1 FROM reservation WHERE id = 'f6a7b8c9-d0e1-4f5a-3b4c-5d6e7f8a9b0c');

INSERT INTO reservation (id, name, email, reservation_date_time, number_of_guests, created_at, updated_at)
SELECT 'a7b8c9d0-e1f2-4a5b-4c5d-6e7f8a9b0c1d', 'Isabella Rossi', 'isabella.rossi@email.com', '2025-12-08 17:30:00', 4, '2025-11-24 08:45:00', '2025-11-24 08:45:00'
WHERE NOT EXISTS (SELECT 1 FROM reservation WHERE id = 'a7b8c9d0-e1f2-4a5b-4c5d-6e7f8a9b0c1d');

INSERT INTO reservation (id, name, email, reservation_date_time, number_of_guests, created_at, updated_at)
SELECT 'b8c9d0e1-f2a3-4b5c-5d6e-7f8a9b0c1d2e', 'Michael Brown', 'michael.brown@email.com', '2025-12-08 19:00:00', 2, '2025-11-24 15:20:00', '2025-11-24 15:20:00'
WHERE NOT EXISTS (SELECT 1 FROM reservation WHERE id = 'b8c9d0e1-f2a3-4b5c-5d6e-7f8a9b0c1d2e');

INSERT INTO reservation (id, name, email, reservation_date_time, number_of_guests, created_at, updated_at)
SELECT 'c9d0e1f2-a3b4-4c5d-6e7f-8a9b0c1d2e3f', 'Lisa Patel', 'lisa.patel@email.com', '2025-12-09 18:00:00', 8, '2025-11-25 12:00:00', '2025-11-25 12:00:00'
WHERE NOT EXISTS (SELECT 1 FROM reservation WHERE id = 'c9d0e1f2-a3b4-4c5d-6e7f-8a9b0c1d2e3f');

INSERT INTO reservation (id, name, email, reservation_date_time, number_of_guests, created_at, updated_at)
SELECT 'd0e1f2a3-b4c5-4d5e-7f8a-9b0c1d2e3f4a', 'Thomas Mueller', 'thomas.mueller@email.com', '2025-12-09 20:30:00', 3, '2025-11-25 17:45:00', '2025-11-25 17:45:00'
WHERE NOT EXISTS (SELECT 1 FROM reservation WHERE id = 'd0e1f2a3-b4c5-4d5e-7f8a-9b0c1d2e3f4a');

INSERT INTO reservation (id, name, email, reservation_date_time, number_of_guests, created_at, updated_at)
SELECT 'e1f2a3b4-c5d6-4e5f-8a9b-0c1d2e3f4a5b', 'Yuki Tanaka', 'yuki.tanaka@email.com', '2025-12-10 19:00:00', 2, '2025-11-26 09:30:00', '2025-11-26 09:30:00'
WHERE NOT EXISTS (SELECT 1 FROM reservation WHERE id = 'e1f2a3b4-c5d6-4e5f-8a9b-0c1d2e3f4a5b');

INSERT INTO reservation (id, name, email, reservation_date_time, number_of_guests, created_at, updated_at)
SELECT 'f2a3b4c5-d6e7-4f5a-9b0c-1d2e3f4a5b6c', 'Robert Johnson', 'rob.johnson@email.com', '2025-12-10 18:30:00', 4, '2025-11-26 14:10:00', '2025-11-27 11:20:00'
WHERE NOT EXISTS (SELECT 1 FROM reservation WHERE id = 'f2a3b4c5-d6e7-4f5a-9b0c-1d2e3f4a5b6c');

INSERT INTO reservation (id, name, email, reservation_date_time, number_of_guests, created_at, updated_at)
SELECT 'a3b4c5d6-e7f8-4a5b-0c1d-2e3f4a5b6c7d', 'Anna Kowalski', 'anna.kowalski@email.com', '2025-12-11 19:30:00', 6, '2025-11-27 10:00:00', '2025-11-27 10:00:00'
WHERE NOT EXISTS (SELECT 1 FROM reservation WHERE id = 'a3b4c5d6-e7f8-4a5b-0c1d-2e3f4a5b6c7d');

INSERT INTO reservation (id, name, email, reservation_date_time, number_of_guests, created_at, updated_at)
SELECT 'b4c5d6e7-f8a9-4b5c-1d2e-3f4a5b6c7d8e', 'Lucas Silva', 'lucas.silva@email.com', '2025-12-11 20:00:00', 2, '2025-11-27 16:45:00', '2025-11-27 16:45:00'
WHERE NOT EXISTS (SELECT 1 FROM reservation WHERE id = 'b4c5d6e7-f8a9-4b5c-1d2e-3f4a5b6c7d8e');

INSERT INTO reservation (id, name, email, reservation_date_time, number_of_guests, created_at, updated_at)
SELECT 'c5d6e7f8-a9b0-4c5d-2e3f-4a5b6c7d8e9f', 'Rachel Green', 'rachel.green@email.com', '2025-12-12 18:00:00', 5, '2025-11-28 13:25:00', '2025-11-28 13:25:00'
WHERE NOT EXISTS (SELECT 1 FROM reservation WHERE id = 'c5d6e7f8-a9b0-4c5d-2e3f-4a5b6c7d8e9f');