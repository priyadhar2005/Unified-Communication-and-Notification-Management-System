import React, { useState } from "react";

function NotificationDashboard() {

    const [form, setForm] = useState({
        title: "",
        message: "",
        recipient: "",
        type: "EMAIL",
        scheduleTime: ""
    });

    const handleChange = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const response = await fetch("http://localhost:8080/api/notifications/schedule", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(form)
        });

        const result = await response.text();
        alert(result);
    };

    return (
        <div style={{ width: "500px", margin: "40px auto" }}>
            <h2>Unified Communication & Notification System</h2>

            <form onSubmit={handleSubmit}>

                <input
                    type="text"
                    name="title"
                    placeholder="Title"
                    onChange={handleChange}
                />

                <br /><br />

                <textarea
                    name="message"
                    placeholder="Message"
                    onChange={handleChange}
                />

                <br /><br />

                <input
                    type="text"
                    name="recipient"
                    placeholder="Recipient"
                    onChange={handleChange}
                />

                <br /><br />

                <select
                    name="type"
                    onChange={handleChange}
                >
                    <option value="EMAIL">Email</option>
                    <option value="WHATSAPP">WhatsApp</option>
                </select>

                <br /><br />

                <input
                    type="datetime-local"
                    name="scheduleTime"
                    onChange={handleChange}
                />

                <br /><br />

                <button type="submit">
                    Schedule Notification
                </button>

            </form>
        </div>
    );
}

export default NotificationDashboard;
