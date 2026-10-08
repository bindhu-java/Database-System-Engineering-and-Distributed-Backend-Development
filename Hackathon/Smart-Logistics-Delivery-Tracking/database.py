import sqlite3

DATABASE = "logistics.db"


def get_db():
    connection = sqlite3.connect(DATABASE)
    connection.row_factory = sqlite3.Row
    return connection


def initialize_database():
    db = get_db()

    db.executescript("""
    CREATE TABLE IF NOT EXISTS shipments (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        tracking_id TEXT UNIQUE NOT NULL,
        customer TEXT NOT NULL,
        pickup TEXT NOT NULL,
        destination TEXT NOT NULL,
        package_type TEXT NOT NULL,
        status TEXT NOT NULL,
        current_location TEXT NOT NULL,
        estimated_delivery TEXT NOT NULL,
        priority TEXT NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

    CREATE TABLE IF NOT EXISTS notifications (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        tracking_id TEXT,
        message TEXT NOT NULL,
        notification_type TEXT NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
    """)

    count = db.execute(
        "SELECT COUNT(*) AS total FROM shipments"
    ).fetchone()["total"]

    if count == 0:
        sample_data = [
            (
                "SHP1001",
                "Rahul Kumar",
                "Hyderabad",
                "Bengaluru",
                "Electronics",
                "In Transit",
                "Kurnool",
                "Today, 2:30 PM",
                "High"
            ),
            (
                "SHP1002",
                "Priya Sharma",
                "Delhi",
                "Hyderabad",
                "Documents",
                "Out for Delivery",
                "Hyderabad",
                "Today, 4:15 PM",
                "Normal"
            ),
            (
                "SHP1003",
                "Arjun Reddy",
                "Mumbai",
                "Pune",
                "Clothing",
                "Delivered",
                "Pune",
                "Delivered",
                "Normal"
            ),
            (
                "SHP1004",
                "Sneha Rao",
                "Chennai",
                "Bengaluru",
                "Medical Supplies",
                "Delayed",
                "Vellore",
                "Today, 6:40 PM",
                "High"
            ),
            (
                "SHP1005",
                "Vikram Singh",
                "Hyderabad",
                "Chennai",
                "Machinery",
                "Picked Up",
                "Hyderabad",
                "Tomorrow, 10:00 AM",
                "Normal"
            )
        ]

        db.executemany("""
            INSERT INTO shipments
            (
                tracking_id,
                customer,
                pickup,
                destination,
                package_type,
                status,
                current_location,
                estimated_delivery,
                priority
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """, sample_data)

        notifications = [
            ("SHP1001", "Shipment SHP1001 is currently in transit.",
             "info"),
            ("SHP1002", "Shipment SHP1002 is out for delivery.",
             "warning"),
            ("SHP1003", "Shipment SHP1003 was delivered successfully.",
             "success"),
            ("SHP1004", "Shipment SHP1004 has been delayed.",
             "danger")
        ]

        db.executemany("""
            INSERT INTO notifications
            (tracking_id, message, notification_type)
            VALUES (?, ?, ?)
        """, notifications)

    db.commit()
    db.close()