from flask import Flask, render_template, request, redirect, url_for, jsonify
from database import get_db, initialize_database
import random

app = Flask(__name__)

initialize_database()


@app.route("/")
def dashboard():
    db = get_db()

    total = db.execute(
        "SELECT COUNT(*) AS count FROM shipments"
    ).fetchone()["count"]

    in_transit = db.execute(
        "SELECT COUNT(*) AS count FROM shipments WHERE status='In Transit'"
    ).fetchone()["count"]

    delayed = db.execute(
        "SELECT COUNT(*) AS count FROM shipments WHERE status='Delayed'"
    ).fetchone()["count"]

    delivered = db.execute(
        "SELECT COUNT(*) AS count FROM shipments WHERE status='Delivered'"
    ).fetchone()["count"]

    recent = db.execute("""
        SELECT *
        FROM shipments
        ORDER BY id DESC
        LIMIT 6
    """).fetchall()

    db.close()

    return render_template(
        "dashboard.html",
        total=total,
        in_transit=in_transit,
        delayed=delayed,
        delivered=delivered,
        recent=recent
    )


@app.route("/shipments")
def shipments():
    search = request.args.get("search", "")

    db = get_db()

    if search:
        data = db.execute("""
            SELECT *
            FROM shipments
            WHERE tracking_id LIKE ?
               OR customer LIKE ?
               OR pickup LIKE ?
               OR destination LIKE ?
            ORDER BY id DESC
        """, (
            f"%{search}%",
            f"%{search}%",
            f"%{search}%",
            f"%{search}%"
        )).fetchall()
    else:
        data = db.execute("""
            SELECT *
            FROM shipments
            ORDER BY id DESC
        """).fetchall()

    db.close()

    return render_template(
        "shipments.html",
        shipments=data,
        search=search
    )


@app.route("/tracking", methods=["GET", "POST"])
def tracking():
    shipment = None
    searched = False

    if request.method == "POST":
        tracking_id = request.form.get("tracking_id")
        searched = True

        db = get_db()

        shipment = db.execute("""
            SELECT *
            FROM shipments
            WHERE tracking_id = ?
        """, (tracking_id,)).fetchone()

        db.close()

    return render_template(
        "tracking.html",
        shipment=shipment,
        searched=searched
    )


@app.route("/add-shipment", methods=["GET", "POST"])
def add_shipment():

    if request.method == "POST":

        tracking_id = "SHP" + str(random.randint(10000, 99999))

        customer = request.form["customer"]
        pickup = request.form["pickup"]
        destination = request.form["destination"]
        package_type = request.form["package_type"]
        estimated_delivery = request.form["estimated_delivery"]
        priority = request.form["priority"]

        db = get_db()

        db.execute("""
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
        """, (
            tracking_id,
            customer,
            pickup,
            destination,
            package_type,
            "Picked Up",
            pickup,
            estimated_delivery,
            priority
        ))

        db.execute("""
            INSERT INTO notifications
            (
                tracking_id,
                message,
                notification_type
            )
            VALUES (?, ?, ?)
        """, (
            tracking_id,
            f"New shipment {tracking_id} has been created.",
            "info"
        ))

        db.commit()
        db.close()

        return redirect(url_for("shipments"))

    return render_template("add_shipment.html")


@app.route("/routes")
def routes():
    return render_template("routes.html")


@app.route("/notifications")
def notifications():
    db = get_db()

    data = db.execute("""
        SELECT *
        FROM notifications
        ORDER BY id DESC
    """).fetchall()

    db.close()

    return render_template(
        "notifications.html",
        notifications=data
    )


@app.route("/history")
def history():
    db = get_db()

    data = db.execute("""
        SELECT *
        FROM shipments
        WHERE status = 'Delivered'
        ORDER BY id DESC
    """).fetchall()

    db.close()

    return render_template(
        "history.html",
        shipments=data
    )


@app.route("/database")
def database():
    db = get_db()

    total = db.execute(
        "SELECT COUNT(*) AS count FROM shipments"
    ).fetchone()["count"]

    delayed = db.execute(
        "SELECT COUNT(*) AS count FROM shipments WHERE status='Delayed'"
    ).fetchone()["count"]

    db.close()

    return render_template(
        "database.html",
        total=total,
        delayed=delayed
    )


@app.route("/distributed")
def distributed():
    return render_template("distributed.html")


@app.route("/api/shipments")
def api_shipments():

    db = get_db()

    shipments = db.execute("""
        SELECT *
        FROM shipments
        ORDER BY id DESC
    """).fetchall()

    db.close()

    return jsonify([
        dict(shipment)
        for shipment in shipments
    ])


if __name__ == "__main__":
    app.run(
        host="127.0.0.1",
        port=5001,
        debug=True
    )