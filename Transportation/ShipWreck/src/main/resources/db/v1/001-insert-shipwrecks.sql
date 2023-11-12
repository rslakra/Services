INSERT INTO shipwrecks (id, battery, last_ride_start, last_ride_end, serial_number, created_on, created_at, created_by, updated_on, updated_at, updated_by)
VALUES ('0abe77b9-6a4e-433b-aa09-4185b242d007', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,1, DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000, CURRENT_TIMESTAMP, 'rslakra',
        DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000, CURRENT_TIMESTAMP, 'rslakra');
